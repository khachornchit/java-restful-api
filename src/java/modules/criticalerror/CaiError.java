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
public class CaiError extends BaseCount {

    private String messages;
    private JsonObject caierrors;

    public String getMessages() {
        return messages;
    }
    
    public JsonObject getCaiErrors() {
        return caierrors;
    }
    
    public void setMessages(String messages) {
        this.messages = messages;
    }

    public CaiError() throws ProtocolException, IOException {
        GraylogReader cai = new GraylogReader();
        this.caierrors = cai.CaiErrorToJson();
        
        name = "Cai Error";
        this.count = cai.getCount();
        if (cai.getCount() > 0) {
            color = "red";
        } else {
            color = "green";
        }
    }
}
