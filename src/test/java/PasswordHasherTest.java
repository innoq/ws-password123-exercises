import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordHasherTest {

    private PasswordHasher passwordHasher;

    @Before
    public void setUp() throws Exception {
        passwordHasher = new PasswordHasher();
    }

    @Parameterized.Parameter(0)
    public String password;

    @Parameterized.Parameter(1)
    public byte[] salt;

    @Parameterized.Parameter(2)
    public int cost;

    @Parameterized.Parameter(3)
    public int dklen;

    @Parameterized.Parameter(4)
    public byte[] hash;

    @Parameterized.Parameter(5)
    public String phc;

    @Test
    public void hashPBKDF2WithHmac256PHC() {
        char[] pwd = password.toCharArray();
        String phcString = passwordHasher.hashPBKDF2WithHmac256PHC(pwd, salt, cost, dklen);
        assertEquals(phc, phcString);
        assertArrayEquals(zeroFilledCharArray(pwd.length), pwd);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {";T,G]\"RV9/LXezx{", new byte[]{-87, 55, 11, -113, -109, 58, -44, 5, -119, 97, 62, 91, -116, 57, 47, 13, -78, -15, -51, 22, 115, 69, -95, 87, -47, -44, -69, -126, -120, -114, 77, -37}, 2000, 256, new byte[]{-34, 118, 115, -42, 59, 70, 93, -26, 87, 106, -52, 54, 42, -123, 14, -59, -124, 46, 73, 92, 58, 17, -113, -80, 95, -122, -83, 79, -4, -49, 47, 64}, "$pbkdf2-sha256$c=2000,dklen=256$qTcLj5M61AWJYT5bjDkvDbLxzRZzRaFX0dS7goiOTds$3nZz1jtGXeZXasw2KoUOxYQuSVw6EY+wX4atT/zPL0A"},
                {"=92e2s$ywCB&).2v", new byte[]{-97, 49, 56, 114, 23, -28, 89, -122, -6, 44, 5, -72, 41, -35, 18, 29, -109, 106, 87, 72, -47, -49, -20, 18, 41, 5, -111, -17, 55, -23, 37, -3}, 2000, 256, new byte[]{-55, 82, 87, -50, 45, -66, -88, 74, 11, -32, 51, -101, -36, -118, 91, -70, -43, 112, -49, -100, -126, -92, -91, -68, 110, -119, -43, 95, -35, -77, 80, -62}, "$pbkdf2-sha256$c=2000,dklen=256$nzE4chfkWYb6LAW4Kd0SHZNqV0jRz+wSKQWR7zfpJf0$yVJXzi2+qEoL4DOb3IpbutVwz5yCpKW8bonVX92zUMI"},
                {"=&^{TH5JHU=bp`?>", new byte[]{-72, -20, 15, -39, 11, 23, 67, -10, 22, -99, -52, -3, -80, 123, 74, 92, 8, -31, 80, -29, 18, 25, 29, -113, 125, -15, 79, -37, 10, -76, 68, -34}, 2000, 256, new byte[]{41, -115, -105, -105, -112, -104, -47, 83, -113, -117, -113, 79, -46, 94, 113, -1, -60, -34, 105, -74, 18, -3, 99, -121, 53, 125, 23, -94, 50, 85, 119, 127}, "$pbkdf2-sha256$c=2000,dklen=256$uOwP2QsXQ/YWncz9sHtKXAjhUOMSGR2PffFP2wq0RN4$KY2Xl5CY0VOPi49P0l5x/8TeabYS/WOHNX0XojJVd38"},
                {"YMDXPJfuVe{&Fv$z8#.ZxaAhTyS7c<U[", new byte[]{54, -95, -7, -77, 50, 72, 105, 20, -49, 83, -128, -37, -29, -1, -14, -44, -22, -36, 39, 46, -124, -90, 117, -19, 36, 49, -53, 108, -61, 122, 92, 97}, 2000, 256, new byte[]{6, 31, 0, -104, 42, -43, 36, -107, -21, 102, 122, 80, -87, -55, 60, -125, -38, 69, 23, -2, -83, 33, -35, 6, -52, -118, -9, 29, 107, 2, -38, -36}, "$pbkdf2-sha256$c=2000,dklen=256$NqH5szJIaRTPU4Db4//y1OrcJy6EpnXtJDHLbMN6XGE$Bh8AmCrVJJXrZnpQqck8g9pFF/6tId0GzIr3HWsC2tw"},
                {"L2S@sqU;Gg{x)~]\"J_:rn&mW#>8zR-hv", new byte[]{-92, 67, -90, 22, 43, -44, 116, 73, -12, -74, 3, -115, 46, -30, -40, -83, -113, -11, 43, 32, 99, -78, -26, -30, -52, 8, -102, -123, 80, -117, 81, 91}, 2000, 256, new byte[]{-36, 12, 8, 2, 61, -122, -84, -39, -90, 13, 64, 78, 39, -35, -87, 13, -118, -110, -54, -97, 36, 51, 100, -22, -17, -94, 81, 12, 2, -116, -2, 71}, "$pbkdf2-sha256$c=2000,dklen=256$pEOmFivUdEn0tgONLuLYrY/1KyBjsubizAiahVCLUVs$3AwIAj2GrNmmDUBOJ92pDYqSyp8kM2Tq76JRDAKM/kc"},
                {"q;U93ZJCx$Mf+)#`HzbSE@\"A.r<g4te5", new byte[]{-3, -117, 88, 7, 42, 28, 4, -69, 70, -127, 28, 15, 13, -38, -6, 118, -76, 90, -99, 21, 16, -96, -29, -41, -98, 44, -109, -127, -37, 31, -58, -51}, 2000, 256, new byte[]{15, -8, -95, -64, -122, -51, -125, -24, 9, -121, 75, 72, -86, 51, 72, 3, 44, -60, 114, 14, 54, -78, 46, 62, 95, 56, 44, 87, 0, -33, -41, -16}, "$pbkdf2-sha256$c=2000,dklen=256$/YtYByocBLtGgRwPDdr6drRanRUQoOPXniyTgdsfxs0$D/ihwIbNg+gJh0tIqjNIAyzEcg42si4+XzgsVwDf1/A"},
        });
    }

    private char[] zeroFilledCharArray(int len) {
        char[] c = new char[len];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0x0000;
        }
        return c;
    }
}