/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.datalayer;

import com.googlecode.jatl.Html;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Project;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.utils.HTMLExporter;

/**
 *
 * @author Utilizador
 */
public class SaveToHTML {

    /**
     * File location
     */
    private File fileLocation;

    private static final String fontColor = "color: white";
    
    /**
     * Project
     */
    private Project p = new Project();
    
    private NetworkAnalysis net;

    /**
     * Contructor to Save the HTML file
     *
     * @param p
     * @throws IOException
     */
    public SaveToHTML(Project p,NetworkAnalysis netAnalysis) throws IOException {
        HTMLExporter htmlBuilder = new HTMLExporter();
        this.p = p;
        this.net=netAnalysis;
        buildHTML(htmlBuilder.getHTML());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(fileChooser);
        File file = fileChooser.getSelectedFile();
        this.fileLocation = file;
        output(htmlBuilder);
    }

    /**
     * Empty contructor.Only creates the HTMLExporter object
     */
    public SaveToHTML() {
        Class<HTMLExporter> htmlBuilder = HTMLExporter.class;
        htmlBuilder.getClass();
    }

    /**
     * Starts building the file
     *
     * @param htmlBuilder
     * @return
     */
    public Html startHTML(Html html) {
        buildHTML(html);
        return html;
    }

    private void buildHTML(Html html) {

        html.html()
                .style().text("body {"
                        + " background-color: #503f75 "
                        + "}"
                        + "p {"
                        + fontColor
                        + "}"
                        + "h1 {"
                        + fontColor
                        + "}"
                        + "h2 {"
                        + fontColor
                        + "}"
                        + "h3 {"
                        + fontColor
                        + "}"
                        + "h4 {"
                        + fontColor
                        + "}").end(2)
                .title().text(p.getName()).end()
                /**
                 * Head
                 */
                .head().font().face("Calibri").end()
                .center().h1().text(p.getName()).end(2)
                .p().text(p.getDescription()).end()
                /*Analysis*/
                .center().h2().text("Analysis by "+this.net.getType()).end(2)
                .p().text(""+this.net.getId()).br()
                .text(this.net.getName()).br()
                .text(""+this.net.getTravellTime()).end()
                .end();
        /**
         * End Head
         */
        /**
         * Begin Body
         */
        html.body().font().face("Calibri Regular").end()
                .div()
                .center().h2().text("ROADS").end(2);
        for (int i = 0; i < p.getNetwork().getSectionList().size(); i++) {
            RoadSection temp = p.getNetwork().getSectionList().get(i);
            html.div()/*.center()*/.h3().text("Road " + temp.getRoadId()).end()
                    .div().center().h4().text("SEGMENTS").end(2);
            for (Segment seg : temp.getSegmentList()) {
                html.h4().text("Segment " + seg.getId()).end()
                        .p().text("Length : " + seg.getLength()).end()
                        .p().text("Wind Speed : " + seg.getWindSpeed()).end()
                        .p().text("Minimal Velocity : " + seg.getMinVelocity()).end()
                        .p().text("Maximum Velocity : " + seg.getMaxVelocity()).end()
                        .p().text("Initial Height : " + seg.getInitHeight()).end()
                        .p().text("Final Height : " + seg.getFinalHeight()).end()
                        .p().text("Wind Direction : " + seg.getWindDirection()).end()
                        /*Add as needed*/
                        .center().h3().text("**").end(2);
            }
            html.end();
        }
        html.end();

        html.div()
                .center().p().text("ISEP").br().text("2017-2018").br().text("LAPR3 Group042").end(2);
        html.endAll();
        html.done();
    }

    /**
     * Writes to file , no file ,provided it is selected on creation
     *
     * @param outputData
     * @throws IOException
     */
    public void output(HTMLExporter outputData) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileLocation))) {
            out.println(outputData.getOutput());
        }
    }

    /**
     * Writes to file, recieves the file via parameter
     *
     * @param outputData
     * @throws IOException
     */
    protected void output(HTMLExporter outputData, File fileLocation) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileLocation))) {
            out.println(outputData.getOutput());
        }
    }

    /**
     * Sets the Project
     *
     * @param p
     */
    protected void setProject(Project p) {
        this.p = p;
    }

}
