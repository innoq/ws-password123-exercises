package magiclink;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
class MagicLinkService {

  private final MagicLinkRepository repository;
  private final Mailer mailer;

  MagicLinkService(MagicLinkRepository repository, Mailer mailer) {
    this.repository = repository;
    this.mailer = mailer;
  }

  void sendMailWithMagicLink(String email) {
    String token = generateRandomToken();

    // define time limit
    Instant validUntil = Instant.now().plus(10, ChronoUnit.MINUTES);

    MagicLink magicLink = new MagicLink(email, token, validUntil);
    repository.save(magicLink);

    mailer.sendMagicLinkEmail(email, token);
  }

  private String generateRandomToken() {
    return UUID.randomUUID().toString();
  }

  String verify(String token) {
    MagicLink magicLink = repository.findByToken(token)
        .orElseThrow(() -> new RuntimeException("Could not find token"));
    if (Instant.now().isAfter(magicLink.getValidUntil())) {
      throw new RuntimeException("Token expired");
    }
    return magicLink.getUsername();
  }

}
