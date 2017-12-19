package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr.project.model.Project;
import static lapr.project.ui.MessagesAndUtils.dim;

public class MainPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Empty constructor, 
     */
    public MainPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setPreferredSize(dim);
        setBackground(Color.GRAY);
        ImageIcon image = new ImageIcon("src/main/resources/car1.jpg");
        JLabel label = new JLabel(image, JLabel.CENTER);
        p.add(label, BorderLayout.CENTER);
        add(p);
    }

    /**
     * panel with the project information
     * @param pr 
     */
    public MainPanel(Project pr) {
        JPanel p = new JPanel(new GridLayout(3, 2, 40, 40));
        p.setPreferredSize(dim);
        JPanel pName = new JPanel();
        JLabel nameLB = new JLabel("Project Name: ");
        pName.add(nameLB);

        JPanel nameBox = new JPanel();
        JTextField nameTxt = new JTextField(pr.getName());
        nameTxt.setEditable(false);
        nameBox.add(nameTxt);

        JPanel pDesc = new JPanel();
        JLabel descLB = new JLabel("Project Description: ");
        pDesc.add(descLB);

        
        JTextArea descTxt = new JTextArea(pr.getDescription());
        descTxt.setLineWrap(true);
        descTxt.setWrapStyleWord(true);
        descTxt.setEditable(false);

        p.add(pName);
        p.add(nameBox);
        p.add(pDesc);
        p.add(new JScrollPane(descTxt));

        add(p);
    }

}
