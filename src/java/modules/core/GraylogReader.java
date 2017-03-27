/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import datetime.management.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.json.JsonObject;

/**
 *
 * @author kajornjit.songsaen
 */
public class GraylogReader {

    protected String initurl;
    protected String query;
    protected String from;
    protected String to;
    protected String fields;
    protected int limit;
    protected String urlstring;
    protected final String yesterdayfrom;
    protected final String yesterdayto;
    protected final String todayfrom;
    protected final String todayto;
    protected final String name = "kajornjit.songsaen";
    protected final String password = "clbs1234";
    protected int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GraylogReader() {
        initurl = "http://graylog2web.coast.ebuero.de:80/api/search/universal/absolute?query=";
        todayfrom = DateTimeManage.graylogTodayTimeBegin();
        todayto = DateTimeManage.graylogTodayTimeEnd();
        yesterdayfrom = DateTimeManage.graylogYesterdayTimeBegin();
        yesterdayto = DateTimeManage.graylogYesterdayTimeEnd();
        fields = "timestamp,source,project,level,message";
        limit = 300;
    }

    private JsonObject toJsonObject() throws MalformedURLException, ProtocolException, IOException {
        try {
            JsonObject json = Rest.getJson(name, password, getUrlStringYesterday());
            setCount(json.getInt("total_results"));
            return json;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected HttpURLConnection toTextYesterday() throws MalformedURLException, ProtocolException, IOException {
        try {
            return Rest.getTextCsv(name, password, getUrlStringYesterday());
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected HttpURLConnection toTextErrorHandling() throws MalformedURLException, ProtocolException, IOException {
        try {
            return Rest.getTextCsv(name, password, getUrlStringErrorHandling());
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected HttpURLConnection toTextAllWithApplicantName() throws MalformedURLException, ProtocolException, IOException {
        try {
            return Rest.getTextCsv(name, password, getUrlStringWithApplicationName());
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected HttpURLConnection toTextToday() throws MalformedURLException, ProtocolException, IOException {
        try {
            return Rest.getTextCsv(name, password, getUrlStringToday());
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected HttpURLConnection toTextFixDate(String fixDate) throws MalformedURLException, ProtocolException, IOException {
        try {
            return Rest.getTextCsv(name, password, getUrlStringFixDate(fixDate));
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected HttpURLConnection toTextErrorHandlingFixDate(String fixDate) throws MalformedURLException, ProtocolException, IOException {
        try {
            return Rest.getTextCsv(name, password, getUrlStringErrrorHandlingFixDate(fixDate));
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    private void setSugarErrorQuery() {
        this.query = "(project:SugarCRM AND full_message:\"Query Failed\") OR (project:SugarCRM AND full_message:\"could not get an IMAP connection resource for ID\") OR (project:SugarCRM  AND  full_message:\"p:sugardb.coast.ebuero.de\")";
    }

    private void setCaiErrorQuery() {
        this.query = "source:vmcrminternal0* AND project: sugar-rest AND level:3 AND (loggerName:ag.pinguin.sugarcrm.numberprovider.NumberProviderService OR loggerName:ag.pinguin.sugarcrm.numberprovider.SugarCrmDao)";
    }

    private void setCaiNotFoundQuery() {
        this.query = "source:vmcrminternal0* AND project:sugar-rest AND level:4 AND message:\"NOT FOUND NEXT NUMBER RESPONSE HTTP CODE 404\"";
    }

    public HttpURLConnection SugarErrorToTextCsv() throws MalformedURLException, ProtocolException, IOException {
        setSugarErrorQuery();
        return toTextYesterday();
    }

    public HttpURLConnection CaiErrorToTextCsv() throws MalformedURLException, ProtocolException, IOException {
        setCaiErrorQuery();
        return toTextYesterday();
    }

    public HttpURLConnection CaiNotFoundToTextCsv() throws MalformedURLException, ProtocolException, IOException {
        setCaiNotFoundQuery();
        return toTextYesterday();
    }

    public JsonObject SugarErrorToJson() throws MalformedURLException, ProtocolException, IOException {
        setSugarErrorQuery();
        return toJsonObject();
    }

    public JsonObject CaiErrorToJson() throws MalformedURLException, ProtocolException, IOException {
        setCaiErrorQuery();
        return toJsonObject();
    }

    public JsonObject CaiNotFoundToJson() throws MalformedURLException, ProtocolException, IOException {
        setCaiNotFoundQuery();
        return toJsonObject();
    }

    public String getUrlStringYesterday() {
        urlstring = getInitUrl() + getQuery() + "&from=" + yesterdayfrom + "&to=" + yesterdayto + "&fields=timestamp,source,project,level,message,full_message&limit=300";
        return urlstring;
    }

    public String getUrlStringFixDate(String fixDate) {
        String begindate = DateTimeManage.graylogFixDateBegin(fixDate);
        urlstring = getInitUrl() + getQuery() + "&from=" + begindate + "&to=" + yesterdayto + "&fields=timestamp,source,project,level,message,full_message&limit=300";
        return urlstring;
    }

    public String getUrlStringErrorHandling() {
        String last3dayfrom = DateTimeManage.last3daysbegin();
        urlstring = getInitUrl() + getQuery() + "&from=" + last3dayfrom + "&to=" + todayto + "&fields=timestamp,source,project,level,message,full_message&limit=300";
        return urlstring;
    }

    public String getUrlStringWithApplicationName() {
        String last3dayfrom = DateTimeManage.last3daysbegin();
        urlstring = getInitUrl() + getQuery() + "&from=" + last3dayfrom + "&to=" + todayto + "&fields=timestamp,source,project,level,message,full_message,application_name&limit=300";
        return urlstring;
    }

    private String getUrlStringErrrorHandlingFixDate(String fixDate) {
        String begindate = DateTimeManage.graylogFixDateBegin(fixDate);
        urlstring = getInitUrl() + getQuery() + "&from=" + begindate + "&to=" + todayto + "&fields=timestamp,source,project,level,message,full_message&limit=300";
        return urlstring;
    }

    public String getUrlStringToday() {
        urlstring = getInitUrl() + getQuery() + "&from=" + todayfrom + "&to=" + todayto + "&fields=timestamp,source,project,level,message,full_message&limit=300";
        return urlstring;
    }

    public String getQuery() {
        return query;
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

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
