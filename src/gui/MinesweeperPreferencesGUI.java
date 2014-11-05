package gui;

import base.Main;
import base.MinesweeperPreferences;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperPreferencesGUI extends JDialog implements ActionListener{

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private JPanel prefsPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField nameField;
    private JCheckBox questionMark;
    private JCheckBox timerBox;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperPreferencesGUI(JFrame owner, boolean modal, int limit){
        super(owner, modal);

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

        prefsPanel = new JPanel(new GridBagLayout());
        prefsPanel.add(nameLabel, new GridBagConstraints(
                0, 0, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        prefsPanel.add(nameField, new GridBagConstraints(
                5, 0, 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        prefsPanel.add(questionMark, new GridBagConstraints(
                0, 1, 10, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        prefsPanel.add(timerBox, new GridBagConstraints(
                0, 2, 10, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        prefsPanel.add(okButton, new GridBagConstraints(
                0, 3, 5, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));
        prefsPanel.add(cancelButton, new GridBagConstraints(
                5, 3, 10, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, defaultInsets, 0, 0
        ));

        getContentPane().add(prefsPanel);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton){
            savePreferences();
            setVisible(false);
        }
        else if (e.getSource() == cancelButton){
            setVisible(false);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void savePreferences(){
        getPrefs().setPlayerName(nameField.getText());
        getPrefs().setShowTimer(timerBox.isSelected());
        getPrefs().setUseQuestionMark(questionMark.isSelected());
    }

    private MinesweeperPreferences getPrefs(){
        return Main.getPrefs();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private class LimitDocument extends PlainDocument {
        private int limit;

        private LimitDocument(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}