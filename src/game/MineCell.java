package game;

import java.io.Serializable;

/**
 * TODO: comment
 */
public class MineCell implements Serializable {
    // ===========================================================
    // Constants
    // ===========================================================

	private static final long serialVersionUID = 7664814366212183792L;
	
    // ===========================================================
    // Fields
    // ===========================================================

	private MineCellContent content;
    private MineCellState state;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * TODO: comment
     */
    public MineCell() {
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    /**
     * TODO: comment
     * @param content
     */
    public MineCell(MineCellContent content) {
        this(content, MineCellState.UNMARKED);
    }

    /**
     * TODO: comment
     * @param state
     */
    public MineCell(MineCellState state) {
        this(MineCellContent.EMPTY, state);
    }

    /**
     * TODO: comment
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
     * TODO: comment
     * @return
     */
    public MineCellContent getContent() {
        return content;
    }

    /**
     * TODO: comment
     * @param content
     */
    public void setContent(MineCellContent content) {
        this.content = content;
    }

    /**
     * TODO: comment
     * @return
     */
    public MineCellState getState() {
        return state;
    }

    /**
     * TODO: comment
     * @param state
     */
    public void setState(MineCellState state) {
        this.state = state;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isProtected() {
        return content == MineCellContent.PROTECTED;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isEmpty() {
        return content == MineCellContent.EMPTY;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isBomb() {
        return content == MineCellContent.BOMB;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isRevealed() {
        return state == MineCellState.REVEALED;
    }

    /**
     * TODO: comment
     * @return
     */
    public boolean isQuestionMarked() {
        return state == MineCellState.QUESTIONMARK;
    }

    /**
     * TODO: comment
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
     *  TODO: comment
     * @return
     */
    public int getContentValue() {
        switch (content) {
            case EMPTY:
                return 0;
            case ONE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case BOMB:
                return -1;
            case PROTECTED:
                return -2;
            default:
                return 0;
        }
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}