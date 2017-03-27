/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.graylog;

import javax.json.JsonObject;
import modules.graylog.base.BaseGraylog;
import utilities.JsonUtil;

/**
 *
 * @author kajornjit.songsaen
 */
public final class GraylogSugar extends BaseGraylog {

    public GraylogSugar(String message, boolean bText) {
        try {
            String[] messages = message.split(",");
            this.timestamp = messages[0].trim().replaceAll("\"", "");
            this.source = messages[1].trim().replaceAll("\"", "");
            this.project = messages[2].trim().replaceAll("\"", "");
            this.level = Integer.parseInt(messages[3].trim().replaceAll("\"", ""));
            this.message = messages[4].trim().replaceAll("\"", "");
            this.full_message = FullMessageProcessing(messages[5].trim().replaceAll("\"", ""));
        } catch (Exception e) {

        }
    }

    public GraylogSugar(String message) {

        try {
            JsonObject json = JsonUtil.ReadDataToJsonObject(message);
            JsonObject objmessage = JsonUtil.ReadObjectToJsonObject(json.get("message"));
            this.level = getJsonInt(objmessage, "level");
            this.project = getJsonString(objmessage, "project");
            this.source = getJsonString(objmessage, "source");
            this.message = getJsonString(objmessage, "message");
            this.timestamp = getJsonString(objmessage, "timestamp");
            this.full_message = FullMessageProcessing(getJsonString(objmessage, "full_message"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    
    @Override
    public String FullMessageProcessing(String full_message) {
        int queryindex = full_message.indexOf("Query Failed");
        int scheduleindex = full_message.indexOf("SCHEDULERS");
        int errorindex = full_message.indexOf("Error!!");
        int limit = 200;

        if (queryindex > 0) {
            int index = full_message.length() - queryindex;
            if (limit > index) {
                limit = index;
            }
            return full_message.substring(queryindex, limit) + " ...";
        } else if (scheduleindex > 0) {
            int index = full_message.length() - scheduleindex;
            if (limit > index) {
                limit = index;
            }
            return full_message.substring(scheduleindex, limit) + " ...";
        } else if (errorindex > 0) {
            int index = full_message.length() - errorindex;
            if (limit > index) {
                limit = index;
            }
            return full_message.substring(errorindex, limit) + " ...";
        } else {
            limit = (limit > full_message.length()) ? full_message.length() : limit;
            return full_message.substring(0, limit) + " ...";
        }
    }
}
