package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr.project.controller.CreateAnalysisController;
import lapr.project.model.NetworkAnalysis;
import lapr.project.model.TravelByPhysics;

public class CreateAnalysisUI extends JPanel implements MessagesAndUtils {

    private static final long serialVersionUID = 111L;

    /**
     * TravelByPhysics object
     */
    private final TravelByPhysics tp;
    private final CreateAnalysisController ca;

    private String node1;
    private String node2;
    private String vehicle;
    private String alg;
    private String[][] data = {{"",""}};
    private JTextField analName;
    private NetworkAnalysis na;

    /**
     * UI components
     */
    private final JPanel mPanel;
    private JPanel page1;
    private JPanel page2;
    private JTextArea projectDescription;
    private JTextField projectName;
    private final CardLayout layout = new CardLayout();

    public CreateAnalysisUI(TravelByPhysics tp) {
        this.tp = tp;
        ca = new CreateAnalysisController(tp);
        mPanel = new JPanel(layout);
        mPanel.setPreferredSize(dim);
        mPanel.add(pageOne(), "page1");
        mPanel.add(pageTwo(), "page2");
        layout.show(mPanel, "page1");
        setBackground(Color.GRAY);
        add(mPanel);
    }

    private JPanel pageOne() {

        page1 = new JPanel(new BorderLayout());
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
                if (!node1.equals(node2)) {
                    if (!analName.getText().isEmpty()) {
                        if (ca.runAlgorithm(alg, vehicle, node1, node2, analName.getText())) {
                            na = ca.getAnalysis();
                            fillDataArray();
//                            data = {{"nr_analise", na.getId()+""}, {"nome_analise", na.getName()}, {"travel_time", na.getTravellTime()+""}};
                            layout.show(mPanel, "page2");
                        } else {
                            errMess(ERR_ANAL, MESS_ERR);
                        }
                    } else {
                        errMess(EMPTY_FIELDS, MESS_ERR);
                    }
                } else {
                    errMess(ERR_NODE, MESS_ERR);
                }
            }
        }
        );
        return nextOne;
    }
    
    private void fillDataArray(){
        data = new String[3][2];
        
        data[0][0]="nr_analise";
        data[0][1]=na.getId()+"";
        data[1][0]="nome_analise";
        data[1][1]=na.getName();
        data[2][0]="travel_time";
        data[2][1]=na.getTravellTime()+"";
        
    }

    private JPanel selNode1() {
        String[] allNodes1 = ca.getNodeList().toArray(new String[0]);

        JComboBox<String> boxNodes1 = new JComboBox<>(allNodes1);
        boxNodes1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                node1 = (String) boxNodes1.getSelectedItem();
            }
        }
        );

        JPanel p = new JPanel();
        p.add(boxNodes1);
        return p;
    }

    private JPanel selNode2() {
        String[] allNodes2 = ca.getNodeList().toArray(new String[0]);

        JComboBox<String> boxNodes2 = new JComboBox<>(allNodes2);
        boxNodes2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                node2 = (String) boxNodes2.getSelectedItem();
            }
        }
        );

        JPanel p = new JPanel();
        p.add(boxNodes2);
        return p;
    }

    private JPanel selVehicle() {
        String[] allVehicles = ca.getVehicleList().toArray(new String[0]);

        JComboBox<String> boxVehicles = new JComboBox<>(allVehicles);
        boxVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vehicle = (String) boxVehicles.getSelectedItem();
            }
        }
        );
        JPanel p = new JPanel();
        p.add(boxVehicles);
        return p;
    }

    private JPanel selAlg() {
        String[] allAlg = ca.getAlgorithmList().toArray(new String[0]);

        JComboBox<String> boxAlg = new JComboBox<>(allAlg);
        boxAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                alg = (String) boxAlg.getSelectedItem();
            }
        }
        );
        JPanel p = new JPanel();
        p.add(boxAlg);
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
        JPanel p = new JPanel(new GridLayout(2, 2));
        JLabel text1 = new JLabel("Project: ");
        text1.setHorizontalAlignment(JLabel.CENTER);
        p.add(text1);

        JLabel text2 = new JLabel("name");
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

    private JPanel pageTwo() {
        page2 = new JPanel(new GridLayout(2, 1));

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
        String[] colNames = {" ", "Values"};

        JTable table = new JTable(data, colNames);
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
                new SaveToFile(tp.getProjectList().getActualProject());
            }
        }
        );
        pSave.add(save);

        JPanel pDatab = new JPanel();
        JButton database = new JButton("Export to DB");
        database.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Not Implemented", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        );
        pDatab.add(database);

        JPanel canc = new JPanel();
        canc.add(new CancelButton(this));

        bt.add(canc);
        bt.add(pDatab);
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
}
