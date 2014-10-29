package gui;

import game.MineCell;
import game.MineField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MinesweeperGUI extends JFrame implements WindowListener{

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
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

    private GridLayout fieldLayout;

    private MineField mineField;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperGUI(MineField mineField){
        super("Minesweeper");

        this.mineField = mineField;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        createUI();

    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void createUI(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGameMenuItem = new JMenuItem("New Game", KeyEvent.VK_T);
        mainPanel = new JPanel();
        statusBar = new JPanel();
        mineFieldPanel = new MineFieldGUI();

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");

        menu.setMnemonic(KeyEvent.VK_A);
        menu.add(newGameMenuItem);
        menuBar.add(menu);

        mainPanel.add(mineFieldPanel, new GridBagConstraints(0, 0, ROWS, COLUMNS, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0
        ));
        mainPanel.add(statusBar, new GridBagConstraints(0, ROWS, 1, COLUMNS, 0, 0, GridBagConstraints.CENTER,
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
