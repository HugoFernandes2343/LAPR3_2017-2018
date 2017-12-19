package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr.project.controller.CreateProjectController;
import lapr.project.model.TravelByPhysics;

/**
 *
 * @author 
 */
public class CreateProjectUI extends JPanel implements MessagesAndUtils {

    private static final long serialVersionUID = 105L;

    /**
     * Object of this GUI controller
     */
    private final CreateProjectController cp;

    /**
     * Name of the project
     */
    private String nameStr;

    /**
     * Project description
     */
    private String descrStr;

    /**
     * flag that checks wether the files have been imported, when pressing the
     * done button
     */
    private boolean flag;

    /**
     * TravelByPhysics object
     */
    private final TravelByPhysics tp;

    /**
     * UI components
     */
    private final JPanel mPanel;
    private JPanel page1;
    private JPanel page2;
    private JTextArea projectDescription;
    private JTextField projectName;
    private JTextArea roadsFilePath;
    private JTextArea vehiclesFilePath;
    private final CardLayout layout = new CardLayout();

    /**
     * Constructor for this class
     *
     * @param tp
     */
    public CreateProjectUI(TravelByPhysics tp) {
        this.tp = tp;
        cp = new CreateProjectController(tp);
        mPanel = new JPanel(layout);
        mPanel.add(createPageOne(), "page1");
        mPanel.add(createPageTwo(), "page2");
        mPanel.setPreferredSize(dim);
        layout.show(mPanel, "page1");
        setBackground(Color.GRAY);
        add(mPanel);
    }

