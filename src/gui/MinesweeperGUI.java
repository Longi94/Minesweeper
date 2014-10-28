package gui;

import swing.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MinesweeperGUI {

    private MainFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGameMenuItem;

    private JPanel mainPanel;
    private JPanel mineFieldPanel;

    private GridLayout fieldLayout;

    public MinesweeperGUI(){
        frame = new MainFrame();
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGameMenuItem = new JMenuItem("New Game", KeyEvent.VK_T);
        mainPanel = new JPanel();
        mineFieldPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(2, 1));
        fieldLayout = new GridLayout(5, 5);
        mineFieldPanel.setLayout(fieldLayout);

        newGameMenuItem.getAccessibleContext().setAccessibleDescription("Start a new game");

        menu.setMnemonic(KeyEvent.VK_A);
        menu.add(newGameMenuItem);
        menuBar.add(menu);

        fillThisShit();
        mainPanel.add(mineFieldPanel);

        frame.setJMenuBar(menuBar);
        frame.add(mainPanel);
    }

    public void showUI(){
        frame.pack();
        frame.setVisible(true);
    }

    public void fillThisShit(){
        for (int i = 0; i < fieldLayout.getColumns() * fieldLayout.getRows(); i++){
            mineFieldPanel.add(new JLabel("X"));
        }
    }
}
