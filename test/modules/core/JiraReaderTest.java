/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.core;

import modules.jira.JiraReader;
import java.io.IOException;
import java.net.ProtocolException;
import javax.json.JsonObject;
import org.junit.Test;

/**
 *
 * @author kajornjit.songsaen
 */
public class JiraReaderTest {
    
    public JiraReaderTest() {
    }

    @Test
    public void testHelpTicket() throws ProtocolException, IOException {
        JiraReader helpticket = new JiraReader();
        JsonObject response = helpticket.getSugarTicket();
        
        System.out.println(response.getInt("total"));
        System.out.println(response.getJsonArray("issues"));
    }
    
}
