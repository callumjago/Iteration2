package Model;

public class Angle {
    private int degree;

    Angle(int theta){
        if (theta > 360 || theta < 0) {
            degree = 0;
        }
        else {
            degree = theta;
        }
    }

    public void changeTrajectory(int theta){
        if (theta > 360 || theta < 0) {
            degree = 0;
        }
        else {
            degree = theta;
        }
    }

    public int getDegree(){
        return degree;
    }
}
