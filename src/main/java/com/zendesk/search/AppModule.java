package com.zendesk.search;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.zendesk.search.console.DisplayOrganizationResults;
import com.zendesk.search.console.DisplayTicketResults;
import com.zendesk.search.console.DisplayUserResults;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.service.Search;
import com.zendesk.search.service.ZdOrganizationSearch;
import com.zendesk.search.service.ZdTicketSearch;
import com.zendesk.search.service.ZdUserSearch;

/**
 * Created by @author Sankash on 5/18/2019
 */
public class AppModule extends AbstractModule {
    /**
     * Guice config for dependency injection
     */
    @Override
    protected void configure() {
        bind(ZdDatabase.class).toInstance(ZdDatabase.initialize());
        bind(Search.class).annotatedWith(Names.named("organization")).to(ZdOrganizationSearch.class);
        bind(Search.class).annotatedWith(Names.named("user")).to(ZdUserSearch.class);
        bind(Search.class).annotatedWith(Names.named("ticket")).to(ZdTicketSearch.class);
        bind(DisplayOrganizationResults.class).toInstance(new DisplayOrganizationResults());
        bind(DisplayTicketResults.class).toInstance(new DisplayTicketResults());
        bind(DisplayUserResults.class).toInstance(new DisplayUserResults());
    }
}
