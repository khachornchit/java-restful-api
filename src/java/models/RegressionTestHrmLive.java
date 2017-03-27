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
public class RegressionTestHrmLive extends RegressionTest {

    @Override
    public void Initial() {
        setTitle("Acceptance Test Hrm Live");
        setHeadfailcolumn2("Sugar Hrm Live Cases Fail");
        setHeadpasscolumn2("Sugar Hrm Live Cases Pass");
    }
}
