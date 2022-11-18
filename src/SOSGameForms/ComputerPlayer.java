package SOSGameForms;

import org.w3c.dom.html.HTMLImageElement;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ComputerPlayer extends Player{
    protected int boardSize;
    protected int maxIndex;
    private final long timestamp = System.currentTimeMillis() / 1000;   //gets UTC time to seed the (pseudo)random number generator.
    private final Random randNum = new Random(timestamp);
    protected int numberOfS;
    protected int numberOfO;

    public ComputerPlayer(int bSize) {
        super();
        boardSize = bSize;
        maxIndex = (boardSize * boardSize) - 1;
    }

    public int FindNextTileToPlay(JPanel playArea){
        numberOfS = 0;
        numberOfO = 0;

        boolean isPlayable = false;
        int indexToCheck = randNum.nextInt(maxIndex + 1);

        int adjacentTile;
        playArea.getComponent(indexToCheck);

        //finding a playable tile.
        while(!isPlayable) {
            //We check to see if the selected tile is playable.
            if (((GameTile) playArea.getComponent(indexToCheck)).isPlayable()) {
                //If the tile is playable, we keep that index and break out of the loop.
                isPlayable = true;
            }
            else{
                //Check a different spot
                indexToCheck = randNum.nextInt(maxIndex + 1);
            }
        }
        return indexToCheck;
    }

    public char DecideWhatToPlaceOnTile(JPanel playArea, int indexToCheck){
        numberOfS = 0;
        numberOfO = 0;
        int adjacentTile;

        //deciding what to play on that tile (S or O)
        //Will need to inspect all 8 adjacent tiles and count how many S and O are touching the selected tile.
        //if more S then will play O and vice versa.

        adjacentTile = (indexToCheck - boardSize);
        if((adjacentTile) >= 0) {
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //topRight
        adjacentTile = (indexToCheck - boardSize + 1);
        if((adjacentTile >= 0) && ((adjacentTile % boardSize) != 0)){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //right
        adjacentTile = (indexToCheck + 1);
        if((adjacentTile < maxIndex) && ((adjacentTile % boardSize) != 0)){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //bottomRight
        adjacentTile = (indexToCheck + boardSize + 1);
        if((adjacentTile < maxIndex) && ((adjacentTile % boardSize != 0))){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //bottom
        adjacentTile = (indexToCheck + boardSize);
        if(adjacentTile < maxIndex){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //bottomLeft
        adjacentTile = (indexToCheck + boardSize - 1);
        if((adjacentTile > maxIndex) && (indexToCheck % boardSize != 0)){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //left
        adjacentTile = (indexToCheck - 1);
        if((adjacentTile >= 0) && (indexToCheck % boardSize != 0)){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        //topLeft
        adjacentTile = (indexToCheck - boardSize - 1);
        if((adjacentTile >= 0) && (indexToCheck % boardSize != 0)){
            CountNumberOfSandO((GameTile)playArea.getComponent(adjacentTile));
        }

        try{
            TimeUnit.SECONDS.sleep(randNum.nextInt(3)); //Hopefully will have the comp wait a couple of seconds before returning its decision.
        }
        catch(InterruptedException e){
            System.out.println("Sleep was interrupted");
        };

        if(numberOfS > numberOfO){                  //If there are more adjacent S, return O
            return 'O';
        }
        else if(numberOfO > numberOfS){             //If there are more adjacent O, return S
            return 'S';
        }
        else{
            if(randNum.nextInt(2) % 2 == 0){  //If the number is equal, or both are 0, return a random S or O.
                return 'S';
            }
            else{
                return 'O';
            }
        }
    }

    private void CountNumberOfSandO(GameTile tileToCheck){
        if (tileToCheck.getState() == 'S') {
            numberOfS++;
        } else if (tileToCheck.getState() == 'O') {
            numberOfO++;
        }
    }

    //I'm planning to have the CPU player wait 1-3 seconds before making its move to make
    //it feel more natural and less like a random selection.
    //TimeUnit.SECONDS.sleep(1);
}

