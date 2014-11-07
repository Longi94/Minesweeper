package gui.panel;

import game.MineCellContent;
import game.MineCellState;
import game.Player;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class MineCellPanel {

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int SIZE = 25;

    // ===========================================================
    // Fields
    // ===========================================================

    private JPanel cellPanel;
    private JPanel cellContent;
    private JTextArea cellContentNumber;
    private JButton button;
    private CardLayout cardLayoutManager;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     *
     */
    public MineCellPanel() {
        Font contentFont = new Font("Verdana", Font.BOLD, 12);

        button = new JButton();
        button.setPreferredSize(new Dimension(SIZE, SIZE));
        button.setFocusable(false);
        button.setBackground(new Color(250, 250, 250));

        cellContentNumber = new JTextArea("");
        cellContentNumber.setFont(contentFont);
        cellContentNumber.setFocusable(false);
        cellContentNumber.setEditable(false);
        cellContentNumber.setBackground(new Color(250, 250, 250));

        cellContent = new JPanel(new BorderLayout());
        cellContent.setPreferredSize(new Dimension(SIZE, SIZE));
        cellContent.add(cellContentNumber, BorderLayout.CENTER);

        cardLayoutManager = new CardLayout();

        cellPanel = new JPanel(cardLayoutManager);
        cellPanel.setPreferredSize(new Dimension(SIZE, SIZE));
        cellPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        cellPanel.add(button);
        cellPanel.add(cellContentNumber);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     *
     * @return
     */
    public JButton getButton() {
        return button;
    }

    /**
     *
     * @param button
     */
    public void setButton(JButton button) {
        this.button = button;
    }

    /**
     *
     * @return
     */
    public JPanel getCellPanel() {
        return cellPanel;
    }

    /**
     *
     * @param cellPanel
     */
    public void setCellPanel(JPanel cellPanel) {
        this.cellPanel = cellPanel;
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     *
     */
    public void reveal() {
        if (Player.isAlive()) {
            cellPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        }
        cardLayoutManager.next(cellPanel);
    }

    /**
     *
     * @param content
     */
    public void setContent(MineCellContent content) {
        switch (content) {
            case EMPTY:
                cellContentNumber.setText("");
                break;
            case ONE:
                cellContentNumber.setText("1");
                cellContentNumber.setForeground(new Color(101, 202, 255));
                break;
            case TWO:
                cellContentNumber.setText("2");
                cellContentNumber.setForeground(new Color(0, 210, 100));
                break;
            case THREE:
                cellContentNumber.setText("3");
                cellContentNumber.setForeground(new Color(200, 18, 23));
                break;
            case FOUR:
                cellContentNumber.setText("4");
                cellContentNumber.setForeground(new Color(0, 0, 150));
                break;
            case FIVE:
                cellContentNumber.setText("5");
                cellContentNumber.setForeground(new Color(219, 219, 43));
                break;
            case SIX:
                cellContentNumber.setText("6");
                cellContentNumber.setForeground(new Color(50, 200, 200));
                break;
            case SEVEN:
                cellContentNumber.setText("7");
                cellContentNumber.setForeground(new Color(223, 56, 223));
                break;
            case EIGHT:
                cellContentNumber.setText("8");
                cellContentNumber.setForeground(new Color(255, 136, 20));
                break;
            case BOMB:
                cellContentNumber.setText("B");
                cellContentNumber.setForeground(new Color(0, 0, 0));
                break;
        }
    }

    /**
     *
     * @param state
     * @return
     */
    public MineCellState toggleFlag(MineCellState state) {
        if (Player.isAlive())
            switch (state) {
                case UNMARKED:
                    cellPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                    button.setBackground(new Color(255, 0, 0));
                    return MineCellState.FLAGGED;
                case FLAGGED:
                    cellPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 225, 18)));
                    button.setBackground(new Color(0, 225, 18));
                    return MineCellState.QUESTIONMARK;
                case QUESTIONMARK:
                    cellPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
                    button.setBackground(new Color(230, 230, 230));
                    return MineCellState.UNMARKED;
                case REVEALED:
                    return MineCellState.REVEALED;
            }
        return MineCellState.UNMARKED;
    }

    public void flagCell() {
        cellPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        button.setBackground(new Color(255, 0, 0));
    }

    public void questionMark() {
        cellPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 225, 18)));
        button.setBackground(new Color(0, 225, 18));
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
