package lapr.project.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lapr.project.model.TravelByPhysics;

public class MenuUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel mPanel;

    /**
     * Menu buttons
     */
    JMenuBar mainBar;
    private JMenuItem copy;
    private JMenuItem changeName;
    private JMenuItem importVehicles;
    private JMenuItem importRoads;
    private JMenuItem bestPath;
    private JMenuItem compareVehicle;
    private JMenuItem saveDatabase;

    private final TravelByPhysics tp;

    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 485;

    /**
     * constructor of the class
     *
     * @param tp travel by physics object
     */
    public MenuUI(TravelByPhysics tp) {
        setLookAndFeel();
        this.tp = tp;
        mainBar = createMenuBar();
        setJMenuBar(mainBar);
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

    /**
     * creates the menu bar
     *
     * @return the menu bar
     */
    private JMenuBar createMenuBar() {

        JMenuBar menuBarMain = new JMenuBar();

        JMenu project = createProjectMenu();
        menuBarMain.add(project);

        JMenu statistics = createStatisticsMenu();
        menuBarMain.add(statistics);

        return menuBarMain;
    }

    /**
     * Creates the content of the project button on the menu bar
     *
     * @return the menu created
     */
    private JMenu createProjectMenu() {

        JMenu project = new JMenu("Project");
        project.setMnemonic(KeyEvent.VK_P);

        JMenuItem createProject = new JMenuItem("P02: Create Project");
        createProject.setEnabled(true);
        createProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mPanel.removeAll();
                mPanel.add(new CreateProjectUI(tp, MenuUI.this));
                mPanel.revalidate();
                mPanel.repaint();
            }
        });
        project.add(createProject);

        JMenuItem selectProject = new JMenuItem("P01: Select Project.");
        selectProject.setEnabled(true);
        selectProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mPanel.removeAll();
                mPanel.add(new SelectProjectUI(tp, MenuUI.this));
                mPanel.revalidate();
                mPanel.repaint();
            }
        });
        project.add(selectProject);

        JMenu projectDetails = createDetailsMenu();
        project.add(projectDetails);

        JMenu importInfo = createInfoMenu();
        project.add(importInfo);

        return project;
    }

    /**
     * creates the content of the details menu
     *
     * @return the created menu
     */
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

    /**
     * creates the content of the info menu
     *
     * @return the created menu
     */
    private JMenu createInfoMenu() {
        JMenu m = new JMenu("Imports");

        importVehicles = new JMenuItem("P05: Import vehicles config file");
        importVehicles.setEnabled(false);
        importVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new ImportFilesUI(tp, "vehicle");
            }
        });
        m.add(importVehicles);

        importRoads = new JMenuItem("P06: Import roads config file");
        importRoads.setEnabled(false);
        importRoads.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new ImportFilesUI(tp, "roads");
            }
        });
        m.add(importRoads);

        return m;
    }

    /**
     * Creates the content of the Statistics menu
     *
     * @return the menu created
     */
    private JMenu createStatisticsMenu() {
        JMenu m = new JMenu("Statistics");
        m.setMnemonic(KeyEvent.VK_S);

        bestPath = new JMenuItem("N01: Analyse best path");
        bestPath.setEnabled(false);
        bestPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mPanel.removeAll();
                mPanel.add(new CreateAnalysisUI(tp));
                mPanel.revalidate();
                mPanel.repaint();
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
            }
        });
        m.add(compareVehicle);
        return m;
    }

    /**
     * creates the content of the save menu
     *
     * @return the menu created
     */
    private JMenu createSaveMenu() {
        JMenu m = new JMenu("Save");
        m.setMnemonic(KeyEvent.VK_D);

        saveDatabase = new JMenuItem("N05: Save Project to database");
        saveDatabase.setEnabled(false);
        saveDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        m.add(saveDatabase);
        return m;
    }

    /**
     * sets the windows look and feel
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MenuUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Enables the buttons after selecting a project
     */
    public void setProject() {
        copy.setEnabled(true);
        changeName.setEnabled(true);
        importVehicles.setEnabled(true);
        importRoads.setEnabled(true);
        bestPath.setEnabled(true);
        compareVehicle.setEnabled(true);
        saveDatabase.setEnabled(true);
    }

}
