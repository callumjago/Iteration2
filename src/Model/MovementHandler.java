package Model;

import java.awt.*;

public class MovementHandler {
    private GameState State;

    public MovementHandler(GameState State){
        this.State = State;

    }

    public void checkMove(Entity e, Angle dir){
        if (dir.getDegree() == 270) {//NORTH
            if (State.checkMove((int) e.getPosition().getX(),(int) e.getPosition().getY()-1)){
                e.setPosition(new Point(e.getPosition().x, e.getPosition().y-1));
            }
        }
        else if (dir.getDegree() == 315) {//NORTHEAST
            if (State.checkMove((int) e.getPosition().getX()+1,(int) e.getPosition().getY()-1)){
                e.setPosition(new Point(e.getPosition().x+1, e.getPosition().y-1));
            }
        }
        else if (dir.getDegree() == 225) {//NORTHWEST
            if (State.checkMove((int) e.getPosition().getX()-1,(int) e.getPosition().getY()-1)){
                e.setPosition(new Point(e.getPosition().x-1, e.getPosition().y-1));
            }
        }
        else if (dir.getDegree() == 90) {//SOUTH
            if (State.checkMove((int) e.getPosition().getX(),(int) e.getPosition().getY()+1)){
                e.setPosition(new Point(e.getPosition().x, e.getPosition().y+1));
            }
        }
        else if (dir.getDegree() == 45) {//SOUTHEAST
            if (State.checkMove((int) e.getPosition().getX()+1,(int) e.getPosition().getY()+1)){
                e.setPosition(new Point(e.getPosition().x+1, e.getPosition().y+1));
            }
        }

        else if (dir.getDegree() == 135) {//SOUTHWEST
            if (State.checkMove((int) e.getPosition().getX()-1,(int) e.getPosition().getY()+1)){
                e.setPosition(new Point(e.getPosition().x-1, e.getPosition().y+1));

            }
        }
        else if (dir.getDegree() == 0) {//EAST
            if (State.checkMove((int) e.getPosition().getX()+1,(int) e.getPosition().getY())){
                e.setPosition(new Point(e.getPosition().x+1, e.getPosition().y));
            }
        }
        else if (dir.getDegree() == 180) {//WEST
            if (State.checkMove((int) e.getPosition().getX()-1,(int) e.getPosition().getY())){
                e.setPosition(new Point(e.getPosition().x-1, e.getPosition().y));
            }
        }
        else {
            return;
        }
    }
}
