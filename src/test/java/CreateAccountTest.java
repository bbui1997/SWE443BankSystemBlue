/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm; 1:54pm - 2:43 pm
 */
import com.sun.tools.javah.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateAccountTest {
    User sal;
    @Before
    public void setUp() {
        sal = new User("Sal");
    }

    @Test
    public void scenarioOneTest() throws FileNotFoundException {
        //String args[] = {};
        //BlueBank.main(args);
        //assertTrue(BlueBank.acct.getName().equals("Salonika Bose"));

        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioOneInput = new FileInputStream(new File("/Users/Salonika/Desktop/GitRepo/SWE443BankSystemBlue/src/test/java/ScenarioOneInput.txt"));
        System.setIn(ScenarioOneInput);
        BlueBank.main(args);
        System.setIn(original);

        assertEquals(BlueBank.acct.getName(),"Salonika Bose" );
        assertEquals(BlueBank.acct.getSsn(),123456789);
        assertEquals(BlueBank.acct.getDob(), "01011995");
        assertEquals(BlueBank.acct.getUsername(), "salonikab");
        assertEquals(BlueBank.acct.getPassword(), "asdf");
        assertEquals(BlueBank.acct.getInitialAmount(), 100.00, 0);
    }

    @Test
    public void scenarioTwoTest() throws FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioTwoInput = new FileInputStream(new File("/Users/Salonika/Desktop/GitRepo/SWE443BankSystemBlue/src/test/java/ScenarioTwoInput.txt"));
        System.setIn(ScenarioTwoInput);
        BlueBank.main(args);
        System.setIn(original);

        assertEquals(BlueBank.acct.getName(),"Salonika Bose" );
        assertEquals(BlueBank.acct.getSsn(),123456789);
        assertEquals(BlueBank.acct.getDob(), "01011995");
        assertEquals(BlueBank.acct.getUsername(), "salonikab");
        assertEquals(BlueBank.acct.getPassword(), "");
        assertEquals(BlueBank.acct.getInitialAmount(), 100.00, 0);
    }

    @Test
    public void scenarioThreeTest() throws FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioThreeInput = new FileInputStream(new File("/Users/Salonika/Desktop/GitRepo/SWE443BankSystemBlue/src/test/java/ScenarioThreeInput.txt"));
        System.setIn(ScenarioThreeInput);
        BlueBank.main(args);
        System.setIn(original);

        assertEquals(BlueBank.acct.getName(),"Salonika Bose" );
        assertEquals(BlueBank.acct.getSsn(),123456789);
        assertEquals(BlueBank.acct.getDob(), "01011995");
        assertEquals(BlueBank.acct.getUsername(), "salonikab");
        assertEquals(BlueBank.acct.getPassword(), "asdf");
        assertEquals(BlueBank.acct.getInitialAmount(), 0.00, 0);

    }

}
