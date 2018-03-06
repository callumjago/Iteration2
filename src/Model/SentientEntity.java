package Model;

public abstract class SentientEntity extends Entity {
    private Inventory inventory;
    private Equipment[] equipment;
    private Health HP;
    private Mana MP;
    private Attack Atk;
    private Defense Def;
    private Level Lvl;
    private Wallet Coffer;

    public void modifyHP(int delta){
        HP.modifyHealth(delta);
    }

    public void modifyMP(int delta){
        MP.modifyMana(delta);
    }

    public void modifyMoney(int delta){
        Coffer.modifyMoney(delta);
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

    public Boolean isDead(){
        return HP.isDead();
    }
}
