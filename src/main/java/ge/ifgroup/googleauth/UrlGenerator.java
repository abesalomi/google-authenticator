package ge.ifgroup.googleauth;

/**
 * Created by abesalomi on 1/6/15.
 */
public class UrlGenerator {

    SecretGenerator secretGenerator;

    public void setSecretGenerator(SecretGenerator secretGenerator) {
        this.secretGenerator = secretGenerator;
    }

    public String getUrl(String user, String host, String issuer){
        return getQRBarcodeOtpAuthURL(user,host,secretGenerator.code(),issuer);
    }

    public static String getQRBarcodeOtpAuthURL(String user, String host, String secret, String issuer) {
        return String.format("otpauth://totp/%s@%s?secret=%s&issuer=%s", user, host, secret, issuer);
    }
}
