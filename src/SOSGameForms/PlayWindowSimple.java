package SOSGameForms;

import javax.swing.*;
import java.awt.*;

public class PlayWindowSimple extends JFrame {

    static private JFrame frame;
    private JPanel panel1;
    private JRadioButton sRadioButton;
    private JRadioButton oRadioButton;
    private JRadioButton sRadioButton1;
    private JRadioButton oRadioButton2;
    private JLabel P1Turn;
    private JLabel P2Turn;

    public boolean turn = true; //true = P1, false = P2

    public PlayWindowSimple(int boardSize) throws HeadlessException {
        frame = new JFrame("PlayWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(500,500);

    }
}
