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
public class RegressionTest {

    private int pass;
    private int fail;
    private int total;
    private String title;
    private String button1name;
    private String button2name;
    private String button3name;
    private String button4name;
    private String headfailcolumn1;
    private String headfailcolumn2;
    private String headfailcolumn3;
    private String headfailcolumn4;
    private String headfailcolumn5;
    private String headpasscolumn1;
    private String headpasscolumn2;
    private String headpasscolumn3;
    private String headpasscolumn4;
    private String headpasscolumn5;
    List<RegressionTestItem> regressiontestfailitems = new ArrayList<>();
    List<RegressionTestItem> regressiontestpassitems = new ArrayList<>();

    public RegressionTest() {
        InitialCommon();
        Initial();
    }

    public void InitialCommon() {
        setTitle("Acceptance Test Result");
        
        setHeadfailcolumn1("No");
        setHeadfailcolumn2("Cases Failed");
        setHeadfailcolumn3("Tested Date Time");
        setHeadfailcolumn4("Test Index (ms)");
        setHeadfailcolumn5("Result");

        setHeadpasscolumn1("No");
        setHeadpasscolumn2("Cases Passed");
        setHeadpasscolumn3("Tested Date Time");
        setHeadpasscolumn4("Test Index (ms)");
        setHeadpasscolumn5("Result");
    }

    public void Initial() {

    }

    public String getHeadpasscolumn5() {
        return headpasscolumn5;
    }

    public void setHeadpasscolumn5(String headpasscolumn5) {
        this.headpasscolumn5 = headpasscolumn5;
    }

    public String getHeadpasscolumn4() {
        return headpasscolumn4;
    }

    public void setHeadpasscolumn4(String headpasscolumn4) {
        this.headpasscolumn4 = headpasscolumn4;
    }

    public String getHeadpasscolumn3() {
        return headpasscolumn3;
    }

    public void setHeadpasscolumn3(String headpasscolumn3) {
        this.headpasscolumn3 = headpasscolumn3;
    }

    public String getHeadpasscolumn2() {
        return headpasscolumn2;
    }

    public void setHeadpasscolumn2(String headpasscolumn2) {
        this.headpasscolumn2 = headpasscolumn2;
    }

    public String getHeadpasscolumn1() {
        return headpasscolumn1;
    }

    public void setHeadpasscolumn1(String headpasscolumn1) {
        this.headpasscolumn1 = headpasscolumn1;
    }

    public String getHeadfailcolumn5() {
        return headfailcolumn5;
    }

    public void setHeadfailcolumn5(String headfailcolumn5) {
        this.headfailcolumn5 = headfailcolumn5;
    }

    public String getHeadfailcolumn4() {
        return headfailcolumn4;
    }

    public void setHeadfailcolumn4(String headfailcolumn4) {
        this.headfailcolumn4 = headfailcolumn4;
    }

    public String getHeadfailcolumn3() {
        return headfailcolumn3;
    }

    public void setHeadfailcolumn3(String headfailcolumn3) {
        this.headfailcolumn3 = headfailcolumn3;
    }

    public String getHeadfailcolumn2() {
        return headfailcolumn2;
    }

    public void setHeadfailcolumn2(String headfailcolumn2) {
        this.headfailcolumn2 = headfailcolumn2;
    }

    public String getHeadfailcolumn1() {
        return headfailcolumn1;
    }

    public void setHeadfailcolumn1(String headfailcolumn1) {
        this.headfailcolumn1 = headfailcolumn1;
    }

    public String getButton4name() {
        return button4name;
    }

    public void setButton4name(String button4name) {
        this.button4name = button4name;
    }

    public String getButton3name() {
        return button3name;
    }

    public void setButton3name(String button3name) {
        this.button3name = button3name;
    }

    public String getButton2name() {
        return button2name;
    }

    public void setButton2name(String button2name) {
        this.button2name = button2name;
    }

    public String getButton1name() {
        return button1name;
    }

    public void setButton1name(String button1name) {
        this.button1name = button1name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addFailItem(RegressionTestItem failitem) {
        regressiontestfailitems.add(failitem);
    }

    public void addPassItem(RegressionTestItem passitem) {
        regressiontestpassitems.add(passitem);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

}
