/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.management;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author kajornjit.songsaen
 */
public class PageSpeedCrm {
    
    private int id;
    private String pagename;
    private String url;
    private String value;
    private DateTime datetime;
    private boolean averageanalysis;

    public boolean getAverageanalysis() {
        return averageanalysis;
    }

    public void setAverageanalysis(boolean averageanalysis) {
        this.averageanalysis = averageanalysis;
    }


    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}