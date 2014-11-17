package test;

import game.MineCellContent;
import game.MineCellState;
import gui.panel.MineCellPanel;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class MineCellPanelTest {

    private MineCellPanel panel;

    @Before
    public void setUp(){
        panel = new MineCellPanel();
        panel.setContent(MineCellContent.EMPTY);
    }

    @Test
    public void testReveal() {
        assertTrue(panel.getButton().isVisible());
        panel.reveal(MineCellContent.ONE);
        assertEquals(2, panel.getCellPanel().getComponentCount());
        assertTrue(panel.getCellContent().isVisible());

        panel = new MineCellPanel();

        assertTrue(panel.getButton().isVisible());
        panel.reveal(MineCellContent.BOMB);
        assertEquals(2, panel.getCellPanel().getComponentCount());
        assertTrue(panel.getMineIconLabel().isVisible());
    }

    @Test
    public void testSetContent() {
    }

    @Test
    public void testToggleFlag() {
        assertEquals(MineCellState.FLAGGED, panel.toggleFlag(MineCellState.UNMARKED, true));
        assertEquals(MineCellState.QUESTIONMARK, panel.toggleFlag(MineCellState.FLAGGED, true));
        assertEquals(MineCellState.UNMARKED, panel.toggleFlag(MineCellState.QUESTIONMARK, true));
        assertEquals(MineCellState.FLAGGED, panel.toggleFlag(MineCellState.UNMARKED, false));
        assertEquals(MineCellState.UNMARKED, panel.toggleFlag(MineCellState.FLAGGED, false));
    }
}