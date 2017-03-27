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
public class SpeedPoint {

    private String measureddate;
    private String measuredtime;
    private int speed;
    private int lsl;
    private int usl;
    
    private int slow;
    private int medium;
    private int fast;

    public int getFast() {
        return fast;
    }

    public int getMedium() {
        return medium;
    }

    public int getSlow() {
        return slow;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpeedPoint() {
    }

    public SpeedPoint(String name, String measureddate, String measuredtime, int speed, int lsl, int usl) {
        this.name = name;
        this.measureddate = measureddate;
        this.measuredtime = measuredtime;
        this.speed = speed;
        this.lsl = lsl;
        this.usl = usl;
        judgeSpeed();
    }

    private void judgeSpeed() {
        slow = medium = fast = 0;
        if (speed < lsl) {
            fast = 1;
        } else if (speed < usl) {
            medium = 1;
        } else {
            slow = 1;
        }
    }

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getMeasuredtime() {
        return measuredtime;
    }

    public void setMeasuredtime(String measuredtime) {
        this.measuredtime = measuredtime;
    }

    public String getMeasureddate() {
        return measureddate;
    }

    public void setMeasureddate(String measureddate) {
        this.measureddate = measureddate;
    }

}
