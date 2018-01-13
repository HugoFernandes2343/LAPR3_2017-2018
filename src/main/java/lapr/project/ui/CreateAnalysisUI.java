package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import lapr.project.controller.CreateAnalysisController;
import lapr.project.datalayer.DAOHandler;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.TravelByPhysics;

public class CreateAnalysisUI extends JPanel implements MessagesAndUtils {

    private static final long serialVersionUID = 111L;

    /**
     * Atributes that keep the information for building the table
     */
    private Object[][] data;
    private String[] columnsHeader = {"", "Valor"};
    private DefaultTableModel tableModel;

    /**
     * TravelByPhysics object
     */
    private final TravelByPhysics tp;
    private final CreateAnalysisController ca;

    /**
     * DAOHandler object
     */
    protected DAOHandler daoHandler;

    private String node1;
    private String node2;
    private String vehicle;
    private String alg;
    private JTextField analName;
    private JTextField load;
    private JTextField accelaration;
    private JTextField braking;
    private NetworkAnalysis na;
    private JTable table;

    /**
     * UI components
     */
    private final JPanel mPanel;
    private JTextArea projectDescription;
    private JTextField projectName;
    private final CardLayout layout = new CardLayout();

    public CreateAnalysisUI(TravelByPhysics tp) {
        this.tp = tp;
        this.daoHandler = tp.getDAOHandler();
        ca = new CreateAnalysisController(tp);
        mPanel = new JPanel(layout);
        mPanel.setPreferredSize(dim);
        mPanel.add(pageOne(), "page1");

        layout.show(mPanel, "page1");
        setBackground(Color.GRAY);
        add(mPanel);
    }

    private JPanel pageOne() {

        JPanel page1 = new JPanel(new BorderLayout());
        page1.add(createHeader(), BorderLayout.NORTH);
        JPanel selects = new JPanel(new GridLayout(2, 2));
        selects.add(selNode1());
        selects.add(selNode2());
        selects.add(selVehicle());
        selects.add(selAlg());
        page1.add(selects, BorderLayout.CENTER);
        page1.add(createPageOneButtons(), BorderLayout.SOUTH);
        return page1;
    }

