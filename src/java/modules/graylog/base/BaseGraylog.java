/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.graylog.base;

import modules.graylog.method.IGraylogMessageProcessing;
import com.google.gson.Gson;
import javax.json.JsonObject;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseGraylog implements IGraylogMessageProcessing {

    protected String timestamp = null;
    protected String source = null;
    protected String project = null;
    protected int level = -1;
    protected String message = null;
    protected String full_message = null;

    public String getFull_message() {
        return full_message;
    }
  
    protected int getJsonInt(JsonObject objmessage, String fieldname) {
        try {
            return objmessage.getInt(fieldname);
        } catch (Exception e) {
            return -1;
        }
    }

    protected String getJsonString(JsonObject objmessage, String fieldname) {
        try {
            return objmessage.getString(fieldname);
        } catch (Exception e) {
            return "error";
        }
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", project, source, level, message, timestamp);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public String getProject() {
        return project;
    }

    public int getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String FullMessageProcessing(String full_message) {
        return full_message;
    }

}
