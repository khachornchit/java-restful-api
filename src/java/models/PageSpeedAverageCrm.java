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
public class PageSpeedAverageCrm extends SpeedModel {

    public PageSpeedAverageCrm() {
        setDBDatabase("CLBSDASHBOARD");
        setDBTable("PAGESPEEDAVERAGECRM");
    }

    public String getPageName() {
        return getName();
    }

    public void setPageName(String pagename) {
        setName(pagename);
    }

    public int getPageSpeed() {
        return getSpeed();
    }

    public void setPageSpeed(int pagespeed) {
        setSpeed(pagespeed);
    }

    public boolean InsertPageSpeedAverage() {
        return insertPageSpeedAverage();
    }
    
    public PageSpeedAverageCrm GetLastPageSpeedAverage() {
        return getLastPageSpeedAverage();
    }
}
