package magiclink;

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
    // TODO implement

    mailer.sendMagicLinkEmail(email, "some-token");
  }

  /**
   * @return validated email address
   * @throws RuntimeException if token is not valid
   */
  String verify(String token) {
    // TODO implement
    return null;
  }

}
