package Model;

import java.awt.*;
import java.util.ArrayList;

public class Ring extends Equipment {

    private Skill Effect;

    public Ring(int EQID, String name, String description, String skillName, String skillDesc, Level reqLvl, Stat stat, int modifyAmt) {
        super(EQID, reqLvl, name, description);
        Effect = new PassiveSkill(skillName, skillDesc, reqLvl, stat, modifyAmt);
    }
    
    public Ring() {
        super();
		this.setName("Ring");
		Effect = new PassiveSkill("Magic Ring", "Does some shit", new Level(1), new Health(), 5);
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    ArrayList<String> getStats() {
        ArrayList<String> stats = new ArrayList<>();
        stats.add("Name: " + getName());
        stats.add(Effect.getName());
        stats.addAll(Effect.getStats());
        return stats;
    }
    public Skill getEffect() {
        return Effect;
    }

    public void setEffect(Skill effect) {
        Effect = effect;
    }

    public void ApplySkill() {
        Effect.ApplySkill();
        return;
    }

    public void RemoveSkill() {
        Effect.RemoveSkill();
    }
}
