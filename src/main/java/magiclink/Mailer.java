package magiclink;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import org.springframework.stereotype.Component;

/**
 * Send Mails. We use SendGrid Java Client for simplicity. Any SMTP provider can be used.
 */
@Component
public class Mailer {

  void sendMagicLinkEmail(String to, String token) {
    String subject = "Magic Link";
    String magicLinkUrl = "http://localhost:8080/login/" + token;
    String body = "Click here to login: " + magicLinkUrl;
    sendEmail(to, subject, body);
  }

  private void sendEmail(String recipient, String subject, String body) {
    Email from = new Email("test@localhost.intern");
    Email to = new Email(recipient);
    Content content = new Content("text/plain", body);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(
        "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");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
