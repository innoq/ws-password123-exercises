import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

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

    private void clearPassword(char[] password) {
        for (int i = 0; i < password.length; i++) {
            password[i] = 0x0000;
        }
    }
}
