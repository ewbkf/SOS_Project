package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayWindow extends JFrame {

    //PANELS
    protected JPanel mainPanel;
    protected JPanel leftPanel;
    protected JPanel rightPanel;
    protected JPanel titlePanel;
    protected JPanel footerPanel;
    protected JPanel leftScorePanel;
    protected JPanel rightScorePanel;
    protected JPanel footerButtonPanel;
    protected JPanel playAreaPanel;


    //LABELS
    protected JLabel titleSOSIcon;
    protected JLabel Player1WinsText;
    protected JLabel Player2WinsText;
    protected JLabel CPU1WinsText;
    protected JLabel CPU2WinsText;
    protected JLabel itsADrawText;
    protected JLabel gameOverText;
    protected JLabel leftPlayerLabel;
    protected JLabel rightPlayerLabel;
    protected JLabel leftScoreText;
    protected JLabel leftScoreLabel;
    protected JLabel rightScoreText;
    protected JLabel rightScoreLabel;
    protected JLabel rightCompIcon;
    protected JLabel leftCompIcon;
    protected JLabel rightCompThinkingIcon;
    protected JLabel leftCompThinkingIcon;


    //BUTTONS:
    protected JButton replayButton;
    protected JButton newGameButton;
    protected JButton quitButton;
    protected JButton STARTGAMEButton;


    //RADIO BUTTONS
    protected JRadioButton leftRadioButtonS;
    protected JRadioButton leftRadioButtonO;
    protected JRadioButton rightRadioButtonS;
    protected JRadioButton rightRadioButtonO;


    //PLAYERS
    protected Player playerOne;
    protected Player playerTwo;


    //General Variables
    protected int tilesRemaining;
    protected int tileToPlayComp;
    protected char typeToPlayComp;
    protected char typeToPlayHuman;
    protected int boardSize;
    protected boolean isItPlayerOnesTurn = false; //is it player ones turn? if not, its player two/ the computers turn.
    protected boolean gameOver = false;


    //MISC
    protected TileLocation lastTilePlayed = new TileLocation();
    protected final Color redTeamColor = new Color(225, 112,109);
    protected final Color blueTeamColor = new Color(112,133,225);
    protected final Color offTurnColor = new Color(150,150,150);


    //UI Lists - Names should be self-explanatory.
    protected List<JComponent> UIElementsMain  = new ArrayList<>();
    protected List<JComponent> UIElementsLeft  = new ArrayList<>();
    protected List<JComponent> UIElementsRight = new ArrayList<>();

    public PlayWindow(int size, int pMode) throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_windowIcon.png")));
        this.setIconImage(frameIcon.getImage());
        BuildUIElementLists();
        NextPlayer();
        boardSize = size;
        tilesRemaining = (size * size);

        titleSOSIcon.setVisible(true);
        gameOverText.setVisible(false);
        replayButton.setVisible(false);
        quitButton.setVisible(false);
        Player1WinsText.setVisible(false);
        Player2WinsText.setVisible(false);
        CPU1WinsText.setVisible(false);
        CPU2WinsText.setVisible(false);
        STARTGAMEButton.setVisible(false);
        leftCompThinkingIcon.setVisible(false);
        rightCompThinkingIcon.setVisible(false);
        newGameButton.setVisible(false);
        itsADrawText.setVisible(false);

        playAreaPanel = new JPanel();
        playAreaPanel.setLayout(new GridLayout(size, size, 0, 0));
        playAreaPanel.setMaximumSize(new Dimension(64,64));
        mainPanel.add(playAreaPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        populateBoard(playAreaPanel, size);

        this.pack();

        switch (pMode) {
            case 1 -> {
                playerOne = new HumanPlayer();
                playerTwo = new HumanPlayer();
            }
            case 2 -> {
                playerOne = new HumanPlayer();
                playerTwo = new ComputerPlayer(boardSize);
            }
            case 3 -> {
                playerOne = new ComputerPlayer(boardSize);
                playerTwo = new ComputerPlayer(boardSize);
                STARTGAMEButton.setVisible(true);
            }
            default -> {
                System.out.println("ERROR: No player type selected");
                System.exit(2);
            }
        }

    }

    public void MakeMove(GameTile button){
        if(!gameOver){
            //Three cases:
            //If neither player is a robot:
            if(!playerOne.isRobot() && !playerTwo.isRobot()){
                //Human vs. Human
                if(isItPlayerOnesTurn){
                    PlayerOneMakeMove(button);
                }
                else{
                    PlayerTwoMakeMove(button);
                }
            }
            //If only one player is a robot:
            else if (!playerOne.isRobot() && playerTwo.isRobot()){
                if(isItPlayerOnesTurn){
                    PlayerOneMakeMove(button);
                    //After the Players move is made, the computer will get a turn.
                    while(!isItPlayerOnesTurn) {
                        ComputerTwoMakeMove();
                    }
                }
                //If it is not the players turn, nothing will happen.
            }
            //If both players are robots:
            else if (playerOne.isRobot() && playerTwo.isRobot()){
                while(tilesRemaining != 0){
                    ComputerPlayerMakesMove();
                }
            }
        }
        checkForWin();
    }

    public void PlayerOneMakeMove(GameTile button){
        typeToPlayHuman = ' ';

        if(leftRadioButtonS.isSelected()){
            typeToPlayHuman = 'S';
        }
        else if(leftRadioButtonO.isSelected()){
            typeToPlayHuman = 'O';
        }

        button.played();
        button.setState(typeToPlayHuman);

        if (typeToPlayHuman == 'S'){
            button.setIcon(new TileIcon().getWhiteS());

        }
        if (typeToPlayHuman == 'O'){
            button.setIcon(new TileIcon().getWhiteO());
        }

        checkForSOS(button.getCoords(), boardSize);
        this.lastTilePlayed = button.getCoords();
        tilesRemaining--;
    }

    public void PlayerTwoMakeMove(GameTile button){
        typeToPlayHuman = ' ';

        if(rightRadioButtonS.isSelected()){
            typeToPlayHuman = 'S';
        }
        else if(rightRadioButtonO.isSelected()){
            typeToPlayHuman = 'O';
        }

        button.played();
        button.setState(typeToPlayHuman);

        if (typeToPlayHuman == 'S'){
            button.setIcon(new TileIcon().getWhiteS());

        }
        if (typeToPlayHuman == 'O'){
            button.setIcon(new TileIcon().getWhiteO());
        }

        checkForSOS(button.getCoords(), boardSize);
        this.lastTilePlayed = button.getCoords();
        tilesRemaining--;
    }

    public void ComputerPlayerMakesMove(){
        if(isItPlayerOnesTurn){
            ComputerOneMakeMove();
        }
        else{
            ComputerTwoMakeMove();
        }
    }

    public void ComputerOneMakeMove(){
        tileToPlayComp = 0;
        typeToPlayComp = ' ';
        tileToPlayComp = ((ComputerPlayer)playerOne).FindNextTileToPlay(playAreaPanel);
        typeToPlayComp = ((ComputerPlayer)playerOne).DecideWhatToPlaceOnTile(playAreaPanel, tileToPlayComp);

        ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).played();
        ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).setState(typeToPlayComp);

        if(typeToPlayComp == 'S'){
            ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).setIcon(new TileIcon().getWhiteS());
        }
        else{
            ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).setIcon(new TileIcon().getWhiteO());
        }

        this.lastTilePlayed = ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).getCoords();
        checkForSOS(((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).getCoords(), boardSize);
        tilesRemaining--;
    }

    public void ComputerTwoMakeMove(){
        tileToPlayComp = 0;
        typeToPlayComp = ' ';
        tileToPlayComp = ((ComputerPlayer)playerTwo).FindNextTileToPlay(playAreaPanel);
        typeToPlayComp = ((ComputerPlayer)playerTwo).DecideWhatToPlaceOnTile(playAreaPanel, tileToPlayComp);

        ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).played();
        ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).setState(typeToPlayComp);

        if(typeToPlayComp == 'S'){
            ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).setIcon(new TileIcon().getWhiteS());
        }
        else{
            ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).setIcon(new TileIcon().getWhiteO());
        }

        this.lastTilePlayed = ((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).getCoords();
        checkForSOS(((GameTile)(playAreaPanel.getComponent(tileToPlayComp))).getCoords(), boardSize);
        tilesRemaining--;
    }

    public void NextPlayer(){
        isItPlayerOnesTurn = !isItPlayerOnesTurn;
        if (isItPlayerOnesTurn){
            leftPanel.setBackground(redTeamColor);
            leftRadioButtonO.setBackground(redTeamColor);
            leftRadioButtonS.setBackground(redTeamColor);

            rightPanel.setBackground(offTurnColor);
            rightRadioButtonO.setBackground(offTurnColor);
            rightRadioButtonS.setBackground(offTurnColor);
        }
        else{
            leftPanel.setBackground(offTurnColor);
            leftRadioButtonO.setBackground(offTurnColor);
            leftRadioButtonS.setBackground(offTurnColor);

            rightPanel.setBackground(blueTeamColor);
            rightRadioButtonO.setBackground(blueTeamColor);
            rightRadioButtonS.setBackground(blueTeamColor);
        }
    }

    public void populateBoard(JPanel area, int boardSize){
        for (int i = 1; i < boardSize + 1; i++){
            for (int j = 1; j < boardSize + 1; j++){
                GameTile button = new GameTile(new TileLocation(j, i), new TileIcon().getBLANK());
                button.addActionListener(e -> MakeMove(button));
                area.add(button);
            }
        }
    }

    public void checkForSOS(TileLocation last, int boardSize){
        //converting tile coordinates to an index so that we can access it in the JPanel GridLayout.
        int playedTileIndex = (boardSize * (last.getyCoord() - 1) + (last.getxCoord() - 1)); //Index of the last tile played.
        //Grabbing a copy of the last tile played.
        GameTile lastTilePlayed = (GameTile) playAreaPanel.getComponent(playedTileIndex);

        int firstTileIndex;
        int secondTileIndex;
        int boardMaxIndex = ((int)Math.pow(boardSize, 2) - 1);
        boolean SOSAwarded = false;

        if(lastTilePlayed.getState() == 'S'){
            //Above
            //Checking if the two tiles above exist.
            firstTileIndex = playedTileIndex - boardSize;
            secondTileIndex = firstTileIndex - boardSize;
            //as long as there are two tiles above to be checked, we will copy those into new objects.
            if((firstTileIndex > 0) && (secondTileIndex) > 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

            //topRight
            firstTileIndex = (playedTileIndex - boardSize + 1);
            secondTileIndex = (firstTileIndex - boardSize + 1);
            if(((firstTileIndex > 0) && (secondTileIndex > 0)) && ((firstTileIndex % boardSize != 0) && (secondTileIndex % boardSize != 0))){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

            //Right
            firstTileIndex  = (playedTileIndex + 1);
            secondTileIndex = (playedTileIndex + 2);
            //if the next item to the left's index is evenly divisible by the board size, we know that it is the first item in the next line.
            if(((firstTileIndex * secondTileIndex) % boardSize != 0) && (firstTileIndex <= boardMaxIndex) && (secondTileIndex <= boardMaxIndex)){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

            //bottomRight
            firstTileIndex = (playedTileIndex + boardSize + 1);
            secondTileIndex = (firstTileIndex + boardSize + 1);
            if((firstTileIndex * secondTileIndex) % boardSize != 0 && firstTileIndex <= boardMaxIndex && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

            //Bottom
            firstTileIndex = playedTileIndex + boardSize;
            secondTileIndex = firstTileIndex + boardSize;
            if(firstTileIndex < boardMaxIndex && secondTileIndex < boardMaxIndex){
                //as long as there are two tiles below to be checked, we will copy those into new objects.
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

            //bottomLeft
            firstTileIndex = (playedTileIndex + boardSize - 1);
            secondTileIndex = (firstTileIndex + boardSize - 1);
            if(firstTileIndex <= boardMaxIndex && secondTileIndex <= boardMaxIndex && (playedTileIndex * firstTileIndex) % boardSize != 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

            //Left
            firstTileIndex  = (playedTileIndex - 2);
            secondTileIndex = (playedTileIndex - 1);
            if((((secondTileIndex % boardSize) != 0  && (playedTileIndex % boardSize) != 0) && (firstTileIndex >= 0 && secondTileIndex > 0))){
                SOSAwarded = AwardSOS(playedTileIndex, secondTileIndex, firstTileIndex, true);
            }

            //topLeft
            firstTileIndex = (playedTileIndex - boardSize - 1);
            secondTileIndex = (firstTileIndex - boardSize - 1);
            if((((firstTileIndex % boardSize) != 0 && (playedTileIndex % boardSize) != 0) && firstTileIndex > 0 && secondTileIndex >= 0)){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, true);
            }

        }

        else if(lastTilePlayed.getState() == 'O'){
            //top -> bottom
            firstTileIndex = (playedTileIndex - boardSize);     //tile above
            secondTileIndex = (playedTileIndex + boardSize);    //tile below
            if(firstTileIndex >= 0 && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, false);
            }

            //left -> right
            firstTileIndex = (playedTileIndex - 1);     //left tile
            secondTileIndex = (playedTileIndex + 1);    //right tile
            if((playedTileIndex * secondTileIndex) % boardSize != 0 && firstTileIndex >=0 && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, false);
            }

            //topLeft -> bottomRight
            firstTileIndex = (playedTileIndex - boardSize - 1);
            secondTileIndex = (playedTileIndex + boardSize + 1);
            if(firstTileIndex >= 0 && ((playedTileIndex * secondTileIndex) % boardSize != 0) && secondTileIndex <= boardMaxIndex){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, false);
            }

            //bottomLeft -> topRight
            firstTileIndex = (playedTileIndex + boardSize - 1);
            secondTileIndex =  (playedTileIndex - boardSize + 1);
            if(firstTileIndex <= boardMaxIndex && ((playedTileIndex * secondTileIndex) % boardSize != 0) && secondTileIndex >= 0){
                SOSAwarded = AwardSOS(playedTileIndex, firstTileIndex, secondTileIndex, false);
            }
        }

        if(!SOSAwarded){
            NextPlayer();
        }
    }

    public boolean AwardSOS(int iP, int i1, int i2, boolean isS){
        Icon SIcon;
        Icon OIcon;
        GameTile firstTileToBeTested;
        GameTile secondTileToBeTested;
        firstTileToBeTested = (GameTile) playAreaPanel.getComponent(i1);
        secondTileToBeTested = (GameTile) playAreaPanel.getComponent(i2);

        if(isItPlayerOnesTurn){
            SIcon = new TileIcon().getRedS();
            OIcon = new TileIcon().getRedO();
        }
        else {
            SIcon = new TileIcon().getBlueS();
            OIcon = new TileIcon().getBlueO();
        }
        if(isS) {
            if (firstTileToBeTested.getState() == 'O' && secondTileToBeTested.getState() == 'S') {
                ((GameTile) playAreaPanel.getComponent(iP)).setIcon(SIcon);
                ((GameTile) playAreaPanel.getComponent(i1)).setIcon(OIcon);
                ((GameTile) playAreaPanel.getComponent(i2)).setIcon(SIcon);
                PointScored();
                return true;
            } else {
                return false;
            }
        }
        else{
            if (firstTileToBeTested.getState() == 'S' && secondTileToBeTested.getState() == 'S') {
                ((GameTile) playAreaPanel.getComponent(i1)).setIcon(SIcon);
                ((GameTile) playAreaPanel.getComponent(iP)).setIcon(OIcon);
                ((GameTile) playAreaPanel.getComponent(i2)).setIcon(SIcon);
                PointScored();
                return true;
            } else {
                return false;
            }
        }
    }

    public void PointScored(){
        //decide who the point goes to:
        if (isItPlayerOnesTurn){
            playerOne.score++;
            leftScoreText.setText(String.valueOf(playerOne.score));
        }
        else{
            playerTwo.score++;
            rightScoreText.setText(String.valueOf(playerTwo.score));
        }
    }

    private void BuildUIElementLists(){
        //Main list of all UI Elements
        UIElementsMain.add(titlePanel);
        UIElementsMain.add(leftScorePanel);
        UIElementsMain.add(rightScorePanel);
        UIElementsMain.add(footerButtonPanel);
        UIElementsMain.add(footerPanel);
        UIElementsMain.add(rightPanel);
        UIElementsMain.add(rightRadioButtonO);
        UIElementsMain.add(rightRadioButtonS);
        UIElementsMain.add(leftPanel);
        UIElementsMain.add(leftRadioButtonO);
        UIElementsMain.add(leftRadioButtonS);

        //List of Right Elements
        //UIElementsRight.add();

        //List of Left Elements

    }

    protected void checkForWin(){/*Overridden in child class*/}
    public void setP1RadioButtonS(boolean status) {leftRadioButtonS.setSelected(status);}
    public void setP1RadioButtonO(boolean status) {leftRadioButtonO.setSelected(status);}
    public void setP2RadioButtonS(boolean status) {rightRadioButtonS.setSelected(status);}
    public void setP2RadioButtonO(boolean status) {rightRadioButtonO.setSelected(status);}
}
