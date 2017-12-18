package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr.project.controller.EditProjectController;
import lapr.project.model.TravelByPhysics;
import static lapr.project.ui.MessagesAndUtils.dim;

public class EditProjectUI extends JPanel implements MessagesAndUtils {

    private static final long serialVersionUID = 109L;
    /**
     * TravelByPhysics object
     */
    private final TravelByPhysics tp;
    private final EditProjectController ep;

    private final JPanel mPanel;
    private JTextArea projectDescription;
    private JTextField projectName;

    public EditProjectUI(TravelByPhysics tp) {
        this.tp = tp;
        ep = new EditProjectController(tp);
        ep.readyEdit();
        mPanel = new JPanel();
        mPanel.setPreferredSize(dim);
        mPanel.add(createPageOne());
        setBackground(Color.GRAY);
        add(mPanel);
    }

    /**
     * Method that creates the page one of the card layout
     *
     * @return
     */
    private JPanel createPageOne() {
        JPanel page1 = new JPanel(new GridLayout(3, 1, 20, 20));
        JPanel nameLabel = createHeader("Project Name: ");
        page1.add(nameLabel);
        projectName = new JTextField(30);
        projectName.setText(tp.getProjectList().getActualProject().getName());
        JPanel pName = new JPanel();
        pName.add(projectName);
        page1.add(pName);
        JPanel descriptionLabel = createHeader("Project Description: ");
        page1.add(descriptionLabel);
        projectDescription = new JTextArea(7, 20);
        projectDescription.setLineWrap(true);
        projectDescription.setText(tp.getProjectList().getActualProject().getDescription());
        page1.add(new JScrollPane(projectDescription));

        page1.add(createPageOneButtons(1));
        page1.add(createPageOneButtons(2));

        return page1;
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
     * Method that creates buttons of the 1st page of the card layout
     *
     * @return
     */
    private JPanel createPageOneButtons(int c) {
        JPanel buttonsPageOne = new JPanel();
        if (c == 1) {
            JPanel btCancel = new JPanel();
            btCancel.add(new CancelButton(this, tp.getProjectList().getActualProject()));
            buttonsPageOne.add(btCancel);
            return buttonsPageOne;
        } else {
            JPanel btNext = new JPanel();
            btNext.add(createSaveButton());
            buttonsPageOne.add(btNext);
            return buttonsPageOne;
        }
    }

    /**
     * Method that creates and returns the "next" button, of the 1st page
     *
     * @return "next" button
     */
    private JButton createSaveButton() {
        JButton nextOne = new JButton("Save");
        nextOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nameStr = projectName.getText();
                String descrStr = projectDescription.getText();
                if (ep.alterProject(nameStr, descrStr)) {
                    if (ep.saveChanges()) {
                        suc_mess(UP_SUCC, MESS_SUCC);
                        removeAll();
                        add(new MainPanel(tp.getProjectList().getActualProject()));
                        revalidate();
                        repaint();
                    } else {
                        err_mess("Project name already exists", MESS_ERR);
                    }
                } else {
                    err_mess("Fields are empty", MESS_ERR);
                }
            }
        }
        );
        return nextOne;
    }

    @Override
    public void err_mess(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void suc_mess(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
