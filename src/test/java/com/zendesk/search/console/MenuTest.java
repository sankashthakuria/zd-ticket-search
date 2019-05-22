package com.zendesk.search.console;

import com.zendesk.search.model.data.Organization;
import com.zendesk.search.model.data.Ticket;
import com.zendesk.search.model.data.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Created by @author Sankash on 5/20/2019 */
class MenuTest {

  @Test
  void calling_showSearchableFieldsToUser_For_Ticket_must_return_string() throws Exception {
    String fields = Whitebox.invokeMethod(Menu.class, "showSearchableFieldsToUser", Ticket.class);
    assertNotNull(fields);
    assertTrue(StringUtils.contains(fields, "external_id"));
    assertTrue(StringUtils.contains(fields, "subject"));
    assertTrue(StringUtils.contains(fields, "submitter_id"));
    assertTrue(StringUtils.contains(fields, "assignee_id"));
  }

  @Test
  void calling_showSearchableFieldsToUser_For_user_must_return_string() throws Exception {
    String fields = Whitebox.invokeMethod(Menu.class, "showSearchableFieldsToUser", User.class);
    assertNotNull(fields);
    assertTrue(StringUtils.contains(fields, "last_login_at"));
    assertTrue(StringUtils.contains(fields, "email"));
    assertTrue(StringUtils.contains(fields, "phone"));
  }

  @Test
  void calling_showSearchableFieldsToUser_For_Organization_must_return_string() throws Exception {
    String fields =
        Whitebox.invokeMethod(Menu.class, "showSearchableFieldsToUser", Organization.class);
    assertNotNull(fields);
    assertTrue(StringUtils.contains(fields, "external_id"));
    assertTrue(StringUtils.contains(fields, "shared_tickets"));
    assertTrue(StringUtils.contains(fields, "domain_names"));
  }
}
