
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lapr.project.model.TravelByPhysics;

public class MenuUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JMenuBar menuBarMain;
    private JPanel mPanel;

    /**
     * Menu buttons
     */
    private static JMenuItem copy;
    private static JMenuItem changeName;
    private static JMenuItem importVehicles;
    private static JMenuItem importRoads;
    private static JMenuItem bestPath;
    private static JMenuItem compareVehicle;
    private static JMenuItem saveDatabase;
    private static JMenuItem saveHTML;

    TravelByPhysics tp;

//    private static boolean ProjectIsSelected = false;
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 485;

    private static final String UC_IMPLEMENTATION = "Not Implemented";

    public MenuUI(TravelByPhysics tp) {
        setLookAndFeel();
        this.tp = tp;
        JMenuBar barraMenu = criarBarraMenu();
        setJMenuBar(barraMenu);
        mPanel = new MainPanel();
        mPanel.setBackground(Color.GRAY);
        add(mPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setTitle("Travel by Physics");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH));
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private JMenuBar criarBarraMenu() {

        menuBarMain = new JMenuBar();

        JMenu project = createProjectMenu();
        menuBarMain.add(project);

        JMenu statistics = createStatisticsMenu();
        menuBarMain.add(statistics);

        return menuBarMain;
    }

    private JMenu createProjectMenu() {
        //butao de menu que abre em cascata outras opcoes
        JMenu Project = new JMenu("Project");
        Project.setMnemonic(KeyEvent.VK_P);

        //opcoes abertas pelo butao de cima
        JMenuItem createProject = new JMenuItem("P02: Create Project");
        createProject.setEnabled(true);
        createProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mPanel.removeAll();
                mPanel.add(new CreateProjectUI(tp));
                mPanel.revalidate();
                mPanel.repaint();
            }
        });
        Project.add(createProject);

        JMenuItem selectProject = new JMenuItem("P01: Select Project.");
        selectProject.setEnabled(true);
        selectProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mPanel.removeAll();
                mPanel.add(new SelectProjectUI(tp));
                mPanel.revalidate();
                mPanel.repaint();
            }
        });
        Project.add(selectProject);

        JMenu projectDetails = createDetailsMenu();
        Project.add(projectDetails);

        JMenu importInfo = createInfoMenu();
        Project.add(importInfo);

        return Project;
    }

    private JMenu createDetailsMenu() {
        JMenu m = new JMenu("Details");

        copy = new JMenuItem("P03: Copy Project");
        copy.setEnabled(false);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new CopyProjectUI(tp);
            }
        });
        m.add(copy);

        changeName = new JMenuItem("P04: Change Project Name and Description");
        changeName.setEnabled(false);
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mPanel.removeAll();
                mPanel.add(new EditProjectUI(tp));
                mPanel.revalidate();
                mPanel.repaint();
            }
        });
        m.add(changeName);
        return m;
    }

    private JMenu createInfoMenu() {
        JMenu m = new JMenu("Imports");

        importVehicles = new JMenuItem("P05: Import vehicles config file");
        importVehicles.setEnabled(false);
        importVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //accao do butao
            }
        });
        m.add(importVehicles);

        importRoads = new JMenuItem("P06: Import roads config file");
        importRoads.setEnabled(false);
        importRoads.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //accao do butao
            }
        });
        m.add(importRoads);

        return m;
    }

    private JMenu createStatisticsMenu() {
        JMenu m = new JMenu("Statistics");
        m.setMnemonic(KeyEvent.VK_S);

        bestPath = new JMenuItem("N01: Analyse best path");
        bestPath.setEnabled(false);
        bestPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //butao
            }
        });
        m.add(bestPath);

        JMenu save = createSaveMenu();
        m.add(save);

        compareVehicle = new JMenuItem("N20: Compare Vehicles");
        compareVehicle.setEnabled(false);
        compareVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //createNotImplementedWindow(UC_IMPLEMENTATION);
            }
        });
        m.add(compareVehicle);
        return m;
    }

    private JMenu createSaveMenu() {
        JMenu m = new JMenu("Save");
        m.setMnemonic(KeyEvent.VK_D);

        saveDatabase = new JMenuItem("N05: Save Project to database");
        saveDatabase.setEnabled(false);
        saveDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //createNotImplementedWindow(UC_IMPLEMENTATION);
            }
        });
        m.add(saveDatabase);

        saveHTML = new JMenuItem("N06: Save Project to html file");
        saveHTML.setEnabled(false);
        saveHTML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //createNotImplementedWindow(UC_IMPLEMENTATION);
            }
        });
        m.add(saveHTML);
        return m;
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void SetProject() {
        copy.setEnabled(true);
        changeName.setEnabled(true);
        importVehicles.setEnabled(true);
        importRoads.setEnabled(true);
        bestPath.setEnabled(true);
        compareVehicle.setEnabled(true);
        saveDatabase.setEnabled(true);
        saveHTML.setEnabled(true);
    }

    public void createNotImplementedWindow(String text) {
        JFrame info = new JFrame();

        JPanel showInfo = new JPanel();
        JLabel label = new JLabel();
        label.setText(text);
        label.setFont(new Font("Serif", Font.PLAIN, 25));
        showInfo.add(label);
        info.add(showInfo);

        info.setLocationRelativeTo(null);
        info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info.setMinimumSize(new Dimension(300, 300));
        info.setVisible(true);
    }

}
