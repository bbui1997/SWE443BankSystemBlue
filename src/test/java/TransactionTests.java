/**
 * Created by Spade on 3/26/17.
 * The test of this class tests the 3 scenarios for depositing money and
 * and the 3 scenarios for withdrawing money.
 *
 * @Author: Marcus Yarbrough
 */
import java.util.*;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

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
     */
    @Test
    public void testDepositScenario1(){
        setup(); //setup the environment

        sal.deposit(50); //sal deoposits $50
        assertEquals("Balance:",50,sal.getAccountBalance(),0); //check that current balance equals $50

        destroy(); //teardown the environment
    }

    /**
     * This method tests deposit Scenario2:
     * Sal brings her account balance positive.
     * Sal initially has a negative balance of ($25).
     * She deposits $100 and the new balance is $75.
     */
    @Test
    public void testDepositScenario2(){
        setup(); //setup the envinronment
        sal.setAccountBalance(-25); //set inital balance to -$25

        sal.deposit(100); //Sal deposits $100 into her account
        assertEquals("Balance:",75,sal.getAccountBalance(),0); //check that current balance equals $75 after deposit

        destroy(); //teardown the environment
    }

    /**
     * This method tests deposit Scenario3:
     * Sal attempts to make two deposits in one transaction.
     * She has an intial balance of $120 and makes a deposit of $80.
     * She then makes another deposit of $40.
     */
    @Test
    public void testDepositScenario3(){
        setup(); //setup the environment
        sal.setInitialAmount(120); //set initial balance to $120

        sal.deposit(80); //first deposit of $80
        sal.deposit(40); //second deposit of $40
        assertEquals("Balance:",240,sal.getAccountBalance(),0); //check that the current balance equals $240 after both deposits

        destroy(); //teardwon the environment
    }

    @Test
    public void testWithdrawScenario1(){

    }

    @Test
    public void testWithdrawScenario2(){

    }

    @Test
    public void testWithdrawScenario3(){

    }

    /**
     * This method sets the gloabal object sal to null.
     * The method will be used after each test run to destory object before the next test.
     */
    @After
    public void destroy(){
        sal = null; //set the object to null
    }
}
