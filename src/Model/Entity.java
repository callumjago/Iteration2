package Model;

import java.awt.*;

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

    public int getDegree(){ return orientation.getDegree(); }

    public int getX(){ return (int) position.getX();}

    public int getY(){ return (int) position.getY();}

    public boolean getAttemptMove(){ return attemptMove;}

    public void resetAttemptMove(){ attemptMove = false; }

    public void toggeleMovement(){ moveable = !moveable;}
}
