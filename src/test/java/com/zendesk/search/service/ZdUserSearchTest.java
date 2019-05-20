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
class ZdUserSearchTest {

    private static ZdDatabase zdDatabase;
    ZdUserSearch userSearch;

    @BeforeAll
    static void setUp() throws Exception {
        zdDatabase = ZdDatabase.initialize();

    }

    @BeforeEach
    void setup() {
        userSearch = new ZdUserSearch();
        Whitebox.setInternalState(userSearch, "zdDatabase", zdDatabase);
    }


    @Test
    void search_results_contains_the_org_searched() {
        ZdSearchResults search = userSearch.search("_id", "1");
        assertNotNull(search);
        assertEquals("1", search.getUserSearchResult().get(0).getUser().getId());
    }

    @Test
    void search_results_works_for_arrays_such_as_tags() {
        ZdSearchResults search = userSearch.search("tags", "Springville");
        assertNotNull(search);
        assertEquals(true, search.getUserSearchResult().get(0).getUser().getTags().contains("Springville"));
    }

    @Test
    void search_results_contains_tickets() {
        ZdSearchResults search = userSearch.search("_id", "1");
        assertNotEquals(0, search.getUserSearchResult().get(0).getTicketsAssigned().size());
    }

    @Test
    void search_results_for_user_id_1_contains_tickets_with_subject_A_Problem_in_Russian_Federation() {
        ZdSearchResults search = userSearch.search("_id", "1");
        assertNotNull(search);
        assertNotEquals(0, search.getUserSearchResult().get(0)
                .getTicketsAssigned()
                .stream()
                .anyMatch(p -> p.getSubject().equals("A Problem in Russian Federation"))
        );
    }

    @Test
    void search_results_for_user_id_1_contains_assigned_tickets() {
        ZdSearchResults search = userSearch.search("_id", "1");
        assertNotNull(search);
        assertNotEquals(0, search.getUserSearchResult().get(0).getTicketsAssigned().size());
    }

    @Test
    void search_results_for_user_id_1_contains_submitted_tickets() {
        ZdSearchResults search = userSearch.search("_id", "1");
        assertNotNull(search);
        assertNotEquals(0, search.getUserSearchResult().get(0).getTicketsSubmitted().size());
    }

    @Test
    void search_results_for_non_existing_field_value_should_return_empty() {
        assertEquals(0, userSearch.search("_id", "999").getUserSearchResult().size());
    }

    @Test
    void search_results_for_non_existing_field_name_should_return_exception() {
        assertThrows(NullPointerException.class, () -> userSearch.search("test", "0"));
    }
}