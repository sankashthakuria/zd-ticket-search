package com.zendesk.search.console;

import com.google.inject.Inject;
import com.zendesk.search.model.data.ZdDatabase;
import com.zendesk.search.model.results.ZdSearchResults;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by @author Sankash on 5/18/2019
 * This class writes the Ticket Search results to the console
 */

public class DisplayTicketResults implements Display<ZdSearchResults> {
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
        result.getTicketSearchResult()
                .stream()
                .forEach(p -> {
                    System.out.println(String.format("Ticket Id: %s \nTicket Subject: %s",
                            p.getTicket().getId(),
                            p.getTicket().getSubject())
                    );
                    String assinedTo;
                    String submittedby;
                    if (p.getAssignedToUser() != null) {
                        assinedTo = p.getAssignedToUser().orElse("NA");
                        if (!assinedTo.equals("NA"))
                            assinedTo = zdDatabase.getUserIndex().get(assinedTo).getName();
                    } else assinedTo = "NA";
                    if (p.getSubmittedByUser() != null) {
                        submittedby = p.getSubmittedByUser().orElse("NA");
                        if (!submittedby.equals("NA"))
                            submittedby = zdDatabase.getUserIndex().get(submittedby).getName();
                    } else submittedby = "NA";


                    System.out.println(String.format("Assigned to: %s", assinedTo));
                    System.out.println(String.format("Submitted by : %s", submittedby));
                    System.out.println(StringUtils.repeat("-", 150));
                });
        System.out.println(Menu.FOOTER);
    }
}
