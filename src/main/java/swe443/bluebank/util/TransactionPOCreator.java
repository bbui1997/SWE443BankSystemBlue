package swe443.bluebank.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import swe443.bluebank.Transaction;

public class TransactionPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TransactionPO(new Transaction[]{});
      } else {
          return new TransactionPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return swe443.bluebank.util.CreatorCreator.createIdMap(sessionID);
   }
}
