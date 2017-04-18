/*
   Copyright (c) 2017 Spade
   
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
    * 
    * @see <a href='../../../../../src/main/java/Model.java'>Model.java</a>
 */
   public  class Transaction implements SendableEntity
{
    public enum Type{deposit,withdraw,transfer,account};  //enum to compare type of transaction
    private Account acct1 = null; //primary account involved in transaction
    private Account acct2 = null; //secondary account involved in transaction
    private Double fee; //fee of the transaction
    private boolean undo = false; //the type of transaction to undo



   //==========================================================================

    /**
     * Builds the log to be written to the primary and/or secondary account
     * @param type String containing the type of transaction (deposit,withdraw,transfer,account,modiyf)
     * @return String object of the log to be written to the file.
     */
    private String logbuilder(Type type)
   {
       Date currentDate = new Date();
       SimpleDateFormat formatDate = new SimpleDateFormat("MM-dd-yyyy HH:mm");

       StringBuilder log = new StringBuilder();
       if(undo==false){
           switch (type){
               case deposit:
                   log.append("Deposited "+getAmount()+" /"+formatDate.format(currentDate));
                   break;
               case withdraw:
                   log.append("Withdrew "+getAmount()+" /"+formatDate.format(currentDate));
                   break;
               case transfer:
                   log.append("Transfer "+getAmount()+" to "+acct2.getName()+"'s account /"+formatDate.format(currentDate));
                   break;
               case account:
                   log.append(acct1.getName()+" opened an account.");
                   break;
           }
       }else{ //add undo transaction to the log
           switch (type) {
               case deposit:
                   log.append("UNDONE - DEPOSIT " + this.getAmount() + "/" + formatDate.format(currentDate));
                   break;
               case withdraw:
                   log.append("UNDONE - WITHDRAW " + this.getAmount() + "/" + formatDate.format(currentDate));
                   break;
               case transfer:
                   log.append("UNDONE - TRANSFER " + this.getAmount() + " to account " + this.acct2 + "/" + formatDate.format(currentDate));
                   break;
           }
       }

       if((this.fee > 0) && (undo==true)){ //log for fee reversal
           log.append("UNDONE FEE "+this.fee);
       }else if(this.fee >0){ //log for fee associated with transaction
           log.append("Fee for "+type+":"+this.fee);
       }
       return log.toString(); //return log string
   }

    /**
     * Writes the transaction type to the logfile of a specified account or accounts in the case of a transfer.
     * @param type specifies the type of transaction (deposit,withdraw,transfer,account,modify)
     * @param acct1 Account object one for the primary account to write the log
     * @param acct2 Account object two (can be null) to specify the account to write the log in the event of a transfer
     * @param amt int specify the amount of money involved in the transaction
     */
   public void writeLog(Type type,Account acct1,Account acct2,double amt,double fee,boolean undo){
       this.acct1 = acct1; //set the primary account
       this.acct2 = acct2; //set the secondary account
       this.setAmount(amt); //set the amount invovled in transaction
       this.fee = fee; //set fee of transaction
       this.undo = undo; //set undo flag (true or false)

       String log = this.logbuilder(type); //call the logbuilder to build the log

       /**
        * 1. Get the log accounts logfile; create a new one if doesn't exist
        * 2. Write log to file
        * 3. Close file
        */

       if(type.equals(Type.transfer)){
           //log to the second account
       }

       //set file name
       File file = new File("src/logs/"+this.acct1.getName().toString()+"_log");

       //create/retrieve log file with account name
       try(BufferedWriter write2file = new BufferedWriter(new FileWriter(file,true))){
           //write log to file
           write2file.write(log);
           write2file.newLine();
           write2file.close();

       }catch (Exception e){
           System.out.print("Exception during file write in writeLog function:"+e);
       }
   }

    //not used; use logbuilder(Type type)
    public String logbuilder() {
        return null;
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
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_AMOUNT = "amount";
   
   private double amount;

   public double getAmount()
   {
      return this.amount;
   }
   
   public void setAmount(double value)
   {
      if (this.amount != value) {
      
         double oldValue = this.amount;
         this.amount = value;
         this.firePropertyChange(PROPERTY_AMOUNT, oldValue, value);
      }
   }
   
   public Transaction withAmount(int value)
   {
      setAmount(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getAmount());
      return result.substring(1);
   }

}
