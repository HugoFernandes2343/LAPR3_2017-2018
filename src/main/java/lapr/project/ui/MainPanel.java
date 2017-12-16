package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lapr.project.model.Project;
import static lapr.project.ui.MessagesAndUtils.dim;

public class MainPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public MainPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setPreferredSize(dim);
        setBackground(Color.GRAY);
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
