package swe443.bluebank.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import swe443.bluebank.Account;

public class AccountPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AccountPO(new Account[]{});
      } else {
          return new AccountPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return swe443.bluebank.util.CreatorCreator.createIdMap(sessionID);
   }
}
