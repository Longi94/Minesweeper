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

    boolean useQuestionMark = true;
    boolean showTimer = true;

    private String playerName;

    private MineField saveGame;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MinesweeperPreferences() {
        numberOfBombs = 99;
        numberOfColumns = 30;
        numberOfRows = 16;
        bombsLeft = 99;

        useQuestionMark = true;
        showTimer = true;

        playerName = "Player";

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
        this.numberOfBombs = bombs;
        numberOfRows = rows;
        numberOfColumns = columns;
    }

    public int incrementBombs(){
        return ++bombsLeft;
    }

    public int decrementBombs(){
        return --bombsLeft;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}