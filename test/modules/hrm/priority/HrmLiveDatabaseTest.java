/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.priority;

import org.junit.Test;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmLiveDatabaseTest {

    public HrmLiveDatabaseTest() {
    }

    @Test
    public void testSomeMethod() {
        HrmPriorityTicket db = new HrmPriorityTicket();
        db.getTickets().stream().forEach((ticket) -> {
            System.out.println(ticket);
        });
        System.out.println(db.getPriorityTicketSyntax());
        System.out.println(String.format("jql=project = \"HRM(CLBS)\" AND (%s)", db.getPriorityTicketSyntax()));
    }

}
