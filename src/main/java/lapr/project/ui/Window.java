/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Window Class
 *
 * @author Utilizador
 */
public class Window extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Contruct that creates a default window to allow data to be displayed
     *
     * @param globalWidth width of window
     * @param globalHeight height of window
     */
    public Window(int globalWidth, int globalHeight) {
        setLookAndFeel();
        setPreferredSize(new Dimension(globalWidth, globalHeight));
        this.setResizable(false);
    }

    /**
     * Creates and adds a default JPanel to the Frame. Return said Panel for
     * further customization
     *
     * @param layout layout for the panel
     * @param width width
     * @param height height
     * @return the newly created panel
     */
    public JPanel createPanel(LayoutManager layout, int width, int height) {
        JPanel newPanel = new JPanel(layout);
        newPanel.setSize(new Dimension(width, height));
        this.getContentPane().add(newPanel);
        return newPanel;
    }

    /**
     * Gets rid of window
     */
    public void destroyWindow() {
        this.dispose();
    }

    /**
     * Sets Look and Feel to the Swing Components
     */
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
