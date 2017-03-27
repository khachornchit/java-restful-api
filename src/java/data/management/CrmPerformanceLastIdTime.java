/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.management;

/**
 *
 * @author kajornjit.songsaen
 */
public class CrmPerformanceLastIdTime {

    private int id;
    private String time;

    public CrmPerformanceLastIdTime() {
    }
    
    public CrmPerformanceLastIdTime(int id, String time) {
        this.id = id;
        this.time = time;
    }       

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
