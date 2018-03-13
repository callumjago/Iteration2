package Model;

public class MovementHandler {
    private GameState State;

    public MovementHandler(){}

    public void checkMove(Entity e, Angle dir){
        if (dir.getDegree() == 90) {
            if (State.checkMove((int) e.getPosition().getX(),(int) e.getPosition().getY()-1)){
                e.moveNorth();
            }
        }
        else if (dir.getDegree() == 45) {
            if (State.checkMove((int) e.getPosition().getX()+1,(int) e.getPosition().getY()-1)){
                e.moveNorthEast();
            }
        }
        else if (dir.getDegree() == 135) {
            if (State.checkMove((int) e.getPosition().getX()-1,(int) e.getPosition().getY()-1)){
                e.moveNorthWest();
            }
        }
        else if (dir.getDegree() == 180) {
            if (State.checkMove((int) e.getPosition().getX(),(int) e.getPosition().getY()+1)){
                e.moveSouth();
            }
        }
        else if (dir.getDegree() == 315) {
            if (State.checkMove((int) e.getPosition().getX()+1,(int) e.getPosition().getY()+1)){
                e.moveSouthEast();
            }
        }

        else if (dir.getDegree() == 225) {
            if (State.checkMove((int) e.getPosition().getX()-1,(int) e.getPosition().getY()+1)){
                e.moveSouthWest();
            }
        }
        else if (dir.getDegree() == 0) {
            if (State.checkMove((int) e.getPosition().getX()+1,(int) e.getPosition().getY())){
                e.moveEast();
            }
        }
        else if (dir.getDegree() == 180) {
            if (State.checkMove((int) e.getPosition().getX()-1,(int) e.getPosition().getY())){
                e.moveWest();
            }
        }
        else {
            return;
        }
    }
}
