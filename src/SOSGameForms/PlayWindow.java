package SOSGameForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PlayWindow extends JFrame {

    static private JFrame frame;
    private JPanel mainPanel;
    private JRadioButton P1RadioButtonS;
    private JRadioButton P1RadioButtonO;
    private JRadioButton P2RadioButtonS;
    private JRadioButton P2RadioButtonO;
    private JLabel P1Turn;
    private JLabel P2Turn;
    private JLabel titleSOS;
    private JLabel gameOverLabel;
    private JPanel P1Panel;
    private JPanel P2Panel;
    private JPanel titlePanel;
    private JPanel footerPanel;

    private JLabel TestButton;

    private int tilesRemaining;

    boolean isPlayer1Turn = true; //is it player ones turn? if not, its player two/ the computers turn.
    boolean gameOver = false;

    private int gameType = 0;

    int P1Score = 0, P2Score = 0, CPUScore = 0;

    public PlayWindow(int boardSize, int gMode, int pMode) throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_windowIcon.png")));
        this.setIconImage(frameIcon.getImage());

        tilesRemaining = (boardSize * boardSize);
        gameOverLabel.setVisible(false);
        P1Turn.setVisible(false);
        P2Turn.setVisible(false);
        Player player1 = new Player();

        if (pMode == 1) {
            Player player2 = new Player();
        }

        //TODO: see if the below code is even necessary.
        //Icon SOSBlank = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_Blank.png")));
        //TestButton.setIcon(SOSBlank);

        frame = new JFrame("PlayWindow");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JPanel playArea = new JPanel();
        playArea.setLayout(new GridLayout(boardSize, boardSize, 0, 0));
        playArea.setMaximumSize(new Dimension(64,64));
        mainPanel.add(playArea);

        for (int i = 1; i < boardSize + 1; i++){
            for (int j = 1; j < boardSize + 1; j++){
                GameTile button = new GameTile(new TileLocation(i, j), new TileIcon().getBLANK());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GeneralTurn(button);
                    }
                });
                playArea.add(button);
            }
        }
        this.pack();
    }

    private void simpleTurn(){
        //TODO: Simple game turn
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

    private void WhoGoesFirst(){

    }

    private void P1MakeMove(GameTile button){
        if (P1RadioButtonS.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteS());
            button.played();
            NextPlayer();
            tilesRemaining--;
        }
        if (P1RadioButtonO.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteO());
            button.played();
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
            NextPlayer();
            tilesRemaining--;
        }
        if (P2RadioButtonO.isSelected() && button.isPlayable()){
            button.setIcon(new TileIcon().getWhiteO());
            button.played();
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
        this.pack();
    }

    public boolean IsPlayer1Turn() {
        return isPlayer1Turn;
    }

    private void NextPlayer(){
        isPlayer1Turn = !isPlayer1Turn;
    }

    public void SetPlayer1Turn(boolean player1Turn) {
        isPlayer1Turn = player1Turn;
    }
}
