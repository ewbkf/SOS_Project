package SOSGameForms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayWindowSimple extends PlayWindow{

    Color simpleGameColor = new Color(0, 255, 255, 255);

    public PlayWindowSimple(int boardSize, int pMode) throws HeadlessException {
        super(boardSize, pMode);
        titlePanel.setBackground(simpleGameColor);
        scorePanelP1.setBackground(simpleGameColor);
        scorePanelP2.setBackground(simpleGameColor);
        footerPanel.setBackground(simpleGameColor);
        footerButtonPanel.setBackground(simpleGameColor);
        P1ScoreText.setVisible(false);
        P2ScoreText.setVisible(false);
        PLayer1ScoreText.setVisible(false);
        Player2ScoreText.setVisible(false);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayWindowSimple newGame = new PlayWindowSimple(boardSize, 0);
                PlayWindowSimple.this.dispose();
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurationWindow newGame = new ConfigurationWindow();
                PlayWindowSimple.this.dispose();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
