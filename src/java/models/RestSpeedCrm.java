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
public class RestSpeedCrm extends SpeedModel {

    public RestSpeedCrm() {
        setDBDatabase("CLBSDASHBOARD");
        setDBTable("RESTSPEEDCRM");
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
    
    public boolean InsertRestSpeed() {
        return insertRestSpeed();
    }
    
    public RestSpeedCrm GetLastRestSpeed() {
        return getLastRestSpeed();
    }
}
