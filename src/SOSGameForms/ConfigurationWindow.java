package SOSGameForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ConfigurationWindow extends JFrame{

    //Frames
    static private JFrame frame;


    //Panels
    private JPanel ConfigurationWindowPanel;
    private JPanel welcomeMenuPanel;
    private JPanel innerMenuPanel;


    //Text Fields
    private JTextField boardSizeTextField;


    //Labels
    private JLabel boardSizeTipText;
    private JLabel sizeSavedText;
    private JLabel invalidBoardSizeAlertText;
    private JLabel gameSelectMenuTitleText;
    private JLabel gameTypeAlertText;
    private JLabel playerTypeAlertText;


    //Buttons
    private ButtonGroup playerSelectButtonGroup;
    private ButtonGroup gameSelectButtonGroup;
    private JRadioButton computerVsComputerRadioButton;
    private JRadioButton simpleGameRadioButton;
    private JRadioButton generalGameRadioButton;
    private JRadioButton playerVsPlayerRadioButton;
    private JRadioButton playerVsComputerRadioButton;
    private JButton startButton;


    //General Variables
    private int boardSize = 0;
    private int playerType = 0;
    private final int maxBoardSize = 15;
    private final int minBoardSize = 3;


    //Constructor
    public ConfigurationWindow() throws HeadlessException {
        ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_windowIcon.png")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setIconImage(frameIcon.getImage());
        welcomeMenuPanel.setVisible(true);
        innerMenuPanel.setVisible(true);
        sizeSavedText.setVisible(false);
        invalidBoardSizeAlertText.setVisible(false);
        gameTypeAlertText.setVisible(false);
        playerTypeAlertText.setVisible(false);
        gameSelectMenuTitleText.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetPlayerType();

                if (boardSize == 0){
                    invalidBoardSizeAlertText.setVisible(true);
                }
                if (playerSelectButtonGroup.getSelection() == null){
                    playerTypeAlertText.setVisible(true);
                }
                if (gameSelectButtonGroup.getSelection() == null){
                    gameTypeAlertText.setVisible(true);
                }
                if (boardSize != 0 && gameSelectButtonGroup.getSelection() != null && playerSelectButtonGroup.getSelection() != null && simpleGameRadioButton.isSelected()) {
                    new PlayWindowSimple(boardSize, playerType);
                    frame.dispose();
                    //frame.setVisible(false);
                }
                if (boardSize != 0 && gameSelectButtonGroup.getSelection() != null && playerSelectButtonGroup.getSelection() != null && generalGameRadioButton.isSelected()) {
                    new PlayWindowGeneral(boardSize, playerType);
                    frame.dispose();
                    //frame.setVisible(false);
                }
            }
        });

        boardSizeTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = boardSizeTextField.getText();
                if(Integer.parseInt(textFieldValue) < minBoardSize || Integer.parseInt(textFieldValue) > maxBoardSize){
                    boardSize = 0;
                    boardSizeTipText.setForeground(Color.RED);
                    sizeSavedText.setVisible(false);
                }
                else {
                    boardSize = Integer.parseInt(textFieldValue);
                    boardSizeTipText.setForeground(Color.BLACK);
                    sizeSavedText.setVisible(true);
                    sizeSavedText.setForeground(Color.GREEN);
                }
            }
        });
    }

    private void GetPlayerType(){
        if(playerVsPlayerRadioButton.isSelected()){
            playerType = 1;
        }
        else if(playerVsComputerRadioButton.isSelected()){
            playerType = 2;
        }
        else if(computerVsComputerRadioButton.isSelected()){
            playerType = 3;
        }
        else{
            playerType = 0;
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("ConfigurationWindow");
        frame.setContentPane(new ConfigurationWindow().ConfigurationWindowPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }
}
