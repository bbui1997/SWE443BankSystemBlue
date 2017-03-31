
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import swe443.bluebank.Bank;
import swe443.bluebank.util.BankCreator;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Truong on 3/29/17.
 */
public class Driver {


    public static void main (String[] argv) throws IOException,FileNotFoundException {
        File data;
        data = new File("src/test/java/DB.txt");               //check to see if existing DB is available
        if(!data.exists()) {
            data.createNewFile();                                        //if it is it will load up the existing data
        }
        Bank blue = new Bank();
        BufferedReader br = new BufferedReader(new FileReader("src/test/java/DB.txt"));
        if (br.readLine() == null) {
            System.out.println("file is empty");
        } else {
            //System.out.println("file is not empty");
            String jsonText = new Scanner(data).useDelimiter("\\Z").next();
            IdMap readerMap = BankCreator.createIdMap("demo");
            Object rootObject = readerMap.decode(jsonText);
            blue = (Bank) rootObject;
        }

        Scanner input = new Scanner(System.in); //placeholder for user input
        int opt = -1; //placeholder for user option

        System.out.println("Welcome to BlueBank!");

        /**
         * while loop for user interface to display options
         * 1 - create account
         * 2 - log in
         * 3 - make deposit
         * 4 - make withdrawal
         * 0 - to exit
         */
        while (opt != 0) {
            System.out.print(blue.mainMenu()); //print main menu
            System.out.print("Please enter option (0 to exit):"); //print line for user to enter menu option
            opt = input.nextInt(); //scan user input

            //call function based on user option selected
            switch (opt) {
                case 1:
                    blue.createAccount();                                       //after creating a new account it will update the json file to keep
                    IdMap idMap = BankCreator.createIdMap("demo");    //to keep track of the data.
                    JsonArray jsonArray = idMap.toJsonArray(blue);
                    String jsonText = jsonArray.toString(3);
                    try {
                        File file = new File("src/test/java/DB.txt");
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(jsonText);
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    blue.logIn();
                    break;
                case 3:
                    blue.makeDeposit();
                    break;
                case 4:
                    blue.makeWithdrawal();
                    break;
            }
        }
    }

}
