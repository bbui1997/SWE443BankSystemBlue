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
   
package swe443.bluebank.util;

import de.uniks.networkparser.list.SimpleSet;
import swe443.bluebank.Bank;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import java.util.LinkedHashSet;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.Account;
import swe443.bluebank.util.UserSet;
import swe443.bluebank.User;

public class BankSet extends SimpleSet<Bank>
{
	protected Class<?> getTypClass() {
		return Bank.class;
	}

   public BankSet()
   {
      // empty
   }

   public BankSet(Bank... objects)
   {
      for (Bank obj : objects)
      {
         this.add(obj);
      }
   }

   public BankSet(Collection<Bank> objects)
   {
      this.addAll(objects);
   }

   public static final BankSet EMPTY_SET = new BankSet().withFlag(BankSet.READONLY);


   public BankPO createBankPO()
   {
      return new BankPO(this.toArray(new Bank[this.size()]));
   }


   public String getEntryType()
   {
      return "swe443.bluebank.Bank";
   }


   @Override
   public BankSet getNewList(boolean keyValue)
   {
      return new BankSet();
   }


   public BankSet filter(Condition<Bank> condition) {
      BankSet filterList = new BankSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public BankSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Bank>)value);
      }
      else if (value != null)
      {
         this.add((Bank) value);
      }
      
      return this;
   }
   
   public BankSet without(Bank value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public LinkedHashSet<Object> mainMenu()
   {
      
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (Bank obj : this)
      {
         result.add( obj.mainMenu() );
      }
      return result;
   }

   
   //==========================================================================
   
   public BankSet createAccount()
   {
      return BankSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public BankSet logIn()
   {
      return BankSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public BankSet makeDeposit()
   {
      return BankSet.EMPTY_SET;
   }

   
   //==========================================================================
   
   public BankSet makeWithdrawal()
   {
      return BankSet.EMPTY_SET;
   }


   /**
    * Loop through the current set of Bank objects and collect a list of the bankName attribute values. 
    * 
    * @return List of String objects reachable via bankName attribute
    */
   public ObjectSet getBankName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Bank obj : this)
      {
         result.add(obj.getBankName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the bankName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet filterBankName(String value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value.equals(obj.getBankName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the bankName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet filterBankName(String lower, String upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower.compareTo(obj.getBankName()) <= 0 && obj.getBankName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and assign value to the bankName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Bank objects now with new attribute values.
    */
   public BankSet withBankName(String value)
   {
      for (Bank obj : this)
      {
         obj.setBankName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Bank objects and collect a set of the Account objects reached via Account_Has. 
    * 
    * @return Set of Account objects reachable via Account_Has
    */
   public AccountSet getAccount_Has()
   {
      AccountSet result = new AccountSet();
      
      for (Bank obj : this)
      {
         result.with(obj.getAccount_Has());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Bank objects and collect all contained objects with reference Account_Has pointing to the object passed as parameter. 
    * 
    * @param value The object required as Account_Has neighbor of the collected results. 
    * 
    * @return Set of Account objects referring to value via Account_Has
    */
   public BankSet filterAccount_Has(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      BankSet answer = new BankSet();
      
      for (Bank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getAccount_Has()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Bank object passed as parameter to the Account_Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Account_Has attributes.
    */
   public BankSet withAccount_Has(Account value)
   {
      for (Bank obj : this)
      {
         obj.withAccount_Has(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Bank object passed as parameter from the Account_Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BankSet withoutAccount_Has(Account value)
   {
      for (Bank obj : this)
      {
         obj.withoutAccount_Has(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Bank objects and collect a set of the User objects reached via Bank_Has. 
    * 
    * @return Set of User objects reachable via Bank_Has
    */
   public UserSet getBank_Has()
   {
      UserSet result = new UserSet();
      
      for (Bank obj : this)
      {
         result.with(obj.getBank_Has());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Bank objects and collect all contained objects with reference Bank_Has pointing to the object passed as parameter. 
    * 
    * @param value The object required as Bank_Has neighbor of the collected results. 
    * 
    * @return Set of User objects referring to value via Bank_Has
    */
   public BankSet filterBank_Has(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      BankSet answer = new BankSet();
      
      for (Bank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBank_Has()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Bank object passed as parameter to the Bank_Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bank_Has attributes.
    */
   public BankSet withBank_Has(User value)
   {
      for (Bank obj : this)
      {
         obj.withBank_Has(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Bank object passed as parameter from the Bank_Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BankSet withoutBank_Has(User value)
   {
      for (Bank obj : this)
      {
         obj.withoutBank_Has(value);
      }
      
      return this;
   }

}
