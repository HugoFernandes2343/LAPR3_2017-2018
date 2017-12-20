package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.SelectProjectController;
import lapr.project.model.TravelByPhysics;

public class SelectProjectUI extends JPanel implements MessagesAndUtils {

    private static final long serialVersionUID = 106L;

    private final TravelByPhysics tp;
    private final SelectProjectController sp;

    private final JPanel mPanel;
    private final JList<String> projectList;
    private String name = "";

    /**
     * constructor
     *
     * @param tp
     */
    public SelectProjectUI(TravelByPhysics tp) {
        this.tp = tp;
        sp = new SelectProjectController(this.tp);
        mPanel = new JPanel();

        projectList = new JList<>();

        /**
         * Tries to get the Project list, if it is not possible shows error
         * message
         */
        try {
            List<String> eventsList = sp.getProjects();
            projectList.setListData(eventsList.toArray(new String[0]));
        } catch (NullPointerException ex) {
            Logger.getLogger(SelectProjectUI.class.getName()).log(Level.SEVERE, null, ex);
            errMess(NO_PROJ, MESS_ERR);
            removeAll();
            add(new MainPanel());
            revalidate();
            repaint();
        }

        mPanel.add(createPage());
        setBackground(Color.GRAY);
        add(mPanel);
    }

    /**
     * Method that creates and returns the main page inside a panel
     *
     * @return Panel with all the components
     */
    private JPanel createPage() {
        JPanel page = new JPanel(new GridLayout(1, 2));
        page.setPreferredSize(dim);
        page.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel lb = new JPanel(new GridLayout(2, 1, 20, 20));

        JPanel lp = new JPanel();
        JLabel sel = new JLabel("Select a Project: ");
        lp.add(sel);
        lb.add(lp);
        lb.add(createPageButtons());

        page.add(lb);
        page.add(new JScrollPane(createProjectList()));

        return page;
    }

    /**
     * Method that creates and returns all the page's buttons
     *
     * @return a panel with all the buttons for the page
     */
    private JPanel createPageButtons() {
        JPanel bt = new JPanel(new GridLayout(2, 1));

        JPanel btSelect = new JPanel();
        btSelect.add(createBtSelect());

        JPanel btCancel = new JPanel();
        btCancel.add(getCancelButton());

        bt.add(btSelect);
        bt.add(btCancel);
        return bt;
    }

    /**
     * creates the cancel button for this uc
     *
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
     * Method that creates the select button
     *
     * @return a panel with the select button
     */
    private JPanel createBtSelect() {
        JPanel btS = new JPanel();
        JButton nuttonSelect = new JButton("Select");
        nuttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (!name.isEmpty()) {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Project: " + name + "\nSelect this Project?",
                                MESS_CONF, dialogButton);
                        if (dialogResult == 0) {
                            sp.loadActiveProject(name);
                            sucMess(SEL_SUCC, MESS_SUCC);
                            removeAll();
                            add(new MainPanel(tp.getProjectList().getActualProject()));
                            revalidate();
                            repaint();
                        }
                    } else {
                        errMess(ERR_NO_PROJECT, MESS_ERR);
                    }
                } catch (NullPointerException ex) {
                    Logger.getLogger(SelectProjectUI.class.getName()).log(Level.SEVERE, null, ex);
                    errMess(ERR_PROJ, MESS_ERR);
                }
            }
        });
        btS.add(nuttonSelect);
        return btS;
    }

    /**
     * Method that creates the JList and returns it inside a Jpanel
     *
     * @return a panel with the jlist inside
     */
    private JPanel createProjectList() {
        JPanel list = new JPanel(new BorderLayout());
        list.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        projectList.setVisibleRowCount(10);
        projectList.setCellRenderer(new DefaultListCellRenderer() {
            /**
             * Variable that defines the serialVersionUID
             */
            static final long serialVersionUID = 107L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel) {
                    ((JLabel) renderer).setText(((String) value));
                }
                return renderer;
            }
        });

        projectList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                name = projectList.getSelectedValue();
            }
        });
        list.add(projectList);
        return list;
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
