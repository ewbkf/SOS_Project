package SOSGameForms;

import SOSGameForms.Resources.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class PlayWindowSimpleReplay extends PlayWindowSimple {

    Color replayGameColor = new Color(104, 255, 86, 255);

    Timer timer = new Timer();

    public PlayWindowSimpleReplay(int size, int pMode, Vector<Move> mList) throws HeadlessException {
        super(size, pMode);
        playerOne = new HumanPlayer();
        playerTwo = new HumanPlayer();

        int delayOffset = 1000;

        startGameButton.setVisible(false);

        for (Move i : mList) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (i.getPlayerOneTurn()) {
                        ReplayMovePlayerOne((GameTile) playAreaPanel.getComponent(i.getMoveIndex()), i);
                    } else {
                        ReplayMovePlayerTwo((GameTile) playAreaPanel.getComponent(i.getMoveIndex()), i);
                    }
                }
            }, delayOffset);
            delayOffset += 1000;
            //Needed to increase the offset as all the timers ar basically triggered immediately, so each one would need
            //to be one second longer than the last.
        }
    }

    public void ReplayMovePlayerOne(GameTile tile, Move move) {
        ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).played();
        ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).setState(move.getMoveType());

        if (move.getMoveType() == 'S') {
            ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).setIcon(new TileIcon().getWhiteS());
        } else if (move.getMoveType() == 'O') {
            ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).setIcon(new TileIcon().getWhiteO());
        }
        checkForSOS(tile.getCoords(), boardSize);
        tilesRemaining--;
        checkForWin();
    }

    public void ReplayMovePlayerTwo(GameTile tile, Move move) {
        ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).played();
        ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).setState(move.getMoveType());

        if (move.getMoveType() == 'S') {
            ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).setIcon(new TileIcon().getWhiteS());
        } else if (move.getMoveType() == 'O') {
            ((GameTile) playAreaPanel.getComponent(move.getMoveIndex())).setIcon(new TileIcon().getWhiteO());
        }
        checkForSOS(tile.getCoords(), boardSize);
        tilesRemaining--;
        checkForWin();
    }
}
