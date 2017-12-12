/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author hugod
 */
public class ProjectList {
    
    private Set<Project> setProjects;
    
    public ProjectList(){
    this.setProjects = new HashSet();
    }
}
