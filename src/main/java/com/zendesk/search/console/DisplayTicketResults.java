package com.zendesk.search.console;

import com.google.inject.Inject;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by @author Sankash on 5/18/2019 This class writes the Ticket Search results to the
 * console
 */
public class DisplayTicketResults extends Display<ZdSearchResults> {
  @Inject private ZdDatabase zdDatabase;

  /**
   * This displays the Org Search results on the console
   *
   * @param result the result to the printed on the console
   */
  @Override
  public void show(ZdSearchResults result) {
    System.out.println(Menu.HEADER);
    System.out.println(String.format("\n%s", StringUtils.repeat("-", 100)));
    System.out.println("Tickets");
    result
        .getTicketSearchResult()
        .forEach(
            p -> {
              try {
                displayObjectFields(p.getTicket());
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              }
              System.out.println(String.format("\n%s", StringUtils.repeat("-", 100)));
            });
    System.out.println(Menu.FOOTER);
  }
}
