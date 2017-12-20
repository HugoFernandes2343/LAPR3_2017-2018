/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lapr.project.datalayer.SaveToCSV;
import lapr.project.datalayer.SaveToHTML;
import lapr.project.model.Project;

/**
 *
 * @author Utilizador
 */
public class SaveToFile extends JFrame {//Add the implementation later

    private static final long serialVersionUID = 1L;

    /**
     * Current project to be exported
     */
    private Project thisProject;

    /**
     * Serves the purpose of allowing the actionListeners to access the contents
     * of this Frame
     */
    private final JFrame temp;

    /**
     * Flag de escolha de formato de output
     *
     * Valores: 0 - HTML 1- CSV
     */
    private static int chosenFormat = -1;

    /**
     * SaveToHTML object
     */
    protected SaveToHTML htmlSave;
    
    /**
     * SaveToCSV object
     */
    protected SaveToCSV csvSave;

    /**
     * SaveToFile contructor
     *
     * @param p current project/network analysis
     */
    public SaveToFile(Project p) {
        htmlSave = null;
        csvSave = null;
        setLookAndFeel();
        temp = this;
        checkProject(p);
        setConfigurations();
        //JFileChooser fileChooser = new JFileChooser(); add to a method that is activated to a button
        //int input = fileChooser.showOpenDialog(fileChooser); ^^
    }

    /**
     * Create the current frame configuration for visualization
     */
    private void setConfigurations() {
        this.setAlwaysOnTop(true);
        this.setTitle("Export(Choose format):");
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
        createElements(mainPanel);
        this.add(mainPanel);
    }

    /**
     * Create the required elements to be displayed onto the panel
     *
     * @param mainPanel recieves the panel to which the elements should be added
     * to
     */
    private void createElements(JPanel mainPanel) {
        JRadioButton jHtmlButton = new JRadioButton("HTML");
        JRadioButton jCsvButton = new JRadioButton("CSV");
        jCsvButton.setEnabled(false);
        ButtonGroup selectionGroup = new ButtonGroup();
        selectionGroup.add(jHtmlButton);
        selectionGroup.add(jCsvButton);
        JButton exportButton = new JButton("Export...");
        JButton cancelButton = new JButton("Cancel");

        jHtmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jHtmlButton.isSelected()) {
                    setChosenFormat(0);
                }
            }
        });

        jCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jCsvButton.isSelected()) {
                    setChosenFormat(1);
                }
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Needs testing to see if it can keep the listener from being deleted in case of no file selection
                switchTypes();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //vv Workaround maybe?? vv
                temp.dispose();
            }
        });

        mainPanel.add(jHtmlButton);
        mainPanel.add(jCsvButton);
        mainPanel.add(exportButton);
        mainPanel.add(cancelButton);
    }

    private void switchTypes() {
        switch (chosenFormat) {
            case 0:
                toHTML();
                break;
            case 1:
                toCSV();
                break;
            case -1:
                JOptionPane.showMessageDialog(null, "Choose a file first!");
                break;
            default:
                break;
        }
    }

    private void toHTML() {
        try {
            htmlSave = new SaveToHTML(thisProject);
        } catch (IOException ex) {
            Logger.getLogger(SaveToFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void toCSV() {
        csvSave = new SaveToCSV(thisProject);
    }

    /**
     * Custom action to determine the best course of action if a given project
     * is not selected ::NOTE:: This is still placeholder however if only used
     * after a network analisys is performed then it shouldnt raise issues
     *
     * @param p project to have it's contents checked
     */
    private void checkProject(Project p) {
        if (p == null) {
            /*Treat this by allowing a list to select the available projects
            * in the program?
             */
        } else {
            thisProject = p;
        }
    }

    /**
     * Set the specified number flag for the chosen format
     *
     * @param n
     */
    private static void setChosenFormat(int n) {
        chosenFormat = n;
    }

    /**
     * Sets the desired Look and Feel of the window
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SaveToFile.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
