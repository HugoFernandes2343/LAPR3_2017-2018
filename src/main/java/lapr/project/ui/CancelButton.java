package lapr.project.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import lapr.project.model.Project;

public class CancelButton extends JButton {
    private static final long serialVersionUID = 108L;
    
    /**
     * Cancel button that returns to the car image menu
     * @param p panel to alter
     */
    public CancelButton(JPanel p) {

        setText("Cancel");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p.removeAll();
                p.add(new MainPanel());
                p.revalidate();
                p.repaint();
            }
        });
    }
    
    /**
     * Cancel button that returns to the project information menu
     * @param p pane to be altered
     * @param pj project which details will be shown
     */
    public CancelButton(JPanel p, Project pj){
        setText("Cancel");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                p.removeAll();
                p.add(new MainPanel(pj));
                p.revalidate();
                p.repaint();
            }
        });
    }
}
