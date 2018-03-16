package Model;

import java.awt.*;

public class Teleport extends AOE {
    private Point location;
    private int mapID;

    public Teleport (Point location, int MapID){
        this.location = location;
        this.mapID = mapID;
    }

    public void setMapID(int mapID){
        this.mapID = mapID;
    }
    public int getMapID(){
        return mapID;
    }

    public void setLocation(Point location){
        this.location = location;
    }

    public Point getLocation(){
        return location;
	}
	
	@Override
    public int getValue() {
        return 0;
    }
}
