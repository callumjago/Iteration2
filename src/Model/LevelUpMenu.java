package Model;

import View.Bound;
import javafx.scene.canvas.Canvas;
import java.awt.*;
import java.util.ArrayList;

public class LevelUpMenu {
    private PlayerClass playerClass;
    ArrayList<Integer> skillChangeValues;
    private boolean levelUpApplied;
    private int numOfSkills;
    private int pointsToApply;

    public LevelUpMenu(PlayerClass pc) {
        playerClass = pc;
        skillChangeValues = new ArrayList<>();
        levelUpApplied = false;
        numOfSkills = pc.getClassStatValues().size();
        pointsToApply = pc.getSkillPointsAssignable();
        System.out.println(pointsToApply);
        for(int i = 0; i < numOfSkills; i++) {
            skillChangeValues.add(0);
        }

    }

    public void incrementStatValue(int index) {
        if(index > skillChangeValues.size()) { return; }
        skillChangeValues.set(index, skillChangeValues.get(index)+1);
    }

    public ArrayList<Integer> getSkillChangeValues() {
        return skillChangeValues;
    }

    public void apply() {

        for(int i = 0; i < skillChangeValues.size(); i++) {
            System.out.println(skillChangeValues.get(i));
            for(int j = 0; j < skillChangeValues.get(i); j++) {
                playerClass.raiseSkill(i);
            }
        }
        levelUpApplied = true;
    }

    public boolean isLevelUpApplied() {
        return levelUpApplied;
    }

    public int getNumSkills() {
        return numOfSkills;
    }

    public ArrayList<String> getSkillNames() {
        return playerClass.getClassStats();
    }

    public ArrayList<Integer> getSkillValues() {
        return playerClass.getClassStatValues();
    }

    public void click(Point p, Canvas canvas) {
        for(int i = 0; i < getNumSkills(); i++) {
            if(getMinusBound(i, (int)canvas.getWidth()).collisionTest(p.x, p.y)) {//Clicked on minus
                if(skillChangeValues.get(i) > 0) {
                    skillChangeValues.set(i, skillChangeValues.get(i)-1);
                    pointsToApply++;
                }
            }
            if(getPlusBound(i, (int)canvas.getWidth()).collisionTest(p.x, p.y)) {//Clicked on minus
                if(pointsToApply > 0) {
                    skillChangeValues.set(i, skillChangeValues.get(i) + 1);
                    pointsToApply--;
                }

            }


        }
        if(getConfirmButtonBound((int)canvas.getWidth()).collisionTest(p.x, p.y)) {//Confirm clicked
            apply();
        }
    }

    public Bound getMinusBound(int index, int canvasWidth) {
        return new Bound((int)canvasWidth-230, (int)canvasWidth-190, 190+(60*index), 230+(60*index));
    }
    public Bound getPlusBound(int index, int canvasWidth) {
        return new Bound((int)canvasWidth-170, (int)canvasWidth-130, 190+(60*index), 230+(60*index));
    }
    public Bound getConfirmButtonBound(int canvasWidth) {
        return new Bound((canvasWidth/2)-100, (canvasWidth/2)+100, 550, 650);
    }

    public int getPointsToApply() {
        return pointsToApply;
    }
}
