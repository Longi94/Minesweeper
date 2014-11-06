package game;

import java.io.Serializable;

public class MineCell implements Serializable {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private MineCellContent content;
    private MineCellState state;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MineCell() {
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    public MineCell(MineCellContent content) {
        this(content, MineCellState.UNMARKED);
    }

    public MineCell(MineCellState state) {
        this(MineCellContent.EMPTY, state);
    }

    public MineCell(MineCellContent content, MineCellState state) {
        this.content = content;
        this.state = state;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public MineCellContent getContent() {
        return content;
    }

    public void setContent(MineCellContent content) {
        this.content = content;
    }


    public MineCellState getState() {
        return state;
    }

    public void setState(MineCellState state) {
        this.state = state;
    }

    public boolean isProtected() {
        return content == MineCellContent.PROTECTED;
    }

    public boolean isEmpty() {
        return content == MineCellContent.EMPTY;
    }

    public boolean isBomb() {
        return content == MineCellContent.BOMB;
    }

    public boolean isRevealed() {
        return state == MineCellState.REVEALED;
    }

    public boolean isQuestionMarked() {
        return state == MineCellState.QUESTIONMARK;
    }

    public boolean isFlagged() {
        return state == MineCellState.FLAGGED;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void toggleFlag() {
        if (Player.isAlive())
            switch (state) {
                case UNMARKED:
                    state = MineCellState.FLAGGED;
                    break;
                case FLAGGED:
                    state = MineCellState.QUESTIONMARK;
                    break;
                case QUESTIONMARK:
                    state = MineCellState.UNMARKED;
                    break;
                case REVEALED:
                    break;
            }
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}