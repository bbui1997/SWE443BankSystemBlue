package swe443.bluebank.util;

import org.sdmlib.models.pattern.PatternObject;
import swe443.bluebank.User;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import swe443.bluebank.util.AccountPO;
import swe443.bluebank.Account;
import swe443.bluebank.util.UserPO;
import swe443.bluebank.util.AccountSet;
import swe443.bluebank.util.BankPO;
import swe443.bluebank.Bank;

public class UserPO extends PatternObject<UserPO, User>
{

    public UserSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UserSet matches = new UserSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((User) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UserPO(){
      newInstance(null);
   }

   public UserPO(User... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UserPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UserPO createUserNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserPO createUserNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_USERNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserPO createUserNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getUserName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((User) getCurrentMatch()).getUserName();
      }
      return null;
   }
   
   public UserPO withUserName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((User) getCurrentMatch()).setUserName(value);
      }
      return this;
   }
   
   public AccountPO createAccount_HasPO()
   {
      AccountPO result = new AccountPO(new Account[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(User.PROPERTY_ACCOUNT_HAS, result);
      
      return result;
   }

   public AccountPO createAccount_HasPO(String modifier)
   {
      AccountPO result = new AccountPO(new Account[]{});
      
      result.setModifier(modifier);
      super.hasLink(User.PROPERTY_ACCOUNT_HAS, result);
      
      return result;
   }

   public UserPO createAccount_HasLink(AccountPO tgt)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_ACCOUNT_HAS);
   }

   public UserPO createAccount_HasLink(AccountPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_ACCOUNT_HAS, modifier);
   }

   public AccountSet getAccount_Has()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((User) this.getCurrentMatch()).getAccount_Has();
      }
      return null;
   }

   public BankPO createUser_HasPO()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(User.PROPERTY_USER_HAS, result);
      
      return result;
   }

   public BankPO createUser_HasPO(String modifier)
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(modifier);
      super.hasLink(User.PROPERTY_USER_HAS, result);
      
      return result;
   }

   public UserPO createUser_HasLink(BankPO tgt)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_USER_HAS);
   }

   public UserPO createUser_HasLink(BankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_USER_HAS, modifier);
   }

   public Bank getUser_Has()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((User) this.getCurrentMatch()).getUser_Has();
      }
      return null;
   }

}
