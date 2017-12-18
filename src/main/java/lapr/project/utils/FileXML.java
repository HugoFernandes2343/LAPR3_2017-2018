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
import lapr.project.model.Energy;
import lapr.project.model.Gear;
import lapr.project.model.Regime;
import lapr.project.model.Road;
import lapr.project.model.RoadSection;
import lapr.project.model.Segment;
import lapr.project.model.Throttle;
import lapr.project.model.TollFare;
import lapr.project.model.Vehicle;
import lapr.project.model.VelocityLimit;
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

   public static VehicleList loadXmlVehicleList(String file) {

        try {
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            VehicleList vehicleList = new VehicleList();

            System.out.print("Root element: ");// ROOT
            System.out.println(doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("vehicle");//vehicle
            for (int i = 0; i < nList.getLength(); i++) {
                if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nList.item(i);//vehicle
                    Vehicle car = new Vehicle();
                    System.out.println(e.getAttribute("name"));
                    car.setName(e.getAttribute("name"));//vehicle name
                    System.out.println(e.getAttribute("description"));
                    car.setDescription(e.getAttribute("description"));

                    NodeList nodes = e.getElementsByTagName("type");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setType(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("toll_class");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setTollClass(Integer.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("motorization");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setMotorization(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("fuel");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setFuel(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("mass");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setMass(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("load");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setLoad(nodes.item(j).getTextContent());
                    }

                    nodes = e.getElementsByTagName("drag");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setDrag(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("frontal_area");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setFrontal_area(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("rrc");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setRrc(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("wheel_size");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        System.out.println(nodes.item(j).getTextContent());
                        car.setWheelSize(Double.valueOf(nodes.item(j).getTextContent()));
                    }

                    nodes = e.getElementsByTagName("velocity_limit_list");
                    for (int j = 0; j < nodes.getLength(); j++) {
                        if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element el = (Element) nodes.item(j);//velocity_limit_list
                            NodeList nodes_limit = el.getElementsByTagName("velocity_limit");
                            System.out.println(el.getNodeName());
                            for (int k = 0; k < nodes_limit.getLength(); k++) {
                                if (nodes_limit.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                    Element elem = (Element) nodes_limit.item(k);//velocity_limit
                                    System.out.println(elem.getNodeName());
                                    VelocityLimit limit = new VelocityLimit();

                                    NodeList nodes_limits = elem.getElementsByTagName("segment_type");
                                    for (int l = 0; l < nodes_limits.getLength(); l++) {
                                        System.out.println(nodes_limits.item(l).getTextContent());
                                        limit.setSegment_type(nodes_limits.item(l).getTextContent());
                                    }

                                    nodes_limits = elem.getElementsByTagName("limit");
                                    for (int l = 0; l < nodes_limits.getLength(); l++) {
                                        System.out.println(nodes_limits.item(l).getTextContent());
                                        limit.setLimit(Integer.valueOf(nodes_limits.item(l).getTextContent()));
                                    }

                                    car.getVelocity_limit_list().addVelocityLimit(limit);
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
                                System.out.println(list.item(k).getTextContent());
                                energy.setMinRpm(Integer.valueOf(list.item(k).getTextContent()));
                            }

                            list = el.getElementsByTagName("max_rpm");
                            for (int k = 0; k < list.getLength(); k++) {
                                System.out.println(list.item(k).getTextContent());
                                energy.setMaxRpm(Integer.valueOf(list.item(k).getTextContent()));
                            }

                            list = el.getElementsByTagName("final_drive_ratio");
                            for (int k = 0; k < list.getLength(); k++) {
                                System.out.println(list.item(k).getTextContent());
                                energy.setFinalDriveRatio(Double.valueOf(list.item(k).getTextContent()));
                            }

                            list = el.getElementsByTagName("gear_list");
                            for (int k = 0; k < list.getLength(); k++) {
                                if (list.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                    Element element = (Element) list.item(k);//gear_list
                                    System.out.println(element.getNodeName());

                                    NodeList gear_list = element.getElementsByTagName("gear");
                                    for (int l = 0; l < gear_list.getLength(); l++) {
                                        if (gear_list.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                            Element elem = (Element) gear_list.item(l);//gear
                                            Gear gear = new Gear();
                                            System.out.println(elem.getAttribute("id"));
                                            gear.setId(elem.getAttribute("id"));
                                            NodeList ratios = elem.getElementsByTagName("ratio");
                                            for (int m = 0; m < ratios.getLength(); m++) {
                                                System.out.println(ratios.item(m).getTextContent());
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
                                    System.out.println(eler.getNodeName());
                                    NodeList list_elem = eler.getElementsByTagName("throttle");
                                    for (int k = 0; k < list_elem.getLength(); k++) {
                                        if (list_elem.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                            Element elem = (Element) list_elem.item(k);//throttle
                                            Throttle throttle = new Throttle();
                                            System.out.println(elem.getAttribute("id"));
                                            throttle.setId(elem.getAttribute("id"));

                                            NodeList last_list = elem.getElementsByTagName("regime");
                                            for (int l = 0; l < last_list.getLength(); l++) {
                                                if (last_list.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                    Element regime_elem = (Element) last_list.item(l);//regime
                                                    System.out.println(regime_elem.getNodeName());
                                                    Regime regime = new Regime();
                                                    
                                                    NodeList regime_list = regime_elem.getElementsByTagName("torque");
                                                    for (int m = 0; m < regime_list.getLength(); m++) {
                                                        System.out.println(regime_list.item(m).getTextContent());
                                                        regime.setTorque(Integer.valueOf(regime_list.item(m).getTextContent()));
                                                    }
                                                    
                                                    regime_list = regime_elem.getElementsByTagName("rpm_low");
                                                    for (int m = 0; m < regime_list.getLength(); m++) {
                                                        System.out.println(regime_list.item(m).getTextContent());
                                                        regime.setRpm_low(Integer.valueOf(regime_list.item(m).getTextContent()));
                                                    }
                                                    
                                                    regime_list = regime_elem.getElementsByTagName("rpm_high");
                                                    for (int m = 0; m < regime_list.getLength(); m++) {
                                                        System.out.println(regime_list.item(m).getTextContent());
                                                        regime.setRpm_high(Integer.valueOf(regime_list.item(m).getTextContent()));
                                                    }
                                                    
                                                    regime_list = regime_elem.getElementsByTagName("SFC");
                                                    for (int m = 0; m < regime_list.getLength(); m++) {
                                                        System.out.println(regime_list.item(m).getTextContent());
                                                        regime.setSFC(Double.valueOf(regime_list.item(m).getTextContent()));
                                                    }
                                                    
                                                    
                                                    throttle.getRegime_list().add(regime);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Loads a xml file with the Road Network Data
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
            roadNetwork.loadMap();
            return roadNetwork;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
