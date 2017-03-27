/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author kajornjit.songsaen
 */
public class HttpConnection {

    private String message;

    public String getMessage() {
        return message;
    }

    public boolean IsAliveUrl(String url) throws Exception {
        try {
            URL page = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) page.openConnection();
            conn.connect();
            message = "Success";
            conn.disconnect();
            return true;
        } catch (Exception e) {
            message = e.getMessage();
            if (message.toLowerCase().contains("refuse")) {
                return true;
            }else {
                return false;   
            }
        }
    }
}
