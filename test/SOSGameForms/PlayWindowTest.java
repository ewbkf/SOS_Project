package SOSGameForms;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayWindowTest {
    PlayWindow testWindow = new PlayWindow(3, 1, 1);
    GameTile testButton = new GameTile(new TileLocation(6,9), new TileIcon().getBLANK());

    //Tests to make sure that teh tile's coordinates are correct after instantiation.
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

    //This test will see that child attributes such as coordinates are preserved after an object
    //is retrieved from a JPanel and cast as a GameTile.
    @Test
    public void checkIntegrityOfGameTileAfterCastXCoord(){
        JPanel jPanel = new JPanel();
        testButton.setCoords(6,9);
        jPanel.add(testButton);

        GameTile resultingTile = (GameTile)(jPanel.getComponent(0));

        assertEquals(6, resultingTile.getXCoord());
    }
    @Test
    public void checkIntegrityOfGameTileAfterCastYCoord(){
        JPanel jPanel = new JPanel();
        testButton.setCoords(6,9);
        jPanel.add(testButton);

        GameTile resultingTile = (GameTile)(jPanel.getComponent(0));

        assertEquals(9, resultingTile.getYCoord());
    }
}