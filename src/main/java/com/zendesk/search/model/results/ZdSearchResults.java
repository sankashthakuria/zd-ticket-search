package com.zendesk.search.model.results;

import com.zendesk.search.model.data.Organization;
import com.zendesk.search.model.data.Ticket;
import com.zendesk.search.model.data.User;
import com.zendesk.search.model.data.ZdData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/** Created by @author Sankash on 5/18/2019 Class to hold the results of the search */
@Data
@Builder
public class ZdSearchResults {

  private List<TicketSearchResult> ticketSearchResult;
  private List<UserSearchResult> userSearchResult;
  private List<OraganizationSearchResult> oraganizationSearchResult;

  /** Created by @author Sankash on 5/15/2019 */
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class OraganizationSearchResult extends Organization implements ZdData {
    Organization org;
    List<String> users;
    List<TicketReference> tickets;
  }

  /** Created by @author Sankash on 5/15/2019 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UserSearchResult implements ZdData {
    User user;
    Optional<String> organizationName;
    List<TicketReference> ticketsAssigned;
    List<TicketReference> ticketsSubmitted;
  }

  /** Created by @author Sankash on 5/15/2019 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TicketSearchResult implements ZdData {
    Ticket ticket;
    Optional<String> submittedByUser;
    Optional<String> organizationName;
    Optional<String> assignedToUser;
  }

  /** Created by @author Sankash on 5/15/2019 */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TicketReference {
    String id;
    String subject;
  }
}
