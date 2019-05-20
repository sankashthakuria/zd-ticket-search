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
import com.zendesk.search.service.ZdOrganizationSearch;
import com.zendesk.search.service.ZdTicketSearch;
import com.zendesk.search.service.ZdUserSearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by @author Sankash on 5/18/2019
 */
public class Menu {

    public static final String HEADER = "-----------------------------------Displaying Search Results-------------------------------------------";
    public static final String FOOTER = "-----------------------------------End of Results------------------------------------------------------";
    private static final String ZENDESK_BANNER = "  ______              _           _       _____                     _     \n" +
            " |___  /             | |         | |     / ____|                   | |    \n" +
            "    / / ___ _ __   __| | ___  ___| | __ | (___   ___  __ _ _ __ ___| |__  \n" +
            "   / / / _ \\ '_ \\ / _` |/ _ \\/ __| |/ /  \\___ \\ / _ \\/ _` | '__/ __| '_ \\ \n" +
            "  / /_|  __/ | | | (_| |  __/\\__ \\   <   ____) |  __/ (_| | | | (__| | | |\n" +
            " /_____\\___|_| |_|\\__,_|\\___||___/_|\\_\\ |_____/ \\___|\\__,_|_|  \\___|_| |_|\n" +
            "                                                                          \n" +
            "                                                                          ";
    private static Map<Integer, String> lookupTable = new HashMap<>();

    private static Map<Integer, Map<String, Class>> mainSelection = ImmutableMap.of(
            1, ImmutableMap.of("organization", Organization.class),
            2, ImmutableMap.of("user", User.class),
            3, ImmutableMap.of("tickets", Ticket.class));

    /**
     * Utility method to show a menu to the user
     *
     * @param clazz the class whose fields will the shown to the user to facilitate search input
     * @return string representataion of the menu - user chooses the field index on which she wishes to perform search
     */
    private static String showSearchableFieldsToUser(Class clazz) {
        Object[] objects = Arrays.stream(clazz.getDeclaredFields())
                .filter(p -> p.getAnnotation(JsonProperty.class) != null)
                .map(p -> p.getAnnotation(JsonProperty.class).value())
                .collect(Collectors.toList()).toArray();
        IntStream.range(0, objects.length)
                .forEach(p -> lookupTable.put(p, (String) objects[p]));
        return IntStream.range(0, objects.length)
                .mapToObj(index -> String.format("%s -> %d", objects[index], index))
                .collect(Collectors.joining("\n"));
    }

    /**
     * The main entry point to the program
     */
    public static void searchLauncher() {
        System.out.println(ZENDESK_BANNER);
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "What would you like to search?.....\n1: Organizations\n" +
                            "2: Users\n" +
                            "3: Tickets"
            );
            int entityToSearch = scn.nextInt();
            Map<String, Class> stringClassMap = mainSelection.get(entityToSearch);
            Class clazz = (Class) stringClassMap.values().toArray()[0];
            String firstMenuChoice = stringClassMap.keySet().iterator().next();
            System.out.println("Displaying fields for your selection.....");
            System.out.println(showSearchableFieldsToUser(clazz));
            System.out.println("Enter the field name.....");
            Integer fieldIndex = scn.nextInt();
            String fieldName;
            if (lookupTable.containsKey(fieldIndex)) {
                fieldName = lookupTable.get(fieldIndex);
            } else {
                System.out.println("Invalid choice: Resuming from the beginning.....");
                continue;
            }
            System.out.println("Enter the field value.....");
            String fieldValue = scn.next();
            switch (firstMenuChoice) {
                case "organization": {
                    performSearch(ZdOrganizationSearch.class, fieldName, fieldValue);
                    break;
                }
                case "user": {
                    performSearch(ZdUserSearch.class, fieldName, fieldValue);
                    break;
                }
                case "ticket": {
                    performSearch(ZdTicketSearch.class, fieldName, fieldValue);
                }
            }
            System.out.println("Enter (Y/y) to perform another search - Press any other character to quit.....");
            boolean continueSearch = scn.next().equalsIgnoreCase("Y");
            if (!continueSearch) break;
        }
        scn.close();
    }

    /**
     * Delegates call to the appropriate search object - organization, user or ticket
     *
     * @param clazz      the class entity that is being searched for
     * @param fieldName  the name of the field being searched
     * @param fieldValue the value of the field being searched
     */
    private static void performSearch(Class clazz, String fieldName, String fieldValue) {
        Search instance;
        Injector injector = Guice.createInjector(new AppModule());
        instance = (Search) injector.getInstance(clazz);
        ZdSearchResults search = instance.search(fieldName, fieldValue);
        DisplayOrganizationResults instance1 = injector.getInstance(DisplayOrganizationResults.class);
        instance1.show(search);
    }
}