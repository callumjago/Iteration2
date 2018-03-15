package Model;

import java.awt.*;
import java.util.ArrayList;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int ItemID, int EQID, Level reqLvl, String name, String description, Skill effect) {
        super(ItemID, EQID, reqLvl, name, description);
    }

    public Ring() {
        super();
		this.setName("Ring");
    }
    @Override
    ArrayList<String> getStats() {
        return null;
    }
    public Skill getEffect() {
        return Effect;
    }

    public void setEffect(Skill effect) {
        Effect = effect;
    }
    
    public int getValue() { //temporary implementation
    	return 0;
    }
}
