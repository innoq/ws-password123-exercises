import static org.junit.Assert.assertTrue;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.junit.Assert;
import org.junit.Test;

public class TotpTest {

  @Test
  public void register() {
    // You can use the GoogleAuthenticator from the https://github.com/wstrange/GoogleAuth implementation
    GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

    GoogleAuthenticatorKey credentials = googleAuthenticator.createCredentials();
    String sharedSecret = credentials.getKey();
    String url = GoogleAuthenticatorQRGenerator.getOtpAuthURL("jochenchrist.de", "jochen", credentials);

    // Don't modify the outputs and asserts.
    System.out.println("Shared Secret: " + sharedSecret);
    System.out.println("URL: " + url);
    Assert.assertTrue("Secret should be set", sharedSecret.length() > 5);
    Assert.assertTrue("URL should start with https", url.startsWith("https"));
  }

  @Test
  public void verifyToken() {
    String sharedSecret = "4NKMW6B2IXCP7RIL";
    int token = 334441; // The token is valid for 30 seconds

    boolean isValid = new GoogleAuthenticator().authorize(sharedSecret, token);

    System.out.println(String.format("Expected token was: %s", new GoogleAuthenticator().getTotpPassword(sharedSecret)));
    assertTrue(isValid);
  }
}
