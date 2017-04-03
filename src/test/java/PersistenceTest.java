import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.list.ObjectSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sdmlib.storyboards.Storyboard;
import swe443.bluebank.Account;
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
        data = new File("src/test/java/DBPersitenceTest.txt");               //check to see if existing DB is available
        if(!data.exists()) {
            data.createNewFile();                                        //if it is it will load up the existing data
        }
        BufferedReader br = new BufferedReader(new FileReader("src/test/java/DBPersitenceTest.txt"));
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
        storyboard.assertEquals("The user is Solonika Bose",blue.getAccount_Has().filterName("Salonika Bose").getName().toString(),"(Salonika Bose)" );
        storyboard.assertEquals("The ssn is 123456789",blue.getAccount_Has().filterName("Salonika Bose").getSsn().get(0),123456789);
        storyboard.assertEquals("01011995",blue.getAccount_Has().filterName("Salonika Bose").getDob().toString(), "(01011995)");
        storyboard.assertEquals("The username salonikab",blue.getAccount_Has().filterName("Salonika Bose").getUsername().toString(), "(salonikab)");
        storyboard.assertEquals("The password is asd",blue.getAccount_Has().filterName("Salonika Bose").getPassword().toString(), "(asdf)");
        storyboard.assertEquals("The initial amount is 100.00",blue.getAccount_Has().filterName("Salonika Bose").getInitialAmount().get(0), 100.00);
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
        assertEquals("(Anna Torres)",blue.getAccount_Has().filterName("Anna Torres").getName().toString() );
        storyboard.assertEquals("The user is Anna Torres",blue.getAccount_Has().filterName("Anna Torres").getName().toString(),"(Anna Torres)" );
        storyboard.assertEquals("The ssn is 1234",blue.getAccount_Has().filterName("Anna Torres").getSsn().get(0),1234);
        storyboard.assertEquals("(05/14/1990)",blue.getAccount_Has().filterName("Anna Torres").getDob().toString(), "(05/14/1990)");
        storyboard.assertEquals("The username (atorres)",blue.getAccount_Has().filterName("Anna Torres").getUsername().toString(), "(atorres)");
        storyboard.assertEquals("The password is 12345678",blue.getAccount_Has().filterName("Anna Torres").getPassword().toString(), "(12345678)");
        storyboard.assertEquals("The initial amount is 100.00",blue.getAccount_Has().filterName("Anna Torres").getInitialAmount().get(0), 100.00);

        IdMap idMap = BankCreator.createIdMap("demo");
        JsonArray jsonArray = idMap.toJsonArray(blue);
        String jsonText = jsonArray.toString(3);
        //System.out.println(jsonText);

        //
        storyboard.dumpHTML();
    }

    //Scenario 1: User Sal makes an account and is saved.
    @Test
    public void salMakesNewAccountAndItsSaved(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Registering Sals account into DB.text");
        storyboard.addObjectDiagram(blue);
        String user = "Sal\n"
                + "1234\n" +
                "04/14/1990\n"+
                "sals\n"+
                "12345678\n"+
                "0";
        System.setIn(new ByteArrayInputStream(user.getBytes()));
        blue.createAccount();
        IdMap idMap = BankCreator.createIdMap("demo");
        JsonArray jsonArray = idMap.toJsonArray(blue);
        String jsonText = jsonArray.toString(3);
        System.out.println(blue.getAccount_Has().filterName("Sal").getName().toString().equals("(Sal)"));
        if(!blue.getAccount_Has().filterName("Sal").getName().toString().equals("(Sal)") ) {
            try {
                File file = new File("src/test/java/DBPersitenceTest.txt");
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonText);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(blue.getAccount_Has().filterName("Sal").getInitialAmount().get(0));
        storyboard.assertEquals("The user is Sal",blue.getAccount_Has().filterName("Sal").getName().toString(),"(Sal)" );
        storyboard.assertEquals("The ssn is 1234",blue.getAccount_Has().filterName("Sal").getSsn().get(0),1234);
        storyboard.assertEquals("(04/14/1990)",blue.getAccount_Has().filterName("Sal").getDob().toString(), "(04/14/1990)");
        storyboard.assertEquals("The username (sals)",blue.getAccount_Has().filterName("Sal").getUsername().toString(), "(sals)");
        storyboard.assertEquals("The password is 12345678",blue.getAccount_Has().filterName("Sal").getPassword().toString(), "(12345678)");
        storyboard.assertEquals("The initial amount is 0.0",blue.getAccount_Has().filterName("Sal").getInitialAmount().get(0), 0.0);
        //
        storyboard.dumpHTML();
    }
    //Scenario 2: User Sal logs into an account she made last time.
    @Test
    public  void salLogsIn(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal logs in ");
        storyboard.addObjectDiagram(blue);
        String login = "sals\n"
                + "12345678\n";
        System.setIn(new ByteArrayInputStream(login.getBytes()));
        storyboard.assertEquals("The initial amount was 0.0",blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0), 0.0);
        //
        storyboard.dumpHTML();

    }
    //Scenario 3: User Sal deposit money from her account.
    @Test
    public  void salDepositMoneyOnHerAccount(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sals deposits money into ");
        storyboard.addObjectDiagram(blue);
        String login = "sals\n"
                + "12345678\n"
                + "120\n";
        System.setIn(new ByteArrayInputStream(login.getBytes()));
        blue.makeDeposit();
        storyboard.assertEquals("The amount in Sals account is 120.0",blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0), 120.0);
        //
        storyboard.dumpHTML();

    }

  //  Scenario 4: User Sal withdraws money from her account.
  @Test
  public  void salWithdrawsMoneyOnHerAccount(){
      Storyboard storyboard = new Storyboard();
      storyboard.add("Sals withdraws money into ");
      storyboard.addObjectDiagram(blue);
      String withSal = "sals\n"
              + "12345678\n"
              + "100\n"
              +"20\n";
      System.setIn(new ByteArrayInputStream(withSal.getBytes()));
      blue.makeDeposit(); 
      blue.makeWithdrawal();
      System.out.println(blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0));
      storyboard.assertEquals("The amount in Sals account is 80.0",blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0), 80.0);
      //
      storyboard.dumpHTML();

  }

    //  Scenario 5: User Sal makes a transfer with her account.
    @Test
    public  void salTransfersMoney(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal transfers money into ");
        storyboard.addObjectDiagram(blue);
        String withSal = "sals\n"
                + "12345678\n"
                + "100\n"
                +"20\n";
        System.setIn(new ByteArrayInputStream(withSal.getBytes()));
        blue.makeDeposit();
        blue.makeTransfer();
        System.out.println(blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0));
        storyboard.assertEquals("The amount in Sals account is 80.0",blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0), 80.0);
        //
        storyboard.dumpHTML();

    }

    //  Scenario 6: User Sal modifies her account information.
    @Test
    public  void salModifiesAccountInfo(){
        Storyboard storyboard = new Storyboard();
        storyboard.add("Sal changes account info into ");
        storyboard.addObjectDiagram(blue);
        String withSal = "sals\n"
                + "12345678\n"
                + "efgh\n";
        System.setIn(new ByteArrayInputStream(withSal.getBytes()));
        blue.getAcct().setPassword(System.in);
        System.out.println(blue.getAccount_Has().filterName("Sal").getAccountBalance().get(0));
        storyboard.assertEquals("Sal's password is now efgh",blue.getAccount_Has().filterName("Sal").getPassword().toString(), " (efgh) ");
        //
        storyboard.dumpHTML();

    }

//    Scenario 7: User Sal's account is deleted.
    // NOT IMPLEMENTED YET THIS FUNCTIONALLITY
    @Test
    public  void salDeletsAccount(){
//        Storyboard storyboard = new Storyboard();
//        storyboard.add("Sals delets account");
//        storyboard.addObjectDiagram(blue);

//        System.setIn(new ByteArrayInputStream(login.getBytes()));
        System.out.println("Not implemented yet on main menu");
        //
       // storyboard.dumpHTML();

    }

}
