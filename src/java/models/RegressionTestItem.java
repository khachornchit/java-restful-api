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
public class RegressionTestItem {

    private String no;
    private String item;
    private String testdatetime;
    private int testindextime;
    private String testresult;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RegressionTestItem() {
    }

    public RegressionTestItem(String no, String item, String testdatetime, int testindextime, String testresult, String url) {
        this.no = no;
        this.item = item;
        this.testdatetime = testdatetime;
        this.testindextime = testindextime;
        this.testresult = testresult;
        this.url = url;
    }

    public String getTestresult() {
        return testresult;
    }

    public void setTestresult(String testresult) {
        this.testresult = testresult;
    }

    public int getTestindextime() {
        return testindextime;
    }

    public void setTestindextime(int testindextime) {
        this.testindextime = testindextime;
    }

    public String getTestdatetime() {
        return testdatetime;
    }

    public void setTestdatetime(String testdatetime) {
        this.testdatetime = testdatetime;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

}
