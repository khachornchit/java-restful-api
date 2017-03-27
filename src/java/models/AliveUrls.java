/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kajornjit.songsaen
 */
public class AliveUrls {

    List<AliveUrl> aliveurls = new ArrayList<AliveUrl>();

    private String name;
    private String message;
    private int alive;
    private int dead;
    private int total;
    private String color;
    private int count;

    public AliveUrls() {
        name = "Alive URL";
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCountdead() {
        return dead;
    }

    public void setCountdead(int countdead) {
        this.dead = countdead;
    }

    public int getCountalive() {
        return alive;
    }

    public void setCountalive(int countalive) {
        this.alive = countalive;
    }

    public void setUrl(AliveUrl aliveurl) {
        this.aliveurls.add(aliveurl);
        if (aliveurl.isIsalive()) {
            alive = alive + 1;
        } else {
            dead = dead + 1;
        }
        total = alive + dead;

        if (dead > 0) {
            name = "Dead URL";
            color = "red";
            count = dead;
        } else {
            name = "Alive URL";
            color = "green";
            count = total;
        }
    }
}
