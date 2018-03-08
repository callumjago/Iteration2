package Model;

import java.awt.*;

public abstract class SentientEntity extends Entity {
    private String Name;
    private Inventory inventory;
    //private Equipment[] equipment[];
    private Health HP;
    private Mana MP;
    private Attack Atk;
    private Defense Def;
    private Level Lvl;
    private Wallet Coffer;

    SentientEntity(Point pos, Angle theta, Image img, String name, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,img);
        Name = name;
        HP = new Health(initHP);
        MP = new Mana(initMP);
        Atk = new Attack(initAtk);
        Def = new Defense(initDef);
        Lvl = new Level(initLvl);
        Coffer = new Wallet(initMoney);
        //equipment = new Equipment()[3];
        inventory = new Inventory();
    }

    SentientEntity(){ // Attribute classes fill with default values
        super();
        Name = "H Y P E B O Y";
        HP = new Health(0);
        MP = new Mana(0);
        Atk = new Attack(0);
        Def = new Defense(0);
        Lvl = new Level(0);
        Coffer = new Wallet(0);
        //equipment = new Equipment()[3];
        inventory = new Inventory();
    }

    public void modifyHP(int delta){
        HP.modifyHealth(delta);
    }

    public void modifyMP(int delta){
        MP.modifyMana(delta);
    }

    public void modifyMoney(int delta){
        Coffer.modifyMoney(delta);
    }

    public void modifyAttack(int delta){
        Atk.modifyAttack(delta);
    }

    public void modifyDefense(int delta){
        Def.modifyDefense(delta);
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

   /* public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] e) {
        equipment = e;
    }*/

    public Boolean isDead(){
        return HP.isDead();
    }

    public void gainExp(int expAmt){
        int check = Lvl.gainExp(expAmt);
        if (check > 0){ // This means a level up has occured.
            HP.raiseMaxHealthPoints((int)Math.log10((1.247*(Lvl.getLevel() * 100))));
            MP.raiseMaxMagicPoints((int)Math.log10((1.247*(Lvl.getLevel() * 100)))-2);
            Atk.raiseBaseAttack((int)(Math.log10((Lvl.getLevel()))*5));
            Def.raiseBaseDefense((int)(Math.log10((Lvl.getLevel()))*3));
            gainExp(expAmt);
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
