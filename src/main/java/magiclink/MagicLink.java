package magiclink;

import java.time.Instant;

class MagicLink {

  private final String username;
  private final String token;
  private final Instant validUntil;


  MagicLink(String username, String token, Instant validUntil) {
    this.username = username;
    this.token = token;
    this.validUntil = validUntil;
  }

  String getUsername() {
    return username;
  }

  String getToken() {
    return token;
  }

  Instant getValidUntil() {
    return validUntil;
  }
}
