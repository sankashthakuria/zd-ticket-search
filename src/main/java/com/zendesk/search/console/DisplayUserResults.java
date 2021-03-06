package com.zendesk.search.console;

import com.google.inject.Inject;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.apache.commons.lang3.StringUtils;

/** Created by @author Sankash on 5/18/2019 This class displays the User Search results */
public class DisplayUserResults extends Display<ZdSearchResults> {

  @Inject private ZdDatabase zdDatabase;

  /**
   * This method displays the User Search results
   *
   * @param result the result to the printed on the console
   */
  @Override
  public void show(ZdSearchResults result) {
    System.out.println(Menu.HEADER);
    System.out.println(String.format("\n%s", StringUtils.repeat("-", 100)));
    result.getUserSearchResult().stream()
        .forEach(
            p -> {
              System.out.println(String.format("%s\n", "User"));
              try {
                displayObjectFields(p.getUser());
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              }
              System.out.println(String.format("\n%s", "Tickets Assigned"));
              p.getTicketsAssigned().stream()
                  .forEach(
                      n ->
                          System.out.println(
                              String.format(
                                  "\tAssigned Tickets: Ticket Id: %s, Ticket Subject: %s",
                                  n.getId(), n.getSubject())));
              System.out.println(String.format("\n%s", "Tickets submitted"));
              p.getTicketsSubmitted().stream()
                  .forEach(
                      x ->
                          System.out.println(
                              String.format(
                                  "\tSubmitted Tickets: Ticket Id: %s, Ticket Subject: %s",
                                  x.getId(), x.getSubject())));
              System.out.println(StringUtils.repeat("-", 150));
            });
    System.out.println(Menu.FOOTER);
  }
}
