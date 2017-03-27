/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author kajornjit.songsaen
 */
public class WidgetGraph {

    private String date;
    private String time;
    private int pagespeed;
    private int restspeed;
    private int reference;
    private int id;

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

    public WidgetGraph() {
    }

    public WidgetGraph(int id, String date, String time, int pagespeed, int restspeed, int reference) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.pagespeed = pagespeed;
        this.restspeed = restspeed;
        this.reference = reference;
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

    public void setRestspeed(int restSpeed) {
        this.restspeed = restSpeed;
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

}
