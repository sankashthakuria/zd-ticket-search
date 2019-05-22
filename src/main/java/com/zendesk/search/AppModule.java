package com.zendesk.search;

import com.google.inject.AbstractModule;
import com.zendesk.search.console.DisplayFactory;
import com.zendesk.search.console.DisplayOrganizationResults;
import com.zendesk.search.console.DisplayTicketResults;
import com.zendesk.search.console.DisplayUserResults;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.service.SearchFactory;
import com.zendesk.search.service.ZdOrganizationSearch;
import com.zendesk.search.service.ZdTicketSearch;
import com.zendesk.search.service.ZdUserSearch;

/** Created by @author Sankash on 5/18/2019 */
public class AppModule extends AbstractModule {
  /** Guice config for dependency injection */
  @Override
  protected void configure() {
    bind(ZdDatabase.class).toInstance(ZdDatabase.initialize());
    bind(ZdOrganizationSearch.class).toInstance(new ZdOrganizationSearch());
    bind(ZdUserSearch.class).toInstance(new ZdUserSearch());
    bind(ZdTicketSearch.class).toInstance(new ZdTicketSearch());
    bind(DisplayOrganizationResults.class).toInstance(new DisplayOrganizationResults());
    bind(DisplayTicketResults.class).toInstance(new DisplayTicketResults());
    bind(DisplayUserResults.class).toInstance(new DisplayUserResults());
    bind(SearchFactory.class).toInstance(new SearchFactory());
    bind(DisplayFactory.class).toInstance(new DisplayFactory());
  }
}
