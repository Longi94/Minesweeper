package gui.dialog;

import base.Main;
import base.MinesweeperPreferences;
import game.MineField;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: comment
 */
public class MinesweeperPreferencesDialog extends JDialog implements ActionListener{

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private JPanel mainPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JCheckBox questionMark;
    private JCheckBox timerBox;
    private MineField mineField;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * TODO: comment
     * @param owner
     * @param modal
     * @param limit
     * @param mineField
     */
    public MinesweeperPreferencesDialog(JFrame owner, boolean modal, int limit, MineField mineField){
        super(owner, modal);
        this.mineField = mineField;

        Insets defaultInsets =  new Insets(2, 2, 2, 2);

        JLabel nameLabel = new JLabel("Player name:");
        nameField = new JTextField(limit);
        nameField.setDocument(new LimitDocument(limit));
        nameField.setText(getPrefs().getPlayerName());

        questionMark = new JCheckBox("Use question marks");
        questionMark.setSelected(getPrefs().isUseQuestionMark());
        questionMark.setHorizontalTextPosition(SwingConstants.LEFT);

        timerBox = new JCheckBox("Show timer");
        timerBox.setSelected(getPrefs().isShowTimer());
        timerBox.setHorizontalTextPosition(SwingConstants.LEFT);

        okButton = new JButton("OK");
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.add(nameLabel, new GridBagConstraints(
                0, 0, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        mainPanel.add(nameField, new GridBagConstraints(
                5, 0, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        mainPanel.add(questionMark, new GridBagConstraints(
                0, 1, 10, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        mainPanel.add(timerBox, new GridBagConstraints(
                0, 2, 10, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        mainPanel.add(okButton, new GridBagConstraints(
                0, 3, 5, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        mainPanel.add(cancelButton, new GridBagConstraints(
                5, 3, 10, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));

        getContentPane().add(mainPanel);

        setResizable(false);
        setTitle("Preferences");
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
        if (e.getSource() == okButton){
            savePreferences();
            setVisible(false);
            mineField.onPreferenceChanged();
        }
        else if (e.getSource() == cancelButton){
            setVisible(false);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * TODO: comment
     */
    private void savePreferences(){
        getPrefs().setPlayerName(nameField.getText());
        getPrefs().setShowTimer(timerBox.isSelected());
        getPrefs().setUseQuestionMark(questionMark.isSelected());
    }

    /**
     * TODO: comment
     * @return
     */
    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * TODO: comment
     */
    private class LimitDocument extends PlainDocument {
        private int limit;

        /**
         * TODO: comment
         * @param limit
         */
        private LimitDocument(int limit) {
            this.limit = limit;
        }

        /**
         * TODO: comment
         * @param offset
         * @param str
         * @param attr
         * @throws BadLocationException
         */
        @Override
        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}