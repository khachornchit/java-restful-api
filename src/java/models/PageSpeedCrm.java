package models;

public class PageSpeedCrm extends SpeedModel {
    public PageSpeedCrm() {
        setDBDatabase("CLBSDASHBOARD");
        setDBTable("PAGESPEEDCRM");
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
    
    public boolean InsertPageSpeed() {
        return insertPageSpeed();
    }    
    
    public PageSpeedCrm GetLastPageSpeed() {
        return getLastPageSpeed();
    }
}
