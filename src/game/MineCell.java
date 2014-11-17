package game;

import java.io.Serializable;

/**
 * The class containing the properties of a mine cell.
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
     * Default constructor.
     */
    public MineCell() {
        this(MineCellContent.EMPTY, MineCellState.UNMARKED);
    }

    /**
     * Constructor.
     * @param content the content to be set
     * @see game.MineCellContent
     */
    public MineCell(MineCellContent content) {
        this(content, MineCellState.UNMARKED);
    }

    /**
     * Constructor.
     * @param state the state to be set
     * @see game.MineCellState
     */
    public MineCell(MineCellState state) {
        this(MineCellContent.EMPTY, state);
    }

    /**
     * Constructor
     * @param content the content to be set
     * @param state the state to be set
     * @see game.MineCellContent
     * @see game.MineCellState
     */
    public MineCell(MineCellContent content, MineCellState state) {
        this.content = content;
        this.state = state;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    /**
     * @return the content of the mine cell
     * @see game.MineCellContent
     */
    public MineCellContent getContent() {
        return content;
    }

    /**
     * @param content the content to be set
     * @see game.MineCellContent
     */
    public void setContent(MineCellContent content) {
        this.content = content;
    }

    /**
     * @return the state of the mine cell
     * @see game.MineCellState
     */
    public MineCellState getState() {
        return state;
    }

    /**
     * @param state the state to be set
     * @see game.MineCellState
     */
    public void setState(MineCellState state) {
        this.state = state;
    }

    /**
     * @return whether the cell is protected
     */
    public boolean isProtected() {
        return content == MineCellContent.PROTECTED;
    }

    /**
     * @return whether the cell is empty
     */
    public boolean isEmpty() {
        return content == MineCellContent.EMPTY;
    }

    /**
     * @return whether the cell contains a mine
     */
    public boolean isBomb() {
        return content == MineCellContent.BOMB;
    }

    /**
     * @return whether the cell has been uncovered
     */
    public boolean isRevealed() {
        return state == MineCellState.REVEALED;
    }

    /**
     * @return whether the cell is marked with a question mark
     */
    public boolean isQuestionMarked() {
        return state == MineCellState.QUESTIONMARK;
    }

    /**
     * @return whether the cell is flagged
     */
    public boolean isFlagged() {
        return state == MineCellState.FLAGGED;
    }

    /**
     * @return whether the cell is unmarked
     */
    public boolean isUnmarked() {
        return state == MineCellState.UNMARKED;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Decodes to content of the mine cell to an integer.
     * @return the decoded integer
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