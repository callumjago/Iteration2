package Model;

public class Quiver {
    private int arrowCount;
    private int quiverSize;
    public Quiver(int quiverSize) {
        this.quiverSize = quiverSize;
        arrowCount = 0;
    }

    public void modifyArrowCount(int delta) {
        if(arrowCount + delta < 0) {
            arrowCount = 0;
        }
        if(arrowCount + delta > quiverSize) {
            arrowCount = quiverSize;
        }
        arrowCount += delta;
    }

    public void setQuiverSize(int size) {
        quiverSize = size;
    }

    public int getQuiverSize() {
        return quiverSize;
    }

    public int getArrowCount() {
        return arrowCount;
    }


}
