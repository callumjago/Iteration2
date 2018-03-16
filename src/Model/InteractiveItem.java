package Model;

public class InteractiveItem extends Item {
    public InteractiveItem() {
        super();
    }

    @Override
    public int getValue() {
        return 0;
    }

    public boolean passedRequirement(){
    	return false; //temporary
    }
}
