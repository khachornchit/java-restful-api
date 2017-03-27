/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import java.util.Comparator;
import javax.json.JsonObject;
import javax.json.JsonValue;
import utilities.JsonUtil;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseTasks {

    private String key;
    private String summary;
    private String assignee;
    private String reporter;
    private String status;
    private String updated;
    private String color;
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public BaseTasks() {
    }

    public BaseTasks(JsonValue bug) {
        this.key = ReadKey(bug);
        this.summary = ReadSummary(bug);
        this.assignee = ReadAssignee(bug);
        this.reporter = ReadReporter(bug);
        this.status = ReadStatus(bug);
        this.updated = ReadUpdated(bug);

        if (status.equals("Done")) {
            status = "Testing";
        }
        if (status.toUpperCase().equals("CLOSED")) {
            status = "Done";
        }
        
        if (assignee.equals("error")) {
            assignee = "Unassigned";
        }
    }
    
    public boolean IsInProgress() {
        return status.contains("Testing") || status.contains("In Progress");
    }
    
    public boolean IsDone() {
        return status.contains("Done");
    }

    public boolean IsOpen() {
        return !(IsInProgress() && IsDone());
    }
    
    private String ReadKey(JsonValue jsonValue) {

        try {
            JsonObject json = JsonUtil.ReadDataToJsonObject(jsonValue.toString());
            return json.getString("key");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    private String ReadSummary(JsonValue jsonValue) {

        try {
            JsonObject bug = JsonUtil.ReadDataToJsonObject(jsonValue.toString());
            JsonObject fields = JsonUtil.ReadObjectToJsonObject(bug.get("fields"));
            return fields.getString("summary");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    private String ReadUpdated(JsonValue jsonValue) {

        try {
            JsonObject bug = JsonUtil.ReadDataToJsonObject(jsonValue.toString());
            JsonObject fields = JsonUtil.ReadObjectToJsonObject(bug.get("fields"));
            return fields.getString("updated");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    private String ReadAssignee(JsonValue jsonValue) {

        try {
            JsonObject bug = JsonUtil.ReadDataToJsonObject(jsonValue.toString());
            JsonObject fields = JsonUtil.ReadObjectToJsonObject(bug.get("fields"));
            JsonObject assignee = JsonUtil.ReadObjectToJsonObject(fields.get("assignee"));
            return assignee.getString("displayName");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    private String ReadReporter(JsonValue jsonValue) {

        try {
            JsonObject bug = JsonUtil.ReadDataToJsonObject(jsonValue.toString());
            JsonObject fields = JsonUtil.ReadObjectToJsonObject(bug.get("fields"));
            JsonObject reporter = JsonUtil.ReadObjectToJsonObject(fields.get("reporter"));
            return reporter.getString("displayName");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    private String ReadStatus(JsonValue jsonValue) {

        try {
            JsonObject bug = JsonUtil.ReadDataToJsonObject(jsonValue.toString());
            JsonObject fields = JsonUtil.ReadObjectToJsonObject(bug.get("fields"));
            JsonObject status = JsonUtil.ReadObjectToJsonObject(fields.get("status"));
            return status.getString("name");
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s", key, summary, assignee, reporter, status, updated);
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

    public static Comparator<BaseTasks> CompareStatus = (BaseTasks bug1, BaseTasks bug2) -> {
        String b1 = bug1.status;
        String b2 = bug2.status;
        return b1.compareTo(b2);
    };

    public static Comparator<BaseTasks> CompareKey = (BaseTasks bug1, BaseTasks bug2) -> {
        String b1 = bug1.key;
        String b2 = bug2.key;
        return b1.compareTo(b2);
    };

    public boolean EqualKey(BaseTasks hrm) {
        if (hrm.getKey().equals(this.key)) {
            return true;
        } else {
            return false;
        }
    }
}
