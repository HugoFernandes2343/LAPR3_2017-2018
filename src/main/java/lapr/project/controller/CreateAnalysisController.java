/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.Vehicle;
import lapr.project.utils.Algorithm;

/**
 *
 * @author filip
 */
public class CreateAnalysisController {

    /**
     * The project to make the analisys.
     */
    private final Project actualproject;

    /**
     * The system base.
     */
    private final TravelByPhysics tb;

    /**
     * Algorithm list
     */
    private List<Algorithm> algorithmList;

    /**
     * Results of the analysis.
     */
    private NetworkAnalysis result;

    /**
     * Constructor.
     *
     * @param tb - system base.
     */
    public CreateAnalysisController(TravelByPhysics tb) {
        this.tb = tb;
        this.actualproject = this.tb.getProjectList().getActualProject();
    }

    public List<String> getAlgorithmList() {
        this.algorithmList = this.tb.getAlgorithmsList();
        return this.tb.getAlgorithmsByName();
    }

    public List<String> getVehicleList() {
        return this.actualproject.getVehicleList().getAllVehicleNames();
    }

    public List<String> getNodeList() {
        return this.actualproject.getNetwork().getNodesByName();
    }

    public boolean runAlgorithm(String algorithm, String vehicle, String begin, String end, String name) {
        Algorithm a = findAlgorithm(algorithm);
        if (a == null) {
            return false;
        }
        Vehicle v = this.actualproject.getVehicleList().getVehicleByName(vehicle);
        if (v == null) {
            return false;
        }

        Node beginN = this.actualproject.getNetwork().searchNode(begin);
        Node endN = this.actualproject.getNetwork().searchNode(end);
        if (beginN == null || endN == null) {
            return false;
        }

        this.result = a.runAlgorithm(actualproject, beginN, endN, v, name, 20);
        return true;
    }

    /**
     * Method that finds the algorithm to use
     *
     * @param algorithm Algorithm to use
     * @return null if doesn´t find the algorithm.
     */
    private Algorithm findAlgorithm(String algorithm) {
        for (Algorithm a : this.algorithmList) {
            if (a.toString().equals(algorithm)) {
                return a;
            }
        }
        return null;
    }

    public NetworkAnalysis getAnalysis() {
        return this.result;
    }

    public boolean validateLoad(String text, String vehicle) {
        try {
            double load = Double.parseDouble(text);
            double maxLoad = (Double.parseDouble(actualproject.getVehicleList().getVehicleByName(vehicle).getLoad().replace(" kg", "")));
            return maxLoad >= load;
        } catch (NumberFormatException ex) {
            Logger.getLogger(CreateAnalysisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
