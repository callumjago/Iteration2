package Model;

import java.awt.*;
import java.util.ArrayList;

public class Weapon extends Equipment {

    private int AttackPoints;
    private AttackOr AttackOrientation;
    private int AttackSpeed;
    private Accuracy Acy;
    private int Range;
    private String tag;

    public Weapon(int ItemID, int EQID, Level reqLvl, String name, String description, int attackPoints, AttackOr attackOrientation, int attackSpeed, Accuracy accuracy, int range, String tag_) {
        super(ItemID, EQID, reqLvl, name, description);
        AttackPoints = attackPoints;
        AttackOrientation = attackOrientation;
        AttackSpeed = attackSpeed;
        Acy = accuracy;
        Range = range;
        tag = tag_;
    }

    public Weapon(){
        super();
        // Change default values as needed
        AttackPoints = 0;
        AttackSpeed = 0;
        Acy = new Accuracy();
        Range = 0;
    }

    @Override
    public int getValue() {
        return AttackPoints;
    }

    public int getAttackPoints() {
        return AttackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        AttackPoints = attackPoints;
    }

    public AttackOr getAttackOrientation() {
        return AttackOrientation;
    }

    public void setAttackOrientation(AttackOr attackOrientation) {
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

    public String getTag() { return tag; }

    @Override
    ArrayList<String> getStats() {
        ArrayList<String> stats = new ArrayList<>();
        stats.add("Name: " + getName());
        stats.add("Attack: " + Integer.toString(AttackPoints));
        stats.add("Speed: " + Integer.toString(AttackSpeed));
        stats.add("Accuracy: " + Integer.toString(Acy.getAccuracy()));
        stats.add("Range: " + Integer.toString(Range));
        return stats;
    }

}
