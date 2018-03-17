package Model;
import Controller.PlayerController;
import java.awt.*;
import java.util.ArrayList;

public class Player extends SentientEntity {
    private PlayerClass Class;

    Player(Point pos, Angle theta, String name, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        Class = new Warrior();
    }

    Player() {
        super(); // Attribute classes fill with default values
        Class = new Warrior();
        Class.addSkill(new PassiveSkill());

    }
	
    public void addItem(Item item) {
        getInventory().addItem(item);
	}

    public ArrayList<String> getSkillsAsStringList() {
        ArrayList<String> skillsList = new ArrayList<>();
        ArrayList<Skill> skills = Class.getTotalSkills();

        for(int i = 0; i < skills.size(); i++) {
            skillsList.add(skills.get(i).getName());
        }
        return skillsList;
    }

    public int getNumSkills() {
        return Class.getTotalSkills().size();
    }
}
