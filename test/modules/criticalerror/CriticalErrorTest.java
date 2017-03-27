/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.criticalerror;

import java.io.IOException;
import org.junit.*;

/**
 *
 * @author kajornjit.songsaen
 */
public class CriticalErrorTest {

    public CriticalErrorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Ignore
    public void testCaiError() throws IOException {
        CaiError cai = new CaiError();
        System.out.println("CAI Error\n" + cai.toString());
        System.out.println(cai.getMessages());
    }
    
    @Test
    public void testCaiErrorTextCsv() throws IOException {
        CaiErrorTextCsv cai = new CaiErrorTextCsv();
        System.out.println(cai.getCaiErrorTextCsv());
    }

    @Ignore
    public void testSugarError() throws IOException {
        SugarError sugar = new SugarError();
        System.out.println("Sugar Error\n" + sugar.toString());
        System.out.println(sugar.getMessages());
    }

    @Ignore
    public void testSugarErrorTotal() throws IOException {
        SugarErrorTotal sugar = new SugarErrorTotal();
        System.out.println("Sugar Error Total\n" + sugar.toString());
    }

    @Ignore
    public void testSugarErrorDetail() throws IOException {
        SugarErrorDetail sugar = new SugarErrorDetail();
        System.out.println("Sugar Error Detail\n" + sugar.toString());
    }
}
