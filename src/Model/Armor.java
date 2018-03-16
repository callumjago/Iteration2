package Model;

import java.awt.*;

public class Armor extends Equipment{

    private int DefensePoints;

    public Armor(int EQID, Level reqLvl, String name, String description, int defensePoints) {
        super(EQID, reqLvl, name, description);
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
}
