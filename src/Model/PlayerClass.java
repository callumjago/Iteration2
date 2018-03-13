package Model;


public interface PlayerClass {
    Skill getSkill(int SkillIndex);
    void addSkill(Skill newSkill);
    Skill[] getTotalSkills();
    void LevelUp(Level newLvl);
}
