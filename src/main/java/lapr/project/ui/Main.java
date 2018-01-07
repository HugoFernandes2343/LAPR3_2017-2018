package lapr.project.ui;


import java.sql.SQLException;
import lapr.project.model.TravelByPhysics;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {


    /**
     * Private constructor to hide implicit public one.
     */
    public Main() {
        // do nothing cause its the constructor of the main
    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        new MenuUI(new TravelByPhysics());
    }
}
