package lapr.project.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lapr.project.model.Network;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lapr.project.model.Road;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.TollFare;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

        TravelByPhysics travel;
        travel = new TravelByPhysics();

        XStream xstream = new XStream(new DomDriver("UTF-8"));

        try (BufferedReader in = new BufferedReader(new FileReader(file_xml))) {

            travel = (TravelByPhysics) xstream.fromXML(in);

        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {

            JOptionPane.showMessageDialog(null,
                    "XML Tags in file '" + file_xml + "have problems",
                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);

            return null;
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

        VehicleList list;
        list = new VehicleList();

        XStream xstream = new XStream(new DomDriver("UTF-8"));

        try (BufferedReader in = new BufferedReader(new FileReader(file_xml))) {

            list = (VehicleList) xstream.fromXML(in);

        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {

            JOptionPane.showMessageDialog(null,
                    "XML Tags in file '" + file_xml + " have problems",
                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return list;

    }


    public static Network loadXmlNetwork(String file) {

        try {
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Network roadNetwork = new Network();

            System.out.print("Root element: ");// ROOT
            System.out.println(doc.getDocumentElement().getNodeName());

            System.out.println("ID: " + doc.getDocumentElement().getAttribute("id"));
            roadNetwork.setId("Description: " + doc.getDocumentElement().getAttribute("id"));

            System.out.println(doc.getDocumentElement().getAttribute("description"));
            roadNetwork.setDescription(doc.getDocumentElement().getAttribute("description"));

            System.out.println("----------------------------");

            //node_list
            NodeList nList = doc.getElementsByTagName("node_list");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println(nNode.getNodeName());

                NodeList nodes = nNode.getChildNodes();

                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nodes.item(i);
                        System.out.println(e.getAttribute("id"));
                        roadNetwork.addNode(e.getAttribute("id"));
                    }
                }

            }

            //road_list
            nList = doc.getElementsByTagName("road_list");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println(nNode.getNodeName());

                NodeList nodes = nNode.getChildNodes();

                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nodes.item(i);//road
                        Road road = new Road();
                        System.out.println(e.getAttribute("id"));
                        road.setId(e.getAttribute("id"));
                        NodeList list = e.getElementsByTagName("road_name");//road_name
                        for (int j = 0; j < list.getLength(); j++) {
                            System.out.println("-" + list.item(j).getTextContent());
                            road.setName(list.item(j).getTextContent());
                        }
                        list = e.getElementsByTagName("typology");
                        for (int j = 0; j < list.getLength(); j++) {
                            System.out.println("-" + list.item(j).getTextContent());
                            road.setTypology(list.item(j).getTextContent());
                        }
                        list = e.getElementsByTagName("toll_fare");
                        for (int j = 0; j < list.getLength(); j++) {
                            if (list.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element toll = (Element) list.item(j);
                                TollFare toll_fare = new TollFare();
                                NodeList toll_list = toll.getElementsByTagName("class");
                                for (int k = 0; k < toll_list.getLength(); k++) {
                                    Element vehicle_class = (Element) toll_list.item(k);
                                    System.out.println(vehicle_class.getAttribute("id"));
                                    System.out.println(toll_list.item(k).getTextContent());
                                    toll_fare.addClass(vehicle_class.getAttribute("id"), Double.valueOf(toll_list.item(k).getTextContent()));
                                }
                                road.setToll_fare(toll_fare);//road toll_fare
                            }
                        }

                        roadNetwork.addRoad(road);//adds the road to the list

                    }
                }
            }

            //section_list
            nList = doc.getElementsByTagName("section_list");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println(nNode.getNodeName());

                NodeList nodes = nNode.getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nodes.item(i);//road_section
                        RoadSection section = new RoadSection();
                        System.out.println(e.getTagName());
                        System.out.println("-" + e.getAttribute("begin"));
                        section.setBegin(e.getAttribute("begin"));
                        System.out.println("-" + e.getAttribute("end"));
                        section.setEnd(e.getAttribute("end"));

                        NodeList list = e.getElementsByTagName("road_id");
                        for (int j = 0; j < list.getLength(); j++) {
                            System.out.println(list.item(j).getTextContent());
                            section.setRoad_id(list.item(j).getTextContent());
                        }

                        list = e.getElementsByTagName("direction");
                        for (int j = 0; j < list.getLength(); j++) {
                            System.out.println(list.item(j).getTextContent());
                            section.setDirection(list.item(j).getTextContent());
                        }

                        list = e.getElementsByTagName("segment_list");
                        for (int j = 0; j < list.getLength(); j++) {
                            if (list.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element el = (Element) list.item(j);
                                System.out.println(el.getTagName());
                                NodeList seglist = el.getElementsByTagName("segment");
                                for (int k = 0; k < seglist.getLength(); k++) {
                                    if (seglist.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                        Element segment = (Element) seglist.item(k);
                                        Segment road_segment = new Segment();
                                        System.out.println(segment.getAttribute("id"));
                                        road_segment.setId(segment.getAttribute("id"));

                                        //init_height
                                        NodeList seg = e.getElementsByTagName("init_height");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setInit_height(Double.valueOf(seg.item(k).getTextContent()));

                                        //final_height
                                        seg = e.getElementsByTagName("final_height");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setFinal_height(Double.valueOf(seg.item(k).getTextContent()));

                                        //length
                                        seg = e.getElementsByTagName("length");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setLength(seg.item(k).getTextContent());
                                        //max_velocity
                                        seg = e.getElementsByTagName("max_velocity");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setMax_velocity(seg.item(k).getTextContent());

                                        //min_velocity
                                        seg = e.getElementsByTagName("min_velocity");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setMin_velocity(seg.item(k).getTextContent());
                                        
                                        //wind_direction
                                        seg = e.getElementsByTagName("wind_direction");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setWind_direction(Double.valueOf(seg.item(k).getTextContent()));
                                        //wind_speed
                                        seg = e.getElementsByTagName("wind_speed");
                                        System.out.println(seg.item(k).getTextContent());
                                        road_segment.setWind_speed(seg.item(k).getTextContent());
                                        
                                        section.addSegment(road_segment);
                                    }
                                }

                            }
                        }

                        roadNetwork.addRoadSection(section);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
