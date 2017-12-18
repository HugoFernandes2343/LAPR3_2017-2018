package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.TravelByPhysics;

public class CopyProjectController {
    
    /**
     * Active project
     */
    private Project active;
    
    /**
     * TravelByPhysics
     */
    private TravelByPhysics travel;
    
    /**
     * Controller constructor
     * @param travel 
     */
    public CopyProjectController(TravelByPhysics travel) {
        this.travel=travel;
    }
    
    /**
     * @return active project name and description
     */
    public String[] getActiveProjectData(){
        this.active=travel.getProjectList().getActualProject();
        String [] array = new String [2];
        array[0]=active.getName();
        array[1]=active.getDescription();
        return array;
    }
    
    /**
     * Copies the active project
     * @return true if copy was sucessfull
     */
    public boolean copyProject(){
        Project copy = new Project();
        String name = this.active.getName() + "-copy";
        name=checkName(name,0);
        copy.setName(name);
        copy.setDescription(this.active.getDescription());
        copy.setNetwork(this.active.getNetwork());
        copy.setVehicleList(this.active.getVehicleList());
        return travel.getProjectList().addProject(copy); 
    }
    
    /**
     * Checks if the project name is already in use
     * @param name -project name
     * @param i - copy number (should start with 0);
     * @return A copy name that is not in use
     */
    private String checkName(String name, int i) {
        for (String n : travel.getProjectList().getAllNames()) {
            if(n.equals(name)){
                i++;
                name+=i;
                return checkName(name, i);
            }
        }
        return name;
    }
}
