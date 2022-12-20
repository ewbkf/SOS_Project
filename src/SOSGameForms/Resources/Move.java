package SOSGameForms.Resources;

import SOSGameForms.TileLocation;

public class Move {
    char moveType = ' ';
    //' ' - n/a
    //'S' - placed an S
    //'O' -  placed an O

    boolean playerOneTurn;
    //true - Red Team;
    //false - Blue Team;

    int moveIndex;
    //Where the move happened. If not applicable this will not be used. Stored as an index in the Grid array.

    TileLocation coordinates;

    public Move(char t, boolean p, int l){
        moveType = t;
        playerOneTurn = p;
        moveIndex = l;
    }


    public char getMoveType() {
        return moveType;
    }

    public boolean getPlayerOneTurn() {
        return playerOneTurn;

    }
    public int getMoveIndex() {
        return moveIndex;
    }
}