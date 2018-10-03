import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHasher {

    public byte[] hashPBKDF2WithHmac256(char[] password, byte[] salt, int cost, int dklen) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, cost, dklen);
            byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
            keySpec.clearPassword();
            clearPassword(password);
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyPBKDF2WithHmac256(char[] password, byte[] salt, int cost, int dklen, byte[] hashToVerify) {
        byte[] hash = hashPBKDF2WithHmac256(password, salt, cost, dklen);
        clearPassword(password);
        return Arrays.equals(hash, hashToVerify);
    }

    public boolean verifyPBKDF2WithHmac256(char[] password, String phcFormattedString) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] parts = phcFormattedString.split("\\$");
        if (parts.length != 5 || !"pbkdf2-sha256".equals(parts[1])) {
            throw new IllegalArgumentException();
        };
        String[] params = parts[2].split(",");
        if (params.length !=2) {
            throw new IllegalArgumentException();
        }
        int cost = extractIntParam(params[0], "c");
        int dklen = extractIntParam(params[1], "dklen");
        byte[] salt = decoder.decode(parts[3]);
        byte[] hash = decoder.decode(parts[4]);
        return verifyPBKDF2WithHmac256(password, salt, cost, dklen, hash);
    }

    private void clearPassword(char[] password) {
        for (int i = 0; i < password.length; i++) {
            password[i] = 0x0000;
        }
    }

    private int extractIntParam(String paramString, String name) {
        String[] param = paramString.split("=");
        if (param.length != 2 || !param[0].equals(name)) {
            throw new IllegalArgumentException();
        }
        try {
            return Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
