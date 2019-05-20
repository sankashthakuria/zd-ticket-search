package com.zendesk.search.service;

import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by @author Sankash on 5/19/2019
 */
class ZdOrganizationSearchTest {

    private static ZdDatabase zdDatabase;
    ZdOrganizationSearch orgSearch;

    @BeforeAll
    static void setUp() throws Exception {
        zdDatabase = ZdDatabase.initialize();

    }

    @BeforeEach
    void setup() {
        orgSearch = new ZdOrganizationSearch();
        Whitebox.setInternalState(orgSearch, "zdDatabase", zdDatabase);
    }

    @Test
    void initialization_of_object_is_successful() {
        ZdSearchResults search = orgSearch.search("_id", "101");
        assertNotNull(search);
    }

    @Test
    void search_results_contains_the_org_searched() {
        ZdSearchResults search = orgSearch.search("_id", "101");
        assertNotNull(search);
        assertEquals("101", search.getOraganizationSearchResult().get(0).getOrg().getId());
    }

    @Test
    void search_results_works_for_arrays_such_as_domain_names() {
        ZdSearchResults search = orgSearch.search("domain_names", "kage.com");
        assertNotNull(search);
        assertEquals(true, search.getOraganizationSearchResult().get(0).getOrg().getDomainNames().contains("kage.com"));
    }

    @Test
    void search_results_works_for_boolean_such_as_shared_tickets() {
        ZdSearchResults search = orgSearch.search("shared_tickets", "false");
        assertNotNull(search);
        assertNotEquals(0, search.getOraganizationSearchResult().size());
    }

    @Test
    void search_results_contains_tickets() {
        ZdSearchResults search = orgSearch.search("_id", "101");
        assertNotNull(search);
        assertNotEquals(0, search.getOraganizationSearchResult().get(0).getTickets().size());
    }

    @Test
    void search_results_contains_users() {
        ZdSearchResults search = orgSearch.search("_id", "101");
        assertNotNull(search);
        assertNotEquals(0, search.getOraganizationSearchResult().get(0).getUsers().size());
    }

    @Test
    void search_results_for_org_id_contains_users_id_5() {
        ZdSearchResults search = orgSearch.search("_id", "101");
        assertNotNull(search);
        assertEquals(true, search.getOraganizationSearchResult().get(0).getUsers().contains("5"));
    }

    @Test
    void search_results_for_org_id_contains_ticket_with_subject_A_Problem_in_Guyana() {
        ZdSearchResults search = orgSearch.search("_id", "101");
        assertNotNull(search);
        assertEquals(true,
                search.getOraganizationSearchResult().get(0)
                        .getTickets()
                        .stream()
                        .map(ZdSearchResults.TicketReference::getSubject)
                        .anyMatch(p -> p.equals("A Problem in Guyana")));
    }

    @Test
    void search_results_for_non_existing_field_value_should_return_empty() {
        ZdSearchResults search = orgSearch.search("_id", "999");
        assertNotNull(search);
        assertEquals(0, search.getOraganizationSearchResult().size());
    }

    @Test
    void search_results_for_non_existing_field_name_should_return_exception() {
        assertThrows(NullPointerException.class, () -> orgSearch.search("test", "0"));
    }

}