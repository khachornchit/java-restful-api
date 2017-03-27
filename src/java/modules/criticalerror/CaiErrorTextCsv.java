/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.criticalerror;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import modules.core.BaseCount;
import modules.core.GraylogReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class CaiErrorTextCsv  extends BaseCount {

    private String messages;
    private HttpURLConnection caierrors;

    public String getMessages() {
        return messages;
    }
    
    public HttpURLConnection getCaiErrorTextCsv() {
        return caierrors;
    }
    
    public void setMessages(String messages) {
        this.messages = messages;
    }

    public CaiErrorTextCsv() throws ProtocolException, IOException {
        GraylogReader cai = new GraylogReader();
        this.caierrors = cai.CaiErrorToTextCsv();
        
        name = "Cai Error";
//        this.count = cai.getCount();
//        if (cai.getCount() > 0) {
//            color = "red";
//        } else {
//            color = "green";
//        }
    }
}
