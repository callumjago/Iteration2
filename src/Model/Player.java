package Model;

import java.awt.*;

public class Player extends SentientEntity {
    //private PlayerClass Class;

    Player(Point pos, Angle theta, Image img, String name, Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,img,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        //Class = new PlayerClass();
    }

    Player() {
        super(); // Attribute classes fill with default values
        //Class = new PlayerClass();
    }
	
	 public void addItem(Item item) {
        getInventory().addItem(item);
	}
}
