package swe443.bluebank.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.bluebank.Account;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.bluebank.util.UserPO;
import swe443.bluebank.User;
import swe443.bluebank.util.AccountPO;
import swe443.bluebank.util.BankPO;
import swe443.bluebank.Bank;

public class AccountPO extends PatternObject<AccountPO, Account>
{

    public AccountSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AccountSet matches = new AccountSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Account) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AccountPO(){
      newInstance(null);
   }

   public AccountPO(Account... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public AccountPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void deposit(double value)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).deposit(value);
      }
   }

   
   //==========================================================================
   
   public double withdraw(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).withdraw(value);
      }
      return 0;
   }

   
   //==========================================================================
   
   public void undoRecentTransaction()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).undoRecentTransaction();
      }
   }

   
   //==========================================================================
   
   public void transfer(double value, Account acct)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).transfer(value, acct);
      }
   }

   public AccountPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AccountPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public AccountPO createSsnCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_SSN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createSsnCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_SSN)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createSsnAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_SSN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getSsn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getSsn();
      }
      return 0;
   }
   
   public AccountPO withSsn(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setSsn(value);
      }
      return this;
   }
   
   public AccountPO createDobCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_DOB)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createDobAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_DOB)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Object getDob()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getDob();
      }
      return null;
   }
   
   public AccountPO withDob(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setDob(value);
      }
      return this;
   }
   
   public AccountPO createUsernameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createUsernameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_USERNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createUsernameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getUsername()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getUsername();
      }
      return null;
   }
   
   public AccountPO withUsername(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setUsername(value);
      }
      return this;
   }
   
   public AccountPO createPasswordCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createPasswordCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_PASSWORD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createPasswordAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getPassword()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getPassword();
      }
      return null;
   }
   
   public AccountPO withPassword(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setPassword(value);
      }
      return this;
   }
   
   public AccountPO createInitialAmountCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_INITIALAMOUNT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createInitialAmountCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_INITIALAMOUNT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createInitialAmountAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_INITIALAMOUNT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getInitialAmount()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getInitialAmount();
      }
      return 0;
   }
   
   public AccountPO withInitialAmount(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setInitialAmount(value);
      }
      return this;
   }
   
   public AccountPO createAccountBalanceCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ACCOUNTBALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createAccountBalanceCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ACCOUNTBALANCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createAccountBalanceAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ACCOUNTBALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getAccountBalance()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getAccountBalance();
      }
      return 0;
   }
   
   public AccountPO withAccountBalance(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setAccountBalance(value);
      }
      return this;
   }
   
   public UserPO createUser_HasPO()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_USER_HAS, result);
      
      return result;
   }

   public UserPO createUser_HasPO(String modifier)
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_USER_HAS, result);
      
      return result;
   }

   public AccountPO createUser_HasLink(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_USER_HAS);
   }

   public AccountPO createUser_HasLink(UserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_USER_HAS, modifier);
   }

   public User getUser_Has()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getUser_Has();
      }
      return null;
   }

   public BankPO createBank_hasPO()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_BANK_HAS, result);
      
      return result;
   }

   public BankPO createBank_hasPO(String modifier)
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_BANK_HAS, result);
      
      return result;
   }

   public AccountPO createBank_hasLink(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_BANK_HAS);
   }

   public AccountPO createBank_hasLink(BankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_BANK_HAS, modifier);
   }

   public Bank getBank_has()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getBank_has();
      }
      return null;
   }

   
   //==========================================================================
   
   public void deposit()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).deposit();
      }
   }

   
   //==========================================================================
   
   public double withdraw()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).withdraw();
      }
      return 0;
   }

   
   //==========================================================================
   
   public void transfer()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).transfer();
      }
   }

}
