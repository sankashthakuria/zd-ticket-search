package com.zendesk.search.console;

import com.google.inject.Inject;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.apache.commons.lang3.StringUtils;

/** Created by @author Sankash on 5/18/2019 This displays the Org Search results on the console */
public class DisplayOrganizationResults extends Display<ZdSearchResults> {
  @Inject private ZdDatabase zdDatabase;

  /**
   * This displays the Org Search results on the console
   *
   * @param result the result to the printed on the console
   */
  @Override
  public void show(ZdSearchResults result) {
    System.out.println(Menu.HEADER);
    result
        .getOraganizationSearchResult()
        .forEach(
            p -> {
              System.out.println(String.format("\n%s", StringUtils.repeat("-", 100)));
              System.out.println("Organization\n");
              try {
                displayObjectFields(p.getOrg());
              } catch (IllegalAccessException e) {
                e.printStackTrace();
              }
              System.out.println(String.format("\n\tUsers\n"));
              p.getUsers()
                  .forEach(
                      x ->
                          System.out.println(
                              String.format(
                                  "\tName: %-20.20s\t|\tEmail: %-25.25s\t|\tPhone: %-20.20s",
                                  zdDatabase.getUserIndex().get(x).getName(),
                                  zdDatabase.getUserIndex().get(x).getEmail(),
                                  zdDatabase.getUserIndex().get(x).getPhone())));
              System.out.println(String.format("\n\t\tTickets\n"));
              p.getTickets()
                  .forEach(
                      y ->
                          System.out.println(
                              String.format(
                                  "\t\tId: %s\t|\tSubject: %s", y.getId(), y.getSubject())));
            });
    System.out.println(String.format("\n%s", StringUtils.repeat("-", 100)));
    System.out.println(String.format("\n%s", Menu.FOOTER));
  }
}
