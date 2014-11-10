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
 * TODO: comment
 */
public class CustomDifficultyDialog extends JDialog implements ActionListener{
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private JPanel mainPanel;

    private JSpinner rowSpinner;
    private JSpinner columnSpinner;
    private JSpinner bombSpinner;
    private SpinnerNumberModel spm;

    private JButton okButton;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * TODO: comment
     * @param owner
     * @param modal
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

        mainPanel = new JPanel(new SpringLayout());

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
     * TODO: comment
     * @param e
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
     * TODO: comment
     * @return
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    /**
     * TODO: comment
     * @return
     */
    private int getMaxNumberOfBombs(){
        return ((Integer)rowSpinner.getValue() - 1) * ((Integer)columnSpinner.getValue() - 1);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * TODO: comment
     */
    private class BombLimitListener implements ChangeListener {

        /**
         * TODO: comment
         * @param e
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            spm.setMaximum(getMaxNumberOfBombs());
            bombSpinner.setValue(Math.min(getMaxNumberOfBombs(), (Integer)bombSpinner.getValue()));
        }
    }
}