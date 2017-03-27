/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm; 1:54pm - 2:43 pm
 */
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
public class CreateAccountTest {
    User sal;
    //Account account;
    @Before
    public void setUp() {
        sal = new User("Sal");
        //account = new Account();
    }

    @Test
    public void scenarioOneTest() throws IOException,FileNotFoundException {
        //String args[] = {};
        //BlueBank.main(args);
        //assertTrue(BlueBank.acct.getName().equals("Salonika Bose"));

        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioOneInput = new FileInputStream(new File("src/test/java/ScenarioOneInput.txt"));

        System.setIn(ScenarioOneInput);
        BlueBank.createAccount();
        //BlueBank.main(args);
        //System.setIn(original);

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
        final FileInputStream ScenarioTwoInput = new FileInputStream(new File("src/test/java/ScenarioTwoInput.txt"));
        System.setIn(ScenarioTwoInput);
        BlueBank.createAccount();
        //BlueBank.main(args);
        //System.setIn(original);

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
        final FileInputStream ScenarioThreeInput = new FileInputStream(new File("src/test/java/ScenarioThreeInput.txt"));
        System.setIn(ScenarioThreeInput);
        BlueBank.createAccount();
        //BlueBank.main(args);
        //System.setIn(original);

        assertEquals(BlueBank.acct.getName(),"Salonika Bose" );
        assertEquals(BlueBank.acct.getSsn(),123456789);
        assertEquals(BlueBank.acct.getDob(), "01011995");
        assertEquals(BlueBank.acct.getUsername(), "salonikab");
        assertEquals(BlueBank.acct.getPassword(), "asdf");
        assertEquals(BlueBank.acct.getInitialAmount(), 0.00, 0);

    }

}
