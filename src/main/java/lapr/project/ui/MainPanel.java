package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr.project.model.Project;

public class MainPanel extends JPanel {

    public MainPanel() {
        JPanel p = new JPanel(new BorderLayout());
        ImageIcon image = new ImageIcon("src/main/resources/car1.jpg");
        JLabel label = new JLabel(image, JLabel.CENTER);
        p.add(label, BorderLayout.CENTER);
        add(p);
    }

    public MainPanel(Project pr) {
        JPanel p = new JPanel(new GridLayout(2, 2, 40, 40));

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

        JPanel descBox = new JPanel();
        JTextArea descTxt = new JTextArea(pr.getDescription());
        descTxt.setEditable(false);
        descBox.add(descTxt);
        
        p.add(pName);
        p.add(nameBox);
        p.add(pDesc);
        p.add(descBox);
        
        add(p);
    }

}
