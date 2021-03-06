package gui;

import base.Main;
import base.MinesweeperPreferences;
import game.Player;
import gui.panel.MineCellPanel;
import gui.dialog.CustomDifficultyDialog;
import gui.dialog.HighScoresDialog;
import gui.dialog.MinesweeperPreferencesDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The main frame of the game.
 */
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
    private JButton faceButton;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Main constructor. Creates every component of the game.
     */
    public MinesweeperGUI(){
        super("Minesweeper");

        mainPanel = new JPanel(new BorderLayout());

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setIconImage((new ImageIcon("assets/mine_icon.png")).getImage());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();

                //we provide and option for the player to save the game if can
                if (Player.isGameStarted()) {
                    int result = JOptionPane.showConfirmDialog(
                            frame,
                            "Save game?",
                            "Exit Application",
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        mineFieldPanel.saveGame();
                        Main.savePreferences();
                    } else if (result == JOptionPane.NO_OPTION) {
                        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        getPrefs().setSavedGame(null);
                        getPrefs().setBombsLeft(getPrefs().getNumberOfBombs());
                        getPrefs().setSavedTime(0);
                        Main.savePreferences();
                    }
                }
                else {
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    getPrefs().setSavedGame(null);
                    getPrefs().setBombsLeft(getPrefs().getNumberOfBombs());
                    getPrefs().setSavedTime(0);
                    Main.savePreferences();
                }
            }
        });

        createStatusBar();
        createMenuBar();
        createUI();
        add(mainPanel);
        pack();
        //put the window in the middle of the screen
        setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getWidth() / 2,
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getHeight() / 2);
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

    /**
     * Creates the bottom status bar.
     */
    private void createStatusBar(){
        Font statusFont = new Font("Roboto Black", Font.BOLD, 12);

        bombsLabel = new JLabel("Mines left: " + getPrefs().getBombsLeft());
        bombsLabel.setFont(statusFont);
        bombsLabel.setBorder(new EmptyBorder(0, 10, 0, 10));

        timeLabel = new JLabel("00:00");
        timeLabel.setFont(statusFont);
        timeLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        if (getPrefs().isShowTimer())
            timeLabel.setForeground(new Color(0, 0, 0));
        else
            timeLabel.setForeground(new Color(230, 230, 230));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(timeLabel, BorderLayout.EAST);
        rightPanel.setBackground(new Color(230, 230, 230));

        faceButton = new JButton(new ImageIcon("assets/alive.png"));
        faceButton.setFocusable(false);
        faceButton.setPreferredSize(new Dimension(40, 40));
        faceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(faceButton);
        buttonPanel.setBackground(new Color(230, 230, 230));

        statusBar = new JPanel(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(MineCellPanel.SIZE * getPrefs().getNumberOfColumns(), 50));
        statusBar.setBackground(new Color(230, 230, 230));
        statusBar.add(bombsLabel, BorderLayout.WEST);
        statusBar.add(rightPanel, BorderLayout.EAST);
        statusBar.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the remaining UI elements.
     */
    private void createUI(){
        mineFieldPanel = new MineFieldGUI(bombsLabel, timeLabel, faceButton);
        mainPanel.add(mineFieldPanel, BorderLayout.CENTER);
        mainPanel.add(statusBar, BorderLayout.PAGE_END);
    }

    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        JRadioButtonMenuItem easyMenuItem = new JRadioButtonMenuItem("Easy");
        easyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrefs().setDifficulty(MinesweeperPreferences.Difficulty.EASY);
            }
        });
        if (getPrefs().getDifficulty() == MinesweeperPreferences.Difficulty.EASY)
            easyMenuItem.setSelected(true);

        JRadioButtonMenuItem mediumMenuItem = new JRadioButtonMenuItem("Medium");
        mediumMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrefs().setDifficulty(MinesweeperPreferences.Difficulty.MEDIUM);
            }
        });
        if (getPrefs().getDifficulty() == MinesweeperPreferences.Difficulty.MEDIUM)
            mediumMenuItem.setSelected(true);

        JRadioButtonMenuItem hardMenuItem = new JRadioButtonMenuItem("Hard");
        hardMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrefs().setDifficulty(MinesweeperPreferences.Difficulty.HARD);
            }
        });
        if (getPrefs().getDifficulty() == MinesweeperPreferences.Difficulty.HARD)
            hardMenuItem.setSelected(true);

        JRadioButtonMenuItem customMenuItem = new JRadioButtonMenuItem("Custom");
        customMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomDifficultyDialog(MinesweeperGUI.this, true);
            }
        });
        if (getPrefs().getDifficulty() == MinesweeperPreferences.Difficulty.CUSTOM)
            customMenuItem.setSelected(true);

        ButtonGroup difficultyButtonGroup = new ButtonGroup();
        difficultyButtonGroup.add(easyMenuItem);
        difficultyButtonGroup.add(mediumMenuItem);
        difficultyButtonGroup.add(hardMenuItem);
        difficultyButtonGroup.add(customMenuItem);

        JMenu difficultyMenuItem = new JMenu("Difficulty");
        difficultyMenuItem.add(easyMenuItem);
        difficultyMenuItem.add(mediumMenuItem);
        difficultyMenuItem.add(hardMenuItem);
        difficultyMenuItem.addSeparator();
        difficultyMenuItem.add(customMenuItem);

        JMenuItem settingsMenuItem = new JMenuItem("Preferences");
        settingsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MinesweeperPreferencesDialog(MinesweeperGUI.this, true, mineFieldPanel.getMineField());
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
                JFrame frame = MinesweeperGUI.this;
                if (Player.isGameStarted()) {
                    //provide an option to save the game
                    int result = JOptionPane.showConfirmDialog(
                            frame,
                            "Save game?",
                            "Exit Application",
                            JOptionPane.YES_NO_CANCEL_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        mineFieldPanel.saveGame();
                        Main.savePreferences();
                        System.exit(0);
                    } else if (result == JOptionPane.NO_OPTION) {
                        getPrefs().setSavedGame(null);
                        getPrefs().setBombsLeft(getPrefs().getNumberOfBombs());
                        getPrefs().setSavedTime(0);
                        Main.savePreferences();
                        System.exit(0);
                    }
                }
                else {
                    getPrefs().setSavedGame(null);
                    getPrefs().setBombsLeft(getPrefs().getNumberOfBombs());
                    getPrefs().setSavedTime(0);
                    Main.savePreferences();
                    System.exit(0);
                }
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

    /**
     * Returns the preferences used by the game.
     * @return the preferences
     * @see base.MinesweeperPreferences
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    /**
     * Rebuilds the board.
     */
    private void startNewGame(){
        int middleX = getX() + getWidth() / 2;
        int middleY = getY() + getHeight() / 2;
        mineFieldPanel.cancelTimer();
        // simply remove everything from the panel and rebuild everything
        mainPanel.removeAll();
        statusBar.setPreferredSize(new Dimension(MineCellPanel.SIZE * getPrefs().getNumberOfColumns(), 50));
        getPrefs().setBombsLeft(getPrefs().getNumberOfBombs());
        bombsLabel.setText("Mines left: " + getPrefs().getBombsLeft());
        timeLabel.setText("00:00");
        faceButton.setIcon(new ImageIcon("assets/alive.png"));
        createUI();
        revalidate();
        repaint();
        pack();
        setLocation(middleX - getWidth() / 2, middleY - getHeight() / 2);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
