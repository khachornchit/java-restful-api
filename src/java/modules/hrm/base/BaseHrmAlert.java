/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.base;

import modules.hrm.method.IHrmAlertMethod;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import models.WidgetCount;
import modules.graylog.GraylogHrm;
import modules.graylog.base.BaseGraylog;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseHrmAlert implements IHrmAlertMethod {

    protected String workflowName = "Not set yet";
    protected String workflowWaS = "Workflow of New WaS";
    protected String workflowInteressent = "Workflow of New Interessent";

    protected WidgetCount countwasyesterday;
    protected WidgetCount countinteressentyesterday;
    protected WidgetCount counttotalyesterday;
    protected List<BaseGraylog> wasyesterday;
    protected List<BaseGraylog> interessentyesterday;

    protected WidgetCount countwastoday;
    protected WidgetCount countinteressenttoday;
    protected WidgetCount counttotaltoday;
    protected List<BaseGraylog> wastoday;
    protected List<BaseGraylog> interessenttoday;

    protected WidgetCount countwas;
    protected WidgetCount countinteressent;

    protected WidgetCount counterrorhandling3;
    protected List<GraylogHrm> errorhandling3;

    protected List<GraylogHrm> errorresolved;

    protected WidgetCount countwassuccessful;
    protected WidgetCount countinteressentsuccessful;
    protected WidgetCount countsuccessfulhandling;
    protected List<GraylogHrm> successfulhandling;

    public BaseHrmAlert() {
        countinteressentyesterday = new WidgetCount();
        countwasyesterday = new WidgetCount();

        counttotalyesterday = new WidgetCount();
        countinteressenttoday = new WidgetCount();
        countwastoday = new WidgetCount();
        counttotaltoday = new WidgetCount();

        errorresolved = new ArrayList<>();

//      Error Handling
        counterrorhandling3 = new WidgetCount();
        countwas = new WidgetCount();
        countinteressent = new WidgetCount();
        errorhandling3 = new ArrayList<>();

//      Successful Handling
        countsuccessfulhandling = new WidgetCount();
        countwassuccessful = new WidgetCount();
        countinteressentsuccessful = new WidgetCount();
        successfulhandling = new ArrayList<>();
    }

    @Override
    public void getWaSYesterday() {
    }

    @Override
    public void getWaSToday() {
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public void setCountWaSYesterday() {
    }

    @Override
    public void setCountWaSToday() {
    }

    @Override
    public void getInteressentYesterday() {
    }

    @Override
    public void getInteressentToday() {
    }

    @Override
    public void getErrorHandling() {
    }

    @Override
    public void getSuccessfulHandling() {
    }

    @Override
    public int addErrorHandlingWaS(GraylogHrm hrm) {
        return -1;
    }

    @Override
    public int addErrorHandlingInteressent(GraylogHrm hrm) {
        return -1;
    }

    @Override
    public int addSuccessfulWaS(GraylogHrm hrm) {
        return -1;
    }

    @Override
    public int addSuccessfulInteressent(GraylogHrm hrm) {
        return -1;
    }

    @Override
    public boolean IsResolved(GraylogHrm hrm) {
        return false;
    }

    protected boolean IsGraylogHeader(String line) {
        return line.contains("timestamp") && line.contains("source") && line.contains("level") && line.contains("message") && line.contains("full_message");
    }

    public String displayCountWaS() {
        return countwas.toJson();
    }

    public String displayCountInteressent() {
        return countinteressent.toJson();
    }

    public String displayCountError() {
        return counterrorhandling3.toJson();
    }

    public String displayCountWaSSuccess() {
        return countwassuccessful.toJson();
    }

    public String displayCountInteressentSuccess() {
        return countinteressentsuccessful.toJson();
    }

    public String displayCountSuccess() {
        return countsuccessfulhandling.toJson();
    }

}
