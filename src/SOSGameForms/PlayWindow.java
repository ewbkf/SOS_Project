package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PlayWindow extends JFrame {
    //static private JFrame frame;
    private JPanel mainPanel;
    private JRadioButton P1RadioButtonS;
    private JRadioButton P1RadioButtonO;
    private JRadioButton P2RadioButtonS;
    private JRadioButton P2RadioButtonO;
    private JLabel titleSOS;
    private JLabel gameOverLabel;
    private JPanel P1Panel;
    private JPanel P2Panel;
    private JPanel titlePanel;
    private JPanel footerPanel;
    private JButton replayButton;
    private JButton quitButton;
    private JLabel P1ScoreText;
    private JLabel P2ScoreText;

    private int tilesRemaining;
    private int P1Score = 0, P2Score = 0, CPUScore = 0;

    private int gameType = 0;
    private int boardSize;
    public TileLocation lastTilePlayed = new TileLocation();

    private JPanel playArea;

    private final Color redTeamColor = new Color(225, 112,109);
    private final Color blueTeamColor = new Color(112,133,225);
    private final Color offTurnColor = new Color(150,150,150);
    protected boolean isPlayer1Turn = false; //is it player ones turn? if not, its player two/ the computers turn.
    protected boolean gameOver = false;

    public PlayWindow(int size, int gMode, int pMode) throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_windowIcon.png")));
        this.setIconImage(frameIcon.getImage());
        boardSize = size;
        NextPlayer();
        tilesRemaining = (size * size);
        gameOverLabel.setVisible(false);
        replayButton.setVisible(false);
        quitButton.setVisible(false);
        Player player1 = new Player();

        playArea = new JPanel();
        playArea.setLayout(new GridLayout(size, size, 0, 0));
        playArea.setMaximumSize(new Dimension(64,64));
        mainPanel.add(playArea);

        if (pMode == 1) {
            Player player2 = new Player();
        }

        //frame = new JFrame("PlayWindow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        populateBoard(playArea, size);



        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               PlayWindow.this.dispose();
            }
        });
        this.pack();
    }

    public void MakeMove(GameTile button){
        if(!gameOver){
            if(isPlayer1Turn){ P1MakeMove(button); }
            else{ P2MakeMove(button); }
        }
        //else: if the game is over, nothing happens.
    }

    public void P1MakeMove(GameTile button){
        if (P1RadioButtonS.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteS());
            button.played();
            button.setState('S');
            this.lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize);
            tilesRemaining--;
        }
        if (P1RadioButtonO.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteO());
            button.played();
            button.setState('O');
            this.lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize);
            tilesRemaining--;
        }
        if(tilesRemaining == 0){
            GameOver();
        }
    }

    public void P2MakeMove(GameTile button){
        if (P2RadioButtonS.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteS());
            button.played();
            button.setState('S');
            this.lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize);
            tilesRemaining--;
        }
        if (P2RadioButtonO.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteO());
            button.played();
            button.setState('O');
            this.lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize);
            tilesRemaining--;
        }
        if(tilesRemaining == 0){
            GameOver();
        }
    }

    public void GameOver(){
        //TODO: Set game over behavior
        gameOverLabel.setVisible(true);
        gameOver = true;
        replayButton.setVisible(true);
        quitButton.setVisible(true);
        this.pack();
    }

    public void NextPlayer(){
        isPlayer1Turn = !isPlayer1Turn;
        if (isPlayer1Turn){
            P1Panel.setBackground(redTeamColor);
            P1RadioButtonO.setBackground(redTeamColor);
            P1RadioButtonS.setBackground(redTeamColor);
            P2Panel.setBackground(offTurnColor);
            P2RadioButtonO.setBackground(offTurnColor);
            P2RadioButtonS.setBackground(offTurnColor);
        }
        else{
            P1Panel.setBackground(offTurnColor);
            P1RadioButtonO.setBackground(offTurnColor);
            P1RadioButtonS.setBackground(offTurnColor);
            P2Panel.setBackground(blueTeamColor);
            P2RadioButtonO.setBackground(blueTeamColor);
            P2RadioButtonS.setBackground(blueTeamColor);
        }
    }

    public void populateBoard(JPanel area, int boardSize){
        for (int i = 1; i < boardSize + 1; i++){
            for (int j = 1; j < boardSize + 1; j++){
                GameTile button = new GameTile(new TileLocation(j, i), new TileIcon().getBLANK());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MakeMove(button);
                    }
                });
                area.add(button);
            }
        }
    }

    public void checkForSOS(TileLocation last, int boardSize){
        //converting tile coordinates to an index so that we can access it in the JPanel GridLayout.
        int playedTileIndex = (boardSize * (last.getyCoord() - 1) + (last.getxCoord() - 1)); //Index of the last tile played.
        //Grabbing a copy of the last tile played.
        GameTile lastTilePlayed = (GameTile)playArea.getComponent(playedTileIndex);

        int firstTileIndex;
        int secondTileIndex;
        int boardMaxIndex = ((int)Math.pow(boardSize, 2) - 1);
        boolean SOSAwarded = false;
        boolean isS;

        if(lastTilePlayed.getState() == 'S'){
            isS = true;
            //Above
            //Checking if the two tiles above exist.
            firstTileIndex = playedTileIndex - boardSize;
            secondTileIndex = firstTileIndex - boardSize;
            //as long as there are two tiles above to be checked, we will copy those into new objects.
            if((firstTileIndex > 0) && (secondTileIndex) > 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //topRight
            firstTileIndex = (playedTileIndex - boardSize + 1);
            secondTileIndex = (firstTileIndex - boardSize + 1);
            if(((firstTileIndex > 0) && (secondTileIndex > 0)) && ((firstTileIndex % boardSize != 0) && (secondTileIndex % boardSize != 0))){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //Right
            firstTileIndex  = (playedTileIndex + 1);
            secondTileIndex = (playedTileIndex + 2);
            //if the next item to the left's index is evenly divisible by the board size, we know that it is the first item in the next line.
            if(((firstTileIndex * secondTileIndex) % boardSize != 0) && (firstTileIndex <= boardMaxIndex) && (secondTileIndex <= boardMaxIndex)){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //bottomRight
            firstTileIndex = (playedTileIndex + boardSize + 1);
            secondTileIndex = (firstTileIndex + boardSize + 1);
            if((firstTileIndex * secondTileIndex) % boardSize != 0 && firstTileIndex <= boardMaxIndex && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //Bottom
            firstTileIndex = playedTileIndex + boardSize;
            secondTileIndex = firstTileIndex + boardSize;
            if(firstTileIndex < boardMaxIndex && secondTileIndex < boardMaxIndex){
                //as long as there are two tiles below to be checked, we will copy those into new objects.
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //bottomLeft
            firstTileIndex = (playedTileIndex + boardSize - 1);
            secondTileIndex = (firstTileIndex + boardSize - 1);
            if(firstTileIndex <= boardMaxIndex && secondTileIndex <= boardMaxIndex && (playedTileIndex * firstTileIndex) % boardSize != 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //Left
            firstTileIndex  = (playedTileIndex - 1);
            secondTileIndex = (playedTileIndex - 2);
            if(((firstTileIndex * playedTileIndex) % boardSize != 0) && firstTileIndex > 0 && secondTileIndex >= 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //topLeft
            firstTileIndex = (playedTileIndex - boardSize - 1);
            secondTileIndex = (firstTileIndex - boardSize - 1);
            if(((firstTileIndex * playedTileIndex) % boardSize != 0) && firstTileIndex > 0 && secondTileIndex >= 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

        }

        else if(lastTilePlayed.getState() == 'O'){
            isS = false;

            //top -> bottom
            firstTileIndex = (playedTileIndex - boardSize);     //tile above
            secondTileIndex = (playedTileIndex + boardSize);    //tile below
            if(firstTileIndex >= 0 && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //left -> right
            firstTileIndex = (playedTileIndex - 1);     //left tile
            secondTileIndex = (playedTileIndex + 1);    //right tile
            if((playedTileIndex * secondTileIndex) % boardSize != 0 && firstTileIndex >=0 && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //topLeft -> bottomRight
            firstTileIndex = (playedTileIndex - boardSize - 1);
            secondTileIndex = (playedTileIndex + boardSize + 1);
            if(firstTileIndex >= 0 && ((playedTileIndex * secondTileIndex) % boardSize != 0) && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }

            //bottomLeft -> topRight
            firstTileIndex = (playedTileIndex + boardSize - 1);
            secondTileIndex =  (playedTileIndex - boardSize + 1);
            if(firstTileIndex <= boardMaxIndex && ((playedTileIndex * secondTileIndex) % boardSize != 0) && secondTileIndex >= 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, isS);
            }
        }
        else if (lastTilePlayed.getState() == ' '){
            //TODO: throw an exception as the tile was not played correctly.
        }
        else {
            //TODO: something I guess.
        }
        if(!SOSAwarded){
            NextPlayer();
        }
    }

    public void setP1RadioButtonS(boolean status) {
        P1RadioButtonS.setSelected(status);
    }

    public void setP1RadioButtonO(boolean status) {
        P1RadioButtonO.setSelected(status);
    }

    public void setP2RadioButtonS(boolean status) {
        P2RadioButtonS.setSelected(status);
    }

    public void setP2RadioButtonO(boolean status) {
        P2RadioButtonO.setSelected(status);
    }

    public boolean AwardSOS(int iP, int i1, int i2, boolean isS){
        GameTile firstTileToBeTested;
        GameTile secondTileToBeTested;
        firstTileToBeTested = (GameTile)playArea.getComponent(i1);
        secondTileToBeTested = (GameTile)playArea.getComponent(i2);

        Icon SIcon;
        Icon OIcon;
        if(isPlayer1Turn){
            SIcon = new TileIcon().getRedS();
            OIcon = new TileIcon().getRedO();
        }
        else {
            SIcon = new TileIcon().getBlueS();
            OIcon = new TileIcon().getBlueO();
        }
        if(isS) {
            if (firstTileToBeTested.getState() == 'O' && secondTileToBeTested.getState() == 'S') {
                ((GameTile) playArea.getComponent(iP)).setIcon(SIcon);
                ((GameTile) playArea.getComponent(i1)).setIcon(OIcon);
                ((GameTile) playArea.getComponent(i2)).setIcon(SIcon);
                PointScored();
                return true;
            } else {
                return false;
            }
        }
        else{
            if (firstTileToBeTested.getState() == 'S' && secondTileToBeTested.getState() == 'S') {
                ((GameTile) playArea.getComponent(i1)).setIcon(SIcon);
                ((GameTile) playArea.getComponent(iP)).setIcon(OIcon);
                ((GameTile) playArea.getComponent(i2)).setIcon(SIcon);
                PointScored();
                return true;
            } else {
                return false;
            }
        }
    }

    public void PointScored(){
        //decide who the point goes to:
        if (isPlayer1Turn){
            P1Score++;
            P1ScoreText.setText(String.valueOf(P1Score));
        }
        else{
            P2Score++;
            P2ScoreText.setText(String.valueOf(P2Score));
        }
    }
}
