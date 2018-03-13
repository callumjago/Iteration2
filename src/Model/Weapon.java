package Model;

import java.awt.*;

public class Weapon extends Equipment {

    private int AttackPoints;
    private int AttackOrientation;
    private int AttackSpeed;
    private Accuracy Acy;
    private int Range;

    public Weapon(int ObjID, Image Sprite, int ItemID, int EQID, Level reqLvl, String name, String description, int attackPoints, int attackOrientation, int attackSpeed, Accuracy accuracy, int range) {
        super(ObjID, Sprite, ItemID, EQID, reqLvl, name, description);
        AttackPoints = attackPoints;
        AttackOrientation = attackOrientation;
        AttackSpeed = attackSpeed;
        Acy = accuracy;
        Range = range;
    }

    public Weapon(){
        super();
        // Change default values as needed
        AttackPoints = 0;
        AttackOrientation = 0;
        AttackSpeed = 0;
        Acy = new Accuracy();
        Range = 0;
    }

    public int getAttackPoints() {
        return AttackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        AttackPoints = attackPoints;
    }

    public int getAttackOrientation() {
        return AttackOrientation;
    }

    public void setAttackOrientation(int attackOrientation) {
        AttackOrientation = attackOrientation;
    }

    public int getAttackSpeed() {
        return AttackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        AttackSpeed = attackSpeed;
    }

    public int getAccuracy() {
        return Acy.getAccuracy();
    }

    public void setAccuracy(int accuracy) {
        Acy.setAccuracy(accuracy);
    }

    public int getRange() {
        return Range;
    }

    public void setRange(int range) {
        Range = range;
    }
    
    public int getValue() {
    	return AttackPoints;
    }

}
