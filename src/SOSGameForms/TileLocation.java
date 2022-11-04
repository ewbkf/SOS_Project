package SOSGameForms;

public class TileLocation {
    private int xCoord;
    private int yCoord;

    public TileLocation() {
        this.xCoord = -1;
        this.yCoord = -1;
    }
    public TileLocation(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
