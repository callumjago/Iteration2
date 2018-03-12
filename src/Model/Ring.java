package Model;

import java.awt.*;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int ObjID, Image Sprite, int ItemID, int EQID, Level reqLvl, String name, String description, Skill effect) {
        super(ObjID, Sprite, ItemID, EQID, reqLvl, name, description);
    }

    public Ring() {
        super();
    }

    public Skill getEffect() {
        return Effect;
    }

    public void setEffect(Skill effect) {
        Effect = effect;
    }
}
