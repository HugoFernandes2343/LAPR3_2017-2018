package lapr.project.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lapr.project.model.RoadNetwork;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.VehicleList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class FileXML implements Serializable {
     /**
     * Variable that defines the serialVersionUID
     */
    static final long serialVersionUID = 111L;

    /**
     * Variable that creates the XML FILE
     */
    private static final File XML_FILE = new File("file.xml");

    /**
     * Method that saves the current data to a file
     * @param name - file name (if default uses the final variable of this class)
     * @throws IOException - Exception of Input/Output
     */
    public static void saveXml(TravelByPhysics travel, String name) throws IOException {
        File file_xml;
        if(name.equalsIgnoreCase("default")){
            file_xml = XML_FILE;
        } else {
            file_xml = new File(name);
        }

        XStream xstream = new XStream(new DomDriver("UTF-8"));// Solves portuguese special caracters problem

        String xml = xstream.toXML(travel);
        try (PrintWriter out = new PrintWriter(new FileWriter(file_xml))) {
            out.println(xml);
        }

    }

    /**
     * Loads an Object from a xml file
     * @param name - file path
     * @return object  - object of TravelByPhysics
     * @throws IOException - Exception of Input/Output
     */
    public static TravelByPhysics loadXml(String name) throws IOException {
        File file_xml;
        if(name.equalsIgnoreCase("default")){
            file_xml = XML_FILE;
        } else {
            file_xml = new File(name);
        }

        TravelByPhysics travel = new TravelByPhysics();

        XStream xstream = new XStream(new DomDriver("UTF-8"));

        try (BufferedReader in = new BufferedReader(new FileReader(file_xml))) {

            travel = (TravelByPhysics) xstream.fromXML(in);

        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {

            JOptionPane.showMessageDialog(null,
                    "XML Tags in file '" + file_xml + "have problems",
                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);
        }
        return travel;

    }

    /**
     * Load a vehicle list file and saves data
     * @param name - file path
     * @return VehicleList
     * @throws IOException
     */
    public static VehicleList loadXmlVehicleList(String name) throws IOException {
        File file_xml;
        if(name.equalsIgnoreCase("default")){
            file_xml = XML_FILE;
        } else {
            file_xml = new File(name);
        }

        VehicleList list = new VehicleList();

        XStream xstream = new XStream(new DomDriver("UTF-8"));

        try (BufferedReader in = new BufferedReader(new FileReader(file_xml))) {

            list = (VehicleList) xstream.fromXML(in);

        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {

            JOptionPane.showMessageDialog(null,
                    "XML Tags in file '" + file_xml + "have problems",
                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);
        }
        return list;

    }


    /**
     * Load a road network file and saves data
     * @param name - file path
     * @return RoadNetwork
     * @throws IOException
     */
    public static RoadNetwork loadXmlRoadNetwork(String name) throws IOException {
        File file_xml;
        if(name.equalsIgnoreCase("default")){
            file_xml = XML_FILE;
        } else {
            file_xml = new File(name);
        }

        RoadNetwork network = new RoadNetwork();

        XStream xstream = new XStream(new DomDriver("UTF-8"));

        try (BufferedReader in = new BufferedReader(new FileReader(file_xml))) {

            network = (RoadNetwork) xstream.fromXML(in);

        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {

            JOptionPane.showMessageDialog(null,
                    "XML Tags in file '" + file_xml + "have problems",
                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);
        }
        return network;

    }





}
