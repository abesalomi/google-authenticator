import com.google.zxing.WriterException;
import ge.ifgroup.googleauth.QRGenerator;
import ge.ifgroup.googleauth.SecretGenerator;
import ge.ifgroup.googleauth.UrlGenerator;
import ge.ifgroup.googleauth.Verifier;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by abesalomi on 1/6/15.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        SecretGenerator secretGenerator = new SecretGenerator();
        UrlGenerator urlGenerator = new UrlGenerator();

        urlGenerator.setSecretGenerator(secretGenerator);

        QRGenerator generator = new QRGenerator();

        QRGenerator qrGenerator = new QRGenerator();

        qrGenerator.setKey(urlGenerator.getUrl("abesalomi", "ifgroup.ge", "IFGroup"));
        qrGenerator.generate();

        ImageIO.write(qrGenerator.generate(), "png", new File("CrunchifyQR.png"));

        System.out.println(secretGenerator.code());
        System.out.println(urlGenerator.getUrl("abesalomi", "ifgroup.ge", "IFGroup"));


        Verifier verifier = new Verifier();


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println((verifier.verify(scanner.nextLong(), secretGenerator.code()) ? "Yes" : "No"));
        }
    }

}
