package SOSGameForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayWindow extends JFrame {

    static private JFrame frame;
    private JPanel mainPanel;
    private JRadioButton sRadioButton;
    private JRadioButton oRadioButton;
    private JRadioButton sRadioButton1;
    private JRadioButton oRadioButton2;
    private JLabel P1Turn;
    private JLabel P2Turn;
    private JLabel titleSOS;
    private JLabel TestButton;

    boolean isPlayer1Turn; //is it player ones turn? if not, its player two/ the computers turn.

    private int gameType = 0;

    int P1Score = 0, P2Score = 0, CPUScore = 0;

    public PlayWindow(int boardSize, int gMode, int pMode) throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(getClass().getResource("Resources/SOS_windowIcon.png"));
        this.setIconImage(frameIcon.getImage());
        P1Turn.setVisible(false);
        P2Turn.setVisible(false);
        Player player1 = new Player();
        if (pMode == 1) {
            Player player2 = new Player();
        }
        Icon SOSBlank = new ImageIcon(getClass().getResource("Resources/SOS_Button_Blank.png"));

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
                GameTile button = new GameTile(Integer.toString(i) + ", " + Integer.toString(j), new TileIcon().getBLANK());
                button.setIcon(SOSBlank);
                playArea.add(button);
            }
        }
        this.pack();
        sRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(PlayWindow.frame.)
            }
        });
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        isPlayer1Turn = player1Turn;
    }
}
