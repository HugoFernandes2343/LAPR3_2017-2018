package lapr.project.ui;

import java.awt.Dimension;

public interface MessagesAndUtils {
    
    static final Dimension dim = new Dimension(600, 395);
    /**
     * Success
     */
    static final String MESS_SUCC = "SUCCESS";
    static final String MESS_CONF = "CONFIRM";
    static final String IMPORT_SUC = "Data imported with success";
    static final String CREATE_SUC = "Created with Success";
    static final String SEL_SUCC = "Project Selected with Success";
    static final String UP_SUCC = "Project updated with Success";
    
    /**
     * Error messageS
     */
    static final String MESS_ERR = "ERROR";
    static final String ERR_WRONG_FILE = "You need to choose an .xml file";
    static final String ERR_NO_FILE = "You need to choose a file";
    static final String ERR_NO_PROJECT = "No project selected";
    static final String ERR_IMPORT = "Error importing file";
    static final String EMPTY_FIELDS = "Please fill in all the required fields/Name already in use";
    static final String NO_PROJ = "There are no Projects available";
    static final String ERR_PROJ = "Error selecting Project";
    static final String ERR_NODE = "Nodes are equal";
    static final String ERR_ANAL = "Error analysing project";
    static final String ERR_LOAD = "Error invalid load";
    static final String ERR_CSEL = "Select all necessary information";
    static final String ERR_DB = "Project wont be saved to databased";
    
    
    
    public void errMess(String message, String title);
    public void sucMess(String message, String title);
}
