/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kajornjit.songsaen
 */
public class RestSpeedCrms {

    private int count;

    public int getCount() {
        return lstRestSpeedCrm.size();
    }
    private List<RestSpeedCrm> lstRestSpeedCrm = new ArrayList<RestSpeedCrm>();

    public void addRestSpeedCrm(RestSpeedCrm restSpeed) {
        lstRestSpeedCrm.add(restSpeed);
    }

    public RestSpeedCrm getRestSpeed(int index) {
        return lstRestSpeedCrm.get(index);
    }

    public List<RestSpeedCrm> getRestSpeeds() {
        return lstRestSpeedCrm;
    }    
}
