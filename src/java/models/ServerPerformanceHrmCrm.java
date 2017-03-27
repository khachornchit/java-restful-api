/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;

/**
 *
 * @author kajornjit.songsaen
 */
public class ServerPerformanceHrmCrm {

    private int slow;
    private int medium;
    private int fast;
    private int total;
    private WidgetCount widgetCount;

    public WidgetCount getWidgetCount() {
        return widgetCount;
    }

    public ServerPerformanceHrmCrm() throws SQLException {

        RestSpeedInformation restinfo = new RestSpeedInformation();
        restinfo.setSpeedChartConfig(new SpeedChartConfig(5000, 0, "Rest Speed (ms)", "Rest Module"));
        RestSpeedCrm restSpeeds = new RestSpeedCrm();

        for (RestSpeedCrm restSpeed : restSpeeds.getDisplayRestSpeed().getRestSpeeds()) {
            restinfo.addNewPoint(new SpeedPoint(restSpeed.getModuleName(), restSpeed.getDate(), restSpeed.getTime(), restSpeed.getRestSpeed(), 1000, 2000));
        }
        restinfo.setMeasureddate();
        restinfo.setMeasuredtime();
        
        PageSpeedInformation pageinfo = new PageSpeedInformation();
        pageinfo.setSpeedChartConfig(new SpeedChartConfig(5000, 0, "Page Speed (ms)", "Page Name"));
        PageSpeedCrm pageSpeeds = new PageSpeedCrm();
        
        for (PageSpeedCrm pageSpeed: pageSpeeds.getDisplayPageSpeed().getPageSpeeds()) {
            pageinfo.addNewPoint(new SpeedPoint(pageSpeed.getPageName(), pageSpeed.getDate(), pageSpeed.getTime(), pageSpeed.getPageSpeed(), 1000, 2000));
        }

        pageinfo.setMeasureddate();
        pageinfo.setMeasuredtime();
        
        slow = pageinfo.getSlow() + restinfo.getSlow();
        medium = pageinfo.getMedium() + restinfo.getMedium();
        fast = pageinfo.getFast() + restinfo.getFast();
        total = pageinfo.getTotal() + restinfo.getTotal();

        widgetCount = new WidgetCount();
        widgetCount.setNamePerformanceTest();

        if (slow > 0) {
            widgetCount.setCount(slow);
            widgetCount.setFailed();
        } else if (medium > 0) {
            widgetCount.setCount(medium);
            widgetCount.setAlert();
        } else {
            widgetCount.setCount(fast);
            widgetCount.setPassed();
        }
    }
}
