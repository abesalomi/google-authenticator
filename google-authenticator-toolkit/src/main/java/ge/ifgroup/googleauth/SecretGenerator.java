package ge.ifgroup.googleauth;

import org.apache.commons.codec.binary.Base32;

import java.security.SecureRandom;

/**
 * Created by abesalomi on 1/6/15.
 */
public class SecretGenerator {

    private String secret;

    public String code() {
        if (secret != null)
            return secret;

        byte[] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);
        secret = new String(new Base32().encode(buffer));

        return secret;
    }
}
