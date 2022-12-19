package SOSGameForms;

import SOSGameForms.Resources.Move;

import java.awt.*;
import java.util.Vector;

public class PlayWindowSimpleReplay extends PlayWindowSimple {

    Color replayGameColor = new Color(104, 255, 86, 255);

    public PlayWindowSimpleReplay(int size, int pMode, Vector<Move> mList) throws HeadlessException {
        super(size, pMode);


    }
}
