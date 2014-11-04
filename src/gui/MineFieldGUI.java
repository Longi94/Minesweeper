package gui;

import base.Main;
import base.MinesweeperPreferences;
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
        setLayout(new GridLayout(getPrefs().getNumberOfRows(), getPrefs().getNumberOfColumns()));
        mineField = new MineField(getPrefs().getNumberOfRows(), getPrefs().getNumberOfColumns(), getPrefs().getNumberOfBombs());
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
        for(int i = 0; i < getPrefs().getNumberOfRows(); i++){
            for(int j = 0; j < getPrefs().getNumberOfColumns(); j++){
                add(mineField.getCells()[i][j].getCellPanel());
                mineField.getCells()[i][j].getButton().addMouseListener(new CellButtonMouseListener(mineField, i, j));
            }
        }
    }

    public void resetBoard() {
        mineField.reset();
        buildButtons();
        revalidate();
        repaint();
    }

    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
