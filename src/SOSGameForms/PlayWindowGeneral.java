package SOSGameForms;

import java.awt.*;

public class PlayWindowGeneral extends PlayWindow{

    Color generalGameColor = new Color(164, 144, 32);

    public PlayWindowGeneral(int boardSize, int pMode) throws HeadlessException {
        super(boardSize, pMode);
        titlePanel.setBackground(generalGameColor);
        scorePanelP1.setBackground(generalGameColor);
        scorePanelP2.setBackground(generalGameColor);
        footerPanel.setBackground(generalGameColor);
        footerButtonPanel.setBackground(generalGameColor);
    }

    @Override
    protected void checkForWin(int P1, int P2){
        //In a general game the game will continue until all tiles are played.
        if(tilesRemaining < 1) {
            if (P1 > P2) {
                //TODO: P1 is victorious
                PlayerOneWins();
                GameOver();
            } else if (P2 > P1) {
                //TODO: P2 is victorious
                PlayerTwoWins();
                GameOver();
            } else {
                //TODO: Its a Draw
                GameOver();
            }
        }
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
