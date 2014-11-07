package game;

import java.io.Serializable;

/**
 *
 */
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

    /**
     *
     */
    public MineCell() {
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    /**
     *
     * @param content
     */
    public MineCell(MineCellContent content) {
        this(content, MineCellState.UNMARKED);
    }

    /**
     *
     * @param state
     */
    public MineCell(MineCellState state) {
        this(MineCellContent.EMPTY, state);
    }

    /**
     *
     * @param content
     * @param state
     */
    public MineCell(MineCellContent content, MineCellState state) {
        this.content = content;
        this.state = state;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     *
     * @return
     */
    public MineCellContent getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(MineCellContent content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public MineCellState getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(MineCellState state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public boolean isProtected() {
        return content == MineCellContent.PROTECTED;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return content == MineCellContent.EMPTY;
    }

    /**
     *
     * @return
     */
    public boolean isBomb() {
        return content == MineCellContent.BOMB;
    }

    /**
     *
     * @return
     */
    public boolean isRevealed() {
        return state == MineCellState.REVEALED;
    }

    /**
     *
     * @return
     */
    public boolean isQuestionMarked() {
        return state == MineCellState.QUESTIONMARK;
    }

    /**
     *
     * @return
     */
    public boolean isFlagged() {
        return state == MineCellState.FLAGGED;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     *
     */
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