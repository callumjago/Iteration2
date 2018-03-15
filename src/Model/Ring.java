package Model;

import java.awt.*;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int ObjID, int ItemID, int EQID, Level reqLvl, String name, String description, Skill effect) {
        super(ObjID, ItemID, EQID, reqLvl, name, description);
    }

    public Ring() {
        super();
		this.setName("Ring");
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
