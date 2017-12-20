package lapr.project.utils;

import com.googlecode.jatl.Html;
import java.io.StringWriter;

/**
 *
 * @author Utilizador
 */
public class HTMLExporter {

    protected StringWriter sw;
    protected Html html;

    /**
     * HTML Exporter
     */
    public HTMLExporter() {
        sw = new StringWriter();
        html = new Html(sw);
    }

    /**
     * Method to return a String representation of the actual output built by
     * JATL from the buffer
     *
     * @return string retrieved from the buffer
     */
    public String getOutput() {
        return sw.getBuffer().toString();
    }

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
