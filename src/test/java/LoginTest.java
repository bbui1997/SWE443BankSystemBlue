import de.uniks.networkparser.IdMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Bank;
import swe443.bluebank.util.BankCreator;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Truong on 4/2/17.
 */
public class LoginTest {
    Bank blue = new Bank();

    @Before
    public void setup() throws IOException,FileNotFoundException {
        File data;
        data = new File("src/test/java/DBtest.txt");               //check to see if existing DB is available
        if(!data.exists()) {
            data.createNewFile();                                        //if it is it will load up the existing data
        }
        BufferedReader br = new BufferedReader(new FileReader("src/test/java/DBtest.txt"));
        if (br.readLine() == null) {
            //System.out.println("file is empty");
        } else {
            //System.out.println("file is not empty");
            String jsonText = new Scanner(data).useDelimiter("\\Z").next();
            IdMap readerMap = BankCreator.createIdMap("demo");
            Object rootObject = readerMap.decode(jsonText);
            blue = (Bank) rootObject;
        }
    }

    @After
    public void clean(){
        blue.resetAcct();
    }




      /**
    * 
    * @see <a href='../../../doc/SuccessLoginTest.html'>SuccessLoginTest.html</a>
 */
   @Test
    public void SuccessLoginTest(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Successfully logging in");
        storyboard.addObjectDiagram(blue);
        String login = "salonikab\n"
                + "asdf\n";
        System.setIn(new ByteArrayInputStream(login.getBytes()));
        blue.logIn();
        storyboard.assertEquals("solonika successfully logs in", "Salonika Bose",blue.getAcct().getName());
        storyboard.dumpHTML();
        //System.out.println(blue.getAcct().getName());

    }

      /**
    * 
    * @see <a href='../../../doc/LoginUsernameNotInDbTest.html'>LoginUsernameNotInDbTest.html</a>
 */
   @Test
    public void LoginUsernameNotInDbTest(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Username not in DB");
        storyboard.addObjectDiagram(blue);
        String login = "1\n"
                + "2\n"
                + "3\n"
                + "4\n"
                + "5\n";
        System.setIn(new ByteArrayInputStream(login.getBytes()));
        blue.logIn();
        System.out.println(blue.getAcct());
        storyboard.assertNull("No username found in DB", blue.getAcct());
        storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../doc/WrongPasswordTest.html'>WrongPasswordTest.html</a>
 */
   @Test
    public void WrongPasswordTest(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Wrong password");
        storyboard.addObjectDiagram(blue);
        String login = "salonikab\n"
                + "1\n"
                + "2\n"
                + "3\n"
                + "4\n"
                + "5\n";
        System.setIn(new ByteArrayInputStream(login.getBytes()));
        blue.logIn();
        storyboard.assertNull("wrong password", blue.getAcct());
        storyboard.dumpHTML();
    }

      /**
    * 
    * @see <a href='../../../doc/AnotherSuccessfulLoginTest.html'>AnotherSuccessfulLoginTest.html</a>
 */
   @Test
    public void AnotherSuccessfulLoginTest(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Wrong password");
        storyboard.addObjectDiagram(blue);
        String login = "1\n"
                +"salonikab\n"
                +"2\n"
                + "asdf\n";

        System.setIn(new ByteArrayInputStream(login.getBytes()));
        blue.logIn();
        storyboard.assertEquals("solonika successfully logs in", "Salonika Bose",blue.getAcct().getName());
        storyboard.dumpHTML();
    }

}
