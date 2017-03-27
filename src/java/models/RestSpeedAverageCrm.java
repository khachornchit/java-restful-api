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
public class RestSpeedAverageCrm extends SpeedModel {

    public RestSpeedAverageCrm() {
        setDBDatabase("CLBSDASHBOARD");
        setDBTable("RESTSPEEDAVERAGECRM");
    }

    public String getModuleName() {
        return getName();
    }

    public void setModuleName(String pagename) {
        setName(pagename);
    }

    public int getRestSpeed() {
        return getSpeed();
    }

    public void setRestSpeed(int pagespeed) {
        setSpeed(pagespeed);
    }

    public boolean InsertRestSpeedAverage() {
        return insertRestSpeedAverage();
    }
    
    public RestSpeedAverageCrm GetLastRestSpeedAverage() {
        return getLastRestSpeedAverage();
    }
}
