package base;

import java.io.Serializable;

/**
 * TODO: comment
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
     * TODO: comment
     * @param name
     * @param seconds
     */
    public HighScore(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * TODO: comment
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * TODO: comment
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * TODO: comment
     * @return
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * TODO: comment
     * @param seconds
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