/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
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

    /**
     * Project
     */
    private Project p = new Project();

    /**
     * Contructor to Save the HTML file
     *
     * @param p
     * @throws IOException
     */
    public SaveToHTML(Project p) throws IOException {
        HTMLExporter htmlBuilder = new HTMLExporter();
        this.p = p;
        buildHTML(htmlBuilder);
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
        HTMLExporter htmlBuilder = new HTMLExporter();
        htmlBuilder.getClass();
    }

    /**
     * Starts building the file
     *
     * @param htmlBuilder
     * @return
     */
    public HTMLExporter startHTML(HTMLExporter htmlBuilder) {
        buildHTML(htmlBuilder);
        return htmlBuilder;
    }

    /**
     * Builds the html file according to specifications
     *
     * @param htmlBuilder
     */
    private void buildHTML(HTMLExporter htmlBuilder) {
        /**
         * Head
         */
        htmlBuilder.addHtml().addTitle(p.getName()).closeTag()
                .addHead()
                .addHead1(p.getName()).closeTag()
                .addParagraph("-"+p.getDescription()).closeTag()
                //add analysis name
                //travel time
                //number
                //CLOSE THE TAGS UNTIL HEAD END
                .closeTag();//closeHead

        /**
         * Body
         */
        htmlBuilder.addBody().addDiv("")
                .addHead2("------------ROADS------------").closeTag();
        for (int i = 0; i < p.getNetwork().getSection_list().size(); i++) {
            RoadSection temp = p.getNetwork().getSection_list().get(i);
            htmlBuilder.addDiv("")
                        .addHead3("Road "+temp.getRoad_id()).closeTag();
            for (Segment seg : temp.getSegment_list()) {
                htmlBuilder.addDiv("").addHead4("-----------------SEGMENTS-----------------").closeTag()
                        .addHead4("Segment "+seg.getId()).closeTag()
                        .addParagraph("--Length : " + seg.getLength()).closeTag()
                        .addParagraph("--Wind Speed : "+seg.getWind_speed()).closeTag()
                        .addParagraph("--Minimal Velocity : "+seg.getMin_velocity()).closeTag()
                        .addParagraph("--Maximum Velocity : "+seg.getMax_velocity()).closeTag()
                        .addParagraph("--Initial Height : "+seg.getInit_height()).closeTag()
                        .addParagraph("--Final Height : "+seg.getFinal_height()).closeTag()
                        .addParagraph("--Wind Direction : "+seg.getWind_direction()).closeTag()
                        /*Add the rest of data*/
                        .addParagraph("_______________________________________").closeTag();
            }
            
        }
        htmlBuilder.closeAllTags()
                .endWriting();
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
    public void output(HTMLExporter outputData,File fileLocation) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileLocation))) {
            out.println(outputData.getOutput());
        }
    }

    /**
     * Sets the Project
     * @param p 
     */
    public void setProject(Project p) {
        this.p = p;
    }

}
