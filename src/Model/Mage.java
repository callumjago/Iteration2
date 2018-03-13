package Model;

import java.util.ArrayList;

public class Mage implements PlayerClass{

    private ArrayList<Skill> SkillSet;
    private ArrayList<Skill> CompleteSkillSet;
    private ClassStat EnchantmentSkilPoints;
    private ClassStat BoonSkillPoints;
    private ClassStat BaneSkillPoints;
    private ClassStat StaffSkillPoints;

    public Mage() {
        SkillSet = new ArrayList<Skill>();
        generateCompleteSkillSet();
        EnchantmentSkilPoints = new ClassStat();
        BoonSkillPoints = new ClassStat();
        BaneSkillPoints = new ClassStat();
        StaffSkillPoints = new ClassStat();
    }

    private void generateCompleteSkillSet(){
        CompleteSkillSet = new ArrayList<Skill>();
        //CompleteSkillSet.add(new PassiveSkill("Fortify OH","Strengthens OH attacks by 5",new Level(2),));
    }

    @Override
    public Skill getSkill(int SkillIndex) {
        return SkillSet.get(SkillIndex);
    }

    @Override
    public void addSkill(Skill newSkill) {
        SkillSet.add(newSkill);
    }

    @Override
    public Skill[] getTotalSkills() {
        return (Skill[]) SkillSet.toArray();
    }

    private Boolean checkUnlock(Level newLvl){
        return CompleteSkillSet.get(0).getReqLvl().isEquals(newLvl);
    }

    @Override
    public void LevelUp(Level newLvl) {
        if (checkUnlock(newLvl)){
            SkillSet.add(CompleteSkillSet.remove(0));
            // Raise Class Stats Up?
        }
    }
}
