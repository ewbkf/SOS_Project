package SOSGameForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationWindow extends JFrame{
    static private JFrame frame;
    private JPanel ConfigurationWindow;
    private JPanel welcomeMenu;
    private JPanel innerMenu;
    private JTextField textField1;
    private JRadioButton simpleGameRadioButton;
    private JRadioButton generalGameRadioButton;
    private JRadioButton playerVPlayerRadioButton;
    private JRadioButton playerVComputerRadioButton;
    private JButton STARTButton;
    private JLabel boardSizeTip;
    private JLabel sizeSavedText;
    private JLabel invalidBoardSizeAlert;
    private JLabel gameSelectMenuTitle;
    private ButtonGroup playerSelectGroup;
    private ButtonGroup gameSelectGroup;

    private int boardSize = 0;
    private int gameType = 0;
    private int playerType = 0;

    public ConfigurationWindow() throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(getClass().getResource("Resources/SOS_windowIcon.png"));
        frame.setIconImage(frameIcon.getImage());
        sizeSavedText.setVisible(false);
        invalidBoardSizeAlert.setVisible(false);
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardSize == 0){
                    invalidBoardSizeAlert.setVisible(true);
                }
                else if (playerSelectGroup.getSelection() == null || gameSelectGroup.getSelection() == null){
                    invalidBoardSizeAlert.setForeground(Color.PINK);
                }
                else {
                    PlayWindow game = new PlayWindow(boardSize, gameType, playerType);
                    frame.setVisible(false);
                }
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField1.getText();
                if(Integer.parseInt(textFieldValue) < 3 || Integer.parseInt(textFieldValue) > 40){
                    boardSize = 0;
                    boardSizeTip.setForeground(Color.RED);
                    sizeSavedText.setVisible(false);
                }
                else {
                    boardSize = Integer.parseInt(textFieldValue);
                    boardSizeTip.setForeground(Color.BLACK);
                    sizeSavedText.setVisible(true);
                    sizeSavedText.setForeground(Color.GREEN);
                }
            }
        });
        simpleGameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simpleGameRadioButton.isSelected()){
                    gameType = 1;
                }
            }
        });
        generalGameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(generalGameRadioButton.isSelected()){
                    gameType = 2;
                }
            }
        });
        playerVPlayerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerVPlayerRadioButton.isSelected()){
                    playerType = 1;
                }
            }
        });
        playerVComputerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerVComputerRadioButton.isSelected()){
                    playerType = 2;
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("ConfigurationWindow");
        frame.setContentPane(new ConfigurationWindow().ConfigurationWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }
}
