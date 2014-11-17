package test;

import base.MinesweeperPreferences;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MinesweeperPreferencesTest {

    MinesweeperPreferences prefs;

    @Before
    public void setUp(){
        prefs = new MinesweeperPreferences();
    }

    @Test
    public void testSaveHighScore(){
        prefs.saveHighScore(50, MinesweeperPreferences.Difficulty.EASY);
        prefs.saveHighScore(532, MinesweeperPreferences.Difficulty.HARD);
        prefs.saveHighScore(204, MinesweeperPreferences.Difficulty.MEDIUM);

        assertEquals(50, prefs.getEasyHighScore().getSeconds());
        assertEquals(204, prefs.getMediumHighScore().getSeconds());
        assertEquals(532, prefs.getHardHighScore().getSeconds());
    }

    @Test
    public void testSetDifficulty(){
        prefs.setDifficulty(MinesweeperPreferences.Difficulty.EASY);
        assertEquals(10, prefs.getNumberOfBombs());
        assertEquals(9, prefs.getNumberOfColumns());
        assertEquals(9, prefs.getNumberOfRows());

        prefs.setDifficulty(MinesweeperPreferences.Difficulty.MEDIUM);
        assertEquals(40, prefs.getNumberOfBombs());
        assertEquals(16, prefs.getNumberOfColumns());
        assertEquals(16, prefs.getNumberOfRows());

        prefs.setDifficulty(MinesweeperPreferences.Difficulty.HARD);
        assertEquals(99, prefs.getNumberOfBombs());
        assertEquals(30, prefs.getNumberOfColumns());
        assertEquals(16, prefs.getNumberOfRows());
    }

    @Test
    public void testManipulateBombs(){
        assertEquals(98, prefs.decrementBombs());
        assertEquals(99, prefs.getNumberOfBombs());
        assertEquals(97, prefs.decrementBombs());
        assertEquals(99, prefs.getNumberOfBombs());
        assertEquals(98, prefs.incrementBombs());
        assertEquals(99, prefs.getNumberOfBombs());
        assertEquals(99, prefs.incrementBombs());
        assertEquals(99, prefs.getNumberOfBombs());
    }
}