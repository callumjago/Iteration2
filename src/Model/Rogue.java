package Model;

import java.util.ArrayList;

public class Rogue implements PlayerClass {

    private ArrayList<Skill> SkillSet;
    private ArrayList<Skill> CompleteSkillSet;
    private ClassStat PickPocketSkillPoints;
    private ClassStat TrapSkillPoints;
    private ClassStat SneakSkillPoints;
    private ClassStat RangedSkillPoints;
    private int skillPointsAssignable;
    ArrayList<String> classStatsList;

    public Rogue() {
        SkillSet = new ArrayList<Skill>();
        generateCompleteSkillSet();
        PickPocketSkillPoints = new ClassStat();
        TrapSkillPoints = new ClassStat();
        SneakSkillPoints = new ClassStat();
        RangedSkillPoints = new ClassStat();

        skillPointsAssignable = 5;

        classStatsList = new ArrayList<>();
        classStatsList.add("Pick Pocket");
        classStatsList.add("Trap");
        classStatsList.add("Sneak");
        classStatsList.add("Ranged");
    }

    private void generateCompleteSkillSet() {
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

    public int getSneakSkill(){
        return SneakSkillPoints.getClassStat();
    }

    public ClassStat getTrapSkillPoints() {
        return TrapSkillPoints;
    }

    public ClassStat getPickPocketSkillPoints() {
        return PickPocketSkillPoints;
    }

    public ClassStat getRangedSkillPoints() {
        return RangedSkillPoints;
    }

    public void setRangedSkillPoints(ClassStat rangedSkillPoints) {
        RangedSkillPoints = rangedSkillPoints;
    }

    @Override
    public void raiseSkill(int index) {
        System.out.println("RAISING");
        if(skillPointsAssignable == 0) { return; }
        switch(index) {
            case 0:

                PickPocketSkillPoints.raiseBaseStat(1);
                break;
            case 1:
                TrapSkillPoints.raiseBaseStat(1);
                break;
            case 2:
                SneakSkillPoints.raiseBaseStat(1);
                break;
            case 3:
                RangedSkillPoints.raiseBaseStat(1);
                break;

        }
        skillPointsAssignable--;
    }

    public int getSkillPointsAssignable() {
        return skillPointsAssignable;
    }

    @Override
    public ArrayList<String> getClassStats() {
        return classStatsList;
    }

    @Override
    public ArrayList<Integer> getClassStatValues() {
        ArrayList<Integer> statVals = new ArrayList<>();
        statVals.add(PickPocketSkillPoints.getClassStat());
        statVals.add(TrapSkillPoints.getClassStat());
        statVals.add(SneakSkillPoints.getClassStat());
        statVals.add(RangedSkillPoints.getClassStat());
        return  statVals;
    }



}
