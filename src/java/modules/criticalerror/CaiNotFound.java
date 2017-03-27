/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.criticalerror;

import java.io.IOException;
import java.net.ProtocolException;
import javax.json.JsonObject;
import modules.core.BaseCount;
import modules.core.GraylogReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class CaiNotFound extends BaseCount {

    private String messages;
    private JsonObject cainotfounds;

    public String getMessages() {
        return messages;
    }
    
    public JsonObject getCaiNotFounds() {
        return cainotfounds;
    }
    
    public void setMessages(String messages) {
        this.messages = messages;
    }

    public CaiNotFound() throws ProtocolException, IOException {
        GraylogReader cai = new GraylogReader();
        this.cainotfounds = cai.CaiNotFoundToJson();
        
        name = "Cai Not Found";
        this.count = cai.getCount();
        if (cai.getCount() > 0) {
            color = "yellow";
        } else {
            color = "green";
        }
    }
}
