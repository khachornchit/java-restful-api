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
public class SpeedChartConfig {
    
    private Integer ymax;
    private Integer ymin;   
    private String ylabel;
    private String xlabel;
    private List<String> removemodule = new ArrayList<String>();

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public String getYlabel() {
        return ylabel;
    }

    public void setYlabel(String ylabel) {
        this.ylabel = ylabel;
    }

    public SpeedChartConfig() {
    }

    public SpeedChartConfig(Integer ymax, Integer ymin, String ylabel, String xlabel) {
        this.ymax = ymax;
        this.ymin = ymin;
        this.ylabel= ylabel;
        this.xlabel= xlabel;
    }
        
    public void addExcludingModule(String modulename) {
        removemodule.add(modulename);
    }

    public Integer getYmin() {
        return ymin;
    }

    public void setYmin(Integer ymin) {
        this.ymin = ymin;
    }

    public Integer getYmax() {
        return ymax;
    }

    public void setYmax(Integer ymax) {
        this.ymax = ymax;
    }

}
