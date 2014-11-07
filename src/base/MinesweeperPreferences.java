package base;

import game.MineCell;

import java.io.Serializable;

/**
 *
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

    private MineCell[][] saveGame;
    private int saveTime;


    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     *
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

        saveGame = null;
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     *
     * @return
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     *
     * @param numberOfRows
     */
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    /**
     *
     * @return
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     *
     * @param numberOfColumns
     */
    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    /**
     *
     * @return
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    /**
     *
     * @param numberOfBombs
     */
    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    /**
     *
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     *
     * @return
     */
    public MineCell[][] getSaveGame() {
        return saveGame;
    }

    /**
     *
     * @param saveGame
     */
    public void setSaveGame(MineCell[][] saveGame) {
        this.saveGame = saveGame;
    }

    /**
     *
     * @return
     */
    public boolean isUseQuestionMark() {
        return useQuestionMark;
    }

    /**
     *
     * @param useQuestionMark
     */
    public void setUseQuestionMark(boolean useQuestionMark) {
        this.useQuestionMark = useQuestionMark;
    }

    /**
     *
     * @return
     */
    public boolean isShowTimer() {
        return showTimer;
    }

    /**
     *
     * @param showTimer
     */
    public void setShowTimer(boolean showTimer) {
        this.showTimer = showTimer;
    }

    /**
     *
     * @return
     */
    public int getBombsLeft() {
        return bombsLeft;
    }

    /**
     *
     * @param bombsLeft
     */
    public void setBombsLeft(int bombsLeft) {
        this.bombsLeft = bombsLeft;
    }

    /**
     *
     * @return
     */
    public HighScore getEasyHighScore() {
        return easyHighScore;
    }

    /**
     *
     * @param easyHighScore
     */
    public void setEasyHighScore(HighScore easyHighScore) {
        this.easyHighScore = easyHighScore;
    }

    /**
     *
     * @return
     */
    public HighScore getMediumHighScore() {
        return mediumHighScore;
    }

    /**
     *
     * @param mediumHighScore
     */
    public void setMediumHighScore(HighScore mediumHighScore) {
        this.mediumHighScore = mediumHighScore;
    }

    /**
     *
     * @return
     */
    public HighScore getHardHighScore() {
        return hardHighScore;
    }

    /**
     *
     * @param hardHighScore
     */
    public void setHardHighScore(HighScore hardHighScore) {
        this.hardHighScore = hardHighScore;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     *
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
     *
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
     *
     * @return
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @return
     */
    public int incrementBombs(){
        return ++bombsLeft;
    }

    /**
     *
     * @return
     */
    public int decrementBombs(){
        return --bombsLeft;
    }

    /**
     *
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
     *
     */
    public enum Difficulty implements Serializable{
        EASY, MEDIUM, HARD, CUSTOM
    }
}