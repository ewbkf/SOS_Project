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

    private Icon SIcon;
    private Icon OIcon;
    private int tilesRemaining;
    private int P1Score = 0, P2Score = 0, CPUScore = 0;
    private int gameType = 0;
    private int boardSize;
    private TileLocation lastTilePlayed = new TileLocation();

    private JPanel playArea;

    private final Color redTeamColor = new Color(225, 112,109);
    private final Color blueTeamColor = new Color(112,133,225);
    private final Color offTurnColor = new Color(150,150,150);
    boolean isPlayer1Turn = false; //is it player ones turn? if not, its player two/ the computers turn.
    boolean gameOver = false;

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

    private void GeneralTurn(GameTile button){
        if(!gameOver){
            if(isPlayer1Turn){
                P1MakeMove(button);
            }
            else{
                P2MakeMove(button);
            }
        }
        //else: if the game is over, nothing happens.
    }

    private void P1MakeMove(GameTile button){
        if (P1RadioButtonS.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteS());
            button.played();
            button.setState('S');
            lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize, isPlayer1Turn);
            NextPlayer();
            tilesRemaining--;

        }
        if (P1RadioButtonO.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteO());
            button.played();
            button.setState('O');
            lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize, isPlayer1Turn);
            NextPlayer();
            tilesRemaining--;
        }
        if(tilesRemaining == 0){
            GameOver();
        }
    }

    private void P2MakeMove(GameTile button){
        if (P2RadioButtonS.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteS());
            button.played();
            button.setState('S');
            lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize, isPlayer1Turn);
            NextPlayer();
            tilesRemaining--;
        }
        if (P2RadioButtonO.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteO());
            button.played();
            button.setState('O');
            lastTilePlayed = button.getCoords();
            checkForSOS(button.getCoords(), boardSize, isPlayer1Turn);
            NextPlayer();
            tilesRemaining--;
        }
        if(tilesRemaining == 0){
            GameOver();
        }
    }

    private void GameOver(){
        //TODO: Set game over behavior
        gameOverLabel.setVisible(true);
        gameOver = true;
        replayButton.setVisible(true);
        quitButton.setVisible(true);
        this.pack();
    }

    public boolean IsPlayer1Turn() {
        return isPlayer1Turn;
    }

    private void NextPlayer(){
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

    private void populateBoard(JPanel area, int boardSize){
        for (int i = 1; i < boardSize + 1; i++){
            for (int j = 1; j < boardSize + 1; j++){
                GameTile button = new GameTile(new TileLocation(j, i), new TileIcon().getBLANK());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GeneralTurn(button);
                    }
                });
                area.add(button);
            }
        }
    }

    private void checkForSOS(TileLocation last, int boardSize, boolean P1Turn){
        GameTile tile = (GameTile)playArea.getComponentAt(last.getxCoord(), last.getyCoord());
        if(P1Turn){
            SIcon = new TileIcon().getRedS();
            OIcon = new TileIcon().getRedO();
        }
        else {
            SIcon = new TileIcon().getBlueS();
            OIcon = new TileIcon().getBlueO();
        }
        if(tile.getState() == 'S'){
            //Above
            if ((last.getyCoord() - 2) > 0) {
                GameTile above = (GameTile)((playArea.getComponentAt(last.getxCoord(), last.getyCoord() - 1)));
                if (above.getState() == 'O'){
                    GameTile nextAbove = (GameTile)((playArea.getComponentAt(last.getxCoord(), last.getyCoord() - 2)));
                    if (nextAbove.getState() == 'S'){
                        //Success!
                        ((GameTile) playArea.getComponentAt(last.getxCoord(), last.getyCoord())).setIcon(SIcon);
                        ((GameTile) playArea.getComponentAt(last.getxCoord(), last.getyCoord()-1)).setIcon(OIcon);
                        ((GameTile) playArea.getComponentAt(last.getxCoord(), last.getyCoord()-2)).setIcon(SIcon);
                        if (isPlayer1Turn){P1Score++;}
                        else {P2Score++;}
                    }
                }
            }

            //topRight

            //Right

            //bottomRight

            //Bottom

            //bottomLeft

            //Left

            //topLeft

        }
        else if(tile.getState() == 'O'){

        }
        else if (tile.getState() == ' '){
            //TODO: throw an exception as the tile was not played correctly.
        }
        else {
            //TODO: if for some reason this value is not S or O then there is an issue.
        }
    }
}
