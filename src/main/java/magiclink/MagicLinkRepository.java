package magiclink;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class MagicLinkRepository {

  private Map<String, MagicLink> database = new HashMap<>();

  void save(MagicLink magicLink) {
    database.put(magicLink.getToken(), magicLink);
  }

  Optional<MagicLink> findByToken(String token) {
    return Optional.ofNullable(database.get(token));
  }

}
