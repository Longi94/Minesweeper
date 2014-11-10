package game;

/**
 * TODO: comment
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
     * TODO: comment
     * @return
     */
    public static boolean isAlive() {
        return isAlive;
    }

    /**
     * TODO: comment
     * @param isAlive
     */
    public static void setIsAlive(boolean isAlive) {
        Player.isAlive = isAlive;
    }

    /**
     * TODO: comment
     * @return
     */
    public static boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * TODO: comment
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