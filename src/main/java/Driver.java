
import swe443.bluebank.Bank;

import java.util.Scanner;

/**
 * Created by Truong on 3/29/17.
 */
public class Driver {


    public static void main (String[] argv) {

        Bank blue = new Bank();
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
                    blue.createAccount();
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
