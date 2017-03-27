/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.criticalerror;

import java.io.IOException;
import java.net.ProtocolException;
import javax.json.JsonObject;
import modules.core.*;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarError extends BaseCount {

    private String messages;
    private JsonObject sugarerrors;

    public JsonObject getSugarErrors() {
        return sugarerrors;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public SugarError() throws ProtocolException, IOException {
        GraylogReader sugar = new GraylogReader();
        this.sugarerrors = sugar.SugarErrorToJson();        
        name = "Sugar Error";

        this.count = sugar.getCount();
        if (sugar.getCount() > 0) {
            color = "red";
        } else {
            color = "green";
        }
    }
}
