package Model;

import java.awt.*;
import java.util.ArrayList;

public class Armor extends Equipment{

    private int DefensePoints;

    public Armor(int ItemID, int EQID, Level reqLvl, String name, String description, int defensePoints) {
        super(ItemID, EQID, reqLvl, name, description);
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
        ArrayList<String> stats = new ArrayList<>();
        stats.add("Name: " + getName());
        stats.add("Defense: " +DefensePoints);
        return stats;
    }
    public int getDefensePoints() {
        return DefensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        DefensePoints = defensePoints;
    }
}
