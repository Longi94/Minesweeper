package game;

public class MineCell {

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int SIZE = 25;

    // ===========================================================
    // Fields
    // ===========================================================

    MineCellContent content;
    MineCellState state;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineCell(){
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    public MineCell(MineCellContent content, MineCellState state){
        this.content = content;
        this.state = state;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
