package Model;

import java.util.ArrayList;

public class Mage implements PlayerClass{

    ArrayList<Skill> SkillSet;

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
