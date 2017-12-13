/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

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
     * Test of addDiv method, of class HTMLExporter.
     */
    @Test
    public void testAddDiv() {
        System.out.println("addDiv");
        ht = "\n"
                + "<div>teste\n"
                + "</div>";
        HTMLExporter instance = new HTMLExporter();
        instance.addDiv("teste");
        instance.closeTag();
        instance.endWriting();
        assertEquals(ht, instance.getOutput());
    }

    /**
     * Test of addHead2 method, of class HTMLExporter.
     */
    @Test
    public void testAddHead2() {
        System.out.println("addHead2");
        ht="\n"
                +"<h2>HeaderText\n"
                + "</h2>";
        String headerText = "HeaderText";
        HTMLExporter instance = new HTMLExporter();
        instance.addHead2(headerText);
        instance.closeTag();
        instance.endWriting();
        assertEquals(ht,instance.getOutput());
    }

    /**
     * Test of addParagraph method, of class HTMLExporter.
     */
    @Test
    public void testAddParagraph() {
        System.out.println("addParagraph");
        ht = "\n"
                + "<p>teste\n"
                + "</p>";
        HTMLExporter instance = new HTMLExporter();
        instance.addParagraph("teste");
        instance.closeTag();
        instance.endWriting();
        assertEquals(ht, instance.getOutput());
    }

    /**
     * Test of getOutput method, of class HTMLExporter.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        HTMLExporter instance = new HTMLExporter();
        instance.html.text("test");
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
        instance.html.text("garbageToBeReset");
        String test = "garbageToBeReset";
        instance.resetHTML();
        assertNotEquals(test, instance.getOutput());
        assertEquals(instance.getOutput(),"");
    }

}
