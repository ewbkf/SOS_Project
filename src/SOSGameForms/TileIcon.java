package SOSGameForms;

import javax.swing.*;

public class TileIcon {
    private Icon redS;

    private Icon redO;

    private Icon blueS;

    private Icon blueO;

    private Icon BLANK;

    public TileIcon() {
        this.redS = new ImageIcon(getClass().getResource("Resources/SOS_Button_RED_S.png"));
        this.redO = new ImageIcon(getClass().getResource("Resources/SOS_Button_RED_O.png"));
        this.blueS = new ImageIcon(getClass().getResource("Resources/SOS_Button_BLUE_S.png"));
        this.blueO = new ImageIcon(getClass().getResource("Resources/SOS_Button_BLUE_O.png"));
        this.BLANK = new ImageIcon(getClass().getResource("Resources/SOS_Button_Blank.png"));
    }

    public Icon getRedS() {
        return redS;
    }

    public Icon getRedO() {
        return redO;
    }

    public Icon getBlueS() {
        return blueS;
    }

    public Icon getBlueO() {
        return blueO;
    }

    public Icon getBLANK() {
        return BLANK;
    }
}
