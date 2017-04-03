/*
   Copyright (c) 2017 Truong
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package swe443.bluebank;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.util.UserSet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;
/**
 *
 * @see <a href='../../../../../src/main/java/model.java'>model.java</a>
 * @see <a href='../../../../../src/main/java/Model.java'>Model.java</a>
 */
public  class Bank implements SendableEntity
{


   static Account acct = null;
   static User user = null;

   public Account getAcct(){     //manually entered this(ht)
      return this.acct;
   }

   public void resetAcct(){
      this.acct = null;
   }


   //==========================================================================
   public Object mainMenu(  )
   {
      StringBuilder menu = new StringBuilder();
      menu.append("==================================");
      if(acct != null){
         menu.append("\n["+acct.getName().toString()+"]" + " is Logged on");
      }
      menu.append("\n\nMain Menu:\n");
      menu.append("1. Create Account\n");
      menu.append("2. Log In\n");
      menu.append("3. Make Deposit\n");
      menu.append("4. Make Withdraw\n");
      menu.append("5. View Balance\n");
      menu.append("\n==================================\n");
      return menu;
   }


   //==========================================================================
   public void createAccount(  )
   {

      String name;
      acct = new Account(); //create a new account
      user = new User();
      Scanner scanStr = new Scanner(System.in);
      Scanner scanInt = new Scanner(System.in);
      Scanner scanDouble = new Scanner(System.in);

      System.out.println("Welcome to BlueBank! Lets create an account: Please enter your first and last name.");
      name = scanStr.nextLine();
      acct.setName(name);
      user.setUserName(name);
      System.out.println("Please enter the last four (4) digits of your Social Security Number.");
      acct.setSsn(Integer.parseInt(scanStr.nextLine()));
      System.out.println("Please enter your date of birth, in the format MM/DD/YYYY.");
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
      double x = Double.parseDouble(scanStr.nextLine());
      while(x < 0){
         System.out.println("please enter a positive value");
         x = Double.parseDouble(scanStr.nextLine());

      }
      acct.setInitialAmount(x);
      acct.setAccountBalance(x);
      //acct.setInitialAmount(Double.parseDouble(argv[5]));
      System.out.println("Thank you for the information, " + acct.getName() + ".\n You currently have $"
              + doubleToMoneyFormat(acct.getInitialAmount()) + " in your account.");

      withAccount_Has(acct);
      withBank_Has(user);
      user.withAccount_Has(acct);



   }
   Scanner scanStr;

   //==========================================================================
   public void logIn()
   {
      int tries=0;
      scanStr = new Scanner(System.in);
      String userName = null;
      String password = null;
      System.out.println("Please enter your Username");
      userName = scanStr.nextLine();
      while(getAccount_Has().filterUsername(userName).getUsername().toString().equals("("+userName+")")==false && tries != 5){   //searches to see if the username exist
         if(tries == 4){                                                                                                         //if the user tries 5 times it will return to main menu
            System.out.println("too many tries, going back to main menu");
            return;
         }
         System.out.println("That username does not exist, please try again");
         userName = scanStr.nextLine();
         tries++;
      }


      tries = 0;
      System.out.println("Please enter your Password");
      password = scanStr.nextLine();
      //System.out.println(getAccount_Has().filterUsername(userName).getPassword().toString());

      while(getAccount_Has().filterUsername(userName).getPassword().toString().equals("("+password+")")==false && tries != 5){   //similar process to to username except this time it looks for correct password
         if(tries == 4){
            System.out.println("too many tries, going back to main menu");
            return;
         }
         System.out.println("Wrong password, Please try again");
         password = scanStr.nextLine();
         tries++;
      }

      acct = getAccount_Has().filterUsername(userName).get(0);
      System.out.println("Login success!");
      System.out.println("You have logged in as " + acct.getName());

   }


   //==========================================================================
   public void makeDeposit(  )
   {
      /**
       * Check if user created an account. Call create account if not.
       */
      if(Account_Has==null){
         System.out.println("Please create an account first! You will be redirected to create account.");
         createAccount();
      }

      if(acct == null){
         System.out.println("Please Login first!!");
         logIn();
         if(acct == null){
            return;
         }
      }

      Scanner input = new Scanner(System.in);
      double amt;

      System.out.println("\n");
      System.out.print("Please enter the deposit amount:"); //prompt user for amount
      amt = input.nextDouble(); //read deposit amount

      acct.deposit(amt); //deposit amount into account
      System.out.println();

      // Ensures the account balance is in the format 0.00
      double num = acct.getAccountBalance();
      num = Math.round(num*100);
      num = num/100;

      acct.setAccountBalance(num);

      System.out.println("Your remaining balance: $"+doubleToMoneyFormat(acct.getAccountBalance())); //print balance
   }


   //==========================================================================
   public void makeWithdrawal(  )
   {
      /**
       * Check if user created an account. Call create account if not.
       */
      if(Account_Has==null){
         System.out.println("Please create an account first! You will be redirected to create account.");
         createAccount();
      }

      if(acct == null){
         System.out.println("Please Login first!!");
         logIn();
         if(acct == null){
            return;
         }
      }

      Scanner input = new Scanner(System.in);
      double amt;

      System.out.println("\n");
      System.out.println("Machine dispenses money in denominations of $10, $20, or $100");
      System.out.print("Please enter amount to withdraw:"); //prompt for amount to withdraw
      amt  = input.nextDouble(); //read withdrawal amount

      amt = acct.withdraw(amt); //withdraw amount requested;store result

      if(amt==-1){//withdraw amount exceeds balance
         System.out.println("Insufficient funds. Exiting to main screen.");
      }else if(amt==0){//no money withdrawn
         System.out.println("No money has been withdrawn. Exiting to main screen.");
      }else{//money withdrawn from account
         System.out.println("$"+doubleToMoneyFormat(amt)+" has been withdrawn."); //print amount withdrawn
         System.out.println("Your remaining balance: $"+doubleToMoneyFormat(acct.getAccountBalance())); //print balance
      }

      // Ensures the account balance is in the format 0.00
      double num = acct.getAccountBalance();
      num = Math.round(num*100);
      num = num/100;

      acct.setAccountBalance(num);

      System.out.println();
   }


   //==========================================================================


   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null) {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      if (listeners == null) {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      if (listeners == null) {
         listeners.removePropertyChangeListener(listener);
      }
      listeners.removePropertyChangeListener(listener);
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
      if (listeners != null) {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }


   //==========================================================================


   public void removeYou()
   {
      withoutAccount_Has(this.getAccount_Has().toArray(new Account[this.getAccount_Has().size()]));
      withoutBank_Has(this.getBank_Has().toArray(new User[this.getBank_Has().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }


   //==========================================================================

   public static final String PROPERTY_BANKNAME = "bankName";

   private String bankName;

   public String getBankName()
   {
      return this.bankName;
   }

   public void setBankName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.bankName, value)) {

         String oldValue = this.bankName;
         this.bankName = value;
         this.firePropertyChange(PROPERTY_BANKNAME, oldValue, value);
      }
   }

   public Bank withBankName(String value)
   {
      setBankName(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getBankName());
      return result.substring(1);
   }



   /********************************************************************
    * <pre>
    *              one                       many
    * Bank ----------------------------------- Account
    *              Bank_has                   Account_Has
    * </pre>
    */

   public static final String PROPERTY_ACCOUNT_HAS = "Account_Has";

   private AccountSet Account_Has = null;

   public AccountSet getAccount_Has()
   {
      if (this.Account_Has == null)
      {
         return AccountSet.EMPTY_SET;
      }

      return this.Account_Has;
   }

   public Bank withAccount_Has(Account... value)
   {
      if(value==null){
         return this;
      }
      for (Account item : value)
      {
         if (item != null)
         {
            if (this.Account_Has == null)
            {
               this.Account_Has = new AccountSet();
            }

            boolean changed = this.Account_Has.add (item);

            if (changed)
            {
               item.withBank_has(this);
               firePropertyChange(PROPERTY_ACCOUNT_HAS, null, item);
            }
         }
      }
      return this;
   }

   public Bank withoutAccount_Has(Account... value)
   {
      for (Account item : value)
      {
         if ((this.Account_Has != null) && (item != null))
         {
            if (this.Account_Has.remove(item))
            {
               item.setBank_has(null);
               firePropertyChange(PROPERTY_ACCOUNT_HAS, item, null);
            }
         }
      }
      return this;
   }

   public Account createAccount_Has()
   {
      Account value = new Account();
      withAccount_Has(value);
      return value;
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Bank ----------------------------------- User
    *              User_Has                   Bank_Has
    * </pre>
    */

   public static final String PROPERTY_BANK_HAS = "Bank_Has";

   private UserSet Bank_Has = null;

   public UserSet getBank_Has()
   {
      if (this.Bank_Has == null)
      {
         return UserSet.EMPTY_SET;
      }

      return this.Bank_Has;
   }

   public Bank withBank_Has(User... value)
   {
      if(value==null){
         return this;
      }
      for (User item : value)
      {
         if (item != null)
         {
            if (this.Bank_Has == null)
            {
               this.Bank_Has = new UserSet();
            }

            boolean changed = this.Bank_Has.add (item);

            if (changed)
            {
               item.withUser_Has(this);
               firePropertyChange(PROPERTY_BANK_HAS, null, item);
            }
         }
      }
      return this;
   }

   public Bank withoutBank_Has(User... value)
   {
      for (User item : value)
      {
         if ((this.Bank_Has != null) && (item != null))
         {
            if (this.Bank_Has.remove(item))
            {
               item.setUser_Has(null);
               firePropertyChange(PROPERTY_BANK_HAS, item, null);
            }
         }
      }
      return this;
   }

   public User createBank_Has()
   {
      User value = new User();
      withBank_Has(value);
      return value;
   }


   //==========================================================================
   public void viewBalance(  )
   {
      if(acct == null){
         System.out.println("Please login first");
         logIn();
         if(acct == null){
            return;
         }

         // Ensures that the account balance is in the form 1.00 instead of 1.0
         double num = acct.getAccountBalance();
         num = Math.round(num*100);
         num = num/100;
         acct.setAccountBalance(num);
      }
      System.out.println("Account balance: $" + doubleToMoneyFormat(acct.getAccountBalance()));
   }

   
   //==========================================================================
   public void makeTransfer(  )
   {
      
   }

   //==========================================================================
   public String doubleToMoneyFormat(double number)
   {
      number = Math.round(number * 100);
      number = number/100;
      return String.format("%.2f", number);
   }
}
