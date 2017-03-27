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
public class SugarErrorTextCsv  extends BaseCount {

    private String messages;
    private HttpURLConnection sugarerrors;

    public String getMessages() {
        return messages;
    }

    public HttpURLConnection getSugarErrorTextCsv() {
        return sugarerrors;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public SugarErrorTextCsv() throws ProtocolException, IOException {
        GraylogReader sugar = new GraylogReader();
        this.sugarerrors = sugar.SugarErrorToTextCsv();

        name = "Sugar Error";
//        this.count = cai.getCount();
//        if (cai.getCount() > 0) {
//            color = "red";
//        } else {
//            color = "green";
//        }
    }    
}
