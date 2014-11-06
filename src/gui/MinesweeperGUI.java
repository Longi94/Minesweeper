package gui;

import base.Main;
import base.MinesweeperPreferences;
import gui.panel.MineCellPanel;
import gui.dialog.CustomDifficultyDialog;
import gui.dialog.HighScoresDialog;
import gui.dialog.MinesweeperPreferencesDialog;

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

    // ===========================================================
    // Fields
    // ===========================================================

    private MineFieldGUI mineFieldPanel;
    private JPanel mainPanel;
    private JPanel statusBar;

    private JLabel bombsLabel;
    private JLabel timeLabel;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperGUI(){
        super("Minesweeper");

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

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

        mainPanel = new JPanel(new BorderLayout());

        createStatusBar();
        createMenuBar();
        createUI();
        add(mainPanel);
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

    private void createStatusBar(){
        Font statusFont = new Font("Verdana", Font.BOLD, 12);

        bombsLabel = new JLabel("Mines left: " + getPrefs().getBombsLeft());
        bombsLabel.setFont(statusFont);

        timeLabel = new JLabel("00:00");
        timeLabel.setFont(statusFont);

        statusBar = new JPanel(new GridLayout(1, 2));
        statusBar.setPreferredSize(new Dimension(MineCellPanel.SIZE * getPrefs().getNumberOfColumns(), MineCellPanel.SIZE));
        statusBar.add(bombsLabel);
        statusBar.add(timeLabel);
    }

    private void createUI(){
        mineFieldPanel = new MineFieldGUI(bombsLabel, timeLabel);

        mainPanel.add(mineFieldPanel, BorderLayout.CENTER);
        mainPanel.add(statusBar, BorderLayout.PAGE_END);
    }


    private void createMenuBar() {
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mineFieldPanel.cancelTimer();
                mainPanel.removeAll();
                statusBar.setPreferredSize(new Dimension(MineCellPanel.SIZE * getPrefs().getNumberOfColumns(), MineCellPanel.SIZE));
                getPrefs().setBombsLeft(getPrefs().getNumberOfBombs());
                bombsLabel.setText("Mines left: " + getPrefs().getBombsLeft());
                createUI();
                revalidate();
                repaint();
                pack();
            }
        });

        JRadioButtonMenuItem easyMenuItem = new JRadioButtonMenuItem("Easy");
        easyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrefs().setDifficulty(MinesweeperPreferences.Difficulty.EASY);
            }
        });

        JRadioButtonMenuItem mediumMenuItem = new JRadioButtonMenuItem("Medium");
        mediumMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrefs().setDifficulty(MinesweeperPreferences.Difficulty.MEDIUM);
            }
        });

        JRadioButtonMenuItem hardMenuItem = new JRadioButtonMenuItem("Hard");
        hardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrefs().setDifficulty(MinesweeperPreferences.Difficulty.HARD);
            }
        });
        hardMenuItem.setSelected(true);

        JRadioButtonMenuItem customDifficultyMenuItem = new JRadioButtonMenuItem("Custom");
        customDifficultyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomDifficultyDialog(MinesweeperGUI.this, true);
            }
        });

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
                new MinesweeperPreferencesDialog(MinesweeperGUI.this, true, 20);
            }
        });

        JMenuItem recordsMenuItem = new JMenuItem("Records");
        recordsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScoresDialog(MinesweeperGUI.this, true);
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

        setJMenuBar(menuBar);
    }

    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
