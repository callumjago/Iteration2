package Model;

import java.awt.Image;

public class MapTransition extends GameObject {
	private int teleID;
	
    public MapTransition(int _teleID) {
        super(2);
        teleID = _teleID;
    }

    @Override
    public int getValue() {
        return teleID;
    }


}
