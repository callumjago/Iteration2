package Model;
//orientation invariants: orientation > 0 and orientation < 2
//orientation = 0 -> straight line
//orientation = 1 -> diagonal
//orientation = 2 -> spread

public class AttackOr {
    private int orientation;

    public AttackOr(int _orientation) {
        setOrientation(_orientation);
    }

    public void setOrientation(int _orientation) {
        if(_orientation > 2 || _orientation < 0) {

            System.out.println("Invalid Orientation passed");
            return;
        }

        orientation = _orientation;
    }

    public int getOrientation() {
        return orientation;
    }

}
