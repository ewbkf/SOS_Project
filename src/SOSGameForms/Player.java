package SOSGameForms;

public class Player {
    protected int score;

    protected boolean isBot;

    public Player() {
        this.score = 0;
    }

    public boolean isRobot(){
        return isBot;
    }
}
