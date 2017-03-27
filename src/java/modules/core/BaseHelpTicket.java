/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseHelpTicket {

    private String key;
    private String summary;
    private String assignee;
    private String reporter;
    private String status;
    private String remainingtime;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String updated;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public BaseHelpTicket() {
    }

    public BaseHelpTicket(String helpticketstring) {
        JsonObject issue = ReadIssues(helpticketstring);
        JsonObject fields = ReadFields(issue.get("fields"));
        ReadAssignee(fields.get("assignee"));
        ReadStatus(fields.get("status"));
        ReadReporter(fields.get("reporter"));
        ReadRemainingTime(fields.get("customfield_12701"));
        this.updated = fields.getString("updated");
    }

    private JsonObject ReadIssues(String helpticketstring) {
        JsonReader reader = Json.createReader(new StringReader(helpticketstring));
        JsonObject obj = reader.readObject();
        reader.close();
        this.key = obj.getString("key");
        return obj;
    }

    private JsonObject ReadFields(JsonValue fields) {
        JsonObject obj = ReadObjectToJson(fields);
        this.summary = obj.getString("summary");
        return obj;
    }

    private void ReadAssignee(JsonValue assignee) {
        try {
            JsonObject obj = ReadObjectToJson(assignee);
            this.assignee = obj.getString("displayName");
        } catch (Exception e) {
            this.assignee = "Unassigned";
        }
    }

    private void ReadStatus(JsonValue status) {
        try {
            JsonObject obj = ReadObjectToJson(status);
            this.status = obj.getString("name");
        } catch (Exception e) {
            this.status = "error!";
        }
    }

    private void ReadReporter(JsonValue reporter) {
        try {
            JsonObject obj = ReadObjectToJson(reporter);
            this.reporter = obj.getString("displayName");
        } catch (Exception e) {
            this.reporter = "error!";
        }
    }

    private void ReadRemainingTime(JsonValue customfield_12701) {
        try {
            JsonObject custom = ReadObjectToJson(customfield_12701);
            JsonObject ongoingCycle = ReadObjectToJson(custom.get("ongoingCycle"));
            JsonObject remainingTime = ReadObjectToJson(ongoingCycle.get("remainingTime"));
            this.remainingtime = remainingTime.getString("friendly");
        } catch (Exception e) {
            this.remainingtime = "error!";
        }
    }

    private JsonObject ReadObjectToJson(JsonValue value) {
        JsonReader reader = Json.createReader(new StringReader(value.toString()));
        JsonObject obj = reader.readObject();
        reader.close();
        return obj;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s", key, summary, assignee, reporter, status, remainingtime, updated);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemainingTime() {
        return remainingtime;
    }

    public void setRemainingtime(String remainingtime) {
        this.remainingtime = remainingtime;
    }

}
