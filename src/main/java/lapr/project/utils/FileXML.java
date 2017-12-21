package lapr.project.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lapr.project.model.Network;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.VehicleList;

import java.util.logging.Logger;
import java.util.logging.Level;
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
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.Energy;
import lapr.project.model.Gear;
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.Throttle;
import lapr.project.model.TollClass;
import lapr.project.model.Vehicle;
import lapr.project.model.VelocityLimit;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
     *
     * @param travel
     * @param name - file name (if default uses the final variable of this
     * class)
     * @throws IOException - Exception of Input/Output
     */
    public static void saveXml(TravelByPhysics travel, String name) throws IOException {
        File fileXml;
        if ("default".equalsIgnoreCase(name)) {
            fileXml = XML_FILE;
        } else {
            fileXml = new File(name);
        }

        XStream xstream = new XStream(new DomDriver("UTF-8"));// Solves portuguese special caracters problem

        String xml = xstream.toXML(travel);
        try (PrintWriter out = new PrintWriter(new FileWriter(fileXml))) {
            out.println(xml);
        }

    }

    /**
     * Loads an Object from a xml file
     *
     * @param name - file path
     * @return object - object of TravelByPhysics
     * @throws IOException - Exception of Input/Output
     */
    public static TravelByPhysics loadXml(String name) throws IOException {
        File fileXml;
        if ("default".equalsIgnoreCase(name)) {
            fileXml = XML_FILE;
        } else {
            fileXml = new File(name);
        }

        TravelByPhysics travel;
        travel = new TravelByPhysics();

        XStream xstream = new XStream(new DomDriver("UTF-8"));

        try (BufferedReader in = new BufferedReader(new FileReader(fileXml))) {

            travel = (TravelByPhysics) xstream.fromXML(in);

        } catch (com.thoughtworks.xstream.mapper.CannotResolveClassException e) {
            Logger.getLogger(FileXML.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null,
                    "XML Tags in file '" + fileXml + "have problems",
                    "XML Reading Failure", JOptionPane.ERROR_MESSAGE);

            return null;
        }
        return travel;

    }

    public static VehicleList loadXmlVehicleList(String file) {

        try {
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            VehicleList vehicleList = new VehicleList();

            NodeList nList = doc.getElementsByTagName("vehicle");//vehicle
            for (int i = 0; i < nList.getLength(); i++) {
                if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nList.item(i);//vehicle
                    Vehicle car = new Vehicle();

                    car.setName(e.getAttribute("name"));//vehicle name

                    car.setDescription(e.getAttribute("description"));

                    NodeList nodes = e.getElementsByTagName("type");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setType(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("toll_class");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setTollClass(Integer.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("motorization");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setMotorization(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("fuel");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setFuel(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("mass");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setMass(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("load");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setLoad(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("drag");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setDrag(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("frontal_area");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setFrontalArea(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("rrc");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setRrc(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("wheel_size");
                    for (int j = 0; j < nodes.getLength(); j++) {

                        car.setWheelSize(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("velocity_limit_list");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element el = (Element) nodes.item(j);//velocity_limit_list
                            NodeList nodesLimit = el.getElementsByTagName("velocity_limit");
                            for (int k = 0; k < nodesLimit.getLength(); k++) {
                                if (nodesLimit.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                    Element elem = (Element) nodesLimit.item(k);//velocity_limit
                                    
                                    VelocityLimit limit = new VelocityLimit();

                                    NodeList nodesLimits = elem.getElementsByTagName("segment_type");
                                    for (int l = 0; l < nodesLimits.getLength(); l++) {
                                            
                                        limit.setSegmentType(nodesLimits.item(l).getTextContent());
                                    }

                                    nodesLimits = elem.getElementsByTagName("limit");
                                    for (int l = 0; l < nodesLimits.getLength(); l++) {
                                        limit.setLimit(Integer.valueOf(nodesLimits.item(l).getTextContent()));
                                    }

                                    car.getVelocityLimitList().addVelocityLimit(limit);
                                }
                            }
                        }
                    }

                    nodes = e.getElementsByTagName("energy");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element el = (Element) nodes.item(j);//energy
                            Energy energy = new Energy();
                            NodeList list = el.getElementsByTagName("min_rpm");
                            for (int k = 0; k < list.getLength(); k++) {
                                
                                energy.setMinRpm(Integer.valueOf(list.item(k).getTextContent()));
                            }

                            list = el.getElementsByTagName("max_rpm");
                            for (int k = 0; k < list.getLength(); k++) {
                                
                                energy.setMaxRpm(Integer.valueOf(list.item(k).getTextContent()));
                            }

                            list = el.getElementsByTagName("final_drive_ratio");
                            for (int k = 0; k < list.getLength(); k++) {
                                
                                energy.setFinalDriveRatio(Double.valueOf(list.item(k).getTextContent()));
                            }
                            
                            list = el.getElementsByTagName("energy_regeneration_ratio");
                            for (int k = 0; k < list.getLength(); k++) {
                                
                                energy.setErr(Double.valueOf(list.item(k).getTextContent()));
                            }
                            

                            list = el.getElementsByTagName("gear_list");
                            for (int k = 0; k < list.getLength(); k++) {
                                if (list.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                    Element element = (Element) list.item(k);//gear_list
                                
                                    NodeList gearList = element.getElementsByTagName("gear");
                                    for (int l = 0; l < gearList.getLength(); l++) {
                                        if (gearList.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                            Element elem = (Element) gearList.item(l);//gear
                                            Gear gear = new Gear();
                                            gear.setId(elem.getAttribute("id"));
                                            NodeList ratios = elem.getElementsByTagName("ratio");
                                            for (int m = 0; m < ratios.getLength(); m++) {
                                
                                                gear.setRatio(Double.valueOf(ratios.item(m).getTextContent()));
                                            }

                                            energy.addGear(gear);
                                        }

                                    }

                                }

                            }
                            nodes = e.getElementsByTagName("throttle_list");
                            for (int q = 0; q < nodes.getLength(); q++) {
                                if (nodes.item(q).getNodeType() == Node.ELEMENT_NODE) {
                                    Element eler = (Element) nodes.item(q);//throttle_list
                                
                                    NodeList listElem = eler.getElementsByTagName("throttle");
                                    for (int k = 0; k < listElem.getLength(); k++) {
                                        if (listElem.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                            Element elem = (Element) listElem.item(k);//throttle
                                            Throttle throttle = new Throttle();
                                
                                            throttle.setId(elem.getAttribute("id"));

                                            NodeList lastList = elem.getElementsByTagName("regime");
                                            for (int l = 0; l < lastList.getLength(); l++) {
                                                if (lastList.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                    Element regimeElem = (Element) lastList.item(l);//regime
                                                    
                                                    Regime regime = new Regime();
                                                    
                                                    NodeList regimeList = regimeElem.getElementsByTagName("torque_low");
                                                    for (int m = 0; m < regimeList.getLength(); m++) {
                                                        regime.setTorqueLow(Integer.valueOf(regimeList.item(m).getTextContent()));
                                                    }
                                                    
                                                    regimeList = regimeElem.getElementsByTagName("torque_high");
                                                    for (int m = 0; m < regimeList.getLength(); m++) {
                                                        regime.setTorqueHigh(Integer.valueOf(regimeList.item(m).getTextContent()));
                                                    }

                                                    regimeList = regimeElem.getElementsByTagName("rpm_low");
                                                    for (int m = 0; m < regimeList.getLength(); m++) {
                                                        regime.setRpmLow(Integer.valueOf(regimeList.item(m).getTextContent()));
                                                    }

                                                    regimeList = regimeElem.getElementsByTagName("rpm_high");
                                                    for (int m = 0; m < regimeList.getLength(); m++) {
                                                        regime.setRpmHigh(Integer.valueOf(regimeList.item(m).getTextContent()));
                                                    }

                                                    regimeList = regimeElem.getElementsByTagName("SFC");
                                                    for (int m = 0; m < regimeList.getLength(); m++) {
                                                        regime.setSfc(Double.valueOf(regimeList.item(m).getTextContent()));
                                                    }

                                                    throttle.getRegimeList().add(regime);
                                                }
                                            }

                                            energy.getThrottleList().add(throttle);
                                        }
                                    }
                                }
                            }
                            car.setEnergy(energy);
                        }
                    }

                    vehicleList.addVehicle(car);
                }

            }
            return vehicleList;
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            Logger.getLogger(FileXML.class.getName()).log(Level.SEVERE, null, e);

           
        }

        return null;
    }

    /**
     * Loads a xml file with the Road Network Data
     *
     * @param file - file path
     * @return Network object with the data in the file
     */
    public static Network loadXmlNetwork(String file) {

        try {
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Network roadNetwork = new Network();

            roadNetwork.setId("Description: " + doc.getDocumentElement().getAttribute("id"));


            roadNetwork.setDescription(doc.getDocumentElement().getAttribute("description"));

            //node_list
            NodeList nList = doc.getElementsByTagName("node_list");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
               

                NodeList nodes = nNode.getChildNodes();

                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nodes.item(i);
                        
                        roadNetwork.addNode(e.getAttribute("id"));
                    }
                }

            }

            /**
             * road_list
             */
            nList = doc.getElementsByTagName("road_list");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                

                NodeList nodes = nNode.getChildNodes();

                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nodes.item(i);//road
                        Road road = new Road();
                        
                        road.setId(e.getAttribute("id"));
                        NodeList list = e.getElementsByTagName("road_name");//road_name
                        for (int j = 0; j < list.getLength(); j++) {
                            
                            road.setName(list.item(j).getTextContent());
                        }
                        list = e.getElementsByTagName("typology");
                        for (int j = 0; j < list.getLength(); j++) {
                            
                            road.setTypology(list.item(j).getTextContent());
                        }
                        list = e.getElementsByTagName("toll_fare");
                        for (int j = 0; j < list.getLength(); j++) {
                            if (list.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element toll = (Element) list.item(j);
                                NodeList tollList = toll.getElementsByTagName("class");
                                for (int k = 0; k < tollList.getLength(); k++) {
                                    Element vehicle_class = (Element) tollList.item(k);
                                    TollClass classe = new TollClass(vehicle_class.getAttribute("id"), Double.valueOf(tollList.item(k).getTextContent()));
                                    road.getTollFare().getListClasses().add(classe);  
                                }
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
                

                NodeList nodes = nNode.getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nodes.item(i);//road_section
                        RoadSection section = new RoadSection();
                        section.setBegin(e.getAttribute("begin"));
                        section.setEnd(e.getAttribute("end"));

                        NodeList list = e.getElementsByTagName("road_id");
                        for (int j = 0; j < list.getLength(); j++) {
                   
                            section.setRoadId(list.item(j).getTextContent());
                        }

                        list = e.getElementsByTagName("direction");
                        for (int j = 0; j < list.getLength(); j++) {
                            
                            section.setDirection(list.item(j).getTextContent());
                        }

                        list = e.getElementsByTagName("segment_list");
                        for (int j = 0; j < list.getLength(); j++) {
                            if (list.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element el = (Element) list.item(j);
                                
                                NodeList seglist = el.getElementsByTagName("segment");
                                for (int k = 0; k < seglist.getLength(); k++) {
                                    if (seglist.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                        Element segment = (Element) seglist.item(k);
                                        Segment roadSegment = new Segment();
                                        
                                        roadSegment.setId(segment.getAttribute("id"));

                                        //init_height
                                        NodeList seg = e.getElementsByTagName("init_height");
                                        
                                        roadSegment.setInitHeight(Double.valueOf(seg.item(k).getTextContent()));

                                        //final_height
                                        seg = e.getElementsByTagName("final_height");
                                        
                                        roadSegment.setFinalHeight(Double.valueOf(seg.item(k).getTextContent()));

                                        //length
                                        seg = e.getElementsByTagName("length");
                                        
                                        roadSegment.setLength(seg.item(k).getTextContent());
                                        //max_velocity
                                        seg = e.getElementsByTagName("max_velocity");
                                        
                                        roadSegment.setMaxVelocity(seg.item(k).getTextContent());

                                        //min_velocity
                                        seg = e.getElementsByTagName("min_velocity");
                                        
                                        roadSegment.setMinVelocity(seg.item(k).getTextContent());

                                        //wind_direction
                                        seg = e.getElementsByTagName("wind_direction");
                                        
                                        roadSegment.setWindDirection(Double.valueOf(seg.item(k).getTextContent()));
                                        //wind_speed
                                        seg = e.getElementsByTagName("wind_speed");
                                        
                                        roadSegment.setWindSpeed(seg.item(k).getTextContent());

                                        section.addSegment(roadSegment);
                                    }
                                }

                            }
                        }

                        roadNetwork.addRoadSection(section);
                    }
                }
            }
            roadNetwork.loadMap();
            return roadNetwork;
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            Logger.getLogger(FileXML.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

}
