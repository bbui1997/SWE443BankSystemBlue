/**
 * Created by Spade on 3/26/17.
 * The test of this class tests the 3 scenarios for depositing money and
 * and the 3 scenarios for withdrawing money.
 *
 * @Author: Marcus Yarbrough
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Account;

public class TransactionTests {
    Account sal; //represents account for Sal to be used in testing.

    /**
     * This method initializes the object to be used in testing the scenarios.
     * The balance is initialized to zero to allow modification later in testing.
     */
    @Before
    public void setup(){
        sal = new Account(); //establish a new account for Sal
        sal.setName("Sal"); //set name
        sal.setUsername("sal"); //set username
        sal.setPassword("password123"); //set password
        sal.setDob("03/28/1986"); //set date of birth
        sal.setSsn(123568900); //set social security number
        sal.setInitialAmount(0); //set inital balance to 0
    }

    /**
     * This method tests deposit Scenario1:
     * Sal successfully deposits $50 into her account.
     * Her initial balance is zero.
     * @see <a href='../../../doc/DepositScenario1.html'>DepositScenario1.html</a>
     */
    @Test
    public void testDepositScenario1(){
        //setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(sal);

        sal.deposit(50); //sal deposits $50
        storyboard.assertEquals("Balance:",50,sal.getAccountBalance(),0); //check that current balance equals $50
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests deposit Scenario2:
     * Sal brings her account balance positive.
     * Sal initially has a negative balance of ($25).
     * She deposits $100 and the new balance is $75.
     * @see <a href='../../../doc/DepositScenario2.html'>DepositScenario2.html</a>
     */
    @Test
    public void testDepositScenario2(){
        //setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(sal);
        sal.setAccountBalance(-25); //set initial balance to -$25

        sal.deposit(100); //Sal deposits $100 into her account
        storyboard.assertEquals("Balance:",75,sal.getAccountBalance(),0); //check that current balance equals $75 after deposit
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests deposit Scenario3:
     * Sal attempts to make two deposits in one transaction.
     * She has an initial balance of $120 and makes a deposit of $80.
     * She then makes another deposit of $40.
     * @see <a href='../../../doc/DepositScenario3.html'>DepositScenario3.html</a>
     */
    @Test
    public void testDepositScenario3(){
        //setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(sal);
        sal.setAccountBalance(120); //set initial balance to $120

        sal.deposit(80); //first deposit of $80
        sal.deposit(40); //second deposit of $40
        storyboard.assertEquals("Balance:",240,sal.getAccountBalance(),0); //check that the current balance equals $240 after both deposits
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests withdraw Scenario1:
     * Sal withdraws $40 from her account.
     * She initially has a balance of $60 and after the withdrawal the balance is $20.
     * @see <a href='../../../doc/WithdrawScenario1.html'>WithdrawScenario1.html</a>
     */
    @Test
    public void testWithdrawScenario1(){
        // setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal's account initially has an account balance of $60");
        sal.setAccountBalance(60); //Sal has an account balance of $60.
        storyboard.addObjectDiagram(sal);

        storyboard.add("Sal attempts to withdraw $40 from her account and successfully does so.");
        storyboard.add("The account balance is now $20.");
        double cash = sal.withdraw(40); //withdraw $40 from account
        storyboard.addObjectDiagram(sal);

        storyboard.assertEquals("Withdrawn:",40,cash,0); //$40 is returned
        storyboard.assertEquals("Balance:",20,sal.getAccountBalance(),0); //check account balance is $20 after withdrawal
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests withdraw Scenario2:
     * Sal attempts to withdraw an incorrect denomination.
     * She currently has a balance of $75 and attempts to withdraw $35.
     * The transaction fails and returns $0.
     * @see <a href='../../../doc/WithdrawScenario2.html'>WithdrawScenario2.html</a>
     */
    @Test
    public void testWithdrawScenario2(){
        // setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal's account has a balance of $75");
        sal.setAccountBalance(75); //Sal has an account balance of $75.
        storyboard.addObjectDiagram(sal);

        storyboard.add("Sal attempts to withdraw $35 but isn't successfully because $35 is an improper amount.");
        double cash = sal.withdraw(35);
        storyboard.addObjectDiagram(sal);

        storyboard.assertEquals("Withdrawn:",0,cash,0); //no money is returned
        storyboard.assertEquals("Balance:",75,sal.getAccountBalance(),0); //Sal's account balance remains the same.
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests withdraw Scenario3:
     * Sal's account has a negative balance after withdrawal.
     * Sal has an account balance of $30. She withdraws $40
     * and still has an account balance of $30.
     * @see <a href='../../../doc/WithdrawScenario3.html'>WithdrawScenario3.html</a>
     */
    @Test
    public void testWithdrawScenario3(){
        //setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal's account initially has a balance of $30");
        sal.setAccountBalance(30); //Sal has an account balance of $40
        storyboard.addObjectDiagram(sal);

        storyboard.add("Sal attempts to withdraw $40");
        double cash = sal.withdraw(40); //withdraw $40 from account
        storyboard.addObjectDiagram(sal);

        storyboard.assertEquals("Result:",-1,cash,0); //-1 returned for insufficient funds
        storyboard.assertEquals("Balance:",30,sal.getAccountBalance(),0); //account balance is $30
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method sets the global object sal to null.
     * The method will be used after each test run to destroy object before the next test.
     */
    @After
    public void destroy(){
        sal = null; //set the object to null
    }
}
