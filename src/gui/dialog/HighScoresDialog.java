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
 * TODO: comment
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
     * TODO: comment
     * @param owner
     * @param modal
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
     * TODO: comment
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton){
            reset();
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * TODO: comment
     * @param seconds
     * @return
     */
    private String formatTime(int seconds){
        int min = seconds / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d", min, sec);
    }

    /**
     * TODO: comment
     * @param highScore
     * @return
     */
    private String formatHighScore(HighScore highScore){
        return formatTime(highScore.getSeconds()) + " " + highScore.getName();
    }

    /**
     * TODO: comment
     * @return
     */
    private MinesweeperPreferences getPrefs() {
        return Main.getPrefs();
    }

    /**
     *
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