/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.TravelByPhysics;
import lapr.project.model.Vehicle;
import lapr.project.utils.Algorithm;
import lapr.project.utils.MostEfficientPathInEnergySavingModeAlgorithm;
import lapr.project.utils.ShortestTravellTimeAlgorithm;
import lapr.project.utils.TheoreticalMostEnergyEfficientAlgorithm;

/**
 *
 * @author filip
 */
public class CreateAnalysisController {

    /**
     * Constant to check if a string is a number
     */
    private static final String REGEX = "-?\\d+(\\.\\d+)?";

    /**
     * The analysis list for comparison.
     */
    private List<NetworkAnalysis> resultList;

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
        this.resultList = new LinkedList<>();
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

    public boolean runAlgorithm(String algorithm, String vehicle, String begin, String end, String name, String load, String accelaration, String braking) {
        double loadValue = Double.parseDouble(load);
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

        if (a instanceof ShortestTravellTimeAlgorithm) {
            this.result = ((ShortestTravellTimeAlgorithm) a).runAlgorithm(actualproject, beginN, endN, v, name, loadValue);
            resultList.add(result);
            return true;
        }

        if (!accelaration.matches(REGEX) || !braking.matches(REGEX)) {
            return false;
        }

        double aAccelaration = Double.parseDouble(accelaration);
        double aBraking = Double.parseDouble(braking);

        if (aBraking < 0 && aAccelaration > 0) {
            if (a instanceof MostEfficientPathInEnergySavingModeAlgorithm) {
                ((MostEfficientPathInEnergySavingModeAlgorithm) a).setAceleratingAcceleration(aAccelaration);
                ((MostEfficientPathInEnergySavingModeAlgorithm) a).setBrakingAcceleration(aBraking);
                this.result = ((MostEfficientPathInEnergySavingModeAlgorithm) a).runAlgorithm(actualproject, beginN, endN, v, name, loadValue);
                resultList.add(result);
                return true;
            }

            ((TheoreticalMostEnergyEfficientAlgorithm) a).setAceleratingAcceleration(aAccelaration);
            ((TheoreticalMostEnergyEfficientAlgorithm) a).setBrakingAcceleration(aBraking);
            this.result = ((TheoreticalMostEnergyEfficientAlgorithm) a).runAlgorithm(actualproject, beginN, endN, v, name, loadValue);
            resultList.add(result);
            return true;
        }

        return false;
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

    public List<String> getVehicleLoadList() {
        return this.actualproject.getVehicleList().getAllVehicleLoads();
    }

    public Object[][] getInitialTableData(String alg) {
        Object[][] data = new Object[actualproject.getVehicleList().getVehicleList().size()][3];
        if ("Shortest Travell Time (N10)".equals(alg)) {
            data = new Object[actualproject.getVehicleList().getVehicleList().size()][3];
        } else {
            data = new Object[actualproject.getVehicleList().getVehicleList().size()][5];
        }
        int cont = 0;
        for (Vehicle vi : actualproject.getVehicleList().getVehicleList()) {
            data[cont][0] = vi.getName();
            data[cont][1] = vi.getLoad();
            data[cont][2] = false;
            cont++;
        }
        return data;
    }

    public boolean validateCandidates(List<Object[]> chosenRows, String alg) {
        if ("Algorithm: Shortest Travell Time (N10)".equals(alg)) {
            return validateN10Input(chosenRows);
        }
        return validateAlgorithmInput(chosenRows);
    }

    private boolean validateN10Input(List<Object[]> chosenRows) {
        for (Object[] row : chosenRows) {
            String load = (String) row[1];
            if (!load.matches(REGEX)) {
                return false;
            }
            if (!validateLoad(load, (String) row[0])) {
                return false;
            }
        }
        return true;
    }

    private boolean validateAlgorithmInput(List<Object[]> chosenRows) {
        for (Object[] row : chosenRows) {
            String load = (String) row[1];
            String braking = (String) row[3];
            String accelarating = (String) row[4];
            if (!load.matches(REGEX) || !braking.matches(REGEX) || !accelarating.matches(REGEX)) {
                return false;
            }
            if (!validateLoad(load, (String) row[0])) {
                return false;
            }
        }
        return true;
    }

    public List<NetworkAnalysis> runBulkAnalysis(List<Object[]> chosenRows, String alg, String node1, String node2, String name) {
        Algorithm a = findAlgorithm(alg);
        if (a == null) {
            return resultList;
        }
        if ("Algorithm: Shortest Travell Time (N10)".equals(alg)) {
            return runBulkAlgorithmN10(chosenRows, a, node1, node2, name);
        }
        return runBulkAlgorithm(chosenRows, a, node1, node2, name);
    }

    private List<NetworkAnalysis> runBulkAlgorithmN10(List<Object[]> chosenRows, Algorithm a, String node1, String node2, String name) {
        for (Object[] data : chosenRows) {
            Node beginN = this.actualproject.getNetwork().searchNode(node1);
            Node endN = this.actualproject.getNetwork().searchNode(node2);
            String value = (String) data[1];
            double load = Double.parseDouble(value);
            NetworkAnalysis analysis = a.runAlgorithm(actualproject, beginN, endN, actualproject.getVehicleList().getVehicleByName((String) data[0]), name, load);
            resultList.add(analysis);
        }
        return resultList;
    }

    private List<NetworkAnalysis> runBulkAlgorithm(List<Object[]> chosenRows, Algorithm a, String node1, String node2, String name) {
        NetworkAnalysis analysis = null;
        for (Object[] data : chosenRows) {
            Node beginN = this.actualproject.getNetwork().searchNode(node1);
            Node endN = this.actualproject.getNetwork().searchNode(node2);
            String value = (String) data[1];
            String sBraking = (String) data[3];
            String sAccelarating = (String) data[4];
            double load = Double.parseDouble(value);
            double braking = Double.parseDouble(sBraking);
            double accelaration = Double.parseDouble(sAccelarating);
            String vehicleName = (String) data[0];
            Vehicle v = actualproject.getVehicleList().getVehicleByName(vehicleName);
            if (braking < 0 && accelaration > 0) {
                if (a instanceof MostEfficientPathInEnergySavingModeAlgorithm) {
                    ((MostEfficientPathInEnergySavingModeAlgorithm) a).setAceleratingAcceleration(accelaration);
                    ((MostEfficientPathInEnergySavingModeAlgorithm) a).setBrakingAcceleration(braking);
                    analysis = ((MostEfficientPathInEnergySavingModeAlgorithm) a).runAlgorithm(actualproject, beginN, endN, v, name, load);

                }

                ((TheoreticalMostEnergyEfficientAlgorithm) a).setAceleratingAcceleration(accelaration);
                ((TheoreticalMostEnergyEfficientAlgorithm) a).setBrakingAcceleration(braking);
                analysis = ((TheoreticalMostEnergyEfficientAlgorithm) a).runAlgorithm(actualproject, beginN, endN, v, name, load);

            }
            resultList.add(analysis);
        }
        return resultList;
    }

    public Object[][] getFinalTableData() {
        NumberFormat formatter = new DecimalFormat("0.####E0");

        int cont = 0;
        Object[][] data = new Object[resultList.size()][5];
        for (NetworkAnalysis analysis : resultList) {
            String bestPath = analysis.getBestPath().get(0).getBegin();
            data[cont][0] = analysis.getVehicle().getName();
            data[cont][2] = formatter.format(analysis.getTravellTime());
            data[cont][3] = formatter.format(analysis.getEnergyConsumption());
            data[cont][4] = analysis.getTollCost();
            for (int i = 1; i < analysis.getBestPath().size(); i++) {
                bestPath = bestPath + "-" + analysis.getBestPath().get(i).getBegin();
            }
            bestPath = bestPath + "-" + analysis.getBestPath().get(analysis.getBestPath().size() - 1).getEnd();
            data[cont][1] = bestPath;
            cont++;
        }
        return data;
    }

}
