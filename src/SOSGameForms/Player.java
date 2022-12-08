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

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
}
