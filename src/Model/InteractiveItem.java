package Model;

public class InteractiveItem extends Item {
    public InteractiveItem() {
        super(8); // Default ID for interactive Item here
    }
    
    public int getValue() { //temporary implementation
    	return 0;
	}
	
    public boolean passedRequirement(){
    	return false; //temporary
    }
}
