package Model;

import java.awt.*;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int EQID, Level reqLvl, String name, String description, Skill effect) {
        super(EQID, reqLvl, name, description);
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
}
