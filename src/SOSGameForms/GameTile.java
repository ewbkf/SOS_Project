package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTile extends JButton {
    static private JButton tile = new JButton();

    private TileLocation coords = new TileLocation();


    public GameTile(String buttonText, Icon icon){
        //initializing the button
        this.setToolTipText(buttonText);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(64,64));
        this.setBorderPainted(true);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public TileLocation getCoords() {
        return coords;
    }

    public void setCoords(int x, int y) {
        this.coords = new TileLocation(x, y);
    }
}
