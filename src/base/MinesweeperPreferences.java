package base;

import game.MineCell;

import java.io.Serializable;

/**
 * TODO: comment
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
     * TODO: comment
     */
    public MinesweeperPreferences() {
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
     * TODO: comment
     * @return
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * TODO: comment
     * @param numberOfRows
     */
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    /**
     * TODO: comment
     * @return
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * TODO: comment
     * @param numberOfColumns
     */
    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * TODO: comment
     * @return
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    /**
     * TODO: comment
     * @param numberOfBombs
     */
    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    /**
     * TODO: comment
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * TODO: comment
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * TODO: comment
     * @return
     */
    public MineCell[][] getSavedGame() {
        return savedGame;
    }

    /**
     * TODO: comment
     * @param savedGame
     */
    public void setSavedGame(MineCell[][] savedGame) {
        this.savedGame = savedGame;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isUseQuestionMark() {
        return useQuestionMark;
    }

    /**
     * TODO: comment
     * @param useQuestionMark
     */
    public void setUseQuestionMark(boolean useQuestionMark) {
        this.useQuestionMark = useQuestionMark;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isShowTimer() {
        return showTimer;
    }

    /**
     * TODO: comment
     * @param showTimer
     */
    public void setShowTimer(boolean showTimer) {
        this.showTimer = showTimer;
    }

    /**
     * TODO: comment
     * @return
     */
    public int getBombsLeft() {
        return bombsLeft;
    }

    /**
     * TODO: comment
     * @param bombsLeft
     */
    public void setBombsLeft(int bombsLeft) {
        this.bombsLeft = bombsLeft;
    }

    /**
     * TODO: comment
     * @return
     */
    public HighScore getEasyHighScore() {
        return easyHighScore;
    }

    /**
     * TODO: comment
     * @param easyHighScore
     */
    public void setEasyHighScore(HighScore easyHighScore) {
        this.easyHighScore = easyHighScore;
    }

    /**
     * TODO: comment
     * @return
     */
    public HighScore getMediumHighScore() {
        return mediumHighScore;
    }

    /**
     * TODO: comment
     * @param mediumHighScore
     */
    public void setMediumHighScore(HighScore mediumHighScore) {
        this.mediumHighScore = mediumHighScore;
    }

    /**
     * TODO: comment
     * @return
     */
    public HighScore getHardHighScore() {
        return hardHighScore;
    }

    /**
     * TODO: comment
     * @param hardHighScore
     */
    public void setHardHighScore(HighScore hardHighScore) {
        this.hardHighScore = hardHighScore;
    }

    /**
     * TODO: comment
     * @return
     */
    public int getSavedTime() {
        return savedTime;
    }

    /**
     * TODO: comment
     * @param savedTime
     */
    public void setSavedTime(int savedTime) {
        this.savedTime = savedTime;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * TODO: comment
     * @param rows
     * @param columns
     * @param bombs
     */
    public void setDifficulty(int rows, int columns, int bombs){
        numberOfBombs = bombs;
        numberOfRows = rows;
        numberOfColumns = columns;
        difficulty = Difficulty.CUSTOM;
    }

    /**
     * TODO: comment
     * @param difficulty
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
     * TODO: comment
     * @return
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * TODO: comment
     * @return
     */
    public int incrementBombs(){
        return ++bombsLeft;
    }

    /**
     * TODO: comment
     * @return
     */
    public int decrementBombs(){
        return --bombsLeft;
    }

    /**
     * TODO: comment
     * @param time
     * @param difficulty
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
     * TODO: comment
     */
    public enum Difficulty implements Serializable{
        EASY, MEDIUM, HARD, CUSTOM
    }
}