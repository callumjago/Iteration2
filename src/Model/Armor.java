package Model;

import java.awt.*;
import java.util.ArrayList;

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

    @Override
    public int getValue() {
        return DefensePoints;
    }

    @Override
    ArrayList<String> getStats() {
        return null;
    }
    public int getDefensePoints() {
        return DefensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        DefensePoints = defensePoints;
    }
}
