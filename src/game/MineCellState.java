package game;

import java.io.Serializable;

/**
 * Enumeration used to represent the state of a mine cell,
 */
public enum MineCellState implements Serializable{
    UNMARKED,
    FLAGGED,
    QUESTIONMARK,
    REVEALED
}
