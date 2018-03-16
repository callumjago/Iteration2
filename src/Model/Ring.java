package Model;

import java.awt.*;
import java.util.ArrayList;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int ItemID, int EQID, Level reqLvl, String name, String description, Skill effect) {
        super(ItemID, EQID, reqLvl, name, description);
        Effect = effect;
    }

    public Ring() {
        super();
		this.setName("Ring");
    }

    @Override
    public int getValue() {
        return 0;
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
}
