package Model;
import Controller.PlayerController;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;

public class Player extends SentientEntity {

   //holds the index to then player sprite
   private int sprite;
   private int role;
    private Boolean Sneaking;
    private PlayerClass Class;
    private boolean isPickpocketing;
    private boolean canDetectTraps;
    private int PlayerSpeed;
    private boolean Running;
	private Image playerSprite;
    private int playerSpriteIndex;
    private int observationLevel;

    Player(Point pos, Angle theta, String name, PlayerClass PC, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        Sneaking = false;
        Class = PC;
        PlayerSpeed = 10;
        Running = false;
    }

    Player() {
        super(); // Attribute classes fill with default values
        //Class = new PlayerClass()
        Class = new Mage();
        Sneaking = false;
        PlayerSpeed = 10;
        canDetectTraps = false;
        Running = false;
        observationLevel = 0;
    }

    public PlayerClass getPlayerClass(){
        return Class;

    }

    public void setClass(String classString)
    {
        if(classString == "Warrior")
            Class = new Warrior();
        else if(classString == "Mage")
            Class = new Mage();
        else if(classString == "Rogue")
            Class = new Rogue();
    }

    public void toggleRun(){
        if (Running && !Sneaking){
            PlayerSpeed = 10;
        }
        else{
            PlayerSpeed = 5;
        }
        Running = !Running;
    }
	
    public void addItem(Item item) {
        getInventory().addItem(item);
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
        PlayerSpeed = 15;
    }

    public void stopSneaking(){
        PlayerSpeed = 10;
        Sneaking = false;
    }

    public void toggleSneak(){
        if (Sneaking){
            PlayerSpeed = 10;
            Sneaking = false;
        }
        else{
            Sneaking = true;
            PlayerSpeed = 15;
        }
    }

    public int getPlayerSpeed() {
        return PlayerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        PlayerSpeed = playerSpeed;
    }

    public int getSneakAmount(){
        if (Class instanceof Rogue){
            return ((Rogue) Class).getSneakSkill();
        }
        else{
            return 0;
        }
    }

    public boolean canDetectTraps() {
        return canDetectTraps;
    }

    public void setCanDetectTraps(boolean canDetectTraps) {
        this.canDetectTraps = canDetectTraps;
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

    public void setSprite(Image sprite) { this.playerSprite = sprite; }
    public Image getSprite() { return playerSprite; }

    public void setSpriteIndex(int playerSprite) { this.playerSpriteIndex = playerSprite; }
    public int getSpriteIndex() { return playerSpriteIndex; }

    public int getObservationLevel() {
        return observationLevel;
    }

    public void setObservationLevel(int observationLevel) {
        this.observationLevel = observationLevel;
    }
}
