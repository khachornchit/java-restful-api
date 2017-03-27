/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.method;

import java.util.List;
import modules.graylog.GraylogHrm;
import modules.graylog.base.BaseGraylog;

/**
 *
 * @author kajornjit.songsaen
 */
public interface IHrmAlertMethod {
    // Yesterday Method
    void getWaSYesterday();
    void getInteressentYesterday();    
    void setCountWaSYesterday();
    
    // Today Method
    void getWaSToday();
    void getInteressentToday();
    void setCountWaSToday();    
    
    void getErrorHandling();
    void getSuccessfulHandling();
    
    int addErrorHandlingWaS(GraylogHrm hrm);
    int addErrorHandlingInteressent(GraylogHrm hrm);
    
    int addSuccessfulWaS(GraylogHrm hrm);
    int addSuccessfulInteressent(GraylogHrm hrm);
    
    boolean IsResolved(GraylogHrm hrm);
}
