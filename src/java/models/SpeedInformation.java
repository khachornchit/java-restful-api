/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kajornjit.songsaen
 */
public class SpeedInformation {

    private int total;
    private int slow;
    private int medium;
    private int fast;
    private String measureddate;
    private String measuredtime;
    private int averagespeed;
    private int removefromaverage;
    private int sumspeed;
    private SpeedChartConfig speedChartConfig = new SpeedChartConfig();
    private List<SpeedPoint> speedPoints = new ArrayList<SpeedPoint>();

    public SpeedInformation() {
        slow = medium = fast = total = removefromaverage = sumspeed = 0;
    }

    public void setSpeedChartConfig(SpeedChartConfig speedChartConfig) {
        this.speedChartConfig = speedChartConfig;
    }

    public List<SpeedPoint> getSpeedPoints() {
        return speedPoints;
    }

    public void addNewPoint(SpeedPoint speedPoint) {
        speedPoints.add(speedPoint);
        slow = slow + speedPoint.getSlow();
        medium = medium + speedPoint.getMedium();
        fast = fast + speedPoint.getFast();
        total = slow + medium + fast;
        if (speedPoint.getName().equalsIgnoreCase("documents")) {
            removefromaverage = 1;
        } else {
            sumspeed = sumspeed + speedPoint.getSpeed();
            averagespeed = Calculator.getAverage(sumspeed, total - removefromaverage);
        }
    }

    public int getAveragespeed() {
        return averagespeed;
    }

    public String getMeasuredtime() {
        return measuredtime;
    }

    public void setMeasuredtime() {
        SpeedPoint point = speedPoints.get(0);
        this.measuredtime = point.getMeasuredtime();
    }

    public String getMeasureddate() {
        return measureddate;
    }

    public void setMeasureddate() {
        SpeedPoint point = speedPoints.get(0);
        this.measureddate = point.getMeasureddate();
    }

    public int getFast() {
        return fast;
    }

    public int getMedium() {
        return medium;
    }

    public int getSlow() {
        return slow;
    }

    public int getTotal() {
        return total;
    }

}
