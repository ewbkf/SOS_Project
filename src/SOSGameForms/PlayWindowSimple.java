package SOSGameForms;

import java.awt.*;

public class PlayWindowSimple extends PlayWindow{
    public PlayWindowSimple(int size, int gMode, int pMode) throws HeadlessException {
        super(size, gMode, pMode);
    }

    private void checkForWin(int P1, int P2){
        if (gameOver && P1 > 0){
            //P1 is victorious
        }
        else if (gameOver && P2 > 0){
            //P2 is victorious
        }
        else {
            //gameContinues
        }
    }
}
