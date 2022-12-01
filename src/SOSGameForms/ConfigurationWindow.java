package SOSGameForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

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
    private JLabel gameTypeAlert;
    private JLabel playerTypeAlert;
    private JRadioButton computerVComputerRadioButton;
    private ButtonGroup playerSelectGroup;
    private ButtonGroup gameSelectGroup;
    private int boardSize = 0;
    private int playerType = 0;
    private final int maxBoardSize = 15;
    private final int minBoardSize = 3;

    public ConfigurationWindow() throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_windowIcon.png")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setIconImage(frameIcon.getImage());
        sizeSavedText.setVisible(false);
        invalidBoardSizeAlert.setVisible(false);
        gameTypeAlert.setVisible(false);
        playerTypeAlert.setVisible(false);

        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardSize == 0){
                    invalidBoardSizeAlert.setVisible(true);
                }
                if (playerSelectGroup.getSelection() == null){
                    playerTypeAlert.setVisible(true);
                }
                if (gameSelectGroup.getSelection() == null){
                    gameTypeAlert.setVisible(true);
                }
                if (boardSize != 0 && gameSelectGroup.getSelection() != null && playerSelectGroup.getSelection() != null && simpleGameRadioButton.isSelected()) {
                    PlayWindowSimple game = new PlayWindowSimple(boardSize, playerType);
                    frame.dispose();
                    //frame.setVisible(false);
                }
                if (boardSize != 0 && gameSelectGroup.getSelection() != null && playerSelectGroup.getSelection() != null && generalGameRadioButton.isSelected()) {
                    PlayWindowGeneral game = new PlayWindowGeneral(boardSize, playerType);
                    frame.dispose();
                    //frame.setVisible(false);
                }
            }
        });

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = textField1.getText();
                if(Integer.parseInt(textFieldValue) < minBoardSize || Integer.parseInt(textFieldValue) > maxBoardSize){
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

        computerVComputerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(computerVComputerRadioButton.isSelected()){
                    playerType = 3;
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
