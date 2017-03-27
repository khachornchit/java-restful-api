/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author kajornjit.songsaen
 */
public class Calculator {

    private int numbera;
    private int numberb;

    public int getNumberb() {
        return numberb;
    }

    public void setNumberb(int numberb) {
        this.numberb = numberb;
    }

    public int getNumbera() {
        return numbera;
    }

    public void setNumbera(int numbera) {
        this.numbera = numbera;
    }

    public int multiply(int a, int b) {
        this.numbera = a;
        this.numberb = b;
        return a * b;
    }

}
