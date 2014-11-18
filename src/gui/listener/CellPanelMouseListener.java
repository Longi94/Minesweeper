package gui.listener;

import game.MineField;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listener class used with the panels in the cells.
 */
public class CellPanelMouseListener implements MouseListener {

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
     * Main constructor.
     * @param mineField MineField object of the current game
     * @param row the row the cell is in
     * @param column the column the cell is in
     * @see game.MineField
     */
    public CellPanelMouseListener(MineField mineField, int row, int column) {
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
     * Handles mouse button clicks.
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Handles mouse button presses.
     * @param e the mouse event
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
     * Handles mouse button releases.
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mouse1Down = false;
                if (!mouse3Down) {
                    if (isMouseOverButton(e) && twoButtonPushed) {
                        twoButtonPushed = false;
                        mineField.onTwoButtonCellClick(row, column);
                    }
                }
                break;
            case MouseEvent.BUTTON3:
                mouse3Down = false;
                if (!mouse1Down) {
                    if (isMouseOverButton(e) && twoButtonPushed) {
                        twoButtonPushed = false;
                        mineField.onTwoButtonCellClick(row, column);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * Handles the 'mouse entering the window' event.
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Handles the 'mouse exiting the window' event.
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Determines whether the mouse is currently over the given cell.
     * @param e the mouse event
     * @return whether the mouse is currently over the given cell
     */
    private boolean isMouseOverButton(MouseEvent e){
        return -((JComponent)e.getSource()).getX() < e.getX() &&
                -((JComponent)e.getSource()).getY() < e.getY() &&
                ((JComponent)e.getSource()).getX() + ((JComponent)e.getSource()).getWidth()>= e.getX() &&
                ((JComponent)e.getSource()).getY() + ((JComponent)e.getSource()).getHeight()>= e.getY();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}