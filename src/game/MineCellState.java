package game;

import java.io.Serializable;

/**
 * TODO: comment
 */
public enum MineCellState implements Serializable{
    UNMARKED,
    FLAGGED,
    QUESTIONMARK,
    REVEALED
}
