package Model;

import java.awt.*;
import java.util.ArrayList;

public class Armor extends Equipment{

    private int DefensePoints;

    public Armor(int ObjID, Image Sprite, int ItemID, int EQID, Level reqLvl, String name, String description, int defensePoints) {
        super(ObjID, Sprite, ItemID, EQID, reqLvl, name, description);
        DefensePoints = defensePoints;
    }

    public Armor() {
        super();
        DefensePoints = 0;
		this.setName("Armor");
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
