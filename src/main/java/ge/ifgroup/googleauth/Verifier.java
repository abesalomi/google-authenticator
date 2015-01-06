package ge.ifgroup.googleauth;

import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by abesalomi on 1/6/15.
 */
public class Verifier {

    public boolean verify(long code, String secret){
        try {
            return verifyCode(secret, code, getTimeIndex(), 5);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public long getTimeIndex() {
        return System.currentTimeMillis() / 1000 / 30;
    }

    private long getCode(byte[] secret, long timeIndex)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signKey = new SecretKeySpec(secret, "HmacSHA1");
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(timeIndex);
        byte[] timeBytes = buffer.array();
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(timeBytes);
        int offset = hash[19] & 0xf;
        long truncatedHash = hash[offset] & 0x7f;
        for (int i = 1; i < 4; i++) {
            truncatedHash <<= 8;
            truncatedHash |= hash[offset + i] & 0xff;
        }
        return (truncatedHash %= 1000000);
    }

    public boolean verifyCode(String secret, long code, long timeIndex, int variance)
            throws Exception {
        for (int i = -variance; i <= variance; i++) {
            System.out.println(getCode(secret, timeIndex + i));
            if (getCode(secret, timeIndex + i) == code) {
                return true;
            }
        }
        return false;
    }


    long getCode(String secret, long timeIndex)
            throws NoSuchAlgorithmException, InvalidKeyException {
        return getCode(new Base32().decode(secret), timeIndex);
    }
}
