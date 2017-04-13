import java.util.Scanner;

/**
 * Created by Shelby on 3/28/17.
 */
public class LogIn {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    private String passWord;

    public void promptForLogin(){
        Scanner scannInput = new Scanner(System.in);
        System.out.println("Please enter a username: ");
        userName= scannInput.nextLine();
        System.out.println("username: " +userName);
        System.out.println("Please enter your password: ");
        passWord= scannInput.nextLine();

        System.out.println("password size: " +passWord.length());
        System.out.println("Login success!");
    }

    public boolean checkForUser(String userTocheck){
        if(userName.equals(userTocheck) ){
            return true;
        }else{
            System.out.println("Username does not exist, please try again");
            return false;
        }
    }

    public boolean checkPassword(String pass) {

        if (passWord.equals(pass)) {
            return true;
        } else {
            System.out.println("Incorrect password, please try again");
            return false;
        }
    }
}
