package game;

/**
 *
 */
public class Player {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private static boolean isAlive = true;
    private static boolean gameStarted = false;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     *
     * @return
     */
    public static boolean isAlive() {
        return isAlive;
    }

    /**
     *
     * @param isAlive
     */
    public static void setIsAlive(boolean isAlive) {
        Player.isAlive = isAlive;
    }

    /**
     *
     * @return
     */
    public static boolean isGameStarted() {
        return gameStarted;
    }

    /**
     *
     * @param gameStarted
     */
    public static void setGameStarted(boolean gameStarted) {
        Player.gameStarted = gameStarted;
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