package magiclink;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

  private final MagicLinkService magicLinkService;

  public WebController(MagicLinkService magicLinkService) {
    this.magicLinkService = magicLinkService;
  }

  @GetMapping("/")
  public String index() {
    return "login-form";
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(HttpServletRequest request) {
    String email = request.getParameter("email");
    magicLinkService.sendMailWithMagicLink(email);
    return ResponseEntity.ok("Email with token has been sent");
  }

  @GetMapping("/login/{token}")
  public ResponseEntity<String> verify(@PathVariable("token") String token) {
    try {
      String username = magicLinkService.verify(token);
      return ResponseEntity.ok("You are now logged in as " + username);
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }
  }

}
