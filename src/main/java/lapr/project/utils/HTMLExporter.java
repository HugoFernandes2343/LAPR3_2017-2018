package lapr.project.utils;

import com.googlecode.jatl.Html;
import java.io.StringWriter;

/**
 *
 * @author Utilizador
 * @param <T>
 */
public class HTMLExporter/*<T>*/ {

    StringWriter sw = new StringWriter();
//    StringWriter writer = sw;
    private Html html;

    /**
     * HTML Exporter
     */
    public HTMLExporter() {
        html = new Html(sw);
        //initializeHTML();
    }

    /**
     * Initializes the html document. Adds html and body tags in this order.
     * Closing these tags further in the document requires the use of the
     * closeTags family of methods
     */
    public HTMLExporter initializeHTML() {
        html.html();
        html.body();
        return this;
    }

    /**
     * Adds html tag
     *
     * @return
     */
    public HTMLExporter addHtml() {
        html.html();
        return this;
    }

    /**
     * Adds body tag
     *
     * @return
     */
    public HTMLExporter addBody() {
        html.body();
        return this;
    }

    /**
     * Adds a head tag.
     *
     * @param text text to be the title
     * @return
     */
    public HTMLExporter addHead(String text) {
        html.head().text(text);
        return this;
    }

    /**
     * Adds a head tag.
     *
     * @param text text to be the title
     * @return
     */
    public HTMLExporter addHead() {
        html.head();
        return this;
    }

    /**
     * Adds the title tag
     *
     * @param text text to be the title
     */
    public HTMLExporter addTitle(String text) {
        html.title().text(text);
        return this;
    }

    /**
     * Add a division segment div
     *
     * @param text Text to serve as ID for the div when in HTML, this is not
     * displayed outside of source code
     */
    public HTMLExporter addDiv(String text) {
        html.div().text(text);
        return this;
    }

    /**
     * Add a header h1
     *
     * @param headerText text to add/header title
     */
    public HTMLExporter addHead1(String headerText) {
        html.h1().text(headerText);
        return this;
    }

    /**
     * Add a header h2
     *
     * @param headerText text to add/header title
     */
    public HTMLExporter addHead2(String headerText) {
        html.h2().text(headerText);
        return this;
    }

    /**
     * Add a header h3
     *
     * @param headerText text to add/header title
     */
    public HTMLExporter addHead3(String headerText) {
        html.h3().text(headerText);
        return this;
    }

    /**
     * Add a pharagraph
     *
     * @param string Text to add in the pharagraph
     */
    public HTMLExporter addParagraph(String string) {
        html.p().text(string);
        return this;
    }

    /**
     * Closes the last opened tag
     */
    public HTMLExporter closeTag() {
        html.end();
        return this;
    }

    /**
     * Closes a certain number of desired tags
     *
     * @param i number of tags to close
     */
    public HTMLExporter closeSetOfTags(int i) {
        html.end(i);
        return this;
    }

    /**
     * Cloes all openedTags
     */
    public HTMLExporter closeAllTags() {
        html.endAll();
        return this;
    }

    /**
     * Finishes building the file
     */
    public void endWriting() {
        html.done();
    }

    //Add the rest of needed tags as more are needed
    //Figure out how to create the actual export , maybe string stream? after building the html
    /**
     * Method to return a String representation of the actual output built by
     * JATL from the buffer
     *
     * @return string retrieved from the buffer
     */
    public String getOutput() {
        return sw.getBuffer().toString();
    }

//    String getOutput() {
//        String output = sw.getBuffer().toString();
//        return output.substring(2, output.length()-2);
//    }
    /**
     * Resets the buffer cleaning all text
     */
    public void resetHTML() {
        sw = null;
        sw = new StringWriter();
        html = null;
        html = new Html(sw);
    }

    /**
     * Getter for the html Object that allows to append data
     *
     * @return html Object
     */
    public Html getHTML() {
        return this.html;
    }

}
