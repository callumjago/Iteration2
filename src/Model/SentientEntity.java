package Model;

import java.awt.*;
import java.util.ArrayList;

public abstract class SentientEntity extends Entity {
    private String Name;
    private Inventory inventory;
    private Armor EquipArmor;
    private Weapon EquipWeapon;
    private Ring EquipRing;
    private Health HP;
    private Mana MP;
    private Attack Atk;
    private Defense Def;
    private Level Lvl;
    private Wallet Coffer;
    private AttackOr AtOr;
    private boolean attemptAttack;
    private int WeaRange;
    private boolean attemptInteract;
    private Quiver quiver;
    SentientEntity(Point pos, Angle theta, String name, Armor armor, Weapon weapon, Ring ring, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta);
        Name = name;
        EquipArmor = armor;
        EquipWeapon = weapon;
        EquipRing = ring;
        HP = new Health(initHP);
        MP = new Mana(initMP);
        Atk = new Attack(initAtk);
        Def = new Defense(initDef);
        Lvl = new Level(initLvl);
        Coffer = new Wallet(initMoney);

        inventory = new Inventory();
        attemptAttack = false;
        attemptInteract = false;
    }

    SentientEntity(){ // Attribute classes fill with default values
        super();
        Name = "H Y P E B O Y";
        HP = new Health();
        MP = new Mana();
        Atk = new Attack();
        Def = new Defense();
        Lvl = new Level();
        Coffer = new Wallet();
        Coffer.increaseMoney(50);
        inventory = new Inventory();
        EquipWeapon = new Weapon();
        attemptAttack = false;
        // Add starting equipment here
        EquipArmor = new Armor();
        EquipRing = new Ring();

        quiver = new Quiver(5);
        quiver.modifyArrowCount(5);
    }

    public void modifyHP(int delta){
        HP.modify(delta);
    }

    public void modifyMP(int delta){
        MP.modify(delta);
    }

    public void modifyMoney(int delta){
        Coffer.modifyMoney(delta);
    }

    public void modifyAtk(int delta){
        Atk.modify(delta);
    }

    public void modifyDef(int delta){
        Def.modify(delta);
    }

    public int getHP(){
        return HP.getHealthPoints();
    }

    public void setHP(int x) { HP.setHealthPoints(x); }

    public int getMaxHP() { return HP.getMaxHealthPoints(); }

    public int getEXP() { return Lvl.getExperience(); }

    public void setMaxHP(int x) { HP.setMaxHealthPoints(x);}

    public int getEXPRemaining() { return Lvl.getExpToNextLevel(); }

    public int getMP(){
        return MP.getMagicPoints();
    }

    public int getMaxMP() { return MP.getMaxMagicPoints(); }

    public int getAtk(){
        return Atk.getAttackPoints();
    }

    public int getDef(){
        return Def.getDefensePoints();
    }

    public int getLvl(){
        return Lvl.getLevel();
    }

    public int getExp() { return Lvl.getExperience();}

    public int getExpToNextLevel() { return Lvl.getExpToNextLevel();}

    public int getMoney(){
        return Coffer.getMoney();
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Armor getEquipArmor() {
        return EquipArmor;
    }

    public void setEquipArmor(Armor equipArmor) {
        EquipArmor = equipArmor;
    }

    public Weapon getEquipWeapon() {
        return EquipWeapon;
    }

    public AttackOr getAtOr(){return getEquipWeapon().getAttackOrientation();}

    public int getWeaRange(){return getEquipWeapon().getRange();}

    public void setEquipWeapon(Weapon equipWeapon) {
        EquipWeapon = equipWeapon;
    }

    public String getWeaponTag(){return getEquipWeapon().getTag();}

    public Ring getEquipRing() {
        return EquipRing;
    }

    public void setLevel(int lvl) {Lvl.setLevel(lvl); }

    public void setEquipRing(Ring equipRing) {
        EquipRing = equipRing;
    }

    public int getArrowCount() {
        return quiver.getArrowCount();
    }

    public void modifyArrowCount(int delta) {
        quiver.modifyArrowCount(delta);
    }



    public Boolean isDead() {
        if (HP.isDead()){
            HP.refillHP();
            return true;
        }
        else {
            return false;
        }
    }

    public void gainExp(int expAmt){
        int check = Lvl.gainExp(expAmt);
        if (check > -1){ // This means a level up has occurred.
            HP.raiseBaseStat((int)Math.log10((1.247*(Lvl.getLevel() * 100))));
            MP.raiseBaseStat((int)Math.log10((1.1578*(Lvl.getLevel() * 100))));
            Atk.raiseBaseStat((int)(Math.log10((Lvl.getLevel()))*5));
            Def.raiseBaseStat((int)(Math.log10((Lvl.getLevel()))*3));
            gainExp(expAmt);
        }
    }

    public void unequipArmor(){
        if (EquipArmor != null) {
            //inventory.addItem(EquipArmor);
            // Remove Modifier
        } else {
            return;
        }
    }
    public void unequipWeapon(){
        if (EquipWeapon != null) {
            //inventory.addItem(EquipWeapon);
            // Remove Modifier
        } else {
            return;
        }
    }
    public void unequipRing(){
        if (EquipRing != null) {
            //inventory.addItem(EquipRing);
            // Remove Modifier
        } else {
            return;
        }
    }
    
    public void equipGear(Equipment e) {
        if (e instanceof Armor) { 
            unequipArmor();
            EquipArmor = (Armor) e;
        } 
        else if (e instanceof Weapon) {
            unequipWeapon();
            EquipWeapon = (Weapon) e;
        } 
        else if (e instanceof Ring) {
            unequipRing();
            EquipRing = (Ring) e;
        } 
        else {
            return;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getStatNames(){
        String[] s = {"Health", "Mana", "Attack", "Defense", "Level", "Experience","Experience to Next Level","Money"};
        return s;
    }

    public int[] getStats(){
        int[] i = {HP.getHealthPoints(),MP.getMagicPoints(),Atk.getAttackPoints(),Def.getDefensePoints(),Lvl.getLevel(),Lvl.getExperience(),Lvl.getExpToNextLevel(),Coffer.getMoney()};
        return i;
    }

    public ArrayList<String> getStatsAsStringList() {
        ArrayList<String> statsList = new ArrayList<>();
        statsList.add("Health: " + Integer.toString(HP.getHealthPoints()));
        statsList.add("MP: " + Integer.toString(MP.getMagicPoints()));
        statsList.add("Attack: " + Integer.toString(Atk.getAttackPoints()));
        statsList.add("Defense: " + Integer.toString(Def.getDefensePoints()));
        statsList.add("Level: " + Lvl.getLevel());
        statsList.add("Experience: " + Lvl.getExperience());
        statsList.add("Next Lvl: " + Lvl.getExpToNextLevel());
        statsList.add("Gold: " + Coffer.getMoney());

        return statsList;
    }

    public boolean checkCast(int mpCost){ return MP.checkCast(mpCost); }

    public boolean checkUse(int hpCost){ return HP.checkUse(hpCost); }

    public boolean checkLvl(Level lvl) {return Lvl.checkLevel(lvl); }


    public void addToInventory(Item i){
        inventory.addItem(i);
    }

    public boolean isAttemptAttack() {
        return attemptAttack;
    }

    public void setAttemptAttack(boolean attemptAttack) {
        this.attemptAttack = attemptAttack;
    }

    public boolean isAttemptInteract() { return attemptInteract; }

    public void setAttemptInteract(boolean attemptInteract) { this.attemptInteract = attemptInteract; }

    public void talk() {}

    public Point getForewardPosition(int n) {
        Point pos = getPosition();
        if(getOrientation().getDegree() == 0) {
            return new Point(pos.x+n, pos.y);
        } else if(getOrientation().getDegree() == 45) {
            return new Point(pos.x+n, pos.y+n);
        } else if(getOrientation().getDegree() == 90) {
            return new Point(pos.x, pos.y+n);
        } else if(getOrientation().getDegree() == 135) {
            return new Point(pos.x-n, pos.y+n);
        } else if(getOrientation().getDegree() == 180) {
            return new Point(pos.x-n, pos.y);
        } else if(getOrientation().getDegree() == 225) {
            return new Point(pos.x-n, pos.y-n);
        } else if(getOrientation().getDegree() == 270) {
            return new Point(pos.x, pos.y-n);
        } else if(getOrientation().getDegree() == 315) {
            return new Point(pos.x+n, pos.y-n);
        } else {
            return new Point(pos.x+n, pos.y);
        }
    }
}
