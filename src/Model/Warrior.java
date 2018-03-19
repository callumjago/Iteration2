// TODO: Need to implement a codex to generate all the skills in the complete skill set!
package Model;

import java.util.ArrayList;

public class Warrior implements PlayerClass{

    private ArrayList<Skill> SkillSet;
    private ArrayList<Skill> CompleteSkillSet;
    private ClassStat OneHandedSkillPoints;
    private ClassStat TwoHandedSkillPoints;
    private ClassStat BareFistSkillPoints;
    private int skillPointsAssignable;
    ArrayList<String> classStatsList;
    public Warrior(){
        SkillSet = new ArrayList<Skill>();
        generateCompleteSkillSet();
        OneHandedSkillPoints = new ClassStat();
        TwoHandedSkillPoints = new ClassStat();
        BareFistSkillPoints = new ClassStat();

        skillPointsAssignable = 0;

        classStatsList = new ArrayList<>();
        classStatsList.add("One Handed");
        classStatsList.add("Two Handed");
        classStatsList.add("Bare Fists");
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
                OneHandedSkillPoints.raiseBaseStat(1);
                break;
            case 1:
                TwoHandedSkillPoints.raiseBaseStat(1);
                break;
            case 2:
                BareFistSkillPoints.raiseBaseStat(1);
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
        statVals.add(OneHandedSkillPoints.getClassStat());
        statVals.add(TwoHandedSkillPoints.getClassStat());
        statVals.add(BareFistSkillPoints.getClassStat());
        return  statVals;
    }
}
