/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.jira;

import datetime.management.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.json.JsonObject;
import modules.core.Rest;

/**
 *
 * @author kajornjit.songsaen
 */
public class JiraReader {

    protected int count;
    private String initurl;
    private String query;
    private String from;
    private String to;
    private String urlstring;
    private final String yesterdayfrom;
    private final String yesterdayto;
    private final String todayfrom;
    private final String todayto;
    private final String name = "kajornjit.songsaen";
    private final String password = "clbs1234";
    protected JsonObject json;

    public JsonObject getJson() {
        return json;
    }

    public JiraReader() {
        initurl = "http://jira.coast.ebuero.de/rest/api/2/search?";
        todayfrom = DateTimeManage.graylogTodayTimeBegin();
        todayto = DateTimeManage.graylogTodayTimeEnd();
        yesterdayfrom = DateTimeManage.graylogYesterdayTimeBegin();
        yesterdayto = DateTimeManage.graylogYesterdayTimeEnd();
    }

    protected void toJsonObject() throws MalformedURLException, ProtocolException, IOException {
        try {
            json = Rest.getJson(name, password, getUrlString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public JsonObject getSugarTicket() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=(project = Helpdesk AND resolution = \"Unresolved\" AND \"IT Team\" = CRM AND status = \"Action Needed\")");
        toJsonObject();
        count = json.getInt("total");
        return json;
    }
    
    public JsonObject getHrmTicket() throws MalformedURLException, ProtocolException, IOException {
        setQuery("jql=(project = Helpdesk AND resolution = \"Unresolved\" AND \"IT Team\" = HRM AND status = \"Action Needed\")");
        toJsonObject();
        count = json.getInt("total");
        return json;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUrlString() {
        urlstring = getInitUrl() + getQuery();
        return urlstring;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getInitUrl() {
        return initurl;
    }

    public void setInitUrl(String URL) {
        this.initurl = URL;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
