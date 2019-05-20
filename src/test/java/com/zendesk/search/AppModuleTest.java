package com.zendesk.search;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zendesk.search.model.results.ZdSearchResults;
import com.zendesk.search.service.ZdOrganizationSearch;
import com.zendesk.search.service.ZdTicketSearch;
import com.zendesk.search.service.ZdUserSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by @author Sankash on 5/20/2019
 */
class AppModuleTest {

    Injector i = Guice.createInjector(new AppModule());


    @Test
    void test_ZdOrganizationSearch_injector_works() {
        ZdOrganizationSearch instance = i.getInstance(ZdOrganizationSearch.class);
        ZdSearchResults search = instance.search("_id", "101");
        assertNotNull(search);
        assertEquals("101", search.getOraganizationSearchResult().get(0).getOrg().getId());
    }


    @Test
    void test_ZdUserSearch_injector_works() {
        ZdUserSearch instance = i.getInstance(ZdUserSearch.class);
        ZdSearchResults search = instance.search("_id", "1");
        assertNotNull(search);
        assertEquals("1", search.getUserSearchResult().get(0).getUser().getId());
    }

    @Test
    void test_ZdTicketSearch_injector_works() {
        ZdTicketSearch instance = i.getInstance(ZdTicketSearch.class);
        ZdSearchResults search = instance.search("_id", "436bf9b0-1147-4c0a-8439-6f79833bff5b");
        assertNotNull(search);
        assertNotEquals(true, search.getTicketSearchResult().stream().map(p -> p.getSubmittedByUser()).count());
    }
}