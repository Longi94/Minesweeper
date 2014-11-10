package gui.listener;

import game.MineField;
import gui.panel.MineCellPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment
 */
public class CellButtonMouseListener implements MouseListener {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private MineField mineField;
    private int row;
    private int column;

    private boolean twoButtonPushed = false;
    private boolean mouse1Down = false;
    private boolean mouse3Down = false;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * TODO: comment
     * @param mineField
     * @param row
     * @param column
     */
    public CellButtonMouseListener(MineField mineField, int row, int column) {
        this.mineField = mineField;
        this.row = row;
        this.column = column;
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
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * TODO: comment
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mouse1Down = true;
                if (mouse3Down){
                    twoButtonPushed = true;
                }
                break;
            case MouseEvent.BUTTON3:
                mouse3Down = true;
                if (mouse1Down){
                    twoButtonPushed = true;
                }
                break;
            default:
                break;
        }
    }

    /**
     * TODO: comment
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mouse1Down = false;
                if (!mouse3Down) {
                    if (isMouseOverButton(e) && !twoButtonPushed) {
                        mineField.onCellClick(row, column);
                    } else {
                        twoButtonPushed = false;
                    }
                }
                break;
            case MouseEvent.BUTTON3:
                mouse3Down = false;
                if (!mouse1Down) {
                    if (isMouseOverButton(e) && !twoButtonPushed) {
                        mineField.toggleFlag(row, column);
                    } else {
                        twoButtonPushed = false;
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * TODO: comment
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * TODO: comment
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * TODO: comment
     * @param e
     * @return
     */
    private boolean isMouseOverButton(MouseEvent e){
        return ((JComponent)e.getSource()).getX() <= e.getX() &&
                ((JComponent)e.getSource()).getY() <= e.getY() &&
                ((JComponent)e.getSource()).getX() + MineCellPanel.SIZE >= e.getX() &&
                ((JComponent)e.getSource()).getY() + MineCellPanel.SIZE >= e.getY();
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}