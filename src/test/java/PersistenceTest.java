import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.ObjectSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Bank;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.util.BankCreator;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Truong on 3/30/17.
 */
public class PersistenceTest {
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
    
    /**
     *
     * @see <a href='../../../doc/CheckDBTest.html'>CheckDBTest.html</a>
     */
    @Test
    public void CheckDBTest(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("All the information was extracted from json files");
        storyboard.addObjectDiagram(blue);
        storyboard.assertEquals("The user is Solonika Bose",blue.getAccount_Has().getName().toString(),"(Salonika Bose)" );
        storyboard.assertEquals("The ssn is 123456789",blue.getAccount_Has().getSsn().get(0),123456789);
        storyboard.assertEquals("01011995",blue.getAccount_Has().getDob().toString(), "(01011995)");
        storyboard.assertEquals("The username salonikab",blue.getAccount_Has().getUsername().toString(), "(salonikab)");
        storyboard.assertEquals("The password is asd",blue.getAccount_Has().getPassword().toString(), "(asdf)");
        storyboard.assertEquals("The initial amount is 100.00",blue.getAccount_Has().getInitialAmount().get(0), 100.00);
        storyboard.dumpHTML();
    }
    
    /**
     *
     * @see <a href='../../../doc/CreatingNewAccount.html'>CreatingNewAccount.html</a>
     */
    @Test
    public void testCreatingNewAccount(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Putting the account into DB.text");
        storyboard.addObjectDiagram(blue);
        String user = "Anna Torres\n"
        + "1234\n" +
        "05/14/1990\n"+
        "atorres\n"+
        "12345678\n"+
        "100";
        System.setIn(new ByteArrayInputStream(user.getBytes()));
        blue.createAccount();
        System.out.println(blue.getAccount_Has().filterName("Anna Torres").getInitialAmount().get(0));
        assertEquals("(Anna Torres)",blue.getAccount_Has().filterName("Anna Torres").getName().toString() );
        storyboard.assertEquals("The user is Anna Torres",blue.getAccount_Has().filterName("Anna Torres").getName().toString(),"(Anna Torres)" );
        storyboard.assertEquals("The ssn is 1234",blue.getAccount_Has().filterName("Anna Torres").getSsn().get(0),1234);
        storyboard.assertEquals("(05/14/1990)",blue.getAccount_Has().filterName("Anna Torres").getDob().toString(), "(05/14/1990)");
        storyboard.assertEquals("The username (atorres)",blue.getAccount_Has().filterName("Anna Torres").getUsername().toString(), "(atorres)");
        storyboard.assertEquals("The password is 12345678",blue.getAccount_Has().filterName("Anna Torres").getPassword().toString(), "(12345678)");
        storyboard.assertEquals("The initial amount is 100.00",blue.getAccount_Has().filterName("Anna Torres").getInitialAmount().get(0), 100.00);
        //
        storyboard.dumpHTML();
    }
    
}
