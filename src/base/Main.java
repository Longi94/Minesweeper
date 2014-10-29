package base;


import game.MineField;
import gui.MinesweeperGUI;

public class Main {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private static MinesweeperGUI GUI;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public static void main(String[] args) {
        final MineField mineField = new MineField(10, 10);
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI = new MinesweeperGUI(mineField);
                GUI.showUI();
            }
        });
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
