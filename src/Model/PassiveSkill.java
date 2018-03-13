package Model;

public class PassiveSkill extends Skill{

    private Stat Misc;
    private int Modifier;

    PassiveSkill(){
        Modifier = 0;
    }

    public PassiveSkill(Stat misc, int modifier) {
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

}
