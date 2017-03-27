/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.helpticket;

import modules.helpticket.hrm.HrmHelpTicketDetail;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmHelpTicketDetailTest {
    
    public HrmHelpTicketDetailTest() {
    }

    @Test
    public void testSomeMethod() throws IOException {
        HrmHelpTicketDetail helpticket = new HrmHelpTicketDetail();
        System.out.println(helpticket.toJson());
    }
    
}
