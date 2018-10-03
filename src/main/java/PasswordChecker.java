import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordChecker {

    private final String BASE_URL = "https://api.pwnedpasswords.com/range/";

    private OkHttpClient client;

    public PasswordChecker() {
        client = new OkHttpClient();
    }

    public boolean isPwnd(char[] password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] sha1 = digest.digest(toByteArray(password));

            String sha1String = new BigInteger(1, sha1).toString(16).toUpperCase();
            String prefix = sha1String.substring(0, 5);
            String suffix = sha1String.substring(5);

            Request request = new Request.Builder().url(BASE_URL + prefix).build();
            try (Response response = client.newCall(request).execute()) {
                String body = response.body().string();
                String[] suffixes = body.split("\r?\n");
                for (String s : suffixes) {
                    String[] p = s.split(":");
                    if (p[0].equals(suffix)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] toByteArray(char[] chars) {
        CharBuffer cBuf = CharBuffer.wrap(chars);
        ByteBuffer bBuf = StandardCharsets.UTF_8.encode(cBuf);
        byte[] bytes = Arrays.copyOfRange(bBuf.array(), bBuf.position(), bBuf.limit());
        Arrays.fill(cBuf.array(), (char) 0x0000);
        Arrays.fill(bBuf.array(), (byte) 0x00);
        return bytes;
    }
}
