package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayWindowGeneral extends PlayWindow{

    Color generalGameColor = new Color(255, 255, 255);

    public PlayWindowGeneral(int boardSize, int pMode) throws HeadlessException {
        super(boardSize, pMode);
        gameRecord.AddLineToGameLog("New game started: \nGame Mode: General Game \nBoard Size: " + Integer.toString(boardSize).toString() + "\n \n");
        titlePanel.setBackground(generalGameColor);
        leftScorePanel.setBackground(generalGameColor);
        rightScorePanel.setBackground(generalGameColor);
        footerPanel.setBackground(generalGameColor);
        footerButtonPanel.setBackground(generalGameColor);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWindowGeneral(boardSize, pMode);
                PlayWindowGeneral.this.dispose();
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfigurationWindow();
                PlayWindowGeneral.this.dispose();
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
    protected void checkForWin(){
        //In a general game the game will continue until all tiles are played.
        if(tilesRemaining == 0) {
            if (playerOne.score > playerTwo.score) {
                //TODO: P1 is victorious
                PlayerOneWins();
            } else if (playerTwo.score > playerOne.score) {
                //TODO: P2 is victorious
                PlayerTwoWins();
            } else {
                //TODO: Its a Draw
                SetColorsToWinningPlayer(Color.DARK_GRAY);
                itsADrawText.setVisible(true);
                GameOver();
            }
        }
    }

    protected void GameOver(){
        //TODO: Set game over behavior
        gameOverText.setVisible(true);
        gameOver = true;
        replayButton.setVisible(true);
        newGameButton.setVisible(true);
        quitButton.setVisible(true);
        this.pack();
    }

    protected void PlayerOneWins(){
        SetColorsToWinningPlayer(redTeamColor);
        Player1WinsText.setVisible(true);
        GameOver();
    }

    protected void PlayerTwoWins(){
        SetColorsToWinningPlayer(blueTeamColor);
        Player2WinsText.setVisible(true);
        GameOver();
    }

    private void SetColorsToWinningPlayer(Color winingColor){
        for (JComponent components : UIElementsMain) components.setBackground(winingColor);
    }
}
