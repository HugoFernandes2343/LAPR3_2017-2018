/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.NetworkAnalysis;
import lapr.project.model.Node;
import lapr.project.model.Project;
import lapr.project.model.Vehicle;

/**
 *
 * @author Hugo
 */
public interface Algorithm {

    public NetworkAnalysis runAlgorithm(Project project, Node begin, Node end, Vehicle vehicle, String name);

    @Override
    public String toString();
}
