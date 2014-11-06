package base;

import game.MineField;

import java.io.Serializable;

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

    boolean useQuestionMark = true;
    boolean showTimer = true;

    private String playerName;

    private MineField saveGame;

    private HighScore easyHighScore;
    private HighScore mediumHighScore;
    private HighScore hardHighScore;

    // ===========================================================
    // Constructors
    // ===========================================================

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

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public MineField getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(MineField saveGame) {
        this.saveGame = saveGame;
    }

    public boolean isUseQuestionMark() {
        return useQuestionMark;
    }

    public void setUseQuestionMark(boolean useQuestionMark) {
        this.useQuestionMark = useQuestionMark;
    }

    public boolean isShowTimer() {
        return showTimer;
    }

    public void setShowTimer(boolean showTimer) {
        this.showTimer = showTimer;
    }

    public int getBombsLeft() {
        return bombsLeft;
    }

    public void setBombsLeft(int bombsLeft) {
        this.bombsLeft = bombsLeft;
    }

    public HighScore getEasyHighScore() {
        return easyHighScore;
    }

    public void setEasyHighScore(HighScore easyHighScore) {
        this.easyHighScore = easyHighScore;
    }

    public HighScore getMediumHighScore() {
        return mediumHighScore;
    }

    public void setMediumHighScore(HighScore mediumHighScore) {
        this.mediumHighScore = mediumHighScore;
    }

    public HighScore getHardHighScore() {
        return hardHighScore;
    }

    public void setHardHighScore(HighScore hardHighScore) {
        this.hardHighScore = hardHighScore;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void savePreferences(String fileName){

    }

    public void loadPreferences(String fileName){

    }

    public void setDifficulty(int rows, int columns, int bombs){
        numberOfBombs = bombs;
        numberOfRows = rows;
        numberOfColumns = columns;
        difficulty = Difficulty.CUSTOM;
    }

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

    public int incrementBombs(){
        return ++bombsLeft;
    }

    public int decrementBombs(){
        return --bombsLeft;
    }

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    public enum Difficulty{
        EASY, MEDIUM, HARD, CUSTOM
    }
}