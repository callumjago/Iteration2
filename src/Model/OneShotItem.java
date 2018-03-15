package Model;

import java.awt.Image;

public abstract class OneShotItem extends GameObject {
	private int OneShotID;
	
    public OneShotItem(int _objectID, int _oneShotID, Image _sprite) {
        super(_objectID, _sprite);
        
        OneShotID = _oneShotID;
    }
    
    public OneShotItem() {
    	super();
    	OneShotID = 0;
    }
    
    public int getOneShotID() {
    	return OneShotID;
    }
    
    public void setOneShotID(int _oneShotID) {
    	OneShotID = _oneShotID;
    }
}
