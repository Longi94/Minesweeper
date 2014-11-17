package gui.dialog;

import base.Main;
import base.MinesweeperPreferences;
import swing.SpringUtilities;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A dialog for setting a custom difficulty.
 */
public class CustomDifficultyDialog extends JDialog implements ActionListener{
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private JSpinner rowSpinner;
    private JSpinner columnSpinner;
    private JSpinner bombSpinner;
    private SpinnerNumberModel spm;

    private JButton okButton;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Main constructor. It creates and opens the dialog.
     * @param owner the frame from which the dialog is displayed
     * @param modal specifies whether dialog blocks user input to other top-level windows when shown
     */
    public CustomDifficultyDialog(Frame owner, boolean modal) {
        super(owner, modal);

        getContentPane().setLayout(new BorderLayout());

        JLabel rowLabel = new JLabel("Number of rows:", SwingConstants.TRAILING);
        JLabel columnLabel = new JLabel("Number of columns:", SwingConstants.TRAILING);
        JLabel bombLabel = new JLabel("Number of mines:", SwingConstants.TRAILING);

        rowSpinner = new JSpinner(new SpinnerNumberModel(getPrefs().getNumberOfRows(), 9, 24, 1));
        rowSpinner.addChangeListener(new BombLimitListener());
        columnSpinner = new JSpinner(new SpinnerNumberModel(getPrefs().getNumberOfColumns(), 9, 30, 1));
        columnSpinner.addChangeListener(new BombLimitListener());
        spm = new SpinnerNumberModel(getPrefs().getNumberOfBombs(), 10, getMaxNumberOfBombs(), 1);
        bombSpinner = new JSpinner(spm);

        JPanel mainPanel = new JPanel(new SpringLayout());

        mainPanel.add(rowLabel);
        mainPanel.add(rowSpinner);
        mainPanel.add(columnLabel);
        mainPanel.add(columnSpinner);
        mainPanel.add(bombLabel);
        mainPanel.add(bombSpinner);

        SpringUtilities.makeCompactGrid(mainPanel, 3, 2, 5, 5, 5, 5);

        okButton = new JButton("OK");
        okButton.addActionListener(this);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(okButton, BorderLayout.PAGE_END);

        setResizable(false);
        setTitle("Custom Difficulty");
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * This method is called when an action is performed on it's parent component.
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton){
            getPrefs().setDifficulty((Integer)rowSpinner.getValue(), (Integer)columnSpinner.getValue(),
                    (Integer)bombSpinner.getValue());
            setVisible(false);
        }
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Returns the preferences used by the game.
     * @return the preferences
     * @see base.MinesweeperPreferences
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    /**
     * Calculates to maximum number of mines that can be placed on a board of the given size
     * @return the maximum number mines
     */
    private int getMaxNumberOfBombs(){
        return ((Integer)rowSpinner.getValue() - 1) * ((Integer)columnSpinner.getValue() - 1);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * A change listener class.
     */
    private class BombLimitListener implements ChangeListener {

        /**
         * This method is called when the parent component is changed. Sets the maximum number of mines.
         * @param e the event
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            spm.setMaximum(getMaxNumberOfBombs());
            bombSpinner.setValue(Math.min(getMaxNumberOfBombs(), (Integer)bombSpinner.getValue()));
        }
    }
}