/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.helpticket;

import modules.helpticket.sugar.SugarHelpTicket;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarHelpTicketTest {

    public SugarHelpTicketTest() {
    }

    @Test
    public void testHelpTicket() throws IOException {
        SugarHelpTicket sugar = new SugarHelpTicket();
        System.out.println(sugar.toJson());
    }

}
