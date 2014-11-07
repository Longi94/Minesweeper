package game;

import java.io.Serializable;

/**
 *
 */
public enum MineCellState implements Serializable{
    UNMARKED,
    FLAGGED,
    QUESTIONMARK,
    REVEALED
}
