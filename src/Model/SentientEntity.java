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

    SentientEntity(Point pos, Angle theta, Image img, String name, Armor armor, Weapon weapon, Ring ring, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,img);
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
    }

    SentientEntity(){ // Attribute classes fill with default values
        super();
        Name = "H Y P E B O Y";
        HP = new Health();
        HP.setHealthPoints(100);
        MP = new Mana();
        Atk = new Attack();
        Def = new Defense();
        Lvl = new Level();
        Coffer = new Wallet();
        inventory = new Inventory();
        EquipWeapon = new Weapon();
        EquipWeapon.setRange(1);
        EquipWeapon.setAttackPoints(1);
        // Add starting equipment here
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

    public int getMP(){
        return MP.getMagicPoints();
    }

    public int getAtk(){
        return Atk.getAttackPoints();
    }

    public int getDef(){
        return Def.getDefensePoints();
    }

    public int getLvl(){
        return Lvl.getLevel();
    }

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

    public void setEquipWeapon(Weapon equipWeapon) {
        EquipWeapon = equipWeapon;
    }

    public Ring getEquipRing() {
        return EquipRing;
    }

    public void setEquipRing(Ring equipRing) {
        EquipRing = equipRing;
    }

    public Boolean isDead(){
        return HP.isDead();
    }

    public void gainExp(int expAmt){
        int check = Lvl.gainExp(expAmt);
        if (check > 0){ // This means a level up has occurred.
            HP.raiseBaseStat((int)Math.log10((1.247*(Lvl.getLevel() * 100))));
            MP.raiseBaseStat((int)Math.log10((1.247*(Lvl.getLevel() * 100)))-2);
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
}
