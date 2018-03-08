package Model;

import java.awt.*;

public class Player extends SentientEntity {
    //private PlayerClass Class;

    Player(Point pos, Angle theta, Image img, String name, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney){
        super(pos,theta,img,name,initHP,initMP,initAtk,initDef,initLvl,initMoney);
        //Class = new PlayerClass();
    }

    Player() {
        super(); // Attribute classes fill with default values
        //Class = new PlayerClass();
    }
}
