import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class VerifyPHCTest {

    private PasswordHasher passwordHasher;

    @Before
    public void setUp() throws Exception {
        passwordHasher = new PasswordHasher();
    }

    @Parameterized.Parameter
    public String phc;

    @Test(expected = IllegalArgumentException.class)
    public void verifyWrongPHC() {
        char[] pwd = "foo".toCharArray();
        passwordHasher.verifyPBKDF2WithHmac256(pwd, phc);

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"$pbkdf2-sha512$c=2000,dklen=256$qTcLj5M61AWJYT5bjDkvDbLxzRZzRaFX0dS7goiOTds$3nZz1jtGXeZXasw2KoUOxYQuSVw6EY+wX4atT/zPL0A"},
                {"$pbkdf2-sha256$dklen=256,c=2000$nzE4chfkWYb6LAW4Kd0SHZNqV0jRz+wSKQWR7zfpJf0$yVJXzi2+qEoL4DOb3IpbutVwz5yCpKW8bonVX92zUMI"},
                {"$pbkdf2-sha256$iterations=2000,dklen=256$uOwP2QsXQ/YWncz9sHtKXAjhUOMSGR2PffFP2wq0RN4$KY2Xl5CY0VOPi49P0l5x/8TeabYS/WOHNX0XojJVd38"},
                {"$pbkdf2-sha256$c=2000dklen=256$NqH5szJIaRTPU4Db4//y1OrcJy6EpnXtJDHLbMN6XGE$Bh8AmCrVJJXrZnpQqck8g9pFF/6tId0GzIr3HWsC2tw"},
                {"$pbkdf2-sha256$c=2000,dklen=256$3AwIAj2GrNmmDUBOJ92pDYqSyp8kM2Tq76JRDAKM/kc"},
                {"$pbkdf2-sha256$c=2000a,dklen=256$/YtYByocBLtGgRwPDdr6drRanRUQoOPXniyTgdsfxs0$D/ihwIbNg+gJh0tIqjNIAyzEcg42si4+XzgsVwDf1/A"},
        });
    }
}
