import ge.ifgroup.googleauth.SecretGenerator;
import ge.ifgroup.googleauth.UrlGenerator;

/**
 * Created by abesalomi on 1/6/15.
 */
public class Main {

    public static void main(String[] args) {
        SecretGenerator secretGenerator = new SecretGenerator();
        UrlGenerator urlGenerator = new UrlGenerator();

        urlGenerator.setSecretGenerator(secretGenerator);

        System.out.println(secretGenerator.code());
        System.out.println(urlGenerator.getUrl("abesalomi","ifgroup.ge","IFGroup"));

    }

}
