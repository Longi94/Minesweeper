package gui;

import game.MineCell;

import javax.swing.*;
import java.awt.*;

public class MineFieldGUI extends JPanel{

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private JButton[][] mines;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineFieldGUI(){
        setPreferredSize(new Dimension(MinesweeperGUI.WIDTH, MinesweeperGUI.HEIGHT - MineCell.SIZE));
        setLayout(new GridLayout(MinesweeperGUI.ROWS, MinesweeperGUI.COLUMNS));
        mines = new JButton[MinesweeperGUI.ROWS][MinesweeperGUI.COLUMNS];
        buildButtons();
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

    private void buildButtons() {
        for(int i = 0; i < MinesweeperGUI.ROWS; i++){
            for(int j = 0; j < MinesweeperGUI.COLUMNS; j++){
                mines[i][j] = new JButton();
                mines[i][j].setPreferredSize(new Dimension(MineCell.SIZE, MineCell.SIZE));
                add(mines[i][j]);
            }
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
