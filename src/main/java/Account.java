import java.util.Date;

/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm; 1:54pm - 2:43 pm
 */
public class Account {
    String name;
    int ssn;
    //Date should be an actual Date obj but just for simplicity I am keeping it a String for now
    String dob;
    String username;
    String password;
    double initialAmount;

    public Account(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getDob() {

        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() >= 4){
            this.password = password;
        }
        else {
            System.out.println("The password you've entered is too short. Please enter a different password that is at least four (4) characters.");
            this.password = "";
        }

    }


    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        if(initialAmount > 0.0) {
            this.initialAmount = initialAmount;
        }
        else {
            System.out.println("Please do not enter negative number. Please enter a positve initial amount to deposit.");
            this.initialAmount = 0.00;
        }

    }



}
