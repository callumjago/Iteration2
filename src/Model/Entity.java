package Model;

import java.awt.*;

public abstract class Entity {
    Point position;
    Interaction interaction;
    Angle orientation;
    Image sprite;

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
        orientation.changeTrajectory(90);
        position.setLocation(position.getX()+0,position.getY()+1);
    }

    public void moveNorthEast() {
        orientation.changeTrajectory(45);
        position.setLocation(position.getX()+1,position.getY()+1);
    }

    public void moveNorthWest() {
        orientation.changeTrajectory(135);
        position.setLocation(position.getX()-1,position.getY()+1);
    }

    public void moveSouth() {
        orientation.changeTrajectory(270);
        position.setLocation(position.getX()+0,position.getY()-1);
    }

    public void moveSouthEast() {
        orientation.changeTrajectory(315);
        position.setLocation(position.getX()+1,position.getY()-1);
    }

    public void moveSouthWest() {
        orientation.changeTrajectory(225);
        position.setLocation(position.getX()-1,position.getY()-1);
    }

    public void moveEast() {
        orientation.changeTrajectory(0);
        position.setLocation(position.getX()+1,position.getY()+0);
    }

    public void moveWest() {
        orientation.changeTrajectory(180);
        position.setLocation(position.getX()-1,position.getY()+0);
    }
}
