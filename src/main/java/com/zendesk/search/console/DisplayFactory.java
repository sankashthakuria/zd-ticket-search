package com.zendesk.search.console;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Injector;

/** Gets the instance of display implementation Created by @author Sankash on 5/21/2019 */
public class DisplayFactory {
  static final ImmutableMap<Integer, Class<? extends Display>> map =
      ImmutableMap.of(
          1, DisplayOrganizationResults.class,
          2, DisplayUserResults.class,
          3, DisplayTicketResults.class);
  @Inject private Injector injector;
  /**
   * Gets the instance of display implementation
   *
   * @param i index of the entity
   * @return the display implementaion
   */
  protected Display getDisplayImpl(int i) {
    return injector.getInstance(map.get(i));
  }
}
