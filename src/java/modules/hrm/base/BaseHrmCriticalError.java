/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.base;

import modules.hrm.method.IHrmErrorMethod;
import com.google.gson.Gson;
import models.WidgetCount;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseHrmCriticalError implements IHrmErrorMethod {

    protected WidgetCount counttotalyesterday;
    protected WidgetCount counttotaltoday;

    public BaseHrmCriticalError() {
        counttotalyesterday = new WidgetCount();
        counttotaltoday = new WidgetCount();
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public void setCountYesterday() {
    }

    @Override
    public void setCountToday() {
    }
}
