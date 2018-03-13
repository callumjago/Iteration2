package Model;
import Controller.PlayerController;
import java.awt.*;

public class Player extends SentientEntity {
    //private PlayerClass Class;
    private PlayerController pc;
    Player(Point pos, Angle theta, Image img, String name, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,img,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        //Class = new PlayerClass();
    }

    Player() {
        super(); // Attribute classes fill with default values
        //Class = new PlayerClass();
        pc = new PlayerController(this);
    }
	
    public void addItem(Item item) {
        getInventory().addItem(item);
	}
    public void moveUp() {
        System.out.println("TEST");
        setPosition(new Point(getPosition().x, --getPosition().y));
    }
    public void moveDown() {
        setPosition(new Point(getPosition().x, ++getPosition().y));
    }
    public void moveLeft() {
        setPosition(new Point(--getPosition().x, getPosition().y));
    }
    public void moveRight() {
        setPosition(new Point(++getPosition().x, getPosition().y));
    }


    public PlayerController getPc() {
        return pc;
    }

    public boolean isInputRegistered() {
        return pc.isInputRegistered();
    }

    public void reset() {
        pc.resetInputRegistered();
    }
}
