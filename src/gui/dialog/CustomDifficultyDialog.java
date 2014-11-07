package gui.dialog;

import base.Main;
import base.MinesweeperPreferences;
import swing.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
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

    private JButton okButton;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     *
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
        columnSpinner = new JSpinner(new SpinnerNumberModel(getPrefs().getNumberOfColumns(), 9, 30, 1));
        bombSpinner = new JSpinner(new SpinnerNumberModel(getPrefs().getNumberOfBombs(), 10, 668, 1));

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
     *
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
     *
     * @return
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}