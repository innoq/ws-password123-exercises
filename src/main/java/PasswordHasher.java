public class PasswordHasher {


    public boolean verifyPBKDF2WithHmac256(char[] password, String phc);
        /*
          Return 'true' if 'password' matches the hash encoded in 'phc'. Otherwise return 'false'. If 'phc' is not
          correctly formatted throw an 'IllegalArgumentException'. Clear all sensitive date before the method returns.
         */
    }

}
