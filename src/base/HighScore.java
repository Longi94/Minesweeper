package base;

import java.io.Serializable;

public class HighScore implements Serializable{
    // ===========================================================
    // Constants
    // ===========================================================

    private String name;
    private int seconds;

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public HighScore(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}