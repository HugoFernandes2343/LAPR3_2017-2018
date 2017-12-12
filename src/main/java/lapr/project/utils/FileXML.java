//package lapr.project.utils;
//
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.Serializable;
//import javax.swing.JOptionPane;
//
//public class FileXML implements Serializable {
//
//    /**
//     * Variable that defines the serialVersionUID
//     */
//    static final long serialVersionUID = 111L;
//    
//    /**
//     * Variable that creates the XML FILE
//     */
//    private static final File XML_FILE = new File("EventCentre.xml");
//
//    /**
//     * Method that saves the current data to a file
//     * @param ObjectToExport - fair centre object that is being saved
//     * @param name - file name (if default uses the final variable of this class)
//     * @throws IOException 
//     */  
//    public static void saveXml(Object ObjectToExport, String name) throws IOException {
//        File file_xml;
//        if(name.equalsIgnoreCase("default")){
//            file_xml = XML_FILE;
//        } else {
//            file_xml = new File(name);
//        }
//               
//        XStream xstream = new XStream(new DomDriver("UTF-8"));// Solves portuguese special caracters problem
//        
//        String xml = xstream.toXML(ObjectToExport);
//        try (PrintWriter out = new PrintWriter(new FileWriter(file_xml))) {
//            out.println(xml);
//        }
//        
//    }
//    
//    /**
//     * Loads a FairCentre Object from a xml file
//     * @param name
//     * @return faircentre object
//     * @throws IOException 
//     */
//    public static Object loadXml(String name) throws IOException {
//        File file_xml;
//        if(name.equalsIgnoreCase("default")){
//            file_xml = XML_FILE;
//        } else {
//            file_xml = new File(name);
//        }
//        
//        Object objectToExport = new Object();
//        
//        XStream xstream = new XStream(new DomDriver("UTF-8"));
//        
//        try (BufferedReader in = new BufferedReader(new FileReader(file_xml))) {
//            
//            objectToExport = xstream.fromXML(in);
//            
//        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {
//            
//            JOptionPane.showMessageDialog(null,
//                    "XML Tags in file '" + file_xml + "have problems",
//                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);
//        }
//        return objectToExport;
// 
//    }
//    
//    
//    
//    
//}
