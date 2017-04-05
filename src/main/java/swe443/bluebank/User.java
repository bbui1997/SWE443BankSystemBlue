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
import de.uniks.networkparser.EntityUtil;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.Account;
import swe443.bluebank.Bank;
/**
 *
 * @see <a href='../../../../../src/main/java/model.java'>model.java</a>
 * @see <a href='../../../../../src/main/java/Model.java'>Model.java</a>
 */
public  class User implements SendableEntity
{


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
      setUser_Has(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }


   //==========================================================================

   public static final String PROPERTY_USERNAME = "userName";

   private String userName;

   public String getUserName()
   {
      return this.userName;
   }

   public void setUserName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.userName, value)) {

         String oldValue = this.userName;
         this.userName = value;
         this.firePropertyChange(PROPERTY_USERNAME, oldValue, value);
      }
   }

   public User withUserName(String value)
   {
      setUserName(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getUserName());
      return result.substring(1);
   }



   /********************************************************************
    * <pre>
    *              one                       many
    * User ----------------------------------- Account
    *              User_Has                   Account_Has
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

   public User withAccount_Has(Account... value)
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
               item.withUser_Has(this);
               firePropertyChange(PROPERTY_ACCOUNT_HAS, null, item);
            }
         }
      }
      return this;
   }

   public User withoutAccount_Has(Account... value)
   {
      for (Account item : value)
      {
         if ((this.Account_Has != null) && (item != null))
         {
            if (this.Account_Has.remove(item))
            {
               item.setUser_Has(null);
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
    *              many                       one
    * User ----------------------------------- Bank
    *              Bank_Has                   User_Has
    * </pre>
    */

   public static final String PROPERTY_USER_HAS = "User_Has";

   private Bank User_Has = null;

   public Bank getUser_Has()
   {
      return this.User_Has;
   }

   public boolean setUser_Has(Bank value)
   {
      boolean changed = false;

      if (this.User_Has != value)
      {
         Bank oldValue = this.User_Has;

         if (this.User_Has != null)
         {
            this.User_Has = null;
            oldValue.withoutBank_Has(this);
         }

         this.User_Has = value;

         if (value != null)
         {
            value.withBank_Has(this);
         }

         firePropertyChange(PROPERTY_USER_HAS, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public User withUser_Has(Bank value)
   {
      setUser_Has(value);
      return this;
   }

   public Bank createUser_Has()
   {
      Bank value = new Bank();
      withUser_Has(value);
      return value;
   }

}
