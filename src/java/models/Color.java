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
public class Color {
    private String name;    
    private String value;
    private final String Green = "green";
    private final String Red = "red";
    private final String Yellow = "yellow";
    
    public Color() {
        int random = (int )(Math.random()*3+1);
        switch (random) {
            case 1: 
                {
                    this.name=Green;
                    break;
                }
            case 2: 
                {
                    this.name=Yellow;
                    break;
                }
            case 3: 
                {
                    this.name=Red;
                    break;
                }
        }
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue() {
        this.value = value;
    }
    
    public String setColor(String testresult) {
        if (testresult.equalsIgnoreCase("failed")) {
            name=Red;
        }else if (testresult.equalsIgnoreCase("alert")) {
            name=Yellow;
        }else {
            name=Green;
        }
        
        return name;
    }
}
