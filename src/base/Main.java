package base;


import gui.MinesweeperGUI;

import javax.swing.*;
import java.io.*;

/**
 * The main class containing the main method.
 */
public class Main {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private static MinesweeperPreferences prefs;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * @return the preferences used by the program.
     * @see base.MinesweeperPreferences
     */
    public static MinesweeperPreferences getPrefs() {
        return prefs;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * The entry point of the program.
     * @param args command line arguments, unused
     */
    public static void main(String[] args) {

        //load saved data from file if exist, if not we create new data
        File data = new File("data");
        if (data.exists() && !data.isDirectory()){
            try {
                FileInputStream fis = new FileInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(fis);
                prefs = (MinesweeperPreferences)ois.readObject();

                ois.close();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            prefs = new MinesweeperPreferences();
        }

        // change the look and feel to match the systems theme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MinesweeperGUI();
            }
        });


    }

    /**
     * Writes the preferences into a file.
     */
    public static void savePreferences(){
        try {
            FileOutputStream fos = new FileOutputStream("data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(prefs);

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
