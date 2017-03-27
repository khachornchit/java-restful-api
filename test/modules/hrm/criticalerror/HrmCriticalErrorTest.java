/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.criticalerror;

import org.junit.Test;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmCriticalErrorTest {

    public HrmCriticalErrorTest() {
    }

    @Test
    public void testSomeMethod() {
        HrmCriticalError hrm = new HrmCriticalError();
        System.out.println(hrm.toJson());

        System.out.println(hrm.displayCountWaS());
        System.out.println(hrm.displayCountInteressent());
        System.out.println(hrm.displayCountError());
        System.out.println(hrm.displayCountWaSSuccess());
        System.out.println(hrm.displayCountInteressentSuccess());
        System.out.println(hrm.displayCountSuccess());
    }

}
