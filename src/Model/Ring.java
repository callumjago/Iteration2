package Model;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int ItemID, int EQID, Level reqLvl, String name, String description, Skill effect) {
        super(ItemID, EQID, reqLvl, name, description);
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
