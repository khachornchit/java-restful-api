/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.criticalerror;

import java.io.IOException;
import modules.core.*;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarErrorTotal extends BaseCount {

    public SugarErrorTotal() throws IOException {
        CaiError cai = new CaiError();
        SugarError sugar = new SugarError();

        name = "Critical Error";
        count = (cai.getCount() + sugar.getCount());
        if (count > 0) {
            color = "red";
        } else {
            color = "green";
        }
    }

}
