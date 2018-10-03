import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordCheckerTest {

    private PasswordChecker checker;

    @Before
    public void setUp() throws Exception {
        checker = new PasswordChecker();
    }

    @Parameterized.Parameter(0)
    public boolean expected;

    @Parameterized.Parameter(1)
    public char[] password;

    @Test
    public void isPwnd() {
        assertEquals(expected, checker.isPwnd(password));
        assertArrayEquals("password not zeroed out", zeroFilledCharArray(password.length), password);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, "Passwort123!".toCharArray()},
                {true, "Password123!".toCharArray()},
                {true, "123456".toCharArray()},
                {false, ";T,G]\"RV9/LXezx{".toCharArray()},
                {false, "=92e2s$ywCB&).2v".toCharArray()},
                {false, "YMDXPJfuVe{&Fv$z8#.ZxaAhTyS7c<U[".toCharArray()},
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