/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.helpticket.sugar;

import java.io.IOException;
import java.net.ProtocolException;
import modules.core.BaseCount;
import modules.jira.JiraReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarHelpTicket extends BaseCount {

    public SugarHelpTicket() throws ProtocolException, IOException {
        JiraReader ticket = new JiraReader();
        ticket.getSugarTicket();        
        setCount(ticket.getCount());
        setName("Help Ticket");
    }
}
