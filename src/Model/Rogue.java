package Model;

public class Rogue implements PlayerClass {
    @Override
    public Skill getSkill(int SkillIndex) {
        return null;
    }

    @Override
    public void addSkill(Skill newSkill) {

    }

    @Override
    public Skill[] getTotalSkills() {
        return new Skill[0];
    }

    @Override
    public void LevelUp(int newLvl) {

    }
}
