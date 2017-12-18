package lapr.project.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lapr.project.controller.AddRoadsController;
import lapr.project.controller.AddVehiclesController;
import lapr.project.controller.SaveToCSV;
import lapr.project.controller.SaveToHTML;
import lapr.project.model.TravelByPhysics;

public class ImportFilesUI extends JFrame implements MessagesAndUtils {

    private static final long serialVersionUID = 110L;

    String type = "";
    TravelByPhysics tp;
    AddRoadsController roadsC;
    AddVehiclesController vehiclesC;
    JFileChooser chooser;

    public ImportFilesUI(TravelByPhysics tp, String type) {
        this.tp = tp;
        this.type = type;
        setLookAndFeel();
        this.setAlwaysOnTop(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);

        createMainPanel();
        this.pack();
        this.setVisible(true);
    }

    /**
     * Create the current panel configuration for visualization
     */
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(createHeader("Select file to be Imported: "));

        if (type.equalsIgnoreCase("vehicle")) {
            vehiclesC = new AddVehiclesController(tp);
            chooser = new JFileChooser("Choose .xml file to import vehicles data");
            this.setTitle("Import Vehicles:");
            mainPanel.add(CreateImpVehicles());
        } else {
            roadsC = new AddRoadsController(tp);
            chooser = new JFileChooser("Choose .xml file to import roads data");
            this.setTitle("Import Roads:");
            mainPanel.add(CreateImpRoads());
        }
        mainPanel.add(CreateCancelBt());
        this.add(mainPanel);
    }

    private JButton CreateImpVehicles() {
        JButton importVehicles = new JButton("Import...");
        importVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chooser.showOpenDialog(rootPane);
                if (vehiclesC.AddVehicles(chooser.getSelectedFile().getAbsolutePath())) {
                    suc_mess(IMPORT_SUC, MESS_SUCC);
                    dispose();
                } else {
                    err_mess(ERR_IMPORT, MESS_ERR);
                }
            }
        });
        return importVehicles;
    }

    private JButton CreateImpRoads() {
        JButton importRoads = new JButton("Import...");
        importRoads.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chooser.showOpenDialog(rootPane);
                if (roadsC.addNewRoads(chooser.getSelectedFile().getAbsolutePath())) {
                    suc_mess(IMPORT_SUC, MESS_SUCC);
                    dispose();
                } else {
                    err_mess(ERR_IMPORT, MESS_ERR);
                }
            }
        });
        return importRoads;
    }

    private JButton CreateCancelBt() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        return cancelButton;
    }

    /**
     * Method to create headers, returns a panel with the text passed as
     * parameter inside
     *
     * @param text the text to be displayed
     * @return JPanel with text
     */
    private JPanel createHeader(String text) {
        JPanel p = new JPanel();
        p.add(new JLabel(text));
        return p;
    }

    /**
     * Sets the desired Look and Feel of the window
     */
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            //Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ErrorMessages
     *
     * @param message message
     * @param title title of the error message
     */
    @Override
    public void err_mess(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Success messages
     *
     * @param message message
     * @param title title of the success message
     */
    @Override
    public void suc_mess(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
