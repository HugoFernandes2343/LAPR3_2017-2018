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
     * Adds a <header> tag.
     * @param text text to be the title
     */
    Html addHeader(String text) {
        return html.head().text(text);
    }

    /**
     * Adds the <title> tag
     * @param text text to be the title
     */
    Html addTitle(String text){
        return html.title().text(text);
    }
    
    /**
     * Add a division segment <div>
     *
     * @param text Text to serve as ID for the div when in HTML, this is not
     * displayed outside of source code
     */
    Html addDiv(String text) {
        return html.div().text(text);
    }

    /**
     * Add a header <h2>
     *
     * @param headerText text to add/header title
     */
    Html addHead2(String headerText) {
        return html.h2().text(headerText);
    }

    /**
     * Add a pharagraph
     *
     * @param string Text to add in the pharagraph
     */
    Html addParagraph(String string) {
        return html.p().text(string);
    }

    /**
     * Closes the last opened tag
     */
    Html closeTag() {
        return html.end();
    }

    /**
     * Closes a certain number of desired tags
     *
     * @param i number of tags to close
     */
    Html closeSetOfTags(int i) {
        return html.end(i);
    }

    /**
     * Cloes all openedTags
     */
    Html closeAllTags() {
        return html.endAll();
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
        return sw/*.getBuffer()*/.toString();
    }
    
//    String getOutput() {
//        String output = sw.getBuffer().toString();
//        return output.substring(2, output.length()-2);
//    }

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
