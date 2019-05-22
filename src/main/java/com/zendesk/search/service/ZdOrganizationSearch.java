package com.zendesk.search.service;

import com.google.inject.Inject;
import com.zendesk.search.model.data.User;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Created by @author Sankash on 5/16/2019 */
public class ZdOrganizationSearch implements Search {
  @Inject private ZdDatabase zdDatabase;

  @Inject private ZdTicketSearch zdTicketSearch;

  /**
   * Searches the Organization data with the field name and field value
   *
   * @param fieldName name of the field being searched
   * @param fieldValue value of the field being searched
   * @return returns the search output
   */
  @Override
  public ZdSearchResults search(String fieldName, String fieldValue) {
    List<ZdSearchResults.TicketSearchResult> ticketSearchResults = zdTicketSearch.getAllTickets();
    List<ZdSearchResults.OraganizationSearchResult> oraganizationSearchResults =
        getRelatedOrgs(ticketSearchResults);
    List<ZdSearchResults.OraganizationSearchResult> collect =
        oraganizationSearchResults.stream()
            .map(p -> Pair.of(p.getOrg().getId(), p))
            .map(
                p -> {
                  Field name = null;
                  try {
                    name =
                        p.getRight()
                            .getOrg()
                            .getClass()
                            .getDeclaredField(zdDatabase.getOrgFieldMap().get(fieldName));
                    name.setAccessible(true);
                  } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                  }
                  Object o = null;
                  try {
                    o = name.get(p.getRight().getOrg());
                  } catch (IllegalAccessException e) {
                    e.printStackTrace();
                  }
                  return Pair.of(o, p.getRight());
                })
            .filter(
                x -> {
                  if (x.getLeft() instanceof ArrayList) {
                    ArrayList<String> left = (ArrayList<String>) x.getLeft();
                    return left.contains(fieldValue);
                  } else if (x.getLeft() instanceof Boolean) {
                    return Boolean.parseBoolean(Boolean.toString((Boolean) x.getLeft()));
                  } else {
                    return x.getLeft().equals(fieldValue);
                  }
                })
            .map(Pair::getRight)
            .collect(Collectors.toList());
    return ZdSearchResults.builder().oraganizationSearchResult(collect).build();
  }

  /**
   * Builds the relationship between Organiation and Tickets and Users
   *
   * @param ticketSearchResults list of tickets
   * @return returns organization data with related tickets and users
   */
  private List<ZdSearchResults.OraganizationSearchResult> getRelatedOrgs(
      List<ZdSearchResults.TicketSearchResult> ticketSearchResults) {
    List<ZdSearchResults.OraganizationSearchResult> oraganizationSearchResults = new ArrayList<>();
    zdDatabase
        .getOrganizations()
        .forEach(
            organization -> {
              List<ZdSearchResults.TicketReference> collect1 =
                  ticketSearchResults.stream()
                      .filter(
                          ticket ->
                              ticket
                                  .getOrganizationName()
                                  .equals(Optional.of(organization.getId())))
                      .map(
                          ticket ->
                              ZdSearchResults.TicketReference.builder()
                                  .id(ticket.getTicket().getId())
                                  .subject(ticket.getTicket().getSubject())
                                  .build())
                      .collect(Collectors.toList());
              List<String> collect2 =
                  zdDatabase.getUsers().stream()
                      .filter(
                          user ->
                              Optional.ofNullable(user.getOrganizationId())
                                  .equals(Optional.of(organization.getId())))
                      .map(User::getId)
                      .collect(Collectors.toList());
              oraganizationSearchResults.add(
                  ZdSearchResults.OraganizationSearchResult.builder()
                      .org(organization)
                      .tickets(collect1)
                      .users(collect2)
                      .build());
            });
    return oraganizationSearchResults;
  }
}
