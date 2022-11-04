package SOSGameForms;

import javax.swing.*;
import java.util.Objects;

public class TileIcon {
    private final Icon redS;

    private final Icon redO;

    private final Icon blueS;

    private final Icon blueO;

    private final Icon BLANK;

    private final Icon whiteS;

    private final Icon whiteO;

    public TileIcon() {
        this.redS = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_RED_S.png")));
        this.redO = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_RED_O.png")));
        this.blueS = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_BLUE_S.png")));
        this.blueO = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_BLUE_O.png")));
        this.BLANK = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_Blank.png")));
        this.whiteS = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_WHITE_S.png")));
        this.whiteO = new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/SOS_Button_WHITE_O.png")));
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

    public Icon getWhiteS() {
        return whiteS;
    }

    public Icon getWhiteO() {
        return whiteO;
    }
}
