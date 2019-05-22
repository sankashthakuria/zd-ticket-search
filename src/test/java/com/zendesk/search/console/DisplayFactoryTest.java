package com.zendesk.search.console;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zendesk.search.AppModule;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/** Created by @author Sankash on 5/21/2019 */
class DisplayFactoryTest {

  Injector i = Guice.createInjector(new AppModule());

  @Test
  void test_getDisplayImpl_is_DisplayOrganizationResults_when_1() {
    DisplayFactory instance = i.getInstance(DisplayFactory.class);
    assertThat(instance.getDisplayImpl(1), instanceOf(DisplayOrganizationResults.class));
  }

  @Test
  void test_getDisplayImpl_is_DisplayUserResults_when_2() {
    DisplayFactory instance = i.getInstance(DisplayFactory.class);
    assertThat(instance.getDisplayImpl(2), instanceOf(DisplayUserResults.class));
  }

  @Test
  void test_getDisplayImpl_is_DisplayTicketResults_when_3() {
    DisplayFactory instance = i.getInstance(DisplayFactory.class);
    assertThat(instance.getDisplayImpl(3), instanceOf(DisplayTicketResults.class));
  }
}
