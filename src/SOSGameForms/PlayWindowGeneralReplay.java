package SOSGameForms;

import SOSGameForms.Resources.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PlayWindowGeneralReplay extends PlayWindowGeneral {

    Color replayGameColor = new Color(104, 255, 86, 255);

    public PlayWindowGeneralReplay(int size, int pMode, Vector<Move> mList) throws HeadlessException {
        super(size, pMode);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Move i: mList){
                    if(i.getPlayerOneTurn()){
                        PlayerOneMakeMove((GameTile)playAreaPanel.getComponent(i.getMoveIndex()));
                    }
                    else{
                        PlayerTwoMakeMove((GameTile)playAreaPanel.getComponent(i.getMoveIndex()));
                    }

                }
            }
        });;



    }
    public void ReplayMovePlayerOne(){



    }
    public void ReplayMovePlayerTwo(){



    }
}
