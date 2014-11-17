package game;

import java.io.Serializable;

/**
 * Enumeration used to represent the content of a mine cell
 */
public enum MineCellContent implements Serializable{
    EMPTY,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    BOMB,
    PROTECTED
}
