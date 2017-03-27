/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import utilities.*;

/**
 *
 * @author kajornjit.songsaen
 */
public class AliveUrl {

    private String url;
    private String status;
    private boolean isalive;
    private String message;

    public String getMessage() {
        return message;
    }

    public boolean isIsalive() {
        return isalive;
    }

    public AliveUrl() {
    }

    public AliveUrl(String url) throws Exception {
        //WebReader webreader = new WebReader();
        HttpConnection httpConnection = new HttpConnection();

        this.url = url;
        this.isalive = httpConnection.IsAliveUrl(url);
        this.message = httpConnection.getMessage();
        status = (isalive == true) ? "Alive" : "Dead";        
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }
}
