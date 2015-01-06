import ge.ifgroup.googleauth.SecretGenerator;
import ge.ifgroup.googleauth.UrlGenerator;
import ge.ifgroup.googleauth.Verifier;

import java.util.Scanner;

/**
 * Created by abesalomi on 1/6/15.
 */
public class Main {

    public static void main(String[] args) {
        SecretGenerator secretGenerator = new SecretGenerator();
        UrlGenerator urlGenerator = new UrlGenerator();

        urlGenerator.setSecretGenerator(secretGenerator);

        System.out.println(secretGenerator.code());
        System.out.println(urlGenerator.getUrl("abesalomi", "ifgroup.ge", "IFGroup"));


        Verifier verifier = new Verifier();


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println((verifier.verify(scanner.nextLong(), secretGenerator.code()) ? "Yes" : "No"));
        }
    }

}
