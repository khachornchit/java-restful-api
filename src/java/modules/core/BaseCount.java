/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import com.google.gson.Gson;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseCount {

    protected int count;
    protected String name;
    protected String color;
    protected String colorcode;

    @Override
    public String toString() {
        return String.format("count=%d, name=%s, color=%s", count, name, color);
    }

    public String getColor() {
        return color;
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

    public void setColorFail() {
        setRedColor();
    }

    public void setColorPass() {
        setGreenColor();
    }

    public void setColorAlert() {
        setYellowColor();
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        if (count > 0) {
            setColorFail();
        } else {
            setColorPass();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
