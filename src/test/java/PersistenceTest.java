import de.uniks.networkparser.IdMap;
import org.junit.Before;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Bank;
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
}
