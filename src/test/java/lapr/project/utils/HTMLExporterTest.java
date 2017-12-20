/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import com.googlecode.jatl.Html;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class HTMLExporterTest {

    HTMLExporter exporter;
    String ht = new String();

    public HTMLExporterTest() {
        ht = "\n"
                + "<html>\n"
                + "<body>\n"
                + "</body>\n"
                + "</html>";
    }

    /**
     * Test of getOutput method, of class HTMLExporter.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        HTMLExporter instance = new HTMLExporter();
        instance.getHTML().text("test");
        String expResult = "test";
        String result = instance.getOutput();
        assertEquals(expResult, result);
    }

    /**
     * Test of resetHTML method, of class HTMLExporter.
     */
    @Test
    public void testResetHTML() {
        System.out.println("resetHTML");
        HTMLExporter instance = new HTMLExporter();
        Html text = instance.getHTML().text("garbageToBeReset");
        String test = "garbageToBeReset";
        instance.resetHTML();
        assertNotEquals(test, instance.getOutput());
        assertEquals(instance.getOutput(),"");
    }

}
