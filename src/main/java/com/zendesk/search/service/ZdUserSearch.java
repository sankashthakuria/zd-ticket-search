package com.zendesk.search.service;

import com.google.inject.Inject;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by @author Sankash on 5/17/2019
 */
public class ZdUserSearch implements Search {
    @Inject
    private ZdDatabase zdDatabase;

    /**
     * Searches user with the specified field name and field value
     *
     * @param fieldName  the name of the field being searched for
     * @param fieldValue the value of the field being searched for
     * @return the search result
     */
    @Override
    public ZdSearchResults search(String fieldName, String fieldValue) {
        List<ZdSearchResults.TicketSearchResult> tickets = getAllTickets(zdDatabase);
        List<ZdSearchResults.UserSearchResult> users = getAllUsersWithRelatedTickets(tickets);
        List<ZdSearchResults.UserSearchResult> userSearchResults = users.stream()
                .map(p -> Pair.of(p.getUser().getId(), p))
                .map(p -> {
                    Field name = null;
                    try {
                        name = p.getRight().getUser().getClass().getDeclaredField(zdDatabase.getUserFieldMap().get(fieldName));
                        name.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                    }
                    Object o = null;
                    try {
                        o = name.get(p.getRight().getUser());
                    } catch (IllegalAccessException e) {
                    }
                    return Pair.of(o, p.getRight());
                })
                .filter(x -> {
                    if (x.getLeft() instanceof ArrayList) {
                        ArrayList<String> left = (ArrayList<String>) x.getLeft();
                        return left.contains(fieldValue);
                    } else if (x.getLeft() instanceof Boolean) {
                        return Boolean.parseBoolean(Boolean.toString((Boolean) x.getLeft()));
                    } else {
                        return x.getLeft().equals(fieldValue);
                    }
                })
                .filter(car -> Objects.nonNull(car.getLeft()))
                .map(x -> x.getRight())
                .collect(Collectors.toList());
        return ZdSearchResults.builder()
                .userSearchResult(userSearchResults)
                .build();
    }

    /**
     * Builds relationships between tickets and users
     *
     * @param ticketSearchResults list of tickets
     * @return the users and the related tickets
     */
    private List<ZdSearchResults.UserSearchResult> getAllUsersWithRelatedTickets(List<ZdSearchResults.TicketSearchResult> ticketSearchResults) {
        List<ZdSearchResults.UserSearchResult> userSearchResult = new ArrayList<>();
        zdDatabase.getUsers()
                .forEach(user -> {
                    List<ZdSearchResults.TicketReference> collect1 = ticketSearchResults.stream()
                            .filter(ticket -> Optional.of(user.getId()).equals(ticket.getSubmittedByUser()))
                            .map(ticket -> ZdSearchResults.TicketReference.builder()
                                    .id(ticket.getTicket().getId())
                                    .subject(ticket.getTicket().getSubject())
                                    .build())
                            .collect(Collectors.toList());
                    List<ZdSearchResults.TicketReference> collect2 = ticketSearchResults.stream()
                            .filter(ticket -> Optional.of(user.getId()).equals(ticket.getAssignedToUser()))
                            .map(ticket -> ZdSearchResults.TicketReference.builder()
                                    .id(ticket.getTicket().getId())
                                    .subject(ticket.getTicket().getSubject())
                                    .build())
                            .collect(Collectors.toList());
                    userSearchResult.add(
                            ZdSearchResults.UserSearchResult.builder()
                                    .user(user)
                                    .organizationName(Optional.ofNullable(user.getOrganizationId()))
                                    .ticketsSubmitted(collect1)
                                    .ticketsAssigned(collect2)
                                    .build()
                    );
                });
        return userSearchResult;
    }

    /**
     * Gets all the tickets from the database
     *
     * @param zdDatabase the data class that holds the data
     * @return ticket references
     */
    private List<ZdSearchResults.TicketSearchResult> getAllTickets(ZdDatabase zdDatabase) {
        return zdDatabase.getTickets().stream()
                .map(p -> ZdSearchResults.TicketSearchResult.builder()
                        .ticket(p)
                        .assignedToUser(Optional.ofNullable(p.getAssigneeId()))
                        .submittedByUser(Optional.ofNullable(p.getSubmitterId()))
                        .organizationName(Optional.ofNullable(p.getOrganizationId()))
                        .build())
                .collect(Collectors.toList());
    }
}
