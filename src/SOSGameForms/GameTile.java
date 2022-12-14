package SOSGameForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTile extends JButton {
    static private JButton tile = new JButton();

    private char state = ' ';
    boolean playable = true;
    private TileLocation coords = new TileLocation();

    public GameTile(TileLocation location, Icon icon){
        //initializing the button
        this.setCoords(location);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(64,64));
        this.setBorderPainted(true);
        this.setIcon(icon);
        int xCoordinate = location.getxCoord();
        int yCoordinate = location.getyCoord();

        String spot = String.valueOf(xCoordinate) + String.valueOf(yCoordinate);

        this.setToolTipText(spot);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public TileLocation getCoords() {
        return coords;
    }

    public int getXCoord() {
        return coords.getxCoord();
    }

    public int getYCoord() {
        return coords.getyCoord();
    }

    public void setCoords(TileLocation location){
        this.coords = location;
    }
    public void setCoords(int x, int y) {
        this.coords = new TileLocation(x, y);
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void played() {
        this.playable = false;
    }
}
