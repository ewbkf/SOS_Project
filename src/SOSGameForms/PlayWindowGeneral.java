package SOSGameForms;

import java.awt.*;

public class PlayWindowGeneral extends PlayWindow{
    public PlayWindowGeneral(int boardSize, int gMode, int pMode) throws HeadlessException {
        super(boardSize, gMode, pMode);
    }

    private void checkForWin(int P1, int P2){
        if (gameOver && P1 > P2){
            //P1 is victorious
        }
        else if (gameOver && P2 > P1){
            //P2 is victorious
        }
        else {
            //gameContinues
        }
    }
}
