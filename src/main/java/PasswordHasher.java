import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

    private void clearPassword(char[] password) {
        for (int i = 0; i < password.length; i++) {
            password[i] = 0x0000;
        }
    }
}
