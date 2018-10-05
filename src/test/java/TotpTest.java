import static org.junit.Assert.assertTrue;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.junit.Assert;
import org.junit.Test;

public class TotpTest {

  @Test
  public void register() {
    // You can use the GoogleAuthenticator from the https://github.com/wstrange/GoogleAuth implementation
    GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

    // TODO Generate a shared secret (which must be saved with the login)
    String sharedSecret = "";
    // TODO Generate a URL, which returns the QR code, that can be scanned by the user.
    String url = "";

    // Don't modify the outputs and asserts.
    System.out.println("Shared Secret: " + sharedSecret);
    System.out.println("URL: " + url);
    Assert.assertTrue("Secret should be set", sharedSecret.length() > 5);
    Assert.assertTrue("URL should start with https", url.startsWith("https"));
  }

  @Test
  public void verifyToken() {
    // TODO Enter the shared secret from your scanned QR code
    String sharedSecret = "ENTER_YOUR_SHARED_SECRET_HERE";
    // TODO Enter the current token, displayed in your app.
    int token = 123456;

    boolean isValid = new GoogleAuthenticator().authorize(sharedSecret, token);

    System.out.println(String.format("Expected token was: %s", new GoogleAuthenticator().getTotpPassword(sharedSecret)));
    assertTrue(isValid);
  }
}
