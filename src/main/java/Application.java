import ge.ifgroup.googleauth.QRGenerator;
import ge.ifgroup.googleauth.SecretGenerator;
import ge.ifgroup.googleauth.UrlGenerator;
import ge.ifgroup.googleauth.Verifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by abesalomi on 1/6/15.
 */
@Controller
@EnableAutoConfiguration
public class Application {

    private String secret;

    @RequestMapping("/generate")
    @ResponseBody
    void home(String name, String domain, String issuer, HttpServletResponse response) throws IOException {

        SecretGenerator secretGenerator = new SecretGenerator();
        UrlGenerator urlGenerator = new UrlGenerator();

        urlGenerator.setSecretGenerator(secretGenerator);

        this.secret = secretGenerator.code();

        QRGenerator qrGenerator = new QRGenerator();


        String url = urlGenerator.getUrl(name, domain, issuer);

        qrGenerator.setKey(url);
        BufferedImage bi = qrGenerator.generate();

        OutputStream out = response.getOutputStream();
        System.out.println(url);
        ImageIO.write(bi, "jpg", out);
//        return "Hello World!";
    }

    @RequestMapping("/check")
    @ResponseBody
    String check(@RequestParam Long code, HttpServletResponse response) throws IOException {
        Verifier verifier = new Verifier();
        return String.valueOf(verifier.verify(code, secret));
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
