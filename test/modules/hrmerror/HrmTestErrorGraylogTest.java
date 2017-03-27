/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrmerror;

import modules.hrm.alert.HrmLiveAlert;
import modules.hrm.criticalerror.HrmCriticalError;
import modules.hrm.alert.HrmTestAlert;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmTestErrorGraylogTest {

    public HrmTestErrorGraylogTest() {
    }

    @Ignore
    public void testHrmTestAlert() {
        HrmTestAlert hrmgraylog = new HrmTestAlert();
        System.out.println(hrmgraylog.toJson());
    }
    
    @Ignore
    public void testHrmLiveAlert() {
        HrmLiveAlert hrmgraylog = new HrmLiveAlert();
        System.out.println(hrmgraylog.toJson());
    }

    @Test
    public void testHrmCriticalError() {
        HrmCriticalError hrm = new HrmCriticalError();
        System.out.println(hrm.toJson());
    }
}
