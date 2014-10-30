package gui;

import game.MineCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    private JMenuItem newGameMenuItem;

    private JPanel mainPanel;
    private JPanel statusBar;
    private MineFieldGUI mineFieldPanel;

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
                        "U SUR???!",
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
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGameMenuItem = new JMenuItem("New Game", KeyEvent.VK_T);
        mainPanel = new JPanel(new GridBagLayout());
        statusBar = new JPanel(new GridLayout(1, 2));
        mineFieldPanel = new MineFieldGUI();

        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");

        menu.setMnemonic(KeyEvent.VK_A);
        menu.add(newGameMenuItem);
        menuBar.add(menu);

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