    /**
     * Method that creates the page one of the card layout
     *
     * @return
     */
    private JPanel createPageOne() {
        page1 = new JPanel(new GridLayout(3, 1, 20, 20));

        JPanel nameLabel = createHeader("Project Name: ");

        page1.add(nameLabel);
        projectName = new JTextField(20);
        JPanel pName = new JPanel();
        pName.add(projectName);

        page1.add(pName);
        JPanel descriptionLabel = createHeader("Project Description: ");

        page1.add(descriptionLabel);

        projectDescription = new JTextArea(7, 20);
        projectDescription.setLineWrap(true);
        projectDescription.setWrapStyleWord(true);
        projectDescription.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 40));
        page1.add(new JScrollPane(projectDescription));

        page1.add(createPageOneButtons(1));
        page1.add(createPageOneButtons(2));

        return page1;
    }

    /**
     * Method that creates buttons of the 1st page of the card layout
     *
     * @return
     */
    private JPanel createPageOneButtons(int c) {
        JPanel buttonsPageOne = new JPanel();
        if (c == 1) {
            JPanel btCancel = new JPanel();
            btCancel.add(getCancelButton());
            buttonsPageOne.add(btCancel);
            return buttonsPageOne;
        } else {
            JPanel btNext = new JPanel();
            btNext.add(createNextButtonOne());
            buttonsPageOne.add(btNext);
            return buttonsPageOne;
        }
    }

    /**
     * Creates the cancel button for this page
     * @return the cancel button
     */
    private JButton getCancelButton() {
        if (tp.getProjectList().getActualProject() == null) {
            return new CancelButton(this);
        } else {
            return new CancelButton(this, tp.getProjectList().getActualProject());
        }
    }

    /**
     * Method that creates and returns the "next" button, of the 1st page
     *
     * @return "next" button
     */
    private JButton createNextButtonOne() {
        JButton nextOne = new JButton("Next");
        nextOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nameStr = projectName.getText();
                descrStr = projectDescription.getText();
                if (cp.createProject(nameStr, descrStr)) {
                    layout.show(mPanel, "page2");
                } else {
                    errMess(EMPTY_FIELDS, MESS_ERR);
                }
            }
        }
        );
        return nextOne;
    }

    /**
     * Method to create headers, returns a panel with the text passed as
     * parameter inside
     *
     * @param text the text to be displayed
     * @return JPanel with text
     */
    private static JPanel createHeader(String text) {
        JPanel p = new JPanel();
        p.add(new JLabel(text));
        return p;
    }

    /**
     * Method that creates the page two of the card layout
     *
     * @return
     */
    private JPanel createPageTwo() {
        page2 = new JPanel(new GridLayout(2, 1));

        JPanel p1 = new JPanel(new GridLayout(3, 2));
        JPanel pr = new JPanel();
        roadsFilePath = new JTextArea(6, 30);
        roadsFilePath.setText("Select file:");
        roadsFilePath.setLineWrap(true);
        roadsFilePath.setEditable(false);
        pr.add(roadsFilePath);
        p1.add(pr);

        p1.add(createButtonRoads());

        JPanel pv = new JPanel();
        vehiclesFilePath = new JTextArea(6, 30);
        vehiclesFilePath.setText("Select file:");
        vehiclesFilePath.setLineWrap(true);
        vehiclesFilePath.setEditable(false);
        pv.add(vehiclesFilePath);
        p1.add(pv);

        p1.add(createButtonVehicle());

        JPanel empty = new JPanel();
        p1.add(empty);

        page2.add(p1);

        JPanel buttonsPageTwo = new JPanel(new GridLayout(1, 3, 20, 0));
        JPanel btCancel = new JPanel();
        btCancel.add(getCancelButton());
        buttonsPageTwo.add(btCancel);

        JPanel btDone = new JPanel();
        btDone.add(creatButtonImport());
        buttonsPageTwo.add(btDone);

        JPanel btNext = new JPanel();
        btNext.add(createDoneBT());
        buttonsPageTwo.add(btNext);

        page2.add(buttonsPageTwo);
        return page2;
    }

    /**
     * Method that creates and returns the "done" button
     *
     * @return "done" button
     */
    private JButton createDoneBT() {
        JButton buttonDone = new JButton("Done");
        buttonDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (flag) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Project: " + nameStr + "\nCreate this Project?",
                            MESS_CONF, dialogButton);
                    if (dialogResult == 0) {
                        if (cp.addProject()) {
                            sucMess(CREATE_SUC, MESS_SUCC);
                            removeAll();
                            add(new MainPanel(tp.getProjectList().getActualProject()));
                            revalidate();
                            repaint();
                        } else {
                            errMess(ERR_NO_FILE, MESS_ERR);
                        }
                    }
                } else {
                    errMess(ERR_NO_FILE, MESS_ERR);
                }
            }
        });
        return buttonDone;
    }

    /**
     * Method that creates the button to chose the Roads file path.
     *
     * @return the new JButton
     */
    private JPanel createButtonRoads() {
        JPanel p = new JPanel();
        JButton buttonRoads = new JButton("Choose Roads file");
        buttonRoads.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser roadsChooser = new JFileChooser("Choose file to import roads data");
                roadsChooser.showOpenDialog(page2);
                try {
                    String roadsFilePathStr = roadsChooser.getSelectedFile().getAbsolutePath();
                    if (!(roadsFilePathStr.isEmpty()) && validateFile(roadsChooser.getSelectedFile().getAbsolutePath())) {
                        roadsFilePath.setText(roadsFilePathStr);
                    } else {
                        errMess(ERR_WRONG_FILE, MESS_ERR);
                    }
                } catch (NullPointerException ex) {
                    errMess(ERR_NO_FILE, MESS_ERR);
                }
            }

        });
        p.add(buttonRoads);
        return p;
    }

    /**
     * Method that creates the button to chose the Vehicles file path.
     *
     * @return the new JButton and ActionListener in a panel.
     */
    private JPanel createButtonVehicle() {
        JPanel panel = new JPanel();
        JButton buttonVehicles = new JButton("Choose vehicles file");
        buttonVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser vehiclesChooser = new JFileChooser("Choose file to import vehicles data");
                vehiclesChooser.showOpenDialog(page2);
                try {
                    String vehiclesFilePathStr = vehiclesChooser.getSelectedFile().getAbsolutePath();
                    if (!(vehiclesFilePathStr.isEmpty()) && validateFile(vehiclesChooser.getSelectedFile().getAbsolutePath())) {
                        vehiclesFilePath.setText(vehiclesFilePathStr);
                    } else {
                        errMess(ERR_WRONG_FILE, MESS_ERR);
                    }
                } catch (NullPointerException ex) {
                    errMess(ERR_NO_FILE, MESS_ERR);
                }
            }

        });
        panel.add(buttonVehicles);
        return panel;
    }

    /**
     * Verifies if the chosen file is a xml file
     *
     * @return true if it is, false otherwise.
     */
    private static boolean validateFile(String path) {
        return path.endsWith(".xml");
    }

    /**
     * Method that creates the button to import all the info.
     *
     * @return the new JButton and ActionListener.
     */
    private JPanel creatButtonImport() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton buttonImport = new JButton("Import");
        buttonImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (cp.readInfo(roadsFilePath.getText(), vehiclesFilePath.getText())) {
                        sucMess(IMPORT_SUC, MESS_SUCC);
                        flag = true;
                    } else {
                        errMess(ERR_IMPORT, MESS_ERR);
                    }
                } catch (NullPointerException ex) {
                    errMess(ERR_IMPORT, MESS_ERR);
                }
            }
        }
        );
        panel.add(buttonImport);
        return panel;
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
