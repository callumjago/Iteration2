package Model;


import java.util.ArrayList;

public interface PlayerClass {
    Skill getSkill(int SkillIndex);
    void addSkill(Skill newSkill);
    ArrayList<Skill> getTotalSkills();
    void LevelUp(Level newLvl);
    void raiseSkill(int index);
    ArrayList<String> getClassStats();
    ArrayList<Integer> getClassStatValues();
    int getSkillPointsAssignable();
}
