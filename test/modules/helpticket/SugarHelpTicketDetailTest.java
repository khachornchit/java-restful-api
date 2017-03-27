/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.helpticket;

import modules.helpticket.sugar.SugarHelpTicketDetail;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarHelpTicketDetailTest {
    
    public SugarHelpTicketDetailTest() {
    }

    @Test
    public void testSomeMethod() throws IOException {
        SugarHelpTicketDetail helpticket = new SugarHelpTicketDetail();
        System.out.println(helpticket.toJson());
    }
    
}
