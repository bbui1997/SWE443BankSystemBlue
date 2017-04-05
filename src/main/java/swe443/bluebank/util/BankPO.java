package swe443.bluebank.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.bluebank.Bank;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.bluebank.util.AccountPO;
import swe443.bluebank.Account;
import swe443.bluebank.util.BankPO;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.util.UserPO;
import swe443.bluebank.User;
import swe443.bluebank.util.UserSet;

public class BankPO extends PatternObject<BankPO, Bank>
{

    public BankSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BankSet matches = new BankSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Bank) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BankPO(){
      newInstance(null);
   }

   public BankPO(Bank... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BankPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public Object mainMenu()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) getCurrentMatch()).mainMenu();
      }
      return null;
   }

   
   //==========================================================================
   
   public void createAccount()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Bank) getCurrentMatch()).createAccount();
      }
   }

   
   //==========================================================================
   
   public void logIn()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Bank) getCurrentMatch()).logIn();
      }
   }

   
   //==========================================================================
   
   public void makeDeposit()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Bank) getCurrentMatch()).makeDeposit();
      }
   }

   
   //==========================================================================
   
   public void makeWithdrawal()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Bank) getCurrentMatch()).makeWithdrawal();
      }
   }

   public BankPO createBankNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_BANKNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createBankNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_BANKNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BankPO createBankNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Bank.PROPERTY_BANKNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getBankName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) getCurrentMatch()).getBankName();
      }
      return null;
   }
   
   public BankPO withBankName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Bank) getCurrentMatch()).setBankName(value);
      }
      return this;
   }
   
   public AccountPO createAccount_HasPO()
   {
      AccountPO result = new AccountPO(new Account[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_ACCOUNT_HAS, result);
      
      return result;
   }

   public AccountPO createAccount_HasPO(String modifier)
   {
      AccountPO result = new AccountPO(new Account[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bank.PROPERTY_ACCOUNT_HAS, result);
      
      return result;
   }

   public BankPO createAccount_HasLink(AccountPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_ACCOUNT_HAS);
   }

   public BankPO createAccount_HasLink(AccountPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_ACCOUNT_HAS, modifier);
   }

   public AccountSet getAccount_Has()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getAccount_Has();
      }
      return null;
   }

   public UserPO createBank_HasPO()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_BANK_HAS, result);
      
      return result;
   }

   public UserPO createBank_HasPO(String modifier)
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bank.PROPERTY_BANK_HAS, result);
      
      return result;
   }

   public BankPO createBank_HasLink(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_BANK_HAS);
   }

   public BankPO createBank_HasLink(UserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_BANK_HAS, modifier);
   }

   public UserSet getBank_Has()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getBank_Has();
      }
      return null;
   }

   
   //==========================================================================
   
   public void viewBalance()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Bank) getCurrentMatch()).viewBalance();
      }
   }

   
   //==========================================================================
   
   public void makeTransfer()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Bank) getCurrentMatch()).makeTransfer();
      }
   }

   public UserPO createUser is inPO()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bank.PROPERTY_USER IS IN, result);
      
      return result;
   }

   public UserPO createUser is inPO(String modifier)
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bank.PROPERTY_USER IS IN, result);
      
      return result;
   }

   public BankPO createUser is inLink(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_USER IS IN);
   }

   public BankPO createUser is inLink(UserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bank.PROPERTY_USER IS IN, modifier);
   }

   public UserSet getUser is in()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bank) this.getCurrentMatch()).getUser is in();
      }
      return null;
   }

}
