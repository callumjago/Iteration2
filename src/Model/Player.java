package Model;
import Controller.PlayerController;
import java.awt.*;

public class Player extends SentientEntity {
    private Boolean Sneaking;
    private PlayerClass Class;

    Player(Point pos, Angle theta, String name, PlayerClass PC, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        Sneaking = false;
        Class = PC;
    }

    Player() {
        super(); // Attribute classes fill with default values
        Class = new Rogue();
        Sneaking = false;
    }
	
    public void addItem(Item item) {
        getInventory().addItem(item);
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
}
