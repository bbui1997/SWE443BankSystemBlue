/**
 * Created by Salonika on 3/25/17. Started working at 11:35 am - 12:51 pm; 1:54pm - 2:43 pm
 */
public class Account {
    private String name;
    private int ssn;
    //Date should be an actual Date obj but just for simplicity I am keeping it a String for now
    private String dob;
    private String username;
    private String password;
    private double initialAmount;
    private double accountBalance;

    public Account(){
        accountBalance = 0; //account balance begins with no money
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
        if(initialAmount >= 0.0) {
            this.initialAmount = initialAmount;
            this.accountBalance = initialAmount; //set account balance to initial amount deposited
        }
        else {
            System.out.println("Please do not enter negative number. Please enter a positive initial amount to deposit.");
            this.initialAmount = 0.00;
        }

    }

    /**
     * This method is used to increase the acocunt balance by the amount a user
     * deposits into their account.
     *
     * @param amt the amount that is deposited into the account
     */
    public void deposit(double amt){
        this.setAccountBalance(amt+getAccountBalance()); //add amount to the account balance.
    }

    /**
     * This method is used to update the account balance.
     *
     * @param amt the amount of the account balance.
     */
    public void setAccountBalance(double amt){
        accountBalance = amt; //set the account balance to the specified amount.
    }

    /**
     * This method returns the value of the account balance
     * @return double This returns the account balance
     */
    public double getAccountBalance(){
        return this.accountBalance; //return the account balance
    }


}
