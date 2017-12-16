package lapr.project.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CancelButton extends JButton {
    private static final long serialVersionUID = 108L;
    
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
}
