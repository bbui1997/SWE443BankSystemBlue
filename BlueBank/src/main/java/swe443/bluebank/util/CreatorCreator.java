package swe443.bluebank.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new AccountCreator());
      jsonIdMap.with(new AccountPOCreator());
      jsonIdMap.with(new BankCreator());
      jsonIdMap.with(new BankPOCreator());
      jsonIdMap.with(new UserCreator());
      jsonIdMap.with(new UserPOCreator());
      return jsonIdMap;
   }
}
