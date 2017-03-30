package swe443.bluebank.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import swe443.bluebank.User;

public class UserPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UserPO(new User[]{});
      } else {
          return new UserPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return swe443.bluebank.util.CreatorCreator.createIdMap(sessionID);
   }
}
