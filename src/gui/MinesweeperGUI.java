package gui;

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

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu difficultyMenuItem;
    private JMenuItem newGameMenuItem;

    private JPanel mainPanel;
    private JPanel statusBar;
    private MineFieldGUI mineFieldPanel;
    private JRadioButtonMenuItem easyMenuItem;
    private JRadioButtonMenuItem mediumMenuItem;
    private JRadioButtonMenuItem hardMenuItem;
    private JRadioButtonMenuItem customDifficultyMenuItem;
    private ButtonGroup difficultyButtonGroup;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperGUI(){
        super("Minesweeper");

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
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });

        createUI();

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

        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mineFieldPanel.resetBoard();
            }
        });

        easyMenuItem = new JRadioButtonMenuItem("Easy");

        mediumMenuItem = new JRadioButtonMenuItem("Medium");

        hardMenuItem = new JRadioButtonMenuItem("Hard");
        hardMenuItem.setSelected(true);

        customDifficultyMenuItem = new JRadioButtonMenuItem("Custom");

        difficultyButtonGroup = new ButtonGroup();
        difficultyButtonGroup.add(easyMenuItem);
        difficultyButtonGroup.add(mediumMenuItem);
        difficultyButtonGroup.add(hardMenuItem);
        difficultyButtonGroup.add(customDifficultyMenuItem);

        difficultyMenuItem = new JMenu("Difficulty");
        difficultyMenuItem.add(easyMenuItem);
        difficultyMenuItem.add(mediumMenuItem);
        difficultyMenuItem.add(hardMenuItem);
        difficultyMenuItem.addSeparator();
        difficultyMenuItem.add(customDifficultyMenuItem);

        menu = new JMenu("Menu");
        menu.add(newGameMenuItem);
        menu.add(difficultyMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(menu);

        mineFieldPanel = new MineFieldGUI();

        statusBar = new JPanel(new GridLayout(1, 2));

        mainPanel = new JPanel(new GridBagLayout());
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

    public void showUI(){
        pack();
        setVisible(true);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