    /**
     * Method that creates buttons of the 1st page of the card layout
     *
     * @return
     */
    private JPanel createPageOneButtons() {
        JPanel buttonsPageOne = new JPanel(new GridLayout(1, 2));

        JPanel btNext = new JPanel();
        btNext.add(createNextButtonOne());
        JPanel btCancel = new JPanel();
        btCancel.add(new CancelButton(this, tp.getProjectList().getActualProject()));

        buttonsPageOne.add(btCancel);
        buttonsPageOne.add(btNext);
        return buttonsPageOne;
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
                try {
                    if (checkSelected()) {
                        if (!node1.equals(node2)) {
                            if (!analName.getText().isEmpty() && !load.getText().isEmpty()) {
                                if (ca.validateLoad(load.getText(), vehicle)) {
                                    if (!alg.equals("Algorithm: Shortest Travell Time (N10)")) {
                                        JFrame accFrame = new JFrame("Accelaration Values: ");
                                        accFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        accFrame.setLocationRelativeTo(null);
                                        accFrame.add(createAccComponents(accFrame));
                                        accFrame.pack();
                                        accFrame.setVisible(true);
                                    } else {
                                        if (ca.runAlgorithm(alg, vehicle, node1, node2, analName.getText(), load.getText(), "0", "0")) {
                                            na = ca.getAnalysis();

                                            tableModel = new DefaultTableModel();
                                            tableModel.setDataVector(getFinalResultTable(), columnsHeader);
                                            table = new JTable(tableModel);
                                            mPanel.add(pageTwo(), "page2");

                                            layout.show(mPanel, "page2");
                                        } else {
                                            errMess(ERR_ANAL, MESS_ERR);
                                        }
                                    }
                                } else {
                                    errMess(ERR_LOAD, MESS_ERR);
                                }
                            } else {
                                errMess(EMPTY_FIELDS, MESS_ERR);
                            }
                        } else {
                            errMess(ERR_NODE, MESS_ERR);
                        }
                    } else {
                        errMess(ERR_CSEL, MESS_ERR);
                    }
                } catch (NullPointerException ex) {
                    Logger.getLogger(CreateAnalysisUI.class.getName()).log(Level.SEVERE, null, ex);
                    errMess(ERR_CSEL, MESS_ERR);
                }
            }
        }
        );
        return nextOne;
    }

    /**
     * check if the comboboxes are selected, returs true if they are.
     *
     * @return
     */
    private boolean checkSelected() {
        String sel = "Select an option:";
        return (!node1.equals(sel)) && (!node2.equals(sel)) && (!vehicle.equals(sel)) && (!alg.equals(sel));
    }

    private JPanel selNode1() {
        String[] allNodes1 = ca.getNodeList().toArray(new String[0]);

        JComboBox<String> boxNodes1 = new JComboBox<>(allNodes1);
        boxNodes1.setSelectedIndex(-1);
        boxNodes1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                node1 = (String) boxNodes1.getSelectedItem();
            }
        }
        );

        JPanel p = new JPanel(new GridLayout(2, 1));
        JLabel lab = new JLabel("Select 1st Node: ");
        lab.setHorizontalAlignment(JLabel.CENTER);
        p.add(lab);
        JPanel p2 = new JPanel();
        p2.add(boxNodes1);
        p.add(p2);
        return p;
    }

    private JPanel selNode2() {
        String[] allNodes2 = ca.getNodeList().toArray(new String[0]);

        JComboBox<String> boxNodes2 = new JComboBox<>(allNodes2);
        boxNodes2.setSelectedIndex(-1);
        boxNodes2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                node2 = (String) boxNodes2.getSelectedItem();
            }
        }
        );

        JPanel p = new JPanel(new GridLayout(2, 1));
        JLabel lab = new JLabel("Select 2nd Node: ");
        lab.setHorizontalAlignment(JLabel.CENTER);
        p.add(lab);
        JPanel p2 = new JPanel();
        p2.add(boxNodes2);
        p.add(p2);
        return p;
    }

    private JPanel selVehicle() {
        String[] allVehicles = ca.getVehicleList().toArray(new String[0]);

        JComboBox<String> boxVehicles = new JComboBox<>(allVehicles);
        boxVehicles.setSelectedIndex(-1);
        boxVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vehicle = (String) boxVehicles.getSelectedItem();
            }
        }
        );

        JPanel p = new JPanel(new GridLayout(2, 1));
        JLabel lab = new JLabel("Select Vehicle: ");
        lab.setHorizontalAlignment(JLabel.CENTER);
        p.add(lab);
        JPanel p2 = new JPanel();
        p2.add(boxVehicles);
        p.add(p2);
        return p;
    }

    private JPanel selAlg() {
        String[] allAlg = ca.getAlgorithmList().toArray(new String[0]);

        JComboBox<String> boxAlg = new JComboBox<>(allAlg);
        boxAlg.setSelectedIndex(-1);
        boxAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                alg = (String) boxAlg.getSelectedItem();
            }
        }
        );
        JPanel p = new JPanel(new GridLayout(2, 1));
        JLabel lab = new JLabel("Select Algorithm: ");
        lab.setHorizontalAlignment(JLabel.CENTER);
        p.add(lab);
        JPanel p2 = new JPanel();
        p2.add(boxAlg);
        p.add(p2);
        return p;
    }

    /**
     * Method to create headers, returns a panel with the text passed as
     * parameter inside
     *
     * @param text the text to be displayed
     * @return JPanel with text
     */
    private JPanel createHeader() {
        analName = new JTextField(20);
        load = new JTextField(20);
        JPanel p = new JPanel(new GridLayout(3, 2));
        JLabel text1 = new JLabel("Project: ");
        text1.setHorizontalAlignment(JLabel.CENTER);
        p.add(text1);

        JLabel text2 = new JLabel(tp.getProjectList().getActualProject().getName());
        text2.setHorizontalAlignment(JLabel.CENTER);
        p.add(text2);

        JLabel text3 = new JLabel("Analysis: ");
        text3.setHorizontalAlignment(JLabel.CENTER);
        p.add(text3);

        JPanel an = new JPanel();
        an.add(analName);
        p.add(an);

        JLabel text4 = new JLabel("Load: ");
        text4.setHorizontalAlignment(JLabel.CENTER);
        p.add(text4);

        JPanel l = new JPanel();
        l.add(load);
        p.add(l);

        return p;
    }

    private JPanel pageTwo() {
        JPanel page2 = new JPanel(new GridLayout(2, 1));

        JPanel project = new JPanel(new GridLayout(1, 2));
        JPanel name = new JPanel(new GridLayout(2, 1));

        JLabel n = new JLabel(tp.getProjectList().getActualProject().getName());
        n.setHorizontalAlignment(JLabel.CENTER);
        JPanel pn = new JPanel(new BorderLayout());
        pn.add(n);
        name.add(pn);
        name.add(createButtons(), BorderLayout.LINE_END);
        JPanel desc = new JPanel();
        JTextArea lbDesc = new JTextArea(10, 30);
        lbDesc.setText(tp.getProjectList().getActualProject().getDescription());
        lbDesc.setEditable(false);
        lbDesc.setLineWrap(true);
        lbDesc.setWrapStyleWord(true);
        desc.add(lbDesc);
        project.add(name);
        project.add(new JScrollPane(desc));

        page2.add(project);
        page2.add(createTable());
        return page2;
    }

    private JPanel createTable() {
        JPanel p = new JPanel();
        p.add(new JScrollPane(table));
        return p;
    }

    private JPanel createButtons() {
        JPanel bt = new JPanel(new GridLayout(1, 3));

        JPanel pSave = new JPanel();
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SaveToFile saveToFile = new SaveToFile(tp.getProjectList().getActualProject(), na);
                //saveToFile.dispose();
            }
        }
        );
        pSave.add(save);

