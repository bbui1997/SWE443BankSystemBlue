/**
 * Created by Krishna on 3/27/2017.
 * Reused the layout and structural code written by Marcus from Transaction tests.
 * I have written these tests assuming that there is a rollback function called undoRecentTransaction in account.
 * Right now I am testing functions that do not exist.
 * We can update these tests when the functionality is added.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Account;
import swe443.bluebank.Bank;

import java.io.File;

public class ModifyTransactionTests {

    Account sal; //represents account for Sal to be used in testing.
    Bank blue;
    File testFile1;
    /**
     * This method initializes the object to be used in testing the scenarios.
     * The balance is initialized to zero to allow modification later in testing.
     */
    @Before
    public void setup(){
        blue = new Bank();
        blue.setBankName("blue");
        sal = new Account(); //establish a new account for Sal
        sal.setName("Sal"); //set name
        sal.setUsername("sal"); //set username
        sal.setPassword("password123"); //set password
        sal.setDob("03/28/1986"); //set date of birth
        sal.setSsn(123568900); //set social security number
        sal.setInitialAmount(0); //set inital balance to 0
        Bank.setAllFees(0.05);
        blue.withAccount_Has(sal);
        blue.setAcct(sal);
        testFile1 = new File("src/logs/Sal_log");
    }

    /**
     * This method tests MODIFY_TRANSACTION Scenario1 (undo Deposit):
     * Sal has some initial balance.
     * Sal successfully deposits $50 into her account.
     * Then undoes this deposit.
     * Her account balance updates to what it previously was.
     * @see <a href='../../../doc/ModifyTransactionUndoDeposit.html'>ModifyTransactionUndoDeposit.html</a>
     */
    @Test
    public void testModifyTransactionUndoDeposit(){
        setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(sal);

        double initialBalance = sal.getAccountBalance();

        sal.setAccountBalance(50.00);

        sal.deposit(50.0);               //sal deposits $47.50
        System.out.println(sal.getAccountBalance());
        //sal.undoRecentTransaction();        //sal undoes the deposit.
        System.out.println(blue);
        blue.undoMostRecentTransaction();
        System.out.println(sal.getAccountBalance());
        //storyboard.assertTrue(" ", sal.getAccountBalance() == initialBalance);      //check if the balance has been updated to initial value.
        storyboard.assertEquals("Balance:",45.00,sal.getAccountBalance(),0.01); //check that current balance equals $48
        storyboard.assertTrue("Log created",testFile1.exists()); //check file 1 exists
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests MODIFY_TRANSACTION Scenario2 (undo Withdraw):
     * Sal has some initial balance.
     * Sal successfully withdraws $50 from her account.
     * Then undoes this withdrawal.
     * Her account balance updates to what it previously was.
     * @see <a href='../../../doc/ModifyTransactionUndoWithdraw.html'>ModifyTransactionUndoWithdraw.html</a>
     */
    @Test
    public void testModifyTransactionUndoWithdraw(){
        setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.addObjectDiagram(sal);

        sal.setAccountBalance(50.00);

        double initialBalance = sal.getAccountBalance();

        sal.withdraw(45.00);               //sal withdraws $50
        System.out.println(sal.getAccountBalance());
        blue.undoMostRecentTransaction();        //sal undoes the withdrawal.
        System.out.println(sal.getAccountBalance());
        //storyboard.assertTrue(" ",sal.getAccountBalance() == initialBalance);      //check if the balance has been updated to initial value.
        storyboard.assertEquals("Balance:",45.50,sal.getAccountBalance(),0); //check that current balance equals $44.10
        storyboard.dumpHTML();
        destroy(); //teardown the environment
    }

    /**
     * This method tests MODIFY_TRANSACTION Scenario3 (undo Transfer):
     * Sal has some initial balance.
     * Sal successfully transfers $100 from her account to Sara's account.
     * Then she undoes this Transfer.
     * Her account balance updates to what it previously was, and so does Sara's.
     * @see <a href='../../../doc/ModifyTransactionUndoTransfer.html'>ModifyTransactionUndoTransfer.html</a>
     */
    //Need to finish
    @Test
    public void testModifyTransactionUndoTransfer(){
        setup(); //setup the environment
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal's account initially has a balance of $150");
        sal.setAccountBalance(150);
        storyboard.add("Sara's account initially has a balance of $50");
        Account target = new Account()
                .withAccountBalance(50)
                .withName("Sara")
                .withUsername("Sara");
        storyboard.addObjectDiagram(sal,target);

        storyboard.add("Sal transfers $100 to Sara's account");

        storyboard.markCodeStart();
        sal.transfer(100, target,"sal"); //sal transfers $100 to sara's account
        storyboard.addCode();
        storyboard.addObjectDiagram(sal, target);

        blue.undoMostRecentTransaction();




        storyboard.assertEquals("Sal's Balance:",45,sal.getAccountBalance(),0); //check that current balance equals $100
        storyboard.assertEquals("Target Balance:",150, target.getAccountBalance(),0); //check that the current balance
        storyboard.dumpHTML();

        //storyboard.addObjectDiagram(sal);
        //sal.setAccountBalance(50.00);
        //double initialBalance = sal.getAccountBalance();

        //sal.transfer(1223334444);           //Sal transfers money to sara who's account # is 1223334444

        //double beforeUndo = sal.getAccountBalance();
        //storyboard.assertEquals("Balance:",45.50,sal.getAccountBalance(),0); //check that current balance equals $44.10
        //storyboard.assertTrue(" ", sal.getAccountBalance() == initialBalance && beforeUndo != initialBalance);      //check if the balance has been updated to initial value.

        //also we need to check whether sara's account was updated.

        destroy(); //teardown the environment
    }



    @After
    public void destroy(){
        sal = null; //set the object to null
    }
}
