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
    private final Project p;
    
    /**
     * Contructor to Save the HTML file
     * @param p
     * @throws IOException 
     */
    public SaveToHTML(Project p) throws IOException{
        HTMLExporter htmlBuilder = new HTMLExporter();
        this.p=p;
        buildHTML(htmlBuilder);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(fileChooser);
        File file = fileChooser.getSelectedFile();
        this.fileLocation = file;
        output(htmlBuilder);
    }

    private void buildHTML(HTMLExporter htmlBuilder) {
        /**
         * Head
         */
        htmlBuilder.addHtml()
                    .addHead()
                        .addHead2(p.getName()).closeTag()
                        .addParagraph(p.getDescription()).closeTag() 
                    //add analysis name
                    //travel time
                    //nummber
                    //CLOSE THE TAGS UNTIL HEAD END
                    .closeTag();
        
                    /**
                     * Body
                     */
                    htmlBuilder.addBody();
                    for(int i=0;i<p.getNetwork().getSection_list().size();i++){
                        RoadSection temp = p.getNetwork().getSection_list().get(i);
                        htmlBuilder.addDiv("Road "+temp.getRoad_id());
                        for(Segment seg : temp.getSegment_list()){
                            htmlBuilder.addDiv("")
                                    .addHead3("Segment "+seg.getId())
                                    .addParagraph("Length :"+seg.getLength())
                                    /*Add the rest of data*/;
                        }
                    }
        htmlBuilder.closeAllTags()
                    .endWriting();
    }

    private void output(HTMLExporter outputData) throws IOException {
//        outputData.getOutput();
        try (PrintWriter out = new PrintWriter(new FileWriter(fileLocation))) {
            out.println(outputData.getOutput());
        }
    }
    
}
