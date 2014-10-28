package game;

public class MineField {
    MineCell[][] field;

    public MineField(int rows, int columns){
        field = new MineCell[rows][columns];
    }
}
