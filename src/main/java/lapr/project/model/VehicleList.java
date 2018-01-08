package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import lapr.project.utils.DatabaseExchangable;

/**
 *
 * @author
 */
public class VehicleList implements Serializable, DatabaseExchangable {

    private static final long serialVersionUID = 205L;

    private Set<Vehicle> listVehicles;

    public VehicleList() {
        this.listVehicles = new HashSet<>();
    }

    /**
     * @return the vehicle_list
     */
    public Set<Vehicle> getVehicleList() {
        return listVehicles;
    }

    /**
     * @param vehicleList the vehicle_list to set
     */
    public void setVehicleList(Set<Vehicle> vehicleList) {
        this.listVehicles = vehicleList;
    }

    /**
     * Method that adds a vehicle to the list
     *
     * @param car
     */
    public void addVehicle(Vehicle car) {
        if (!this.listVehicles.contains(car)) {
            this.listVehicles.add(car);
        }
    }

    /**
     * Method that returns a list with all the vehicle names present in the list
     *
     * @return List of strings with the names of the vehicles
     */
    public List<String> getAllVehicleNames() {
        Iterator<Vehicle> itr = listVehicles.iterator();
        ArrayList<String> names = new ArrayList<>();
        while (itr.hasNext()) {
            names.add(itr.next().getName());
        }
        return names;
    }

    /**
     * Verifies witch vehicles from the list given as parameter can be added to
     * the VehicleList
     *
     * @param newVehicles the list of chicles to be added
     * @return
     */
    public int verifyAndAddVehicles(Set<Vehicle> newVehicles) {
        int cont = 0;
        for (Vehicle vIn : newVehicles) {
            if (!listVehicles.contains(vIn)) {
                int i = incrementName(vIn.getName(), 0, getAllVehicleNames());
                if (i > 0) {
                    vIn.setName(vIn.getName() + i);
                }
                addVehicle(vIn);
                cont++;
            }
        }
        return cont;
    }

    private static int incrementName(String name, int cont, List<String> names) {
        int count = cont;
        String changedName = name;
        if (names.contains(changedName)) {
            count = count + 1;
            changedName = changedName + count;
            return incrementName(changedName, count, names);
        }
        return cont;
    }

    /**
     * Return a Vehicle Object if it finds it by the name
     *
     * @param name - Vehicle name
     * @return Vehicle object if found, null otherwise
     */
    public Vehicle getVehicleByName(String name) {
        for (Vehicle v : this.listVehicles) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }

     /**
     * method that returns the data to relate to the dataBase
     */
    @Override
    public Set<DatabaseExchangable> getDBData() {
        Set<DatabaseExchangable> temp = new HashSet<>();
        temp.addAll(this.listVehicles);
        return temp;
    }

}
