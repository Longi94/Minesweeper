package base;

import java.io.Serializable;

/**
 *
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
     *
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
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     *
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