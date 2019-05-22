package com.zendesk.search.model.data;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** Created by @author Sankash on 5/19/2019 */
class ZdDatabaseTest {

  static ZdDatabase zdDatabase;

  @BeforeAll
  static void create_instance() {
    zdDatabase = ZdDatabase.initialize();
  }

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void should_initialize_upon_calling_initialize() {
    ZdDatabase initialize = ZdDatabase.initialize();
    assertNotNull(initialize);
  }

  @Test
  void getOrganizationsTest() {
    List<Organization> organizations = zdDatabase.getOrganizations();
    assertNotNull(organizations);
    assertEquals(25, organizations.size());
    assertEquals("101", organizations.get(0).getId());
    assertEquals("Enthaze", organizations.get(0).getName());
  }

  @Test
  void getUsersTest() {
    List<User> users = zdDatabase.getUsers();
    assertNotNull(users);
    assertEquals(75, users.size());
    assertEquals("1", users.get(0).getId());
    assertEquals("Francisca Rasmussen", users.get(0).getName());
  }

  @Test
  void getTicketsTest() {
    List<Ticket> tickets = zdDatabase.getTickets();
    assertNotNull(tickets);
    assertEquals(201, tickets.size());
    assertEquals("436bf9b0-1147-4c0a-8439-6f79833bff5b", tickets.get(0).getId());
    assertEquals("A Catastrophe in Korea (North)", tickets.get(0).getSubject());
  }

  @Test
  void jsonPropToJavaField_returns_mapping_of_json_property_names_to_java_fields()
      throws Exception {
    Map<String, String> map =
        Whitebox.invokeMethod(zdDatabase, "jsonPropToJavaField", Organization.class);
    assertEquals("externalId", map.get("external_id"));
    assertEquals("id", map.get("_id"));
  }

  @Test
  void createOrgList_returns_list_of_orgranizations() throws Exception {
    List<Organization> orgs = Whitebox.invokeMethod(zdDatabase, "createOrgList", new Object[] {});
    assertNotNull(orgs);
    assertEquals(25, orgs.size());
  }

  @Test
  void createOrgIndex_creates_map() throws Exception {
    Map<String, Organization> organizationIndex = zdDatabase.getOrganizationIndex();
    assertNotNull(organizationIndex);
    assertEquals(true, organizationIndex.containsKey("101"));
    assertEquals(25, organizationIndex.size());
  }

  @Test
  void createUserIndex_creates_map() throws Exception {
    Map<String, User> userIndex = zdDatabase.getUserIndex();
    assertNotNull(userIndex);
    assertEquals(75, userIndex.size());
    assertEquals(true, userIndex.containsKey("75"));
  }

  @Test
  void getTicketIndex() {
    Map<String, Ticket> ticketIndex = zdDatabase.getTicketIndex();
    assertNotNull(ticketIndex);
    assertEquals(true, ticketIndex.containsKey("59d803f6-a9cd-448c-a6bd-91ce9f044305"));
    assertEquals(201, ticketIndex.size());
  }

  @Test
  void getOrgFieldMap() {
    Map<String, String> orgFieldMap = zdDatabase.getOrgFieldMap();
    assertNotNull(orgFieldMap);
    assertEquals(9, orgFieldMap.size());
    assertEquals(true, orgFieldMap.containsKey("_id"));
  }

  @Test
  void getUserFieldMap() {
    Map<String, String> userFieldMap = zdDatabase.getUserFieldMap();
    assertNotNull(userFieldMap);
    assertEquals(true, userFieldMap.containsKey("_id"));
    assertEquals(19, userFieldMap.size());
  }

  @Test
  void getTicketFieldMap() {
    Map<String, String> ticketFieldMap = zdDatabase.getTicketFieldMap();
    assertNotNull(ticketFieldMap);
    assertEquals(true, ticketFieldMap.containsKey("_id"));
    assertEquals(16, ticketFieldMap.size());
  }
}
