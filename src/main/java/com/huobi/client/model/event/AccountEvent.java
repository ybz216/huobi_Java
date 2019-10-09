package com.huobi.client.model.event;

import com.huobi.client.impl.AccountsInfoMap;
import com.huobi.client.impl.RestApiJsonParser;
import com.huobi.client.impl.utils.JsonWrapper;
import com.huobi.client.impl.utils.JsonWrapperArray;
import com.huobi.client.impl.utils.TimeService;
import com.huobi.client.model.AccountChange;
import com.huobi.client.model.enums.AccountChangeType;
import com.huobi.client.model.enums.BalanceType;

import java.util.LinkedList;
import java.util.List;

/**
 * The account change information received by subscription of account.
 */
public class AccountEvent {

  private long timestamp = 0;
  private AccountChangeType changeType = AccountChangeType.INVALID;
  private List<AccountChange> accountChangeList = new LinkedList<>();

  /**
   * Get the UNIX formatted timestamp generated by server in UTC.
   *
   * @return The timestamp.
   */
  public long getTimestamp() {
    return timestamp;
  }

  /**
   * The event that asset change notification related.
   *
   * @return The change type, see {@link AccountChangeType}
   */
  public AccountChangeType getChangeType() {
    return changeType;
  }

  /**
   * Get the detail of account change.
   *
   * @return The list of account change, see {@link AccountChange}
   */
  public List<AccountChange> getData() {
    return accountChangeList;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public void setChangeType(AccountChangeType changeType) {
    this.changeType = changeType;
  }

  public void setData(List<AccountChange> accountChangeList) {
    this.accountChangeList = accountChangeList;
  }

  public static RestApiJsonParser<AccountEvent> getParser(String apiKey){
    return (jsonWrapper) -> {
      AccountEvent accountEvent = new AccountEvent();
      accountEvent.setTimestamp(
          TimeService.convertCSTInMillisecondToUTC(jsonWrapper.getLong("ts")));
      JsonWrapper data = jsonWrapper.getJsonObject("data");
      accountEvent.setChangeType(AccountChangeType.lookup(data.getString("event")));
      JsonWrapperArray listArray = data.getJsonArray("list");
      List<AccountChange> accountChangeList = new LinkedList<>();
      listArray.forEach((itemInList) -> {
        accountChangeList.add(AccountChange.parse(itemInList,apiKey));
      });
      accountEvent.setData(accountChangeList);
      return accountEvent;
    };
  }


}
