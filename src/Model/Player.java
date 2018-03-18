package Model;
import Controller.PlayerController;
import java.awt.*;
import java.util.ArrayList;

public class Player extends SentientEntity {

   //holds the index to then player sprite
   private int sprite;
   private int role;
    private Boolean Sneaking;
    private PlayerClass Class;
    private boolean isPickpocketing;


    Player(Point pos, Angle theta, String name, PlayerClass PC, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        Sneaking = false;
        Class = PC;
    }

    Player() {
        super(); // Attribute classes fill with default values
        //Class = new PlayerClass()
        Class = new Rogue();
        Sneaking = false;
        Class.addSkill(new PassiveSkill("HYPE", "Buffs HYPE", new Level(1), new Mana(), 5));
        Class.addSkill(new DefenseBuffSkill(this));
    }

    public PlayerClass getPlayerClass(){
        return Class;

    }
	
    public void addItem(Item item) {
        getInventory().addItem(item);
	}
    
    public int getSprite() {
    	return sprite;
    }
    
    public void setSprite(int _sprite) {
    	sprite = _sprite;
    }
    
    public void setRole(int _role) {
    	role = _role;
    }
    
    public int getRole() {
    	return role;
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

    public void Sneak() {
        Sneaking = true;
    }

    public void stopSneaking(){
        Sneaking = false;
    }

    public int getSneakAmount(){
        if (Class instanceof Rogue){
            return ((Rogue) Class).getSneakSkill();
        }
        else{
            return 0;
        }

    }

    public void applySkill(int skillIndex) {
        if(skillIndex >= Class.getTotalSkills().size()) {
            return;
        }
        Class.getSkill(skillIndex).ApplySkill();
    }

    public ArrayList<String> getSkillStats(int skillIndex) {
        if(skillIndex >= Class.getTotalSkills().size()) {
            return new ArrayList<>();
        }
        return Class.getSkill(skillIndex).getStats();
    }

    public boolean isPickpocketing() {
        return isPickpocketing;
    }
    public void setPickpocketing(boolean bool) {
        isPickpocketing = bool;
    }
}
