/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.bug;

import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarBugsTest {

    public SugarBugsTest() {
    }

    @Test
    public void testSomeMethod() throws IOException {
        SugarBugs sugarbug = new SugarBugs();
        System.out.println(sugarbug.toJson());
    }

}
