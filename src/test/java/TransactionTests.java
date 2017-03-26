/**
 * Created by Spade on 3/26/17.
 * The test of this class tests the 3 scenarios for depositing money and
 * and the 3 scenarios for withdrawing money.
 *
 * @Author: Marcus Yarbrough
 */
import java.util.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;

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

    @Test
    public void testDepositScenario1(){

    }

    @Test
    public void testDepositScenario2(){

    }

    @Test
    public void testDepositScenario3(){

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
    public void exitSystem(){
        sal = null; //set the object to null
    }
}
