import org.junit.Before;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Bank;

import java.io.*;

/**
 * Created by Truong on 3/29/17.
 */
public class CreateAccountTest {
    File test;
    //User Sal = new User();
    Bank blueBank = new Bank();

    @Before
    public void setUp() {
        blueBank.setBankName("Blue_Bank");
        test = new File("src/logs/salonikab_log");
    }

    /**
     *
     * @see <a href='../../../doc/scenarioOneTest.html'>scenarioOneTest.html</a>
     */
    @Test
    public void scenarioOneTest() throws IOException,FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioOneInput = new FileInputStream(new File("src/test/java/ScenarioOneInput.txt"));

        System.setIn(ScenarioOneInput);
        blueBank.createAccount();
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(blueBank);
        storyboard.assertEquals("the user is Salonika Bose", blueBank.getAccount_Has().getName().toString(), "(Salonika Bose)");
        storyboard.assertEquals("the ssn is 123456789" , blueBank.getAccount_Has().getSsn().get(0), 1234);
        storyboard.assertEquals("the dob is 01011995", blueBank.getAccount_Has().getDob().toString(), "(1/1/1995)");
        storyboard.assertEquals("the username is salonikab", blueBank.getAccount_Has().getUsername().toString(), "(salonikab)");
        storyboard.assertEquals("the password is Salonika1!", blueBank.getAccount_Has().getPassword().toString(), "(Salonika1!)");
        storyboard.assertEquals("the initial anount is 100.00", blueBank.getAccount_Has().getInitialAmount().get(0), 100.00);
        storyboard.assertTrue("Log created",test.exists());
        storyboard.dumpHTML();

    }

    /**
     *
     * @see <a href='../../../doc/scenarioTwoTest.html'>scenarioTwoTest.html</a>
     */
    @Test
    public void scenarioTwoTest() throws FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioTwoInput = new FileInputStream(new File("src/test/java/ScenarioTwoInput.txt"));
        System.setIn(ScenarioTwoInput);
        blueBank.createAccount();
        //BlueBank.main(args);
        //System.setIn(original);
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(blueBank);

        storyboard.assertEquals("the user is Salonika Bose",blueBank.getAccount_Has().getName().toString(),"(Salonika Bose)" );
        storyboard.assertEquals("the ssn is 123456789",blueBank.getAccount_Has().getSsn().get(0),1234);
        storyboard.assertEquals("01011995",blueBank.getAccount_Has().getDob().toString(), "(1/1/1995)");
        storyboard.assertEquals("the username salonikab",blueBank.getAccount_Has().getUsername().toString(), "(salonikab)");
        storyboard.assertEquals("the password is Password1!",blueBank.getAccount_Has().getPassword().toString(), "(Password1!)");
        storyboard.assertEquals("the initial amount is 100.00",blueBank.getAccount_Has().getInitialAmount().get(0), 100.00);
        storyboard.dumpHTML();
    }

    /**
     *
     * @see <a href='../../../doc/scenarioThreeTest.html'>scenarioThreeTest.html</a>
     */
    @Test
    public void scenarioThreeTest() throws FileNotFoundException {
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream ScenarioThreeInput = new FileInputStream(new File("src/test/java/ScenarioThreeInput.txt"));
        System.setIn(ScenarioThreeInput);
        blueBank.createAccount();
        //BlueBank.main(args);
        //System.setIn(original);

        Storyboard storyboard = new Storyboard();
        storyboard.add("these information was extracted from a json file");
        storyboard.addObjectDiagram(blueBank);

        storyboard.assertEquals("the user is Salonika Bose",blueBank.getAccount_Has().getName().toString(),"(Salonika Bose)" );
        storyboard.assertEquals("the ssn is 123456789",blueBank.getAccount_Has().getSsn().get(0),1234);
        storyboard.assertEquals("01011995",blueBank.getAccount_Has().getDob().toString(), "(1/1/1995)");
        storyboard.assertEquals("the username salonikab",blueBank.getAccount_Has().getUsername().toString(), "(salonikab)");
        storyboard.assertEquals("the password is Password1!",blueBank.getAccount_Has().getPassword().toString(), "(Password1!)");
        storyboard.assertEquals("the initial amount is 100.00",blueBank.getAccount_Has().getInitialAmount().get(0), 100.00); //should fail since it allows the initial amount to be negative
        storyboard.dumpHTML();

    }

}
