package lapr.project.ui;

import javax.swing.JOptionPane;
import lapr.project.controller.CopyProjectController;
import lapr.project.model.TravelByPhysics;
import static lapr.project.ui.MessagesAndUtils.MESS_CONF;

public class CopyProjectUI implements MessagesAndUtils{

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
     *
     * @param tp - TravelByPhysics object
     */
    public CopyProjectUI(TravelByPhysics tp) {
        this.tp = tp;
        copyCTRL = new CopyProjectController(this.tp);
        exec();
    }

    private void exec() {
        String[] array = copyCTRL.getActiveProjectData();

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Project: " + array[0]  + "\nCopy this Project?",
                MESS_CONF, dialogButton);
        
        if(dialogResult == 0){
            if(copyCTRL.copyProject()){
                sucMess("Operation Sucessfully completed","Project Copy Done");
            }
        } else {
            errMess("Operation canceled by User","Project Copy Canceled");
        }
        

    }
    
    /**
     * ErrorMessages
     *
     * @param message message
     * @param title title of the error message
     */
    @Override
    public void errMess(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Success messages
     *
     * @param message message
     * @param title title of the success message
     */
    @Override
    public void sucMess(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
