import java.util.Scanner;

/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm
 * Either run w/ Scanner like how the program should work, or run with argv to test input?
 */
public class BlueBank {

    public static void main (String[] argv){
        //Scanner scanStr = new Scanner(System.in);
        //Scanner scanInt = new Scanner(System.in);
        //Scanner scanDouble = new Scanner(System.in);
        Account acct = new Account();
        System.out.println("Welcome to BlueBank! Lets create an account: Please enter your first and last name.");
        //acct.setName(scanStr.nextLine());
        acct.setName(argv[0]);
        System.out.println("Please enter your Social Security Number.");
        //acct.setSsn(scanInt.nextInt());
        acct.setSsn(Integer.parseInt(argv[1]));
        System.out.println("Please enter your date of birth, in the format MMDDYYYY.");
        //acct.setDob(scanInt.nextInt());
        acct.setDob(Integer.parseInt(argv[2]));
        System.out.println("Please enter a username.");
        //acct.setUsername(scanStr.nextLine());
        acct.setUsername(argv[3]);
        System.out.println("Please enter a password");
        //acct.setPassword(scanStr.nextLine());
        acct.setPassword(argv[4]);
        System.out.println("Please enter the initial amount you'd like to deposit, in the format 1.00.");
        //acct.setInitialAmount(scanInt.nextDouble());
        acct.setInitialAmount(Double.parseDouble(argv[5]));
        System.out.println("Thank you for the information, "+acct.getName()+" You currently have "
                            +acct.getInitialAmount()+ " dollars in your account.");




    }
}
