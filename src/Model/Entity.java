package Model;

import java.awt.*;
import java.util.*;

public abstract class Entity {
	
    private Point position;
    private Interaction interaction;
    private Angle orientation;
    private boolean attemptMove;

    Entity(){
        position = new Point(0,0);
        orientation = new Angle(90);
        attemptMove = false;
    }

    Entity(Point pos, Angle theta){
        position = pos;
        orientation = theta;
        attemptMove = false;
    }

    public Angle getOrientation() {
        return orientation;
    }

    public void setOrientation(Angle orientation) {
        this.orientation = orientation;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void clearInteraction(){
        interaction = null;
    }

    public Boolean checkInteraction(){
        return interaction != null;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public void setAttemptMove(boolean attemptMove) {
        this.attemptMove = attemptMove;
    }

    public void moveNorth() {
        orientation.changeTrajectory(270);
        //position.setLocation(position.getX()+0,position.getY()-1);
        attemptMove = true;
    }

    public void moveNorthEast() {
        orientation.changeTrajectory(315);
        //position.setLocation(position.getX()+1,position.getY()-1);
        attemptMove = true;
    }

    public void moveNorthWest() {
        orientation.changeTrajectory(225);
        attemptMove = true;
    }

    public void moveSouth() {
        orientation.changeTrajectory(90);
        //position.setLocation(position.getX()+0,position.getY()+1);
        attemptMove = true;
    }

    public void moveSouthEast() {
        orientation.changeTrajectory(45);
        attemptMove = true;
    }

    public void moveSouthWest() {
        orientation.changeTrajectory(135);
        attemptMove = true;
    }

    public void moveEast() {
        orientation.changeTrajectory(0);
        //position.setLocation(position.getX()+1,position.getY()+0);
        attemptMove = true;
    }

    public void moveWest() {
        orientation.changeTrajectory(180);
        //position.setLocation(position.getX()-1,position.getY()+0);
        attemptMove = true;
    }

    public void moveForward(){
        int dir = orientation.getDegree();
        attemptMove = true;
        if (dir == 0){ moveEast(); }
        else if (dir == 45){ moveSouthEast(); }
        else if (dir == 90){ moveSouth(); }
        else if (dir == 135){ moveSouthWest(); }
        else if (dir == 180){ moveWest(); }
        else if (dir == 225){ moveNorthWest(); }
        else if (dir == 270){ moveNorth(); }
        else if (dir == 315){ moveNorthEast(); }
        else if (dir == 360){ moveEast();}
        else { moveNorth();}
    }

    //direction
    //tiles 1 - 2 away
    //y-1 = north
    //y+1 = south
    //x-1 = west
    //x+1 = east

    public Queue<Point> getNearbyLoc(AttackOr weapon, int Range) {
        Queue<Point> que = new LinkedList<Point>();
        int dir = orientation.getDegree();
        switch (weapon.getOrientation()) {
            case 0:
                switch (orientation.getDegree()) {
                    case 0:
                        //East
                        que = tranAck(que,  Range, 1,  0);
                        break;
                    case 45:
                        //SouthEast
                        que = tranAck(que,  Range, 1,  1);
                        break;
                    case 90:
                        //South
                        que = tranAck(que,  Range, 0,  1);
                        break;
                    case 135:
                        //SouthWest
                        que = tranAck(que,  Range, -1,  1);
                        break;
                    case 180:
                        //West
                        que = tranAck(que,  Range, -1,  0);
                        break;
                    case 225:
                        //NorthWest
                        que = tranAck(que,  Range, -1,  -1);
                        break;
                    case 270:
                        //North
                        que = tranAck(que,  Range, 0,  -1);
                        break;
                    case 315:
                        //NorthEast
                        que = tranAck(que,  Range, 1,  -1);
                        break;
                    case 360:
                        //East
                        que = tranAck(que,  Range, 1,  0);
                        break;
                }
                return que;
            case 1:
                if(dir>270)
                {
                    que = tranAck(que,  Range, 1,  0);
                }
                else if(dir > 180 && dir < 270)
                {

                }
                else if(dir > 90 && dir < 180)
                {

                }
                else {

                }


        }
        return  que;
    }

    public Queue<Point> tranAck(Queue<Point> que, int Range, int incX, int incY){
        for(int i = 0; i < Range; i++) {
            Point adj = new Point();
            adj.x = getPosition().x + incX;
            adj.y = getPosition().y + incY;
            que.add(adj);
        }
        return que;
    }

    public boolean getAttemptMove() {
        return attemptMove;
    }

    public void resetAttemptMove() {
        attemptMove = false;
    }
}
