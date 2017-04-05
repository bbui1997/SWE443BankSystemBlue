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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import swe443.bluebank.Bank;
import de.uniks.networkparser.IdMap;
import swe443.bluebank.Account;
import swe443.bluebank.User;

public class BankCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Bank.PROPERTY_BANKNAME,
      Bank.PROPERTY_ACCOUNT_HAS,
      Bank.PROPERTY_BANK_HAS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Bank();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Bank.PROPERTY_BANKNAME.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getBankName();
      }

      if (Bank.PROPERTY_ACCOUNT_HAS.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getAccount_Has();
      }

      if (Bank.PROPERTY_BANK_HAS.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getBank_Has();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Bank.PROPERTY_BANKNAME.equalsIgnoreCase(attrName))
      {
         ((Bank) target).setBankName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Bank.PROPERTY_ACCOUNT_HAS.equalsIgnoreCase(attrName))
      {
         ((Bank) target).withAccount_Has((Account) value);
         return true;
      }
      
      if ((Bank.PROPERTY_ACCOUNT_HAS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Bank) target).withoutAccount_Has((Account) value);
         return true;
      }

      if (Bank.PROPERTY_BANK_HAS.equalsIgnoreCase(attrName))
      {
         ((Bank) target).withBank_Has((User) value);
         return true;
      }
      
      if ((Bank.PROPERTY_BANK_HAS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Bank) target).withoutBank_Has((User) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return swe443.bluebank.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Bank) entity).removeYou();
   }
}
