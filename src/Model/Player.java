package Model;
import Controller.PlayerController;
import java.awt.*;

public class Player extends SentientEntity {
    //private PlayerClass Class;
   private int mapID;
   //holds the index to then player sprite
   private int sprite;

    Player(Point pos, Angle theta, String name, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        //Class = new PlayerClass();
    }

    Player() {
        super(); // Attribute classes fill with default values
        //Class = new PlayerClass();

    }
	
    public void addItem(Item item) {
        getInventory().addItem(item);
	}

    public int getMapID(){
        return mapID;
    }

    public void setMapID(int mapID){
        this.mapID = mapID;
    }
    
    public int getSprite() {
    	return sprite;
    }
    
    public void setSprite(int _sprite) {
    	sprite = _sprite;
    }

}
