package Model;

import java.awt.*;
import java.util.*;

public abstract class Entity {
	
    private Point position;
    private Interaction interaction;
    private Angle orientation;
    private boolean attemptMove;
    private boolean moveable;

    Entity(){
        position = new Point(0,0);
        orientation = new Angle(90);
        attemptMove = false;
        moveable = true;
    }

    Entity(Point pos, Angle theta){
        position = pos;
        orientation = theta;
        attemptMove = false;
        moveable = true;
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
        if (moveable) { attemptMove = true;}
        //position.setLocation(position.getX()+0,position.getY()-1);
    }

    public void moveNorthEast() {
        orientation.changeTrajectory(315);
        //position.setLocation(position.getX()+1,position.getY()-1);
        if (moveable) { attemptMove = true;}
    }

    public void moveNorthWest() {
        orientation.changeTrajectory(225);
        if (moveable) { attemptMove = true;}
    }

    public void moveSouth() {
        orientation.changeTrajectory(90);
        //position.setLocation(position.getX()+0,position.getY()+1);
        if (moveable) { attemptMove = true;}
    }

    public void moveSouthEast() {
        orientation.changeTrajectory(45);
        if (moveable) { attemptMove = true;}
    }

    public void moveSouthWest() {
        orientation.changeTrajectory(135);
        if (moveable) { attemptMove = true;}
    }

    public void moveEast() {
        orientation.changeTrajectory(0);
        //position.setLocation(position.getX()+1,position.getY()+0);
        if (moveable) { attemptMove = true;}
    }

    public void moveWest() {
        orientation.changeTrajectory(180);
        //position.setLocation(position.getX()-1,position.getY()+0);
        if (moveable) { attemptMove = true;}
    }

    public void moveForward(){
        int dir = orientation.getDegree();
        if (moveable) {
            attemptMove = true;
            if (dir == 0) {
                moveEast();
            } else if (dir == 45) {
                moveSouthEast();
            } else if (dir == 90) {
                moveSouth();
            } else if (dir == 135) {
                moveSouthWest();
            } else if (dir == 180) {
                moveWest();
            } else if (dir == 225) {
                moveNorthWest();
            } else if (dir == 270) {
                moveNorth();
            } else if (dir == 315) {
                moveNorthEast();
            } else if (dir == 360) {
                moveEast();
            } else {
                moveNorth();
            }
        }
    }

    //direction
    //tiles 1 - 2 away
    //y-1 = north
    //y+1 = south
    //x-1 = west
    //x+1 = east

    public Point getForewardPosition() {
        Point pos = getPosition();
        if(getOrientation().getDegree() == 0) {
            return new Point(pos.x+1, pos.y);
        } else if(getOrientation().getDegree() == 45) {
            return new Point(pos.x+1, pos.y+1);
        } else if(getOrientation().getDegree() == 90) {
            return new Point(pos.x, pos.y + 1);
        } else if(getOrientation().getDegree() == 135) {
            return new Point(pos.x-1, pos.y+1);
        } else if(getOrientation().getDegree() == 180) {
            return new Point(pos.x-1, pos.y);
        } else if(getOrientation().getDegree() == 225) {
            return new Point(pos.x-1, pos.y-1);
        } else if(getOrientation().getDegree() == 270) {
            return new Point(pos.x, pos.y-1);
        } else if(getOrientation().getDegree() == 315) {
            return new Point(pos.x+1, pos.y-1);
        } else {
            return new Point(pos.x+1, pos.y);
        }
    }

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
        Queue<Point> quex = new LinkedList<Point>();
        for(int i = 1; i <= Range; i++) {
            Point adj = new Point();
            adj.x = getPosition().x + incX*i;
            adj.y = getPosition().y + incY*i;
            System.out.println(getPosition().x + incX*i);
            System.out.println(getPosition().y + incY*i);
            quex.add(adj);
        }
        return quex;
    }
    public int getDegree(){ return orientation.getDegree(); }

    public int getX(){ return (int) position.getX();}

    public int getY(){ return (int) position.getY();}

    public boolean getAttemptMove(){ return attemptMove;}

    public void resetAttemptMove(){ attemptMove = false; }

    public void toggleMovement() {
        moveable = !moveable;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void talk() {}
}
