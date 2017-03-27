/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.priority;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.json.JsonObject;
import modules.jira.JiraReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmPriorityReader extends JiraReader {

    private int countopen;
    private int countinprogress;
    private int countpriority;

    public int getCountpriority() {
        return countpriority;
    }

    public int getCountbugopen() {
        return countopen;
    }

    public int getCountinprogress() {
        return countinprogress;
    }

    public JsonObject getPriorityOpen() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"HRM(CLBS)\" AND (type = Task OR type = Story) AND resolution = Unresolved AND status = Open");
        toJsonObject();
        countopen = json.getInt("total");

        return json;
    }

    public JsonObject getPriorityInProgress() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"HRM(CLBS)\" AND (type = Task OR type = Story) AND resolution = Unresolved AND (status = \"In Progress\" OR status=Done)");
        toJsonObject();
        countinprogress = json.getInt("total");
        return json;
    }

    public JsonObject getPriorityProgressive() throws MalformedURLException, ProtocolException, IOException {
        HrmPriorityTicket tickets = new HrmPriorityTicket();        
        setQuery(String.format("jql=project = \"HRM(CLBS)\" AND (%s)", tickets.getPriorityTicketSyntax()));
        toJsonObject();
        countpriority = json.getInt("total");
        return json;
    }

    public HrmPriorityReader() {
        super();
    }
}
