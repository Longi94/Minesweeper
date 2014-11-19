package gui.dialog;

import base.HighScore;
import base.Main;
import base.MinesweeperPreferences;
import swing.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog for showing the high scores
 */
public class HighScoresDialog extends JDialog implements ActionListener {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private JButton resetButton;
    private final JLabel easyData;
    private final JLabel mediumData;
    private final JLabel hardData;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Main constructor. It creates and opens the dialog.
     * @param owner the frame from which the dialog is displayed
     * @param modal specifies whether dialog blocks user input to other top-level windows when shown
     */
    public HighScoresDialog(Frame owner, boolean modal) {
        super(owner, modal);
        JLabel easyLabel = new JLabel("Easy:", SwingConstants.TRAILING);
        JLabel mediumLabel = new JLabel("Medium:", SwingConstants.TRAILING);
        JLabel hardLabel = new JLabel("Hard", SwingConstants.TRAILING);
        easyData = new JLabel(formatHighScore(getPrefs().getEasyHighScore()));
        mediumData = new JLabel(formatHighScore(getPrefs().getMediumHighScore()));
        hardData = new JLabel(formatHighScore(getPrefs().getHardHighScore()));

        JPanel mainPanel = new JPanel(new SpringLayout());
        mainPanel.add(easyLabel);
        mainPanel.add(easyData);
        mainPanel.add(mediumLabel);
        mainPanel.add(mediumData);
        mainPanel.add(hardLabel);
        mainPanel.add(hardData);

        SpringUtilities.makeCompactGrid(mainPanel, 3, 2, 5, 5, 5, 5);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(resetButton, BorderLayout.PAGE_END);

        setResizable(false);
        setTitle("Records");
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }



    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    /**
     * This method is called when an action is performed on it's parent component.
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton)
            reset();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Formats the given time in seconds to a friendly MM:SS format.
     * @param seconds the number of seconds
     * @return the formatted String
     */
    private String formatTime(int seconds){
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    /**
     * Formats a Highscore object to a friendly 'Time Name' format.
     * @param highScore the high score
     * @return the formatted high score
     * @see base.HighScore
     */
    private String formatHighScore(HighScore highScore){
        return formatTime(highScore.getSeconds()) + " " + highScore.getName();
    }

    /**
     * Returns the preferences used by the game.
     * @return the preferences
     * @see base.MinesweeperPreferences
     */
    private MinesweeperPreferences getPrefs() {
        return Main.getPrefs();
    }

    /**
     * Deletes every high scores.
     */
    private void reset(){
        getPrefs().setEasyHighScore(new HighScore("Player", 99 * 60 + 59));
        getPrefs().setMediumHighScore(new HighScore("Player", 99 * 60 + 59));
        getPrefs().setHardHighScore(new HighScore("Player", 99 * 60 + 59));
        easyData.setText(formatHighScore(getPrefs().getEasyHighScore()));
        mediumData.setText(formatHighScore(getPrefs().getMediumHighScore()));
        hardData.setText(formatHighScore(getPrefs().getHardHighScore()));
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}