package com.zendesk.search.service;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Injector;

/** Factory to return the Search implementation Created by @author Sankash on 5/20/2019 */
public class SearchFactory {

  static final ImmutableMap<Integer, Class<? extends Search>> map =
      ImmutableMap.of(
          1, ZdOrganizationSearch.class,
          2, ZdUserSearch.class,
          3, ZdTicketSearch.class);
  @Inject Injector injector;

  /**
   * Gets the instance of search implementation
   *
   * @param i index of the entity
   * @return the search implementaion
   */
  public Search getSearchImpl(int i) {
    return injector.getInstance(map.get(i));
  }
}
