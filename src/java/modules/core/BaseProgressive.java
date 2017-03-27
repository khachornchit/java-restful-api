/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

/**
 *
 * @author kajornjit.songsaen
 */
public class BaseProgressive extends BaseCount {
    protected int counttoal;
    protected int countinprogress;
    protected int countdone;
    protected int countopen;
    protected String progressive;

    
    
    public int getCounttoal() {
        return counttoal;
    }

    public void setCounttoal(int counttoal) {
        this.counttoal = counttoal;
    }

    public int getCountinprogress() {
        return countinprogress;
    }

    public void setCountinprogress(int countprogressive) {
        this.countinprogress = countprogressive;
    }

    public int getCountdone() {
        return countdone;
    }

    public void setCountdone(int countdone) {
        this.countdone = countdone;
    }

    public String getProgressive() {
        return progressive;
    }

    public void setProgressive(String progressive) {
        this.progressive = progressive;
    }
    
}
