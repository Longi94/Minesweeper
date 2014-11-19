package gui;

import base.Main;
import base.MinesweeperPreferences;
import game.MineField;

import javax.swing.*;
import java.awt.*;

/**
 * Class used for drawing the whole board.
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
     * Main constructor.
     * @param bombsLabel reference to a JLabel for showing the number of mines left
     * @param timeLabel reference to a JLabel for showing current play time
     * @param faceButton
     */
    public MineFieldGUI(JLabel bombsLabel, JLabel timeLabel, JButton faceButton) {
        mineField = new MineField(bombsLabel, timeLabel, faceButton);
        if (getPrefs().getSavedGame() == null)
            setLayout(new GridLayout(getPrefs().getNumberOfRows(), getPrefs().getNumberOfColumns()));
        else
            setLayout(new GridLayout(getPrefs().getSavedGame().length, getPrefs().getSavedGame()[0].length));

        buildButtons();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * @return MineField object of the current game
     * @see game.MineField
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
     * Builds and draws the buttons on the board.
     */
    private void buildButtons() {
        for(int i = 0; i < mineField.getRows(); i++)
            for(int j = 0; j < mineField.getColumns(); j++)
                add(mineField.getCellPanels()[i][j].getCellPanel());
    }

    /**
     * Returns the preferences used by the game.
     * @return the preferences
     * @see base.MinesweeperPreferences
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    /**
     * Stops the timer.
     */
    public void cancelTimer() {
        mineField.cancelTimer();
    }

    /**
     * Saves the game.
     */
    public void saveGame() {
        getPrefs().setSavedGame(mineField.getCells());
        getPrefs().setSavedTime(mineField.getTime());
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