//        JPanel pDatab = new JPanel();
//        JButton database = new JButton("Export to DB");
//        database.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                try {
//                    DAONetwork daoNetwork = new DAONetwork(tp.getProjectList().getActualProject());
//                    daoHandler.addObjectData(daoNetwork, tp.getProjectList().getActualProject().getNetwork());
//                    DAONetworkAnalysis daoNetAnal = new DAONetworkAnalysis();
//                    daoHandler.addObjectData(daoNetAnal, na);
//                } catch (SQLException ex) {
//                    errMess("Error processing request!\n"
//                            + "The project was not saved to database first!", "Error");
//                    Logger.getLogger(CreateAnalysisUI.class.getName()).log(Level.SEVERE, null, ex);
//                } finally {
//                    sucMess("Exportation of Network and Analysis complete!", "Success");
//                }
//            }
//        }
//        );
//        pDatab.add(database);
        JPanel canc = new JPanel();
        canc.add(new CancelButton(this));

        bt.add(canc);
//        bt.add(pDatab);
        bt.add(pSave);
        return bt;
    }

    /**
     * ErrorMessages
     *
     * @param message message
     * @param title title of the error message
     */
    @Override
    public void errMess(String message, String title
    ) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Success messages
     *
     * @param message message
     * @param title title of the success message
     */
    @Override
    public void sucMess(String message, String title
    ) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel createAccComponents(JFrame accFrame) {
        JPanel accMain = new JPanel(new GridLayout(3, 1));
        accMain.add(createAccAccelarationComponent());
        accMain.add(createAccBrakingComponent());
        accMain.add(createAccButtons(accFrame));

        return accMain;
    }

    public JPanel createAccAccelarationComponent() {
        JPanel accMain = new JPanel(new GridLayout(1, 2));
        JPanel textAccelaration = new JPanel();
        JPanel labelAccelaration = new JPanel();
        JLabel lbAccelaration = new JLabel("Acelarating accelaration value");
        lbAccelaration.setHorizontalAlignment(JLabel.CENTER);
        labelAccelaration.add(lbAccelaration);
        this.accelaration = new JTextField(20);
        textAccelaration.add(accelaration);
        accMain.add(labelAccelaration);
        accMain.add(textAccelaration);

        return accMain;
    }

    public JPanel createAccBrakingComponent() {
        JPanel accMain = new JPanel(new GridLayout(1, 2));
        JPanel textBraking = new JPanel();
        JPanel labelBraking = new JPanel();
        JLabel lbBraking = new JLabel("Acelarating braking value");
        lbBraking.setHorizontalAlignment(JLabel.CENTER);
        labelBraking.add(lbBraking);
        this.braking = new JTextField(20);
        textBraking.add(braking);
        accMain.add(labelBraking);
        accMain.add(textBraking);

        return accMain;
    }

    public JPanel createAccButtons(JFrame accFrame) {
        JPanel accMain = new JPanel(new GridLayout(1, 2));
        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ca.runAlgorithm(alg, vehicle, node1, node2, analName.getText(), load.getText(), accelaration.getText(), braking.getText())) {
                    na = ca.getAnalysis();

                    tableModel = new DefaultTableModel();
                    tableModel.setDataVector(getFinalResultTable(), columnsHeader);
                    table = new JTable(tableModel);
                    mPanel.add(pageTwo(), "page2");
                    accFrame.dispose();
                    layout.show(mPanel, "page2");
                } else {
                    errMess(ERR_ANAL, MESS_ERR);
                }
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                accFrame.dispose();
            }
        });
        accMain.add(cancel);
        accMain.add(next);
        return accMain;
    }

    private Object[][] getFinalResultTable() {
        data = new Object[7][2];
        Object[][] result = ca.getFinalTableData();
        data[0][0] = "Analysis Name";
        data[0][1] = na.getName();
        data[1][0] = "Analysis id";
        data[1][1] = na.getId();
        data[2][0] = "Vehicle";
        data[2][1] = result[0][0];
        data[3][0] = "Best Path";
        data[3][1] = result[0][1];
        data[4][0] = "Traveling Time (seconds)";
        data[4][1] = result[0][2];
        data[5][0] = "Energy Consumption (joules)";
        data[5][1] = result[0][3];
        data[6][0] = "Toll cost (euro)";
        data[6][1] = result[0][4];
        return data;
    }
}