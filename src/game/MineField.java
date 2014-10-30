package game;

import java.util.ArrayList;
import java.util.Collections;

public class MineField {

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int NUMBER_OF_BOMBS = 99;

    // ===========================================================
    // Fields
    // ===========================================================

    private int rows;
    private int columns;

    private MineCell[][] cells;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineField(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        cells = new MineCell[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j] = new MineCell();
            }
        }

        randomizeField(NUMBER_OF_BOMBS);

        fillInNumbers();
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

    public void randomizeField(int numberOfBombs){
        ArrayList<MineCellContent> tempList = new ArrayList<MineCellContent>();

        for (int i = 0; i < rows * columns; i++){
            if (i < numberOfBombs)
                tempList.add(MineCellContent.BOMB);
            else
                tempList.add(MineCellContent.EMPTY);
        }

        Collections.shuffle(tempList);

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j].setContent(tempList.get(0));
                tempList.remove(0);
            }
        }
    }

    public void onCellClick(int row, int column){

        switch (cells[row][column].getContent()){
            case EMPTY:
                revealEmptyCells(row, column, Direction.NONE);
                break;
            case BOMB:
                revealClickedCell(row, column);
                cells[row][column].setState(MineCellState.REVEALED);
                break;
            default:
                revealClickedCell(row, column);
                cells[row][column].setState(MineCellState.REVEALED);
                break;
        }
    }

    private void revealClickedCell(int row, int column) {
        if (!cells[row][column].isRevealed()) {
            cells[row][column].reveal();
        }
    }

    private void revealEmptyCells(int row, int column, Direction source) {
        if (row == -1 || column == -1 || row == rows || column == columns || !cells[row][column].isEmpty() || cells[row][column].isRevealed())
            return;

        cells[row][column].reveal();
        cells[row][column].setState(MineCellState.REVEALED);

        revealSurrounding(row, column);

        revealEmptyCells(row - 1, column, Direction.SOUTH);
        revealEmptyCells(row, column - 1, Direction.EAST);
        revealEmptyCells(row + 1, column, Direction.NORTH);
        revealEmptyCells(row, column + 1, Direction.WEST);
    }

    private void fillInNumbers() {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if (!cells[i][j].isBomb())
                    switch (getNeighborBombs(i, j)){
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

    private int getNeighborBombs(int row, int column){

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
    }

    public void revealSurrounding(int row, int column){
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i > -1 && i < rows && j > -1 && j < columns) {
                    if (!cells[i][j].isRevealed() && !cells[i][j].isBomb() && !cells[i][j].isEmpty()){

                        cells[i][j].reveal();
                        cells[i][j].setState(MineCellState.REVEALED);
                    }
                }
            }
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private enum Direction{
        NORTH, SOUTH, EAST, WEST, NONE
    }
}