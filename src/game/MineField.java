package game;

import java.util.ArrayList;
import java.util.Collections;

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

    private MineCell[][] cells;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineField(int rows, int columns, int bombs){
        this.bombs = bombs;
        this.rows = rows;
        this.columns = columns;
        cells = new MineCell[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j] = new MineCell();
            }
        }
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

    private void randomizeField(int numberOfBombs){
        ArrayList<MineCellContent> tempList = new ArrayList<MineCellContent>();

        for (int i = 0; i < rows * columns - 9; i++){
            if (i < numberOfBombs)
                tempList.add(MineCellContent.BOMB);
            else
                tempList.add(MineCellContent.EMPTY);
        }

        Collections.shuffle(tempList);

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(!cells[i][j].isProtected()) {
                    cells[i][j].setContent(tempList.get(0));
                    tempList.remove(0);
                }
            }
        }
    }

    public void onCellClick(int row, int column){

        if (firstClick){
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
        }
        else {
            switch (cells[row][column].getContent()) {
                case EMPTY:
                    revealEmptyCells(row, column);
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
    }

    private void revealClickedCell(int row, int column) {
        if (!cells[row][column].isRevealed()) {
            cells[row][column].reveal();
        }
    }

    private void revealEmptyCells(int row, int column) {
        if (row == -1 || column == -1 || row == rows || column == columns || !cells[row][column].isEmpty() || cells[row][column].isRevealed())
            return;

        cells[row][column].reveal();
        cells[row][column].setState(MineCellState.REVEALED);

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

    private void revealSurrounding(int row, int column){
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

    public void reset() {
        firstClick = true;
        cells = new MineCell[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j] = new MineCell();
            }
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
