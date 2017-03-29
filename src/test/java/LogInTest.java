/**
 * Created by Shelby on 3/28/17.
 */
import org.junit.*;

import java.io.*;
import static org.junit.Assert.*;

import java.util.Scanner;


public class LogInTest {
    User brandon;
    LogIn in;
    @Before
    public void setUser(){
        brandon = new User("Brandon");
        in= new LogIn();
    }
//    Scenario 1:
//            1) Brandon has already turned on the BlueBank program and chooses to log in.
//            2) Brandon gets prompted with a message "Please enter your username".
//            3) Brandon enters his username.
//            4) The system checks to see if there is a username existing inside its database.
//            5) The system found that existing username.
//            6) Brandon gets prompted with a message "Please enter your password".
//            7) Brandon enters his password.
//            8) The system checks to see if that password corresponds with the existing username.
//            9) The system sees that the password does correspond with that username.
//            10) The system prompts a message "Login success!"
//            11) The system prompts Brandon with several options asking what he wants to do. (don't know what we want yet).

    @Test
    public void brandonLogInSuccess(){
        User brandon = new User("Brandon");
        LogIn in= new LogIn();
        in.setUserName("brandon");
        in.setPassWord("1234");
        assertEquals("brandon", in.getUserName());
        assertEquals("1234", in.getPassWord());

        //checking the input on prompt
        String user = "brandon\n"+"1234";
        String pass="1234";
        System.setIn(new ByteArrayInputStream(user.getBytes()));
        in.promptForLogin();

    }

//    Scenario 2:
//            1) Brandon has already turned on the BlueBank program and chooses to log in.
//            2) Brandon gets prompted with a message "Please enter your username".
//            3) Brandon enters a username.
//            4) The system checks to see if there is a username existing inside its database.
//            5) The system does not find that username.
//            6) Brandon gets prompted with a message "That username does not exist, please try again".
//            7) Brandon gets prompted with a message "Please enter your username".
//            8) Brandon exits the program.

    @Test
    public void brandonDoesntHaveAccount(){
        in.setUserName("brandon");
        in.setPassWord("1234");
        assertEquals("brandon", in.getUserName());
        assertEquals("1234", in.getPassWord());

        //checking if user is on the system
        assertFalse(in.checkForUser("brandonS"));
    }

//    Scenario 3:
//            1) Brandon has already turned on the BlueBank program and chooses to log in.
//            2) Brandon gets prompted with a message "Please enter your username".
//            3) Brandon enters a username.
//            4) The system checks to see if there is a username existing inside its database.
//            5) The system does not find that username.
//            6) Brandon gets prompted with a message "That username does not exist, please try again".
//            7) Brandon gets prompted with a message "Please enter your username".
//            8) Brandon enters a new username.
//            9) The system found that existing username.
//            10) Brandon gets prompted with a message "Please enter your password".
//            11) Brandon enters his password.
//            12) The system checks to see if that password corresponds with the existing username.
//            13) The system sees that the password does correspond with that username.
//            14) The system prompts a message "Login success!"
//            15) The system prompts Brandon with several options asking what he wants to do. (don't know what we want yet).

    @Test
    public void brandonEnterWrongUserNameProccedsWithTheRightOne(){
        in.setUserName("brandon");
        in.setPassWord("1234");
        assertEquals("brandon", in.getUserName());
        assertEquals("1234", in.getPassWord());

        //checking if user is on the system
        assertFalse(in.checkForUser("brandonS"));
        String user = "brandon\n"+"1234";
        String pass="1234";
        System.setIn(new ByteArrayInputStream(user.getBytes()));
        in.promptForLogin();

    }

//    Scenario 4:
//            1) Brandon has already turned on the BlueBank program and chooses to log in.
//            2) Brandon gets prompted with a message "Please enter your username".
//            3) Brandon enters a username.
//            4) The system checks to see if there is a username existing inside its database.
//            5) The system does not find that username.
//            6) Brandon gets prompted with a message "That username does not exist, please try again".
//            7) Brandon gets prompted with a message "Please enter your username".
//            8) Brandon enters a new username.
//            9) The system found that existing username.
//            10) Brandon gets prompted with a message "Please enter your password".
//            11) Brandon enters a password.
//            12) The system checks to see if that password corresponds with the existing username.
//            13) The system sees that the password is incorrect.
//            14) Brandon gets prompted with a message "Incorrect password, try again."
//            15) Brandon enters the correct password.
//            16) The system sees that the password does correspond with that username.
//            17) The system prompts a message "Login success!"
//            18) The system prompts Brandon with several options asking what he wants to do. (don't know what we want yet).

    @Test
    public void brandonEnterWrongPassWord(){
    in.setUserName("brandon");
    in.setPassWord("1234");
    assertEquals("brandon", in.getUserName());
    assertEquals("1234", in.getPassWord());

    //checking if user is on the system
    assertTrue(in.checkForUser("brandon"));

    //checking for user wrong password
    assertFalse(in.checkPassword("1235"));
    String user = "brandon\n"+"1234";
    String pass="1234";
    System.setIn(new ByteArrayInputStream(user.getBytes()));
    in.promptForLogin();

    }

}
