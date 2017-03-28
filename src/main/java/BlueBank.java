import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Scanner;

/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm; 1:54pm - 2:43 pm
 */
public class BlueBank {
    static Account acct = null; //account variable for newly created account

    public static void main (String[] argv) {
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
            System.out.print(mainMenu()); //print main menu
            System.out.print("Please enter option (0 to exit):"); //print line for user to enter menu option
            opt = input.nextInt(); //scan user input

            //call function based on user option selected
            switch (opt) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    makeDeposit();
                    break;
                case 4:
                    makeWithdrawal();
                    break;
            }
        }
    }

    /**
     * This method is the main menu for the banking system.
     * @return StringBuilder contains string builder of main menu
     */
    private static StringBuilder mainMenu(){
        StringBuilder menu = new StringBuilder();
        menu.append("\n\nMain Menu:\n");
        menu.append("1. Create Account\n");
        menu.append("2. Log In (NOT FUNCTIONING YET) \n");
        menu.append("3. Make Deposit\n");
        menu.append("4. Make Withdraw\n");
        return menu;
    }

    /**
     * This method creates a new account for the user based on the option chosen at the main menu.
     */
    static void createAccount(){
        acct = new Account(); //create a new account
        Scanner scanStr = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        Scanner scanDouble = new Scanner(System.in);

        System.out.println("Welcome to BlueBank! Lets create an account: Please enter your first and last name.");
        acct.setName(scanStr.nextLine());
        //acct.setName(argv[0]);
        System.out.println("Please enter your Social Security Number.");
        //acct.setSsn(scanInt.nextInt());
        acct.setSsn(Integer.parseInt(scanStr.nextLine()));
        System.out.println("Please enter your date of birth, in the format MMDDYYYY.");
        //acct.setDob(scanInt.nextInt());
        acct.setDob(scanStr.nextLine());
        //acct.setDob(Integer.parseInt(argv[2]));
        System.out.println("Please enter a username.");
        acct.setUsername(scanStr.nextLine());
        //acct.setUsername(argv[3]);
        System.out.println("Please enter a password");
        acct.setPassword(scanStr.nextLine());
        //acct.setPassword(argv[4]);
        System.out.println("Please enter the initial amount you'd like to deposit, in the format 1.00.");
        //acct.setInitialAmount(scanDouble.nextDouble());
        acct.setInitialAmount(Double.parseDouble(scanStr.nextLine()));
        //acct.setInitialAmount(Double.parseDouble(argv[5]));
        System.out.println("Thank you for the information, " + acct.getName() + " You currently have "
                + acct.getInitialAmount() + " dollars in your account.");
    }

    /**
     * This method is used for when we have a persistence layer going and can log in to an existing account
     */
    static void logIn(){

    }

    /**
     * This method is used to deposit money into a user's account based on option chosen at menu.
     */
    static void makeDeposit(){
        /**
         * Check if user created an account. Call create account if not.
         */
        if(acct==null){
            System.out.println("Please create an account first! You will be redirected to create account.");
            createAccount();
        }

        Scanner input = new Scanner(System.in);
        int amt;

        System.out.println("\n");
        System.out.print("Please enter the deposit amount:"); //prompt user for amount
        amt = input.nextInt(); //read deposit amount

        acct.deposit(amt); //deposit amount into account
        System.out.println();
        System.out.println("Your remaining balance: $"+acct.getAccountBalance()); //print balance
    }

    /**
     * This method is used to withdraw money from user's account based on option chosen at menu.
     */
    static void makeWithdrawal(){
        /**
         * Check if user created an account. Call create account if not.
         */
        if(acct==null){
            System.out.println("Please create an account first! You will be redirected to create account.");
            createAccount();
        }
        
        Scanner input = new Scanner(System.in);
        double amt;

        System.out.println("\n");
        System.out.println("Machine dispenses money in denomiations of $10, $20, or $100");
        System.out.print("Please enter amount to withdraw:"); //prompt for amount to withraw
        amt  = input.nextInt(); //read withdrawal amount

        amt = acct.withdraw(amt); //withdraw amount from account
        if(amt==0) System.out.println("No money has been withdrawn."); //check amt to be returned
        System.out.println();
        System.out.println("Your remaining balance: $"+acct.getAccountBalance()); //print balance
    }
}
