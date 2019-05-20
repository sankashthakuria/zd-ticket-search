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
public class ZdTicketSearch implements Search {
    @Inject
    private ZdDatabase zdDatabase;

    /**
     * Search Tickets with field name and field value
     *
     * @param fieldName  the name of the field being searched for
     * @param fieldValue the value of the field being searched for
     * @return search results
     */
    @SuppressWarnings("unchecked")
    @Override
    public ZdSearchResults search(String fieldName, String fieldValue) {
        List<ZdSearchResults.TicketSearchResult> ticketSearchResults = getAllTickets();
        List<ZdSearchResults.TicketSearchResult> results = ticketSearchResults.stream()
                .map(p -> Pair.of(p.getTicket().getId(), p))
                .map(p -> {
                    Field name = null;
                    try {
                        name = p.getRight().getTicket().getClass().getDeclaredField(zdDatabase.getTicketFieldMap().get(fieldName));
                        name.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                    }
                    Object o = null;
                    try {
                        o = name.get(p.getRight().getTicket());
                    } catch (IllegalAccessException e) {
                    }
                    return Pair.of(o, p.getRight());
                })
                .filter(car -> Objects.nonNull(car.getLeft()))
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
                .map(Pair::getRight)
                .collect(Collectors.toList());
        return ZdSearchResults.builder()
                .ticketSearchResult(results)
                .build();
    }

    /**
     * Gets all the tickets from the database
     *
     * @return ticket references
     */
    private List<ZdSearchResults.TicketSearchResult> getAllTickets() {
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
