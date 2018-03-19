package Model;

import java.util.ArrayList;

public class Mage implements PlayerClass{

    private ArrayList<Skill> SkillSet;
    private ArrayList<Skill> CompleteSkillSet;
    private ClassStat EnchantmentSkilPoints;
    private ClassStat BoonSkillPoints;
    private ClassStat BaneSkillPoints;
    private ClassStat StaffSkillPoints;
    private int skillPointsAssignable;
    ArrayList<String> classStatsList;

    public Mage() {
        SkillSet = new ArrayList<Skill>();
        generateCompleteSkillSet();
        EnchantmentSkilPoints = new ClassStat();
        BoonSkillPoints = new ClassStat();
        BaneSkillPoints = new ClassStat();
        StaffSkillPoints = new ClassStat();

        skillPointsAssignable = 0;

        classStatsList = new ArrayList<>();
        classStatsList.add("Enchantment");
        classStatsList.add("Boon");
        classStatsList.add("Bane");
        classStatsList.add("Staff");
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
    public ArrayList<Skill> getTotalSkills() {
        return SkillSet;
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

    @Override
    public void raiseSkill(int index) {
        if(skillPointsAssignable == 0) { return; }
        switch(index) {
            case 0:
                EnchantmentSkilPoints.raiseBaseStat(1);
                break;
            case 1:
                BoonSkillPoints.raiseBaseStat(1);
                break;
            case 2:
                BaneSkillPoints.raiseBaseStat(1);
                break;
            case 3:
                StaffSkillPoints.raiseBaseStat(1);
                break;

        }
        skillPointsAssignable--;
    }

    @Override
    public ArrayList<String> getClassStats() {
        return classStatsList;
    }

    @Override
    public ArrayList<Integer> getClassStatValues() {
        ArrayList<Integer> statVals = new ArrayList<>();
        statVals.add(EnchantmentSkilPoints.getClassStat());
        statVals.add(BoonSkillPoints.getClassStat());
        statVals.add(BaneSkillPoints.getClassStat());
        statVals.add(StaffSkillPoints.getClassStat());
        return  statVals;
    }

    public int getSkillPointsAssignable() {
        return skillPointsAssignable;
    }


}
