package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayWindowSimple extends PlayWindow{

    Color simpleGameColor = new Color(68, 68, 68, 255);

    public PlayWindowSimple(int boardSize, int pMode) throws HeadlessException {
        super(boardSize, pMode);
        gameRecord.AddLineToGameLog("New game started: \nGame Mode: Simple Game \nBoard Size: " + Integer.toString(boardSize).toString() + "\n \n");
        titlePanel.setBackground(simpleGameColor);
        leftScorePanel.setBackground(simpleGameColor);
        rightScorePanel.setBackground(simpleGameColor);
        footerPanel.setBackground(simpleGameColor);
        footerButtonPanel.setBackground(simpleGameColor);
        leftScoreText.setVisible(false);
        rightScoreText.setVisible(false);
        leftScoreLabel.setVisible(false);
        rightScoreLabel.setVisible(false);

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWindowSimpleReplay(boardSize, pMode, moveVector);
                PlayWindowSimple.this.dispose();
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfigurationWindow();
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
    protected void checkForWin(){
        //In a simple game the game will end as soon as one player scores a point.
        if (playerOne.score > 0){
            //TODO: P1 is victorious
            PlayerOneWins();
        }
        else if (playerTwo.score > 0){
            //TODO: P2 is victorious
            PlayerTwoWins();
        }
        else if(tilesRemaining < 1){
            SetColorsToWinningPlayer(Color.DARK_GRAY);
            itsADrawText.setVisible(true);
            GameOver();
        }
        //if none of the above, nothing happens and the game continues.
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
        gameRecord.AddLineToGameLog("Red Team Wins");
        GameOver();
    }

    protected void PlayerTwoWins(){
        SetColorsToWinningPlayer(blueTeamColor);
        Player2WinsText.setVisible(true);
        gameRecord.AddLineToGameLog("Blue Team Wins");
        GameOver();
    }

    private void SetColorsToWinningPlayer(Color winingColor){
        for (JComponent components : UIElementsMain) components.setBackground(winingColor);
    }
}
