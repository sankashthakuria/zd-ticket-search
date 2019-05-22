package com.zendesk.search.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zendesk.search.AppModule;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/** Created by @author Sankash on 5/21/2019 */
class SearchFactoryTest {

  Injector i = Guice.createInjector(new AppModule());

  @Test
  void test_getSearchImpl_is_ZdOrganizationSearch_when_1() {
    SearchFactory instance = i.getInstance(SearchFactory.class);
    assertThat(instance.getSearchImpl(1), instanceOf(ZdOrganizationSearch.class));
  }

  @Test
  void test_getSearchImpl_is_ZdUserSearch_when_2() {
    SearchFactory instance = i.getInstance(SearchFactory.class);
    assertThat(instance.getSearchImpl(2), instanceOf(ZdUserSearch.class));
  }

  @Test
  void test_getSearchImpl_is_ZdTicketSearch_when_3() {
    SearchFactory instance = i.getInstance(SearchFactory.class);
    assertThat(instance.getSearchImpl(3), instanceOf(ZdTicketSearch.class));
  }
}
