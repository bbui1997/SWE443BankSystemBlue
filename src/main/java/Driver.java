
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import swe443.bluebank.Bank;
import swe443.bluebank.util.BankCreator;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by Truong on 3/29/17.
 */
public class Driver {

    public static void main (String[] argv) throws IOException,FileNotFoundException {

        File data;
        data = new File("src/test/java/DB.txt");
        Bank blue = new Bank();
        BufferedReader br = new BufferedReader(new FileReader("src/test/java/DB.txt"));
        if (br.readLine() == null) {
            //System.out.println("file is empty");
        } else {
            //System.out.println("file is not empty");
            String jsonText = new Scanner(data).useDelimiter("\\Z").next();
            //System.out.println(jsonText);
            IdMap readerMap = BankCreator.createIdMap("demo");
            Object rootObject = readerMap.decode(jsonText);
            blue = (Bank) rootObject;
        }

        //String jsonText = new Scanner(new File("src/test/java/DB.txt")).useDelimiter("\\Z").next();
        //System.out.println(jsonText);
        //IdMap readerMap = BankCreator.createIdMap("demo");
        //Object rootObject = readerMap.decode(jsonText);
        //Bank blue = new Bank();
        //Bank blue = (Bank) rootObject;

        Scanner input = new Scanner(System.in); //placeholder for user input
        String opt = "Start"; //placeholder for user option

        System.out.println("Welcome to BlueBank!");

        /**
         * while loop for user interface to display options
         * 1 - create account
         * 2 - log in
         * 3 - make deposit
         * 4 - make withdrawal
         * 5 - view balance
         * 6 - make transfer
         * 7 - undo recent transaction
         * 0 - to exit
         */
        while (opt != "0") {
            System.out.print(blue.mainMenu()); //print main menu
            System.out.print("Please enter option (0 to exit):"); //print line for user to enter menu option
            opt = input.next(); //scan user input

            //call function based on user option selected
            switch (opt) {
                case "1":
                    blue.createAccount();
                    IdMap idMap = BankCreator.createIdMap("demo");
                    JsonArray jsonArray = idMap.toJsonArray(blue);
                    String jsonText = jsonArray.toString(3);
                    //System.out.println(jsonText);
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
                case "2":
                    blue.logIn();
                    break;
                case "3":
                    blue.makeDeposit();
                    idMap = BankCreator.createIdMap("demo");
                    jsonArray = idMap.toJsonArray(blue);
                    jsonText = jsonArray.toString(3);
                    //System.out.println(jsonText);
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
                case "4":
                    blue.makeWithdrawal();
                    idMap = BankCreator.createIdMap("demo");
                    jsonArray = idMap.toJsonArray(blue);
                    jsonText = jsonArray.toString(3);
                    //System.out.println(jsonText);
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
                case "5":
                    blue.viewBalance();
                    break;
                case "6":
                    blue.makeTransfer();
                    idMap = BankCreator.createIdMap("demo");
                    jsonArray = idMap.toJsonArray(blue);
                    jsonText = jsonArray.toString(3);
                    //System.out.println(jsonText);
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

                case "7":
                    blue.undoMostRecentTransaction();
                    idMap = BankCreator.createIdMap("demo");
                    jsonArray = idMap.toJsonArray(blue);
                    jsonText = jsonArray.toString(3);
                    //System.out.println(jsonText);
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

                case "8": // print out bank's total income
                    System.out.println(blue.doubleToMoneyFormat(blue.getTotalIncomeFromAllUsers()));
                    break;

                case "0":
                    exit(0);

                default:
                    System.out.println("Oops! Please enter a valid number.");
                    idMap = BankCreator.createIdMap("demo");
                    jsonArray = idMap.toJsonArray(blue);
                    jsonText = jsonArray.toString(3);
                    //System.out.println(jsonText);
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
            }
        }
    }

}
