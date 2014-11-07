package gui;

import base.Main;
import base.MinesweeperPreferences;
import game.MineField;
import gui.listener.CellButtonMouseListener;

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
        setLayout(new GridLayout(getPrefs().getNumberOfRows(), getPrefs().getNumberOfColumns()));
        mineField = new MineField(bombsLabel, timeLabel);
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

    /**
     *
     */
    private void buildButtons() {
        for(int i = 0; i < getPrefs().getNumberOfRows(); i++){
            for(int j = 0; j < getPrefs().getNumberOfColumns(); j++){
                add(mineField.getCellPanels()[i][j].getCellPanel());
                mineField.getCellPanels()[i][j].getButton().addMouseListener(new CellButtonMouseListener(mineField, i, j));
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
        getPrefs().setSaveGame(mineField.getCells());
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
