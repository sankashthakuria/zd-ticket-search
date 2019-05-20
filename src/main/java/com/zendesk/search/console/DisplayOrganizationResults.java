package com.zendesk.search.console;

import com.google.inject.Inject;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;

/**
 * Created by @author Sankash on 5/18/2019
 * This displays the Org Search results on the console
 */
public class DisplayOrganizationResults implements Display<ZdSearchResults> {
    @Inject
    private ZdDatabase zdDatabase;

    /**
     * This displays the Org Search results on the console
     *
     * @param result the result to the printed on the console
     */
    @Override
    public void show(ZdSearchResults result) {
        System.out.println(Menu.HEADER);
        result.getOraganizationSearchResult()
                .forEach(p -> {
                            System.out.println("Organization\n");
                            System.out.println(String.format("OrgId: %s\t|\tOrg Name: %s\t|\tCreated at: %s\n",
                                    p.getOrg().getId(),
                                    p.getOrg().getName(),
                                    p.getOrg().getCreatedAt()));
                            System.out.println(String.format("\tUsers\n"));
                            p.getUsers()
                                    .forEach(x ->
                                            System.out.println(String.format("\tName: %-35.35s\t\t|\tEmail: %s, Phone: %s",
                                                    zdDatabase.getUserIndex().get(x).getName(),
                                                    zdDatabase.getUserIndex().get(x).getEmail(),
                                                    zdDatabase.getUserIndex().get(x).getPhone())));
                            System.out.println(String.format("\n\t\tTickets\n"));
                            p.getTickets()
                                    .forEach(y ->
                                            System.out.println(String.format("\t\tId: %s\t|\tSubject: %s",
                                                    y.getId(),
                                                    y.getSubject())));
                        }

                );
        System.out.println(String.format("\n%s", Menu.FOOTER));
    }
}
