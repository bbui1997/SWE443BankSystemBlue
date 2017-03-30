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

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import de.uniks.networkparser.EntityUtil;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.Account;
import swe443.bluebank.util.UserSet;
import swe443.bluebank.User;
/**
 *
 * @see <a href='../../../../../src/main/java/model.java'>model.java</a>
 */
public  class Bank implements SendableEntity
{


   static Account acct = null;
   static User user = null;


   //==========================================================================
   public Object mainMenu(  )
   {
      StringBuilder menu = new StringBuilder();
      menu.append("\n\nMain Menu:\n");
      menu.append("1. Create Account\n");
      menu.append("2. Log In (NOT FUNCTIONING YET) \n");
      menu.append("3. Make Deposit\n");
      menu.append("4. Make Withdraw\n");
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
      double x = Double.parseDouble(scanStr.nextLine());
      while(x<0){
         System.out.println("Please provide a positive value");
         x = Double.parseDouble(scanStr.nextLine());
      }
      //acct.setInitialAmount(scanDouble.nextDouble());
      acct.setInitialAmount(x);
      //acct.setInitialAmount(Double.parseDouble(argv[5]));
      System.out.println("Thank you for the information, " + acct.getName() + " You currently have "
              + acct.getInitialAmount() + " dollars in your account.");

      withAccount_Has(acct);
      withBank_Has(user);
      user.withAccount_Has(acct);


   }


   //==========================================================================
   public void logIn(  )
   {

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

      Scanner input = new Scanner(System.in);
      double amt;

      System.out.println("\n");
      System.out.print("Please enter the deposit amount:"); //prompt user for amount
      amt = input.nextDouble(); //read deposit amount

      acct.deposit(amt); //deposit amount into account
      System.out.println();
      System.out.println("Your remaining balance: $"+acct.getAccountBalance()); //print balance
   }


   //==========================================================================
   public void makeWithdrawal(  )
   {
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
}
