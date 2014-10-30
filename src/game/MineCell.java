package game;

import javax.swing.*;
import java.awt.*;

public class MineCell {

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int SIZE = 25;

    // ===========================================================
    // Fields
    // ===========================================================

    private MineCellContent content;
    private MineCellState state;
    private JPanel cellPanel;
    private JPanel cellContent;
    private JTextArea cellContentNumber;
    private JButton button;
    private CardLayout cardLayoutManager;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineCell(){
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    public MineCell(MineCellContent content){
        this(content, MineCellState.UNMARKED);
    }

    public MineCell(MineCellState state){
        this(MineCellContent.EMPTY, state);
    }

    public MineCell(MineCellContent content, MineCellState state){
        this.content = content;
        this.state = state;

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

    public MineCellContent getContent() {
        return content;
    }

    public void setContent(MineCellContent content) {
        this.content = content;
        setCellContentNumber(content);
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public MineCellState getState() {
        return state;
    }

    public void setState(MineCellState state) {
        this.state = state;
    }

    public JPanel getCellPanel() {
        return cellPanel;
    }

    public void setCellPanel(JPanel cellPanel) {
        this.cellPanel = cellPanel;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void reveal(){
        cellPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        cardLayoutManager.next(cellPanel);
    }

    private void setCellContentNumber(MineCellContent content){
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
                cellPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                button.setBackground(new Color(255, 0, 0));
                break;
        }
    }

    public boolean isEmpty(){
        return content == MineCellContent.EMPTY;
    }

    public boolean isBomb(){
        return content == MineCellContent.BOMB;
    }

    public boolean isRevealed() {
        return state == MineCellState.REVEALED;
    }

    public void toggleFlag() {
        switch (state) {
            case UNMARKED:
                cellPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                button.setBackground(new Color(255, 0, 0));
                state = MineCellState.FLAGGED;
                break;
            case FLAGGED:
                cellPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 136, 20)));
                button.setBackground(new Color(255, 136, 20));
                state = MineCellState.QUESTIONMARK;
                break;
            case QUESTIONMARK:
                cellPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
                button.setBackground(new Color(230, 230, 230));
                state = MineCellState.UNMARKED;
                break;
            case REVEALED:
                break;
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
