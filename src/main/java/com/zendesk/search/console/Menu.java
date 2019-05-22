package com.zendesk.search.console;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zendesk.search.AppModule;
import com.zendesk.search.model.data.Organization;
import com.zendesk.search.model.data.Ticket;
import com.zendesk.search.model.data.User;
import com.zendesk.search.model.results.ZdSearchResults;
import com.zendesk.search.service.Search;
import com.zendesk.search.service.SearchFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Created by @author Sankash on 5/18/2019 */
public class Menu {

  public static final String HEADER =
      "-----------------------------------Displaying Search Results-------------------------------------------";
  public static final String FOOTER =
      "-----------------------------------End of Results------------------------------------------------------";
  private static final String ZENDESK_BANNER =
      "  ______              _           _       _____                     _     \n"
          + " |___  /             | |         | |     / ____|                   | |    \n"
          + "    / / ___ _ __   __| | ___  ___| | __ | (___   ___  __ _ _ __ ___| |__  \n"
          + "   / / / _ \\ '_ \\ / _` |/ _ \\/ __| |/ /  \\___ \\ / _ \\/ _` | '__/ __| '_ \\ \n"
          + "  / /_|  __/ | | | (_| |  __/\\__ \\   <   ____) |  __/ (_| | | | (__| | | |\n"
          + " /_____\\___|_| |_|\\__,_|\\___||___/_|\\_\\ |_____/ \\___|\\__,_|_|  \\___|_| |_|\n"
          + "                                                                          \n"
          + "                                                                          ";
  private static Map<Integer, String> lookupTable = new HashMap<>();

  private static Map<Integer, Map<String, Class>> mainSelection =
      ImmutableMap.of(
          1, ImmutableMap.of("organization", Organization.class),
          2, ImmutableMap.of("user", User.class),
          3, ImmutableMap.of("tickets", Ticket.class));

  /**
   * Utility method to show a menu to the user
   *
   * @param clazz the class whose fields will the shown to the user to facilitate search input
   * @return string representataion of the menu - user chooses the field index on which she wishes
   *     to perform search
   */
  private static String showSearchableFieldsToUser(Class clazz) {
    Object[] objects =
        Arrays.stream(clazz.getDeclaredFields())
            .filter(p -> p.getAnnotation(JsonProperty.class) != null)
            .map(p -> p.getAnnotation(JsonProperty.class).value())
            .collect(Collectors.toList())
            .toArray();
    IntStream.range(0, objects.length).forEach(p -> lookupTable.put(p, (String) objects[p]));
    return IntStream.range(0, objects.length)
        .mapToObj(index -> String.format("%s -> %d", objects[index], index))
        .collect(Collectors.joining("\n"));
  }

  /** The main entry point to the program */
  public static void searchLauncher() {
    System.out.println(ZENDESK_BANNER);
    Scanner scn = new Scanner(System.in);
    while (true) {
      try {
        System.out.println(
            "What would you like to search?.....\n1: Organizations\n"
                + "2: Users\n"
                + "3: Tickets");
        String first = scn.next();
        Integer num;
        num = Integer.parseInt(first);
        if (!(1 <= num && num <= 3)) {
          throw new IllegalArgumentException();
        }
        int entityToSearch = num;
        Map<String, Class> stringClassMap;
        if (mainSelection.containsKey(entityToSearch)) {
          stringClassMap = mainSelection.get(entityToSearch);
        } else throw new IllegalArgumentException();
        Class clazz = (Class) stringClassMap.values().toArray()[0];
        System.out.println("Displaying fields for your selection.....");
        System.out.println(showSearchableFieldsToUser(clazz));
        System.out.println("Enter the field name.....");
        Integer fieldIndex;
        fieldIndex = scn.nextInt();
        String fieldName;
        if (lookupTable.containsKey(fieldIndex)) {
          fieldName = lookupTable.get(fieldIndex);
        } else {
          System.out.println("Invalid choice: Resuming from the beginning.....");
          continue;
        }
        System.out.println("Enter the field value.....");
        // consume the left over newline character
        scn.nextLine();
        String fieldValue = scn.nextLine();
        if (fieldValue.isEmpty()) {
          fieldValue = "";
        }
        Injector injector = Guice.createInjector(new AppModule());
        Search searchImpl = injector.getInstance(SearchFactory.class).getSearchImpl(entityToSearch);
        ZdSearchResults search = searchImpl.search(fieldName, fieldValue);
        Display display = injector.getInstance(DisplayFactory.class).getDisplayImpl(entityToSearch);
        display.show(search);
        System.out.println(
            "Enter (Y/y) to perform another search - Press any other character to quit.....");
        boolean continueSearch = scn.next().equalsIgnoreCase("Y");
        if (!continueSearch) break;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Invalid choice -  resuming from beginning");
        continue;
      }
    }
    scn.close();
  }
}
