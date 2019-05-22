package com.zendesk.search.service;

import com.zendesk.search.model.results.ZdSearchResults;

/**
 * Created by @author Sankash on 5/17/2019 Interface to provide access to the search implementation
 * for Organization, User, Tickets
 */
public interface Search {
  /**
   * Method to search an entity by fieldName and fieldValue
   *
   * @param fieldName name of the field to be searched
   * @param fieldValue value of the field that is being searched for
   * @return
   */
  ZdSearchResults search(String fieldName, String fieldValue);
}
