package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTile extends JButton {
    public GameTile(String buttonText){
        JButton gameTile = new JButton(" ");
        gameTile.setToolTipText(buttonText);
        gameTile.setMinimumSize(new Dimension(50,50));
        gameTile.setMaximumSize(new Dimension(50,50));

    }
}
