package gui;

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


    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperPreferencesGUI(JFrame frame, boolean modal, MinesweeperPreferences prefs, int limit){
        super(frame, modal);

        Insets defaultInsets =  new Insets(2, 2, 2, 2);

        JLabel nameLabel = new JLabel("Player name:");
        JTextField nameField = new JTextField(limit);
        nameField.setDocument(new LimitDocument(limit));
        nameField.setText(prefs.getPlayerName());

        JCheckBox questionMark = new JCheckBox("Use question marks");
        questionMark.setSelected(prefs.isUseQuestionMark());
        questionMark.setHorizontalTextPosition(SwingConstants.LEFT);

        JCheckBox timerBox = new JCheckBox("Show timer");
        timerBox.setSelected(prefs.isShowTimer());
        timerBox.setHorizontalTextPosition(SwingConstants.LEFT);

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

        getContentPane().add(prefsPanel);

        setResizable(false);
        setTitle("Preferences");
        pack();
        setLocationRelativeTo(frame);
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

    }

    // ===========================================================
    // Methods
    // ===========================================================

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