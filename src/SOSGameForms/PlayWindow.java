package SOSGameForms;
import javax.swing.*;
import java.awt.*;

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


    public boolean turn = true; //true = P1, false = P2

    public PlayWindow(int boardSize, int gMode, int pMode) throws HeadlessException {
        P1Turn.setVisible(false);
        P2Turn.setVisible(false);
        Player player1 = new Player();
        if (pMode == 1) {
            Player player2 = new Player();
        }

        frame = new JFrame("PlayWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(boardSize*50+100,boardSize*50+25);

        JPanel playArea = new JPanel();
        mainPanel.add(playArea);
        playArea.setLayout(new GridLayout(boardSize, boardSize, 10, 10));

        for (int i = 1; i < boardSize + 1; i++){
            for (int j = 1; j < boardSize + 1; j++){
                GameTile button = new GameTile(Integer.toString(i) + ", " + Integer.toString(j));
                playArea.add(button);
            }
        }
    }
}
