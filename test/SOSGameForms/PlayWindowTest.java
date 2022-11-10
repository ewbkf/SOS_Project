package SOSGameForms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayWindowTest {
    PlayWindow testWindow = new PlayWindow(3, 1, 1);
    GameTile testButton = new GameTile(new TileLocation(6,9), new TileIcon().getBLANK());
    @Test
    public void checkTileXCoordinatesAreCorrectAfterAfterInstantiation(){
        testButton.setState('S');
        testWindow.MakeMove(testButton);
        assertEquals(6, testButton.getCoords().getxCoord());
    }
    @Test
    public void checkTileYCoordinatesAreCorrectAfterAfterInstantiation(){
        testButton.setState('S');
        testWindow.MakeMove(testButton);
        assertEquals(9, testButton.getCoords().getyCoord());
    }
    @Test
    public void checkThatXCoordinatesAreCorrectAfterTurnP1S(){
        testWindow.setP1RadioButtonS(true);
        testWindow.setP2RadioButtonS(true);
        testButton.setState('S');
        testWindow.MakeMove(testButton);
        assertEquals(testButton.getCoords().getxCoord(), testWindow.lastTilePlayed.getxCoord());
    }
}