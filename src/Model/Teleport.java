package Model;

import java.awt.*;

public class Teleport implements AOE {
    private Point location;
    private int mapID;

    private void Teleport (Point location, int MapID){
        this.location = location;
        this.mapID = mapID;
    }

    private void setMapID(int mapID){
        this.mapID = mapID;
    }
    private int getMapID(){
        return mapID;
    }

    private void setLocation(Point location){
        this.location = location;
    }

    private Point getLocation(){
        return location;
    }
}
