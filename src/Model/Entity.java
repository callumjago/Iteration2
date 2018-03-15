package Model;

import java.awt.*;

public abstract class Entity {
	
    private Point position;
    private Interaction interaction;
    private Angle orientation;
    private Image sprite;
    private boolean attemptMove;

    Entity(){
        position = new Point(0,0);
        orientation = new Angle(90);
        attemptMove = false;
        // Default image here
    }

    Entity(Point pos, Angle theta, Image img){
        position = pos;
        orientation = theta;
        sprite = img;
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

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
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

    public boolean getAttemptMove() {
        return attemptMove;
    }

    public void resetAttemptMove() {
        attemptMove = false;
    }
}
