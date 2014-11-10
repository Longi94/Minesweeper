package gui;

import base.Main;
import base.MinesweeperPreferences;
import game.MineField;
import gui.listener.CellButtonMouseListener;
import gui.listener.CellPanelMouseListener;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
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

    /**
     *
     * @param bombsLabel
     * @param timeLabel
     */
    public MineFieldGUI(JLabel bombsLabel, JLabel timeLabel) {
        mineField = new MineField(bombsLabel, timeLabel);
        if (getPrefs().getSavedGame() == null) {
            setLayout(new GridLayout(getPrefs().getNumberOfRows(), getPrefs().getNumberOfColumns()));
        }
        else {
            setLayout(new GridLayout(getPrefs().getSavedGame().length, getPrefs().getSavedGame()[0].length));
        }

        buildButtons();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================


    public MineField getMineField() {
        return mineField;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     *
     */
    private void buildButtons() {
        for(int i = 0; i < mineField.getRows(); i++){
            for(int j = 0; j < mineField.getColumns(); j++){
                add(mineField.getCellPanels()[i][j].getCellPanel());
                mineField.getCellPanels()[i][j].addListeners(new CellButtonMouseListener(mineField, i, j),
                        new CellPanelMouseListener(mineField, i, j));
            }
        }
    }

    /**
     *
     * @return
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    /**
     *
     */
    public void cancelTimer() {
        mineField.cancelTimer();
    }

    /**
     *
     */
    public void saveGame() {
        getPrefs().setSavedGame(mineField.getCells());
        getPrefs().setSavedTime(mineField.getTime());
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
