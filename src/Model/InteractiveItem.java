package Model;

public class InteractiveItem extends Item {
	private Level reqLvl;
	private int value;
	
    public InteractiveItem() {
        super();
        reqLvl = new Level();
        value = 0;
    }
    
    public InteractiveItem(Level _lvl, int _value, int _itemID) {
    	super(_itemID);
    	reqLvl = _lvl;
    	value = _value;
    }

    @Override
    public int getValue() {
        return value;
    }
    
    public int getLvlRequirement() {
    	return reqLvl.getLevel();
    }

    public boolean passedRequirement(){
    	return false; //temporary
    }
}
