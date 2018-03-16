package Model;

import java.awt.*;
import java.util.ArrayList;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int EQID, Level reqLvl, String name, String description) {
        super(EQID, reqLvl, name, description);
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
