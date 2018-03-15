package Model;

public class Armor extends Equipment{

    private int DefensePoints;

    public Armor(int ObjID, int ItemID, int EQID, Level reqLvl, String name, String description, int defensePoints) {
        super(ObjID, ItemID, EQID, reqLvl, name, description);
        DefensePoints = defensePoints;
    }

    public Armor() {
        super();
        DefensePoints = 0;
		this.setName("Armor");
    }

    public int getDefensePoints() {
        return DefensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        DefensePoints = defensePoints;
    }
    
    public int getValue() {
    	return DefensePoints;
    }
}
