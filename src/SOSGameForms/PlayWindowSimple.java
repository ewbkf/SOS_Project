package SOSGameForms;

import java.awt.*;

public class PlayWindowSimple extends PlayWindow{

    Color simpleGameColor = new Color(108, 108, 108, 255);

    public PlayWindowSimple(int size, int pMode) throws HeadlessException {
        super(size, pMode);
        titlePanel.setBackground(simpleGameColor);
        scorePanelP1.setBackground(simpleGameColor);
        scorePanelP2.setBackground(simpleGameColor);
        footerPanel.setBackground(simpleGameColor);
        footerButtonPanel.setBackground(simpleGameColor);
        P1ScoreText.setVisible(false);
        P2ScoreText.setVisible(false);
        PLayer1ScoreText.setVisible(false);
        Player2ScoreText.setVisible(false);
    }

    @Override
    protected void checkForWin(int P1, int P2){
        //In a simple game the game will end as soon as one player scores a point.
        if (P1 > 0){
            //TODO: P1 is victorious
            PlayerOneWins();
            GameOver();
        }
        else if (P2 > 0){
            //TODO: P2 is victorious
            PlayerTwoWins();
            GameOver();
        }
        else if(tilesRemaining < 1){
            //TODO: DRAW
        }
        //if none of the above, nothing happens and the game continues.
    }

    @Override
    protected void GameOver(){
        //TODO: Set game over behavior
        gameOverLabel.setVisible(true);
        gameOver = true;
        replayButton.setVisible(true);
        quitButton.setVisible(true);
        this.pack();
    }

    protected void PlayerOneWins(){
        titlePanel.setBackground(redTeamColor);
        scorePanelP1.setBackground(redTeamColor);
        scorePanelP2.setBackground(redTeamColor);
        footerPanel.setBackground(redTeamColor);
        footerButtonPanel.setBackground(redTeamColor);
        Player1WinsText.setVisible(true);
        //TODO: Player one wins
    }

    protected void PlayerTwoWins(){
        titlePanel.setBackground(blueTeamColor);
        scorePanelP1.setBackground(blueTeamColor);
        scorePanelP2.setBackground(blueTeamColor);
        footerPanel.setBackground(blueTeamColor);
        footerButtonPanel.setBackground(blueTeamColor);
        Player2WinsText.setVisible(true);
        //TODO: Player two wins
    }
}
