package Model;

import java.awt.*;

public class MovementHandler {
    private GameState State;

    public MovementHandler(GameState State){
        this.State = State;
    }

    public void checkMove(Entity e, Angle dir){
        if (dir.getDegree() == 270) {//NORTH
            if (State.checkMove(e,(int) e.getPosition().getX(),(int) e.getPosition().getY()-1, true)){
                e.setPosition(new Point(e.getX(), e.getY()-1));
            }
        }
        else if (dir.getDegree() == 315) {//NORTHEAST
            if (State.checkMove(e,(int) e.getPosition().getX()+1,(int) e.getPosition().getY()-1, true)){
                e.setPosition(new Point(e.getX()+1, e.getY()-1));
            }
        }
        else if (dir.getDegree() == 225) {//NORTHWEST
            if (State.checkMove(e,(int) e.getPosition().getX()-1,(int) e.getPosition().getY()-1, true)){
                e.setPosition(new Point(e.getX()-1, e.getY()-1));
            }
        }
        else if (dir.getDegree() == 90) {//SOUTH
            if (State.checkMove(e,(int) e.getPosition().getX(),(int) e.getPosition().getY()+1, true)){
                e.setPosition(new Point(e.getX(), e.getY()+1));
            }
        }
        else if (dir.getDegree() == 45) {//SOUTHEAST
            if (State.checkMove(e,(int) e.getPosition().getX()+1,(int) e.getPosition().getY()+1, true)){
                e.setPosition(new Point(e.getX()+1, e.getY()+1));
            }
        }
        else if (dir.getDegree() == 135) {//SOUTHWEST
            if (State.checkMove(e,(int) e.getPosition().getX()-1,(int) e.getPosition().getY()+1, true)){
                e.setPosition(new Point(e.getX()-1, e.getY()+1));

            }
        }
        else if (dir.getDegree() == 0) {//EAST
            if (State.checkMove(e,(int) e.getPosition().getX()+1,(int) e.getPosition().getY(), true)){
                e.setPosition(new Point(e.getX()+1, e.getY()));
            }
        }
        else if (dir.getDegree() == 180) {//WEST
            if (State.checkMove(e,(int) e.getPosition().getX()-1,(int) e.getPosition().getY(), true)){
                e.setPosition(new Point(e.getX()-1, e.getY()));
            }
        }
        else {
            return;
        }
    }
}
