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
public class CrmPerformanceChartDisplay {

    private int PerformanceId;

    private String Datex;
    private String Timex;
    private int PageSpeed;
    private int RestSpeed;
    private int Reference;
    private int LSL;
    private int USL;

    public int getUSL() {
        return USL;
    }

    public void setUSL(int USL) {
        this.USL = USL;
    }

    public int getLSL() {
        return LSL;
    }

    public void setLSL(int LSL) {
        this.LSL = LSL;
    }

    public int getReference() {
        return Reference;
    }

    public void setReference(int Reference) {
        this.Reference = Reference;
    }

    public int getRestSpeed() {
        return RestSpeed;
    }

    public void setRestSpeed(int RestSpeed) {
        this.RestSpeed = RestSpeed;
    }

    public int getPageSpeed() {
        return PageSpeed;
    }

    public void setPageSpeed(int PageSpeed) {
        this.PageSpeed = PageSpeed;
    }

    public String getTimex() {
        return Timex;
    }

    public void setTimex(String Timex) {
        this.Timex = Timex;
    }

    public String getDatex() {
        return Datex;
    }

    public void setDatex(String Datex) {
        this.Datex = Datex;
    }

    public int getPerformanceId() {
        return PerformanceId;
    }

    public void setPerformanceId(int PerformanceId) {
        this.PerformanceId = PerformanceId;
    }

}
