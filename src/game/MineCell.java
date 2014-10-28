package game;

public class MineCell {
    MineCellContent content;
    MineCellState state;

    public MineCell(){
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    public MineCell(MineCellContent content, MineCellState state){
        this.content = content;
        this.state = state;
    }
}
