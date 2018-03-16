package Model;

public class Angle {
    private int degree;

    public Angle(){
        degree = 0;
    }

    public Angle(int theta){
        if (theta > 360) {
            while (theta > 360) {
                theta -= 360;
            }
        }
        else if (theta < 0) {
            while (theta < 360) {
                theta += 360;
            }
        }
        degree = theta;
    }

    public void changeTrajectory(int theta){
        if (theta > 360) {
            while (theta > 360) {
                theta -= 360;
            }
        }
        else if (theta < 0) {
            while (theta < 360) {
                theta += 360;
            }
        }
        degree = theta;
    }

    public int getDegree(){
        return degree;
    }
}
