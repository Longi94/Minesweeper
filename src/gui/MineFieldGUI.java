package gui;

import base.Main;
import base.MinesweeperPreferences;
import game.MineField;
import gui.listener.CellButtonMouseListener;
import gui.listener.CellPanelMouseListener;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: comment
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
     * TODO: comment
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

    /**
     * TODO: comment
     * @return
     */
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
     * TODO: comment
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
     * TODO: comment
     * @return
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    /**
     * TODO: comment
     */
    public void cancelTimer() {
        mineField.cancelTimer();
    }

    /**
     * TODO: comment
     */
    public void saveGame() {
        getPrefs().setSavedGame(mineField.getCells());
        getPrefs().setSavedTime(mineField.getTime());
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
