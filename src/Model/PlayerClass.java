package Model;


import java.util.ArrayList;

public interface PlayerClass {
    Skill getSkill(int SkillIndex);
    void addSkill(Skill newSkill);
    ArrayList<Skill> getTotalSkills();
    void LevelUp(Level newLvl);
}
