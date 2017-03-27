/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import modules.jira.JiraReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.json.JsonObject;

/**
 *
 * @author kajornjit.songsaen
 */
public class BugsReader extends JiraReader {

    private int countbugopen;
    private int countbuginprogress;
    private int countbugbacklog;

    public int getCountbugopen() {
        return countbugopen;
    }

    public int getCountbuginprogress() {
        return countbuginprogress;
    }

    public int getCountbugbacklog() {
        return countbugbacklog;
    }

    public JsonObject getSugarBugsOpen() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"SugarCRM (Berlin)\" AND type = Bug AND resolution = Unresolved AND status=Open");
        toJsonObject();
        countbugopen = json.getInt("total");
        
        return json;
    }

    public JsonObject getSugarBugsInProgress() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"SugarCRM (Berlin)\" AND type = Bug AND resolution = Unresolved AND status=\"In Progress\"");
        toJsonObject();
        countbuginprogress = json.getInt("total");
        return json;
    }

    public JsonObject getSugarBugsBacklog() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"SugarCRM (Berlin)\" AND type = Bug AND resolution = Unresolved AND status=\"Backlog\"");
        toJsonObject();
        countbugbacklog = json.getInt("total");
        return json;
    }
    
        public JsonObject getHrmBugsOpen() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"HRM(CLBS)\" AND type = Bug AND resolution = Unresolved AND status=Open");
        toJsonObject();
        countbugopen = json.getInt("total");
        
        return json;
    }

    public JsonObject getHrmBugsInProgress() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"HRM(CLBS)\" AND type = Bug AND resolution = Unresolved AND ( status=\"In Progress\" OR status=Resolved)");
        toJsonObject();
        countbuginprogress = json.getInt("total");
        return json;
    }

    public JsonObject getHrmBugsBacklog() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=project = \"HRM(CLBS)\" AND type = Bug AND resolution = Unresolved AND status=\"Backlog\"");
        toJsonObject();
        countbugbacklog = json.getInt("total");
        return json;
    }

    public BugsReader() {
        super();
    }
    
}
