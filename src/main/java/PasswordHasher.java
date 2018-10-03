public class PasswordHasher {

    public boolean verifyPBKDF2WithHmac256(char[] password, byte[] salt, int cost, int dklen, byte[] hashToVerify) {
        /*
          Return 'true' if 'password' hashed with PBKDF2 (using the given parameters and HMAC256 as PRF)
          matches 'hashToVerify'. Otherwise return 'false'. Clear all sensitive date before the method returns.
         */
    }
}
