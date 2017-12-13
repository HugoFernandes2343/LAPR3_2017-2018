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
    Html html;

    /**
     * HTML Exporter
     */
    HTMLExporter() {
        html = new Html(sw);
        //initializeHTML();
    }

    /**
     * Initializes the html document. Adds <html> and <body> tags in this order.
     * Closing these tags further in the document requires the use of the
     * closeTags family of methods
     */
    void initializeHTML() {
        html.html();
        html.body();
    }

    /**
     * Adds a <header> tag so that the html has a title. Note that this title
     * isnt displayed in the actual visualization of the page. Naming purposes
     * only
     *
     * @param text text to be the title
     */
    void addHeader(String text) {
        html.head().text(text);
    }

    /**
     * Add a division segment <div>
     *
     * @param text Text to serve as ID for the div when in HTML, this is not
     * displayed outside of source code
     */
    void addDiv(String text) {
        html.div().text(text);
    }

    /**
     * Add a header <h2>
     *
     * @param headerText text to add/header title
     */
    void addHead2(String headerText) {
        html.h2().text(headerText);
    }

    /**
     * Add a pharagraph
     *
     * @param string Text to add in the pharagraph
     */
    void addParagraph(String string) {
        html.p().text(string);
    }

    /**
     * Closes the last opened tag
     */
    void closeTag() {
        html.end();
    }

    /**
     * Closes a certain number of desired tags
     *
     * @param i number of tags to close
     */
    void closeSetOfTags(int i) {
        html.end(i);
    }

    /**
     * Cloes all openedTags
     */
    void closeAllTags() {
        html.endAll();
    }

    /**
     * Finishes building the file
     */
    void endWriting() {
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
    String getOutput() {
        return sw.getBuffer().toString();
    }

    /**
     * Resets the buffer cleaning all text
     */
    void resetHTML() {
        sw = null;
        sw = new StringWriter();
        html = null;
        html = new Html(sw);
    }
}
