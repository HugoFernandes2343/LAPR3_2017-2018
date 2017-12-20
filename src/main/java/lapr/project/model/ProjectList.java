package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectList implements Serializable {

    private static final long serialVersionUID = 602L;

    private Set<Project> listProjects;
    private Project actualProject;

    public ProjectList() {
        this.listProjects = new HashSet<>();
        this.actualProject = null;
    }

    /**
     * @return the actualProject
     */
    public Project getActualProject() {
        return actualProject;
    }

    /**
     * @param actualProject the actualProject to set
     */
    public void setActualProject(Project actualProject) {
        this.actualProject = actualProject;
    }

    /**
     * Method that adds a project to the list
     *
     * @param p - project to be added
     * @return true if the project is added, false otherwise.
     */
    public boolean addProject(Project p) {
        if (p == null) {
            return false;
        }

        if (verifyProject(p)) {
            this.listProjects.add(p);
            this.actualProject = p;
            return true;
        }

        return false;
    }

    /**
     * Method that verifies if a project already exists.
     *
     * @param p - project to be added
     * @return true if the project doesn't exists, false otherwise
     */
    private boolean verifyProject(Project p) {
        for (Project p1 : this.listProjects) {
            if (p1.equals(p)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that returns all names of the listProjects
     *
     * @return List of names
     */
    public List<String> getAllNames() {
        List<String> allNames = new ArrayList<>();
        for (Project p : listProjects) {
            allNames.add(p.getName());
        }
        return allNames;
    }

    /**
     *
     * @param name , name of the project to look for
     * @return project with said name
     */
    public Project getProject(String name) {
        for (Project p : listProjects) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * This metho verifies if the new name for the project is available or taken
     * and depending if it is or not it then either changes the name and
     * description and returns true or does not change the name and the
     * descripiton and returns false
     *
     * @param project , project that has the new name and description for the
     * current actual project
     * @return the method returns true if it is able to set the new name and
     * description on the actual project
     */
    public boolean updateActualProject(Project project) {
        if (!verifyProject(project)) {
            return false;
        }
        this.listProjects.remove(this.actualProject);
        this.listProjects.add(project);
        this.actualProject = project;
        return true;
    }
}
