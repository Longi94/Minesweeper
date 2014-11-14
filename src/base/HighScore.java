package base;

import java.io.Serializable;

/**
 * Structure for storing high scores.
 */
public class HighScore implements Serializable{
	// ===========================================================
    // Constants
    // ===========================================================
	
	private static final long serialVersionUID = -6555771104154926204L;
	
    // ===========================================================
    // Fields
    // ===========================================================

	private String name;
    private int seconds;
    
    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Main constructor-
     * @param name the name of the player
     * @param seconds the score of the player
     */
    public HighScore(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * @return the name fo the player
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the score of the player
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @param seconds the score to set
     */
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