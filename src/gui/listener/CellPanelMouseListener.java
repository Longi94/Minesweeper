package gui.listener;

import game.MineField;
import gui.panel.MineCellPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ThanhLong on 2014.11.10..
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
     *
     * @param mineField
     * @param row
     * @param column
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
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     *
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
     *
     * @param e
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

    /**
     *
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