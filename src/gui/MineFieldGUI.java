package gui;

import game.MineCell;
import game.MineField;

import javax.swing.*;
import java.awt.*;

public class MineFieldGUI extends JPanel{

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private MineField mineField;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineFieldGUI(){
        setPreferredSize(new Dimension(MinesweeperGUI.WIDTH, MinesweeperGUI.HEIGHT - MineCell.SIZE));
        setLayout(new GridLayout(MinesweeperGUI.ROWS, MinesweeperGUI.COLUMNS));
        mineField = new MineField(MinesweeperGUI.ROWS, MinesweeperGUI.COLUMNS);
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
                add(mineField.getCells()[i][j].getCellPanel());
                mineField.getCells()[i][j].getButton().addMouseListener(new CellButtonMouseListener(mineField, i, j));
            }
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}