import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordHasher {

    public String hashPBKDF2WithHmac256PHC(char[] password, byte[] salt, int cost, int dklen) {
        Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, cost, dklen);
            byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
            keySpec.clearPassword();
            clearPassword(password);
            String phc = "$" + "pbkdf2-sha256"
                    + "$" + "c=" + cost + ",dklen=" + dklen
                    + "$" + encoder.encodeToString(salt)
                    + "$" + encoder.encodeToString(hash);
            return phc;
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
