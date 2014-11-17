package game;

/**
 * The class used to represent the player and its properties.
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
     * @return whether the player is alive
     */
    public static boolean isAlive() {
        return isAlive;
    }

    /**
     * @param isAlive boolean to set whether the player is alive
     */
    public static void setIsAlive(boolean isAlive) {
        Player.isAlive = isAlive;
    }

    /**
     * @return whether the player has started the game
     */
    public static boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * @param gameStarted boolean to set whether the player has started to game
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