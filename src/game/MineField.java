package game;

import base.Main;
import base.MinesweeperPreferences;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MineField {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private boolean firstClick = true;

    private int rows;
    private int columns;
    private int bombs;
    private int revealed;
    private MinesweeperPreferences.Difficulty currentDifficulty;

    private MineCell[][] cells;

    private JLabel bombsLabel;
    private JLabel timeLabel;

    private Timer timer;
    private int time;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineField(int rows, int columns, int bombs, MinesweeperPreferences.Difficulty difficulty, JLabel bombsLabel, JLabel timeLabel) {
        this.bombs = bombs;
        this.rows = rows;
        this.columns = columns;
        currentDifficulty = difficulty;
        revealed = 0;

        cells = new MineCell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new MineCell();
            }
        }

        this.bombsLabel = bombsLabel;
        this.timeLabel = timeLabel;

        timer = new Timer();
        time = 0;

        Player.setIsAlive(true);
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public MineCell[][] getCells() {
        return cells;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

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
                    tempList.remove(0);
                }
            }
        }
    }

    public void onCellClick(int row, int column) {

        if (firstClick) {
            firstClick = false;

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

            timer.scheduleAtFixedRate(new GameTimerTask(), 1000, 1000);
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

    private void finishGame() {
        Player.setIsAlive(false);
        timer.cancel();

        getPrefs().saveHighScore(time, currentDifficulty);
    }

    private void killPlayer() {

        Player.setIsAlive(false);

        timer.cancel();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cells[i][j].isBomb())
                    revealClickedCell(i, j);
            }
        }
    }

    private void revealClickedCell(int row, int column) {
        if (!cells[row][column].isRevealed()) {
            cells[row][column].reveal();
            revealed++;
        }
    }

    private void revealEmptyCells(int row, int column) {
        if (row == -1 || column == -1 || row == rows || column == columns || !cells[row][column].isEmpty() || cells[row][column].isRevealed())
            return;

        cells[row][column].reveal();
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

    private void fillInNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j].isBomb())
                    switch (getNeighborBombs(i, j)) {
                        case 1:
                            cells[i][j].setContent(MineCellContent.ONE);
                            break;
                        case 2:
                            cells[i][j].setContent(MineCellContent.TWO);
                            break;
                        case 3:
                            cells[i][j].setContent(MineCellContent.THREE);
                            break;
                        case 4:
                            cells[i][j].setContent(MineCellContent.FOUR);
                            break;
                        case 5:
                            cells[i][j].setContent(MineCellContent.FIVE);
                            break;
                        case 6:
                            cells[i][j].setContent(MineCellContent.SIX);
                            break;
                        case 7:
                            cells[i][j].setContent(MineCellContent.SEVEN);
                            break;
                        case 8:
                            cells[i][j].setContent(MineCellContent.EIGHT);
                            break;
                        default:
                            cells[i][j].setContent(MineCellContent.EMPTY);
                            break;
                    }
            }
        }
    }

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

    public void toggleFlag(int row, int column) {
        cells[row][column].toggleFlag();
        if (cells[row][column].isFlagged())
            bombsLabel.setText("Mines left: " + getPrefs().decrementBombs());
        else if (cells[row][column].isQuestionMarked())
            bombsLabel.setText("Mines left: " + getPrefs().incrementBombs());

        if (getPrefs().getBombsLeft() == 0 && revealed == rows * columns - bombs)
            finishGame();
    }

    private MinesweeperPreferences getPrefs() {
        return Main.getPrefs();
    }

    private void revealSurrounding(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i > -1 && i < rows && j > -1 && j < columns) {
                    if (!cells[i][j].isRevealed() && !cells[i][j].isBomb() && !cells[i][j].isEmpty()) {

                        cells[i][j].reveal();
                        cells[i][j].setState(MineCellState.REVEALED);
                        revealed++;

                    }
                }
            }
        }
    }

    private String formatTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d", min, sec);
    }

    public void cancelTimer() {
        timer.cancel();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            timeLabel.setText(formatTime(++time));
            if (time == 99 * 60 + 59) {
                timer.cancel();
            }
        }
    }
}
