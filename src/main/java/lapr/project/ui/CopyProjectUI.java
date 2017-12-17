package lapr.project.ui;

import lapr.project.controller.CopyProjectController;
import lapr.project.model.TravelByPhysics;

public class CopyProjectUI {
    
    /**
     * CopyController
     */
    private CopyProjectController copyCTRL;
    
    /**
     * TravelByPhysics object
     */
    private final TravelByPhysics tp;
    
    /**
     * Constructor
     * @param tp - TravelByPhysics object
     */
    public CopyProjectUI(TravelByPhysics tp) {
        this.tp = tp;
        copyCTRL = new CopyProjectController(tp);
    }
    
    
    
    
}
