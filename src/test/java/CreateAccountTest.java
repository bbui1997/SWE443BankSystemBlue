/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm
 */
import com.sun.tools.javah.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class CreateAccountTest {
    User sal;
    @Before
    public void setUp() {
        sal = new User("Sal");
    }

    @Test
    public void scenarioOneTest() {
        String[] args = {"Salonika Bose", "123456789","01011995","salonikab","abcd","100.00"};
            BlueBank.main(args);
    }

    @Test
    public void scenarioTwoTest() {
        assertTrue(true);
    }

    @Test
    public void scenarioThreeTest() {
        assertTrue(true);
    }

}
