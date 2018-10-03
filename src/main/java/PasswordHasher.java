public class PasswordHasher {

    public byte[] hashPBKDF2WithHmac256(char[] password, byte[] salt, int cost, int dklen) {
        /*
          Return the PBKDF2 hash of 'password'. Use PBKDF with the given parameters:
          - 'salt' = salt
          - 'cost' = iterations
          - 'dklen' = keylength
          - HMAC256 = PRF
          Clear all sensitive data before the method returns.
          Hint:
          Have a look at
          https://docs.oracle.com/javase/10/docs/specs/security/standard-names.html#secretkeyfactory-algorithms
          for the algorithm name.
         */
    }
}
