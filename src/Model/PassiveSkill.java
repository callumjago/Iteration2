package Model;

import java.util.ArrayList;

public class PassiveSkill extends Skill{

    private Stat Misc;
    private int Modifier;

    PassiveSkill(){
        Modifier = 0;
    }

    public PassiveSkill(String name, String description, Level reqLvl, Stat misc, int modifier) {
        super(name,description,reqLvl);
        Misc = misc;
        Modifier = modifier;
    }

    public void changeModifer(int delta){
        Modifier = delta;
        ApplySkill();
    }

    public void ApplySkill() {
        Misc.modify(Modifier);
    }

    @Override
    public void RemoveSkill() {
        Misc.modify(-1*Modifier);
    }

    @Override
    void getSpecificStats(ArrayList<String> stats) {
        stats.add(Misc.getName() + ": " + Modifier);
    }

}
