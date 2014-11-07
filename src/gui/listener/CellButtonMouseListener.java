package gui.listener;

import game.MineField;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
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

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     *
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
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        switch (e.getButton()){
            case MouseEvent.BUTTON1:
                mineField.onCellClick(row, column);
                break;
            case MouseEvent.BUTTON3:
                mineField.toggleFlag(row, column);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}