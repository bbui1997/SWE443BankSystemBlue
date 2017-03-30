package swe443.bluebank.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import swe443.bluebank.Bank;

public class BankPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BankPO(new Bank[]{});
      } else {
          return new BankPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return swe443.bluebank.util.CreatorCreator.createIdMap(sessionID);
   }
}
