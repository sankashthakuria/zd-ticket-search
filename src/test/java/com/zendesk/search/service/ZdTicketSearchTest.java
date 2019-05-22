package com.zendesk.search.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zendesk.search.AppModule;
import com.zendesk.search.model.results.ZdSearchResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Created by @author Sankash on 5/19/2019 */
class ZdTicketSearchTest {

  Injector i = Guice.createInjector(new AppModule());
  ZdTicketSearch ticketSearch;

  @BeforeEach
  void setup() {
    ticketSearch = i.getInstance(ZdTicketSearch.class);
  }

  @Test
  void search_results_contains_the_ticket_searched() {
    ZdSearchResults search = ticketSearch.search("_id", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
    assertNotNull(search);
    assertEquals(
        true,
        search.getTicketSearchResult().stream()
            .anyMatch(p -> p.getTicket().getId().equals("436bf9b0-1147-4c0a-8439-6f79833bff5b")));
  }

  @Test
  void search_results_contains_the_ticket_assigned() {
    ZdSearchResults search = ticketSearch.search("_id", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
    assertNotNull(search);
    assertNotEquals(
        true, search.getTicketSearchResult().stream().map(p -> p.getSubmittedByUser()).count());
  }

  @Test
  void search_results_contains_the_ticket_submitted() {
    ZdSearchResults search = ticketSearch.search("_id", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
    assertNotNull(search);
    assertEquals(
        true,
        search.getTicketSearchResult().stream()
            .anyMatch(p -> p.getTicket().getId().equals("436bf9b0-1147-4c0a-8439-6f79833bff5b")));
  }

  @Test
  void search_results_for_non_existing_field_value_should_return_empty() {
    assertEquals(0, ticketSearch.search("_id", "999").getTicketSearchResult().size());
  }

  @Test
  void search_results_for_non_existing_field_name_should_return_exception() {
    assertThrows(NullPointerException.class, () -> ticketSearch.search("test", "0"));
  }
}
