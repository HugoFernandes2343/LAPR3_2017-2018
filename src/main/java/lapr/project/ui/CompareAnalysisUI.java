package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import lapr.project.controller.CreateAnalysisController;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.TravelByPhysics;

public class CompareAnalysisUI extends JPanel implements MessagesAndUtils {

    private static final long serialVersionUID = 111L;

    /**
     * Atributes that keep the information for building the table
     */
    private String[][] data;
    private String[] columnsHeader = {"", "Values"};
    private DefaultTableModel tableModel;

    /**
     * TravelByPhysics object
     */
    private final TravelByPhysics tp;
    private final CreateAnalysisController ca;

    /**
     * DAOHandler object
     */
//    protected DAOHandler daoHandler;
    private String node1;
    private String node2;
    private String alg;
    private JTextField analName;
    private List<NetworkAnalysis> na;
    private JTable table;

    /**
     * UI components
     */
    private final JPanel mPanel;
    private JTextArea projectDescription;
    private JTextField projectName;
    private final CardLayout layout = new CardLayout();

    public CompareAnalysisUI(TravelByPhysics tp) {
        this.tp = tp;
//		this.daoHandler = tp.getDAOHandler();
        ca = new CreateAnalysisController(tp);
        String[][] emptyData = new String[3][2];
        tableModel = new DefaultTableModel();
        this.table = new JTable(tableModel);
        tableModel.setDataVector(emptyData, columnsHeader);

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
                            if (!analName.getText().isEmpty()) {
                                mPanel.add(pageTwo(), "page2");
                                layout.show(mPanel, "page2");
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
        return (!node1.equals(sel)) && (!node2.equals(sel)) && (!alg.equals(sel));
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
        analName = new JTextField(25);
        JPanel p = new JPanel(new GridLayout(3, 2));
        JLabel text1 = new JLabel("Project: ");
        text1.setHorizontalAlignment(JLabel.CENTER);
        p.add(text1);

        JLabel text2 = new JLabel(tp.getProjectList().getActualProject().getName());
        text2.setHorizontalAlignment(JLabel.CENTER);
        p.add(text2);

        JLabel text3 = new JLabel("Analise: ");
        text3.setHorizontalAlignment(JLabel.CENTER);
        p.add(text3);

        JPanel an = new JPanel();
        an.add(analName);
        p.add(an);

        return p;
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

    private void createInfoScreen() {
        String[] tableLabels = {"Vehicle", "Load", "Enable Analysis", "Braking", "Accelaration"};
        String[] tableLabelsN10 = {"Vehicle", "Load", "Enable Analysis"};
        DefaultTableModel model = new DefaultTableModel();
        if ("Algorithm: Shortest Travell Time (N10)".equals(alg)) {
            model.setDataVector(ca.getInitialTableData(alg), tableLabelsN10);
        } else {
            model.setDataVector(ca.getInitialTableData(alg), tableLabels);
        }
        table = new JTable(model);
        TableColumn useColumn = table.getColumnModel().getColumn(2);
        TableColumn vehicleColumn = table.getColumnModel().getColumn(0);
        JCheckBox checkbox = new JCheckBox();
        JTextField vehicleField = new JTextField();
        vehicleField.setEditable(false);
        useColumn.setCellEditor(new DefaultCellEditor(checkbox));
        vehicleColumn.setCellEditor(new DefaultCellEditor(vehicleField));
        table.setFillsViewportHeight(true);
    }

    private JPanel createPageTwoButtons() {
        JPanel buttonsPageTwo = new JPanel(new GridLayout(1, 2));

        JPanel btNext = new JPanel();
        btNext.add(createNextButtonTwo());
        JPanel btCancel = new JPanel();
        btCancel.add(new CancelButton(this, tp.getProjectList().getActualProject()));

        buttonsPageTwo.add(btCancel);
        buttonsPageTwo.add(btNext);
        return buttonsPageTwo;
    }

    private Component pageTwo() {
        JPanel page2 = new JPanel(new BorderLayout());
        createInfoScreen();
        page2.add(new JScrollPane(table), BorderLayout.CENTER);
        page2.add(createPageTwoButtons(), BorderLayout.SOUTH);
        return page2;
    }

    /**
     * Method that creates and returns the "next" button, of the 2nd page
     *
     * @return "next" button
     */
    private JButton createNextButtonTwo() {
        JButton nextOne = new JButton("Next");
        nextOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    List<Object[]> chosenRows = new ArrayList<>();
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if (table.getValueAt(i, 2).equals(true)) {
                            Object[] dataRow = new Object[table.getColumnCount()];
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                dataRow[j] = table.getValueAt(i, j);
                            }
                            chosenRows.add(dataRow);
                        }
                    }
                    if (chosenRows.size() >= 2) {
                        if (ca.validateCandidates(chosenRows, alg)) {
                            na = ca.runBulkAnalysis(chosenRows, alg, node1, node2, analName.getText());
                            mPanel.add(pageThree(), "page3");
                            layout.show(mPanel, "page3");
                        } else {
                            errMess(ERR_LOAD, MESS_ERR);
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

    private JPanel pageThree() {
        JPanel page3 = new JPanel(new GridLayout(2, 1));

        JPanel project = new JPanel(new GridLayout(1, 2));
        JPanel name = new JPanel(new GridLayout(3, 1));

        JLabel n = new JLabel("Project: " + tp.getProjectList().getActualProject().getName());
        n.setHorizontalAlignment(JLabel.CENTER);

        JLabel a = new JLabel("Analysis Comparison: " + na.get(0).getName());
        a.setHorizontalAlignment(JLabel.CENTER);

        JPanel pn = new JPanel(new BorderLayout());
        JPanel pa = new JPanel(new BorderLayout());

        pn.add(n);
        pa.add(a);

        name.add(pn);
        name.add(pa);
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

        page3.add(project);
        createResultTable();
        page3.add(new JScrollPane(table));
        return page3;
    }

    private void createResultTable() {
        String[] tableLabels = {"Vehicle", "Best Path", "Traveling Time (seconds)", "Energy Consumption (joules)", "Toll cost (euro)"};
        DefaultTableModel model = new DefaultTableModel();
        Object[][] data = ca.getFinalTableData();
        model.setDataVector(data, tableLabels);
        table = new JTable(model);
        JTextField infoField = new JTextField();
        infoField.setEditable(false);

        TableColumn vehicleColumn = table.getColumnModel().getColumn(0);
        TableColumn pathColumn = table.getColumnModel().getColumn(1);
        TableColumn timeColumn = table.getColumnModel().getColumn(2);
        TableColumn energyColumn = table.getColumnModel().getColumn(3);
        TableColumn tollColumn = table.getColumnModel().getColumn(4);

        vehicleColumn.setCellEditor(new DefaultCellEditor(infoField));
        pathColumn.setCellEditor(new DefaultCellEditor(infoField));
        timeColumn.setCellEditor(new DefaultCellEditor(infoField));
        energyColumn.setCellEditor(new DefaultCellEditor(infoField));
        tollColumn.setCellEditor(new DefaultCellEditor(infoField));

        table.setFillsViewportHeight(true);

    }

    private JPanel createButtons() {
        JPanel bt = new JPanel(new GridLayout(1, 3));

        JPanel pSave = new JPanel();
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (NetworkAnalysis analysis : na) {
                    SaveToFile saveToFile = new SaveToFile(tp.getProjectList().getActualProject(), analysis);
                    //saveToFile.dispose();
                }
            }
        }
        );
        pSave.add(save);
        JPanel canc = new JPanel();
        JButton cancb = new CancelButton(this);
        cancb.setText("Close");
        canc.add(cancb);
        bt.add(canc);
        bt.add(pSave);
        return bt;
    }
}
