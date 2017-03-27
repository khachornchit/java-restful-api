/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.Gson;
import modules.core.Colors;

/**
 *
 * @author kajornjit.songsaen
 */
public class WidgetCount {

    protected String name;
    protected int count;
    protected String message;
    protected String color;
    protected String colorcode;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
        if (message.equalsIgnoreCase("failed")) {
            setRedColor();
        } else if (message.equalsIgnoreCase("passed")) {
            setGreenColor();
        } else {
            setYellowColor();
        }

    }

    private void setRedColor() {
        color = "red";
        colorcode = Colors.Red;
    }

    private void setYellowColor() {
        color = "yellow";
        colorcode = Colors.Yellow;
    }

    private void setGreenColor() {
        color = "green";
        colorcode = Colors.Green;
    }

    private void setBlueColor() {
        color = "blue";
        colorcode = Colors.Blue;
    }

    public String getColor() {
        return this.color;
    }

    public void setColorAlert() {
        if (count > 0 || count < 0) {
            setYellowColor();
        } else {
            setGreenColor();
        }
    }

    public void setColor(String color) {
        switch (color) {
            case "red":
                setRedColor();
                break;
            case "yellow":
                setYellowColor();
                break;
            case "green":
                setGreenColor();
                break;
            case "blue":
                setBlueColor();
                break;
            default:
                setGreenColor();
                break;
        }
    }

    public void setNameCriticalError() {
        name = "Critical Error";
    }

    public void setNamePerformanceTest() {
        name = "Performance Test";
    }

    public void setNameRegressionTest() {
        name = "Regression Test";
    }

    public void setNameBugs() {
        name = "Bugs";
    }

    public void setNameHelpTicket() {
        name = "Help Tickets";
    }

    public void setNameAliveUrl() {
        name = "Alive URL";
    }

    public void setFailed() {
        message = "Failed";
        setRedColor();
    }

    public void setPassed() {
        message = "Passed";
        setGreenColor();
    }

    public void setAlert() {
        message = "Alert";
        setYellowColor();
    }

    public void setColorCritical() {
        if (count > 0 || count < 0) {
            setRedColor();
        } else {
            setGreenColor();
        }
    }

    public void setColorAlertBlue() {
        if (count > 0 || count < 0) {
            setBlueColor();
        } else {
            setGreenColor();
        }
    }

    public void setColorAlertRed() {
        if (count > 0 || count < 0) {
            setYellowColor();
        } else {
            setGreenColor();
        }
    }

    public void setColorAlertYellow() {
        if (count > 0 || count < 0) {
            setYellowColor();
        } else {
            setGreenColor();
        }
    }

    public void setColorAlertGreen() {
        if (count > 0) {
            setGreenColor();
        } else {
            setGreenColor();
        }
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
