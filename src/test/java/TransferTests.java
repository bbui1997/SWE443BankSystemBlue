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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransferTests {
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
     * This method tests transfer Scenario1:
     * Sal successfully transfers $100 into Sara's account.
     * Her initial balance is $150.
     */
    @Test
    public void testTransferScenario1(){
        setup(); //setup the environment
        sal.setAccountBalance(150);

        sal.transfer(100, new Account()); //sal transfers $100
        assertEquals("Balance:",50,sal.getAccountBalance(),0); //check that current balance equals $100

        destroy(); //teardown the environment
    }

    /**
     * This method tests transfer Scenario2:
     * Sal wants to transfer $100 to Sara's account.
     * Sal initially has $50.
     * She transfers $100 and the account has the negative balance of $50.
     */
    @Test
    public void testTransferScenario2(){
        setup(); //setup the environment
        sal.setAccountBalance(50); //set initial balance to $50

        sal.transfer(100, new Account()); //Sal deposits $100 into Sara's account
        assertEquals("Balance:",-50,sal.getAccountBalance(),0); //check that current balance equals -$50 after transfer

        destroy(); //teardown the environment
    }

    /**
     * This method tests deposit Scenario3:
     * Sal transfers $100 from one account to another account.
     * She has an initial balance of $150 on her checking account and makes a transfer of $100.
     * She has an initial balance of $50 on her saving account.
     * After transfer she has $50 on checking and $150 on saving.
     */
    @Test
    public void testTransferScenario3(){
        setup(); //setup the environment
        sal.setAccountBalance(150); //set initial balance to $150

        sal.transfer(100, new Account()); // sal transfers $100 to the saving accoount
        assertTrue("Balance:", 50 == sal.getAccountBalance());
        assertEquals("Destination Balance:",150,sal.getAccountBalance(),0); //check that the current balance on saving equals $150

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
