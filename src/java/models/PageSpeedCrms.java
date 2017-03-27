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
public class PageSpeedCrms {

    private int count;

    public int getCount() {
        return lstPageSpeedCrm.size();
    }
    private List<PageSpeedCrm> lstPageSpeedCrm = new ArrayList<PageSpeedCrm>();

    public void addPageSpeedCrm(PageSpeedCrm pageSpeed) {
        lstPageSpeedCrm.add(pageSpeed);
    }

    public PageSpeedCrm getPageSpeed(int index) {
        return lstPageSpeedCrm.get(index);
    }

    public List<PageSpeedCrm> getPageSpeeds() {
        return lstPageSpeedCrm;
    }
}
