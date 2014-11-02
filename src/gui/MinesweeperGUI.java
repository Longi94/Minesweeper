package gui;

import base.MinesweeperPreferences;
import game.MineCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MinesweeperGUI extends JFrame{

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int ROWS = 16;
    public static final int COLUMNS = 30;
    public static final int WIDTH = COLUMNS * MineCell.SIZE;
    public static final int HEIGHT = (ROWS + 1) * MineCell.SIZE;

    // ===========================================================
    // Fields
    // ===========================================================

    private MineFieldGUI mineFieldPanel;
    private MinesweeperPreferences prefs;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperGUI(MinesweeperPreferences prefs){
        super("Minesweeper");

        this.prefs = prefs;

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "Save game?",
                        "Exit Application",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (result != JOptionPane.CANCEL_OPTION)
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });

        createUI();
        pack();
        setVisible(true);

    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    private void createUI(){

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mineFieldPanel.resetBoard();
            }
        });

        JRadioButtonMenuItem easyMenuItem = new JRadioButtonMenuItem("Easy");
        easyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefs.setDifficulty(9, 9, 10);
            }
        });

        JRadioButtonMenuItem mediumMenuItem = new JRadioButtonMenuItem("Medium");
        mediumMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefs.setDifficulty(16, 16, 40);
            }
        });

        JRadioButtonMenuItem hardMenuItem = new JRadioButtonMenuItem("Hard");
        hardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefs.setDifficulty(16, 30, 99);
            }
        });
        hardMenuItem.setSelected(true);

        JRadioButtonMenuItem customDifficultyMenuItem = new JRadioButtonMenuItem("Custom");

        ButtonGroup difficultyButtonGroup = new ButtonGroup();
        difficultyButtonGroup.add(easyMenuItem);
        difficultyButtonGroup.add(mediumMenuItem);
        difficultyButtonGroup.add(hardMenuItem);
        difficultyButtonGroup.add(customDifficultyMenuItem);

        JMenu difficultyMenuItem = new JMenu("Difficulty");
        difficultyMenuItem.add(easyMenuItem);
        difficultyMenuItem.add(mediumMenuItem);
        difficultyMenuItem.add(hardMenuItem);
        difficultyMenuItem.addSeparator();
        difficultyMenuItem.add(customDifficultyMenuItem);

        JMenuItem settingsMenuItem = new JMenuItem("Preferences");
        settingsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MinesweeperPreferencesGUI prefDialog = new MinesweeperPreferencesGUI(MinesweeperGUI.this, true, prefs, 20);
            }
        });

        JMenuItem recordsMenuItem = new JMenuItem("Records");
        recordsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenu menu = new JMenu("Menu");
        menu.add(newGameMenuItem);
        menu.add(difficultyMenuItem);
        menu.add(settingsMenuItem);
        menu.add(recordsMenuItem);
        menu.add(exitMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        mineFieldPanel = new MineFieldGUI();

        JPanel statusBar = new JPanel(new GridLayout(1, 2));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainPanel.add(mineFieldPanel, new GridBagConstraints(0, 0, ROWS, COLUMNS, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0
        ));
        mainPanel.add(statusBar,
                new GridBagConstraints(0, ROWS, 1, COLUMNS, 0, 0, GridBagConstraints.SOUTH,
                        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0
                ));

        setJMenuBar(menuBar);
        add(mainPanel);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
