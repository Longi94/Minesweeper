package game;

import base.Main;
import base.MinesweeperPreferences;
import gui.panel.MineCellPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The core class of the game. Prepares the board for game play and handles game events.
 */
public class MineField {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private boolean firstClick = true;
    private boolean timerRunning = false;
    private boolean timerAdded = getPrefs().isShowTimer();
    private boolean usingQuestionMarks = getPrefs().isUseQuestionMark();

    private int rows;
    private int columns;
    private int bombs;
    private int revealed;
    private MinesweeperPreferences.Difficulty currentDifficulty;

    private MineCellPanel[][] cellPanels;
    private MineCell[][] cells;

    private JLabel bombsLabel;
    private JLabel timeLabel;

    private Timer timer;
    private int time;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * Class constructor. Prepares the board, loads the saved game if present.
     * @param bombsLabel reference to a JLabel where the remaining number of mines is displayed
     * @param timeLabel reference to a JLabel where the current elapsed time is displayed
     */
    public MineField(JLabel bombsLabel, JLabel timeLabel) {

        if (getPrefs().getSavedGame() == null) {
            rows = getPrefs().getNumberOfRows();
            columns = getPrefs().getNumberOfColumns();
        }
        else {
            rows = getPrefs().getSavedGame().length;
            columns = getPrefs().getSavedGame()[0].length;
            firstClick = false;
        }

        bombs = getPrefs().getNumberOfBombs();
        currentDifficulty = getPrefs().getDifficulty();
        revealed = 0;

        this.bombsLabel = bombsLabel;
        this.timeLabel = timeLabel;

        timer = new Timer();
        time = 0;

        Player.setIsAlive(true);

        cellPanels = new MineCellPanel[rows][columns];
        cells = new MineCell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellPanels[i][j] = new MineCellPanel();
                cells[i][j] = new MineCell();
            }
        }

        if(getPrefs().getSavedGame() != null)
            loadGame();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * Returns a 2 dimensional array of MineCellPanel objects.
     * @return a MineCellPanel[][] object
     * @see gui.panel.MineCellPanel
     */
    public MineCellPanel[][] getCellPanels() {
        return cellPanels;
    }

    /**
     * Returns a 2 dimensional array of MineCell objects.
     * @return a MineCell[][] object
     * @see game.MineCell
     */
    public MineCell[][] getCells() {
        return cells;
    }

    /**
     * Returns the elapsed game time.
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns the number of rows of the current game.
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the current game.
     * @return the number of columns
     */
    public int getColumns() {
        return columns;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Assuming the board has been filled with mines. This method fills the rest of the board with numbers representing
     * the number of mines neighboring the given cell.
     */
    private void fillInNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j].isBomb())
                    switch (getNeighborBombs(i, j)) {
                        case 1:
                            cellPanels[i][j].setContent(MineCellContent.ONE);
                            cells[i][j].setContent(MineCellContent.ONE);
                            break;
                        case 2:
                            cellPanels[i][j].setContent(MineCellContent.TWO);
                            cells[i][j].setContent(MineCellContent.TWO);
                            break;
                        case 3:
                            cellPanels[i][j].setContent(MineCellContent.THREE);
                            cells[i][j].setContent(MineCellContent.THREE);
                            break;
                        case 4:
                            cellPanels[i][j].setContent(MineCellContent.FOUR);
                            cells[i][j].setContent(MineCellContent.FOUR);
                            break;
                        case 5:
                            cellPanels[i][j].setContent(MineCellContent.FIVE);
                            cells[i][j].setContent(MineCellContent.FIVE);
                            break;
                        case 6:
                            cellPanels[i][j].setContent(MineCellContent.SIX);
                            cells[i][j].setContent(MineCellContent.SIX);
                            break;
                        case 7:
                            cellPanels[i][j].setContent(MineCellContent.SEVEN);
                            cells[i][j].setContent(MineCellContent.SEVEN);
                            break;
                        case 8:
                            cellPanels[i][j].setContent(MineCellContent.EIGHT);
                            cells[i][j].setContent(MineCellContent.EIGHT);
                            break;
                        default:
                            cellPanels[i][j].setContent(MineCellContent.EMPTY);
                            cells[i][j].setContent(MineCellContent.EMPTY);
                            break;
                    }
            }
        }
    }

    /**
     * Stops the game. Should be called when the player wins the game.
     */
    private void finishGame() {
        Player.setIsAlive(false);
        Player.setGameStarted(false);
        cancelTimer();

        getPrefs().saveHighScore(time, currentDifficulty);
    }

    /**
     * Formats the given time in seconds to a friendly MM:SS format.
     * @param seconds the number of seconds
     * @return the formatted String
     */
    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d", min, sec);
    }

    /**
     * Stops the game. Should be called when the player uncovers a mine.
     */
    private void killPlayer() {

        Player.setIsAlive(false);
        Player.setGameStarted(false);

        cancelTimer();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isBomb())
                    revealClickedCell(i, j);
            }
        }

        getPrefs().setSavedGame(null);
    }

    /**
     * Returns the preferences used by the game.
     * @return the preferences
     * @see base.MinesweeperPreferences
     */
    private MinesweeperPreferences getPrefs() {
        return Main.getPrefs();
    }

    /**
     * Returns the number of flagged cells neighboring the cell in the given row and column.
     * @param row the row the cell is in
     * @param column the column the cell is in
     * @return the number of neighboring flagged cells
     */
    private int getNeighborFlags(int row, int column) {
        int num = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (!(i == -1 || i == rows || j == -1 || j == columns || (i == row && j == column))) {
                    if (cells[i][j].isFlagged()) {
                        num++;
                    }
                }
            }
        }

        return num;
    }

    /**
     * Returns the number of mines neighboring the cell in the given row and column.
     * @param row the row the cell is in
     * @param column the column the cell is in
     * @return the number of neighboring mines
     */
    private int getNeighborBombs(int row, int column) {

        int num = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (!(i == -1 || i == rows || j == -1 || j == columns || (i == row && j == column))) {
                    if (cells[i][j].isBomb()) {
                        num++;
                    }
                }
            }
        }

        return num;
    }

    /**
     * Loads all the data from the saved game.
     */
    private void loadGame() {
        cells = getPrefs().getSavedGame();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cellPanels[i][j].setContent(cells[i][j].getContent());
                if (cells[i][j].isRevealed()) {
                    cellPanels[i][j].reveal(cells[i][j].getContent());
                    revealed++;
                }
                else if (cells[i][j].isFlagged()) {
                    cellPanels[i][j].flagCell();
                }
                else if (cells[i][j].isQuestionMarked()) {
                    cellPanels[i][j].questionMark();
                }
            }
        }

        bombsLabel.setText("Mines left: " + getPrefs().getBombsLeft());
        timeLabel.setText(formatTime(getPrefs().getSavedTime()));
        time = getPrefs().getSavedTime();
        Player.setGameStarted(true);
    }

    /**
     * Randomly fills the board with a given number of mines.
     * @param numberOfBombs the number of mines to be inserted into the board
     */
    private void randomizeField(int numberOfBombs) {
        ArrayList<MineCellContent> tempList = new ArrayList<MineCellContent>();

        for (int i = 0; i < rows * columns - 9; i++) {
            if (i < numberOfBombs)
                tempList.add(MineCellContent.BOMB);
            else
                tempList.add(MineCellContent.EMPTY);
        }

        Collections.shuffle(tempList);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j].isProtected() && !tempList.isEmpty()) {
                    cells[i][j].setContent(tempList.get(0));
                    cellPanels[i][j].setContent(tempList.get(0));
                    tempList.remove(0);
                }
            }
        }
    }

    /**
     * Uncovers the cell in the given row and column
     * @param row the row the cell is in
     * @param column the column the cell is in
     */
    private void revealClickedCell(int row, int column) {
        if (!cells[row][column].isRevealed()) {
            cellPanels[row][column].reveal(cells[row][column].getContent());
            if (cells[row][column].isFlagged() && Player.isAlive())
                bombsLabel.setText("Mines left: " + getPrefs().incrementBombs());
            revealed++;
        }
    }

    /**
     * Recursively uncovers all the connected empty cells and the numbered cells next to them starting from the given
     * row and column.
     * @param row the row the starting cell is in
     * @param column the column the starting cell is in
     */
    private void revealEmptyCells(int row, int column) {
        if (row == -1 || column == -1 || row == rows || column == columns || !cells[row][column].isEmpty() || cells[row][column].isRevealed())
            return;

        cellPanels[row][column].reveal(cells[row][column].getContent());
        if (cells[row][column].isFlagged())
            bombsLabel.setText("Mines left: " + getPrefs().incrementBombs());
        cells[row][column].setState(MineCellState.REVEALED);
        revealed++;

        revealSurrounding(row, column);

        revealEmptyCells(row - 1, column);
        revealEmptyCells(row, column - 1);
        revealEmptyCells(row + 1, column);
        revealEmptyCells(row, column + 1);
        revealEmptyCells(row - 1, column - 1);
        revealEmptyCells(row + 1, column - 1);
        revealEmptyCells(row + 1, column + 1);
        revealEmptyCells(row - 1, column + 1);
    }

    /**
     * Uncovers the 8 adjacent cells to cell in the given row and column.
     * @param row the row the cell is in
     * @param column the column the cell is in
     */
    private void revealSurrounding(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i > -1 && i < rows && j > -1 && j < columns) {
                    if (!cells[i][j].isRevealed() && !cells[i][j].isBomb() && !cells[i][j].isEmpty()) {

                        cellPanels[i][j].reveal(cells[i][j].getContent());
                        if (cells[row][column].isFlagged())
                            bombsLabel.setText("Mines left: " + getPrefs().incrementBombs());
                        cells[i][j].setState(MineCellState.REVEALED);
                        revealed++;

                    }
                }
            }
        }
    }

    /**
     * Stops the timer if started.
     */
    public void cancelTimer() {
        if (timerRunning) {
            timer.cancel();
            timerRunning = false;
        }
    }

    /**
     * Handles clicks on the cell in the given row and column.
     * @param row the row the cell is in
     * @param column the column the cell is in
     */
    public void onCellClick(int row, int column) {

        if (!timerRunning && Player.isAlive()) {
            timer.scheduleAtFixedRate(new GameTimerTask(), 1000, 1000);
            timerRunning = true;
        }

        if (firstClick) {
            firstClick = false;
            Player.setGameStarted(true);

            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (!(i == -1 || i == rows || j == -1 || j == columns)) {
                        cells[i][j].setContent(MineCellContent.PROTECTED);
                    }
                }
            }

            randomizeField(bombs);
            fillInNumbers();

            revealEmptyCells(row, column);

        } else if (Player.isAlive()) {
            switch (cells[row][column].getContent()) {
                case EMPTY:
                    revealEmptyCells(row, column);
                    break;
                case BOMB:
                    revealClickedCell(row, column);
                    cells[row][column].setState(MineCellState.REVEALED);
                    killPlayer();
                    break;
                default:
                    revealClickedCell(row, column);
                    cells[row][column].setState(MineCellState.REVEALED);
                    break;
            }
        }


        if (getPrefs().getBombsLeft() == 0 && revealed == rows * columns - bombs)
            finishGame();
    }

    /**
     * Handles preference modification events.
     */
    public void onPreferenceChanged(){
        if (getPrefs().isShowTimer() && !timerAdded){
            timerAdded = true;
            timeLabel.setForeground(new Color(0, 0, 0));
            timeLabel.repaint();
        }
        else if (!getPrefs().isShowTimer() && timerAdded) {
            timerAdded = false;
            timeLabel.setForeground(new Color(230, 230, 230));
            timeLabel.repaint();
        }

        if (!getPrefs().isUseQuestionMark() && usingQuestionMarks){
            usingQuestionMarks = false;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (cells[i][j].isQuestionMarked())
                        cells[i][j].setState(cellPanels[i][j].toggleFlag(cells[i][j].getState(), usingQuestionMarks));
                }
            }

        }
        else if (getPrefs().isUseQuestionMark() && !usingQuestionMarks){
            usingQuestionMarks = true;
        }
    }

    /**
     * Handles clicks made with both mouse buttons on uncovered numbered cells.
     * @param row the row the cell is in
     * @param column the column the cell is in
     */
    public void onTwoButtonCellClick(int row, int column) {
        if (Player.isAlive() && cells[row][column].isRevealed() && !cells[row][column].isEmpty() &&
                cells[row][column].getContentValue() == getNeighborFlags(row, column)) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i > -1 && i < rows && j > -1 && j < columns && !cells[i][j].isRevealed() && !cells[i][j].isFlagged()) {
                        if (cells[i][j].isBomb())
                            Player.setIsAlive(false);
                        if (cells[i][j].isEmpty())
                            revealEmptyCells(i, j);
                        else {
                            cellPanels[i][j].reveal(cells[i][j].getContent());
                            cells[i][j].setState(MineCellState.REVEALED);
                            revealed++;
                        }
                    }
                }
            }

            if (!Player.isAlive())
                killPlayer();

            if (getPrefs().getBombsLeft() == 0 && revealed == rows * columns - bombs)
                finishGame();
        }
    }

    /**
     * Handles right click on the covered cell in the given row and column.
     * @param row the row the cell is in
     * @param column the column the cell is in
     */
    public void toggleFlag(int row, int column) {
        if (cells[row][column].isUnmarked() && Player.isAlive())
            bombsLabel.setText("Mines left: " + getPrefs().decrementBombs());
        else if (cells[row][column].isFlagged() && Player.isAlive())
            bombsLabel.setText("Mines left: " + getPrefs().incrementBombs());

        cells[row][column].setState(cellPanels[row][column].toggleFlag(cells[row][column].getState(), usingQuestionMarks));

        if (!timerRunning && Player.isAlive()) {
            timer.scheduleAtFixedRate(new GameTimerTask(), 1000, 1000);
            timerRunning = true;
        }

        if (getPrefs().getBombsLeft() == 0 && revealed == rows * columns - bombs)
            finishGame();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * A class extended from the TimerTask class. Used as a timer to save time it took the player to finish the game.
     * @see java.util.TimerTask
     */
    private class GameTimerTask extends TimerTask {

        /**
         * The task that runs every one second.
         */
        @Override
        public void run() {
            timerRunning = true;
            timeLabel.setText(formatTime(++time));
            if (time == 99 * 60 + 59 && timerRunning) {
                timer.cancel();
            }
        }
    }
}
