package com.zendesk.search.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by @author Sankash on 5/18/2019 Class to hold the data from the json files(user,
 * organization, tickets) Also contains auxilary helper methods and index maps to perform search in
 * a speedier manner This is helpful for large datasets
 */
@Getter
public final class ZdDatabase {
  private final List<Organization> organizations;
  private final List<User> users;
  private final List<Ticket> tickets;

  private final Map<String, Organization> organizationIndex;
  private final Map<String, User> userIndex;
  private final Map<String, Ticket> ticketIndex;

  private final Map<String, String> orgFieldMap;
  private final Map<String, String> userFieldMap;
  private final Map<String, String> ticketFieldMap;

  private ZdDatabase() {
    organizations = createOrgList();
    users = createUserList();
    tickets = createTicketList();
    organizationIndex = createOrgIndex(organizations);
    userIndex = createUserIndex(users);
    ticketIndex = createTicketIndex(tickets);
    orgFieldMap = jsonPropToJavaField(Organization.class);
    userFieldMap = jsonPropToJavaField(User.class);
    ticketFieldMap = jsonPropToJavaField(Ticket.class);
  }

  public static ZdDatabase initialize() {
    return new ZdDatabase();
  }

  /**
   * Parse the tickets.json file
   *
   * @return returns the data class that holds tickets
   */
  private static List<Ticket> createTicketList() {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Ticket> tickets;
    try {
      tickets =
          objectMapper.readValue(
              ResourceUtils.getFile("classpath:tickets.json"),
              objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Ticket.class));
    } catch (IOException e) {
      throw new RuntimeException("File does not exist");
    }
    return tickets;
  }

  /**
   * Parses the user.json file
   *
   * @return returns the data that holds the users
   */
  private static List<User> createUserList() {
    ObjectMapper objectMapper = new ObjectMapper();
    List<User> users;
    try {
      users =
          objectMapper.readValue(
              ResourceUtils.getFile("classpath:users.json"),
              objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class));
    } catch (IOException e) {
      throw new RuntimeException("File does not exist");
    }
    return users;
  }

  /**
   * Create an index on the organization object to enable faster search
   *
   * @param organizations list of orgranizatons
   * @return returns a map with id as key and the organization object as value
   */
  private static Map<String, Organization> createOrgIndex(List<Organization> organizations) {

    return ImmutableMap.copyOf(
        organizations.stream().collect(Collectors.toMap(Organization::getId, Function.identity())));
  }

  /**
   * Create an index on the user object to enable faster search
   *
   * @param users list of users
   * @return returns a map with id as key and user object as value
   */
  private static Map<String, User> createUserIndex(List<User> users) {

    return ImmutableMap.copyOf(
        users.stream().collect(Collectors.toMap(User::getId, Function.identity())));
  }

  /**
   * Create an index on the ticket object to enable faster search
   *
   * @param tickets list of tickets
   * @return returns a map with id as key and ticket object as value
   */
  private static Map<String, Ticket> createTicketIndex(List<Ticket> tickets) {

    return ImmutableMap.copyOf(
        tickets.stream().collect(Collectors.toMap(Ticket::getId, Function.identity())));
  }

  /**
   * Read the organization.json file from disk and
   *
   * @return list of organizations
   */
  private List<Organization> createOrgList() {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Organization> organizations;
    try {
      organizations =
          objectMapper.readValue(
              ResourceUtils.getFile("classpath:organizations.json"),
              objectMapper
                  .getTypeFactory()
                  .constructCollectionType(ArrayList.class, Organization.class));
    } catch (IOException e) {
      throw new RuntimeException("File not found");
    }

    return organizations;
  }

  /**
   * Creates a map of json properties in data class to the corresponding java fields This is used in
   * mapping the user input from console to the corresponding java field
   *
   * @param clazz
   * @return
   */
  private Map<String, String> jsonPropToJavaField(Class clazz) {
    return Arrays.stream(clazz.getDeclaredFields())
        .filter(p -> p.getAnnotation(JsonProperty.class) != null)
        .collect(
            Collectors.toMap(p -> p.getAnnotation(JsonProperty.class).value(), Field::getName));
  }
}
