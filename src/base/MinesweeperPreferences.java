package base;

import game.MineCell;

import java.io.Serializable;

/**
 * Stores the preferences, high scores and the saved game.
 */
public class MinesweeperPreferences implements Serializable{

    // ===========================================================
    // Constants
    // ===========================================================

    private static final long serialVersionUID = 1L;

    // ===========================================================
    // Fields
    // ===========================================================

    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfBombs;
    private int bombsLeft;

    private Difficulty difficulty;

    private boolean useQuestionMark = true;
    private boolean showTimer = true;
    private String playerName;

    private HighScore easyHighScore;
    private HighScore mediumHighScore;
    private HighScore hardHighScore;

    private MineCell[][] savedGame;
    private int savedTime;


    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Main constructor.
     */
    public MinesweeperPreferences() {
        //set the default parameters
        numberOfBombs = 99;
        numberOfColumns = 30;
        numberOfRows = 16;
        bombsLeft = 99;

        difficulty = Difficulty.HARD;

        useQuestionMark = true;
        showTimer = true;

        playerName = "Player";

        easyHighScore = new HighScore(playerName, 60*99 + 59);
        mediumHighScore = new HighScore(playerName, 60*99 + 59);
        hardHighScore = new HighScore(playerName, 60*99 + 59);

        savedGame = null;
        savedTime = 0;
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * @return the number of rows
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * @return the number of columns
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * @return the number of mines to find
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    /**
     * @return the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the name to set the player's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @return the saved game
     * @see game.MineCell
     */
    public MineCell[][] getSavedGame() {
        return savedGame;
    }

    /**
     * @param savedGame the game to save
     * @see game.MineCell
     */
    public void setSavedGame(MineCell[][] savedGame) {
        this.savedGame = savedGame;
    }

    /**
     * @return whether the player wants to use question marks
     */
    public boolean isUseQuestionMark() {
        return useQuestionMark;
    }

    /**
     * @param useQuestionMark boolean to set the 'use question mark' option
     */
    public void setUseQuestionMark(boolean useQuestionMark) {
        this.useQuestionMark = useQuestionMark;
    }

    /**
     * @return whether the player wants to see the timer
     */
    public boolean isShowTimer() {
        return showTimer;
    }

    /**
     * @param showTimer boolean to set the 'show timer' option
     */
    public void setShowTimer(boolean showTimer) {
        this.showTimer = showTimer;
    }

    /**
     * @return the number of mines to be uncovered
     */
    public int getBombsLeft() {
        return bombsLeft;
    }

    /**
     * @param bombsLeft number to set the remaining mines
     */
    public void setBombsLeft(int bombsLeft) {
        this.bombsLeft = bombsLeft;
    }

    /**
     * @return the high score in easy difficulty
     * @see base.HighScore
     */
    public HighScore getEasyHighScore() {
        return easyHighScore;
    }

    /**
     * @param easyHighScore high score in easy difficulty to set
     * @see base.HighScore
     */
    public void setEasyHighScore(HighScore easyHighScore) {
        this.easyHighScore = easyHighScore;
    }

    /**
     * @return the high score in medium difficulty
     * @see base.HighScore
     */
    public HighScore getMediumHighScore() {
        return mediumHighScore;
    }

    /**
     * @param mediumHighScore high score in medium difficulty to set
     * @see base.HighScore
     */
    public void setMediumHighScore(HighScore mediumHighScore) {
        this.mediumHighScore = mediumHighScore;
    }

    /**
     * @return the high score in hard difficulty
     * @see base.HighScore
     */
    public HighScore getHardHighScore() {
        return hardHighScore;
    }

    /**
     * @param hardHighScore high score in hard difficulty to set
     * @see base.HighScore
     */
    public void setHardHighScore(HighScore hardHighScore) {
        this.hardHighScore = hardHighScore;
    }

    /**
     * @return the play tim of the saved game
     */
    public int getSavedTime() {
        return savedTime;
    }

    /**
     * @param savedTime play time to save
     */
    public void setSavedTime(int savedTime) {
        this.savedTime = savedTime;
    }


    /**
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Sets the difficulty of the game.
     * @param rows the number of rows
     * @param columns the number of columns
     * @param bombs the number of mines
     * @see base.MinesweeperPreferences.Difficulty
     */
    public void setDifficulty(int rows, int columns, int bombs){
        numberOfBombs = bombs;
        numberOfRows = rows;
        numberOfColumns = columns;
        difficulty = Difficulty.CUSTOM;
    }

    /**
     * @param difficulty the difficulty to set
     * @see base.MinesweeperPreferences.Difficulty
     */
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY:
                numberOfBombs = 10;
                numberOfRows = 9;
                numberOfColumns = 9;
                break;
            case MEDIUM:
                numberOfBombs = 40;
                numberOfRows = 16;
                numberOfColumns = 16;
                break;
            case HARD:
                numberOfBombs = 99;
                numberOfRows = 16;
                numberOfColumns = 30;
                break;
            default:
                break;
        }
    }

    /**
     * Increase the number of mines to be uncovered by one.
     * @return the number of mines to be uncovered after incrementing
     */
    public int incrementBombs(){
        return ++bombsLeft;
    }

    /**
     * Decrease the number of mines to be uncovered by one.
     * @return the number of mines to be uncovered after decrementing
     */
    public int decrementBombs(){
        return --bombsLeft;
    }

    /**
     * Saved the current score as the high score if better.
     * @param time the time of the current score
     * @param difficulty the difficulty of the game
     * @see base.MinesweeperPreferences.Difficulty
     */
    public void saveHighScore(int time, Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                if (easyHighScore.getSeconds() > time) {
                    easyHighScore.setName(playerName);
                    easyHighScore.setSeconds(time);
                }
                break;
            case MEDIUM:
                if (mediumHighScore.getSeconds() > time) {
                    mediumHighScore.setName(playerName);
                    mediumHighScore.setSeconds(time);
                }
                break;
            case HARD:
                if (hardHighScore.getSeconds() > time) {
                    hardHighScore.setName(playerName);
                    hardHighScore.setSeconds(time);
                }
                break;
            case CUSTOM:
                break;
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * Difficulty enumeration.
     */
    public enum Difficulty implements Serializable{
        EASY, MEDIUM, HARD, CUSTOM
    }
}