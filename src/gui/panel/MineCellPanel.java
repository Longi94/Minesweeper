package gui.panel;

import game.MineCellContent;
import game.MineCellState;
import game.Player;
import gui.listener.CellButtonMouseListener;
import gui.listener.CellPanelMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class used for displaying mine cells.
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

    private BufferedImage mineIcon;
    private JLabel mineIconLabel;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Main constructor. Creates the necessary resources.
     */
    public MineCellPanel() {
        Font contentFont = new Font("Verdana", Font.BOLD, 12);

        try {
            mineIcon = ImageIO.read(new File("assets/mine_icon_small.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mineIconLabel = new JLabel(new ImageIcon(mineIcon));
        mineIconLabel.setBackground(new Color(250, 250, 250));

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
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * @return the panel of the mine cell
     */
    public JPanel getCellPanel() {
        return cellPanel;
    }

    /**
     * @return the panel representing the content of the cell
     */
    public JPanel getCellContent() {
        return cellContent;
    }

    /**
     * @return the label containing the mine icon
     */
    public JLabel getMineIconLabel() {
        return mineIconLabel;
    }

    /**
     * @return the button which uncovers the cell when pressed
     */
    public JButton getButton() {
        return button;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Reveals the mine cell.
     */
    public void reveal(MineCellContent content) {
        if (content == MineCellContent.BOMB)
            cellPanel.add(mineIconLabel);
        else
            cellPanel.add(cellContent);
        cardLayoutManager.next(cellPanel);
    }

    /**
     * Sets the content of the panel.
     * @param content the content to be set
     * @see game.MineCellContent
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
            default:
                break;
        }
    }

    /**
     * Toggles between the states of the covered mine cell.
     * @param state the previous state
     * @param usingQuestionMarks specifies whether the question mark option is set
     * @return the next state
     * @see game.MineCellState
     */
    public MineCellState toggleFlag(MineCellState state, boolean usingQuestionMarks) {
        if (Player.isAlive())
            switch (state) {
                case UNMARKED:
                    button.setIcon(new ImageIcon("assets/flag_icon.png"));
                    return MineCellState.FLAGGED;
                case FLAGGED:
                    if (usingQuestionMarks) {
                        button.setIcon(new ImageIcon("assets/question_icon.png"));
                        return MineCellState.QUESTIONMARK;
                    }
                    else {
                        button.setIcon(null);
                        return MineCellState.UNMARKED;
                    }
                case QUESTIONMARK:
                    button.setIcon(null);
                    return MineCellState.UNMARKED;
                case REVEALED:
                    return MineCellState.REVEALED;
            }
        return MineCellState.UNMARKED;
    }

    /**
     * Flags the cell.
     */
    public void flagCell() {
        button.setIcon(new ImageIcon("assets/flag_icon.png"));
    }

    /**
     * Marks the cell with a question mark.
     */
    public void questionMark() {
        button.setIcon(new ImageIcon("assets/question_icon.png"));
    }

    /**
     * Adds listeners to the appropriate components.
     * @param cbml listener for the button
     * @param cpml listener for the panel
     */
    public void addListeners(CellButtonMouseListener cbml, CellPanelMouseListener cpml){
        button.addMouseListener(cbml);
        cellContent.addMouseListener(cpml);
        cellContentNumber.addMouseListener(cpml);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
