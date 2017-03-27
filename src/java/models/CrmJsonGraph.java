/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author kajornjit.songsaen
 */
public class CrmJsonGraph {
    private int id;
    private String date;
    private String time;
    private int pagespeed;
    private int restspeed;
    private int reference;
    private int lsl;
    private int usl;

    public int getUsl() {
        return usl;
    }

    public void setUsl(int usl) {
        this.usl = usl;
    }

    public int getLsl() {
        return lsl;
    }

    public void setLsl(int lsl) {
        this.lsl = lsl;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getRestspeed() {
        return restspeed;
    }

    public void setRestspeed(int restspeed) {
        this.restspeed = restspeed;
    }

    public int getPagespeed() {
        return pagespeed;
    }

    public void setPagespeed(int pagespeed) {
        this.pagespeed = pagespeed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}