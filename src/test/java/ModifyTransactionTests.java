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

import static org.junit.Assert.assertTrue;

public class ModifyTransactionTests {

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
     * This method tests MODIFY_TRANSACTION Scenario1 (undo Deposit):
     * Sal has some initial balance.
     * Sal successfully deposits $50 into her account.
     * Then undoes this deposit.
     * Her account balance updates to what it previously was.
     */
    @Test
    public void testModifyTransactionUndoDeposit(){
        setup(); //setup the environment

        double initialBalance = sal.getAccountBalance();

        sal.deposit(50);               //sal deposits $50
        sal.undoRecentTransaction();        //sal undoes the deposit.

        assertTrue(sal.getAccountBalance() == initialBalance);      //check if the balance has been updated to initial value.

        destroy(); //teardown the environment
    }

    /**
     * This method tests MODIFY_TRANSACTION Scenario2 (undo Withdraw):
     * Sal has some initial balance.
     * Sal successfully withdraws $50 from her account.
     * Then undoes this withdrawal.
     * Her account balance updates to what it previously was.
     */
    @Test
    public void testModifyTransactionUndoWithdraw(){
        setup(); //setup the environment

        double initialBalance = sal.getAccountBalance();

        sal.withdraw(50);               //sal withdraws $50
        sal.undoRecentTransaction();        //sal undoes the withdrawal.

        assertTrue(sal.getAccountBalance() == initialBalance);      //check if the balance has been updated to initial value.

        destroy(); //teardown the environment
    }

    /**
     * This method tests MODIFY_TRANSACTION Scenario3 (undo Transfer):
     * Sal has some initial balance.
     * Sal successfully transfers $100 from her account to Sara's account.
     * Then she undoes this Transfer.
     * Her account balance updates to what it previously was, and so does Sara's.
     */
    @Test
    public void testModifyTransactionUndoTransfer(){
        setup(); //setup the environment

        double initialBalance = sal.getAccountBalance();

        sal.transfer(1223334444);           //Sal transfers money to sara who's account # is 1223334444
        sal.undoRecentTransaction();        //sal undoes the transfer.
        double beforeUndo = sal.getAccountBalance();
        assertTrue(sal.getAccountBalance() == initialBalance && beforeUndo != initialBalance);      //check if the balance has been updated to initial value.

        //also we need to check whether sara's account was updated.

        destroy(); //teardown the environment
    }



    @After
    public void destroy(){
        sal = null; //set the object to null
    }
}