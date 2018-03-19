package View;

import Model.DisplayItem;
import Model.LevelUpMenu;
import Model.LevelUpMenuItem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;


public class LevelUpMenuView {
    private Canvas canvas;
    private GraphicsContext gc;
    private Bound backgroundBound;
    private LevelUpMenuItem levelUpMenuItem;
    private MenuItemView menuItemView;

    public LevelUpMenuView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();

        backgroundBound = new Bound(100, (int)canvas.getWidth()-100, 100, (int)canvas.getHeight()-100);
        levelUpMenuItem = new LevelUpMenuItem(backgroundBound);

        menuItemView = new MenuItemView(canvas);
    }

    //Update levelupmenuitem based on levelup menu, then pass to menuitemview for rendering
    public void render(LevelUpMenu levelUpMenu, Point mouseCoords) {

        DisplayItem levelUpText = new DisplayItem("LEVEL UP!!!", new Bound(120,
                (int)canvas.getWidth()-120, 120, 180), Color.BEIGE);
        levelUpMenuItem.addElement(levelUpText);

        ArrayList<String> skillNames = levelUpMenu.getSkillNames();
        ArrayList<Integer> skillValues = levelUpMenu.getSkillValues();
        for(int i = 0; i < levelUpMenu.getNumSkills(); i++) {
            int displayValue = skillValues.get(i) + levelUpMenu.getSkillChangeValues().get(i);
            DisplayItem skillItem = new DisplayItem(skillNames.get(i)+ ": " +Integer.toString(displayValue),
                    new Bound(120, (int)canvas.getWidth()-120, 185+(60*i), 240+(60*i)), Color.BROWN);
            levelUpMenuItem.addElement(skillItem);

            DisplayItem skillDown = new DisplayItem("-", levelUpMenu.getMinusBound(i, (int)canvas.getWidth()), Color.RED);
            levelUpMenuItem.addElement(skillDown);

            DisplayItem skillUp = new DisplayItem("+", levelUpMenu.getPlusBound(i, (int)canvas.getWidth()), Color.RED);
            levelUpMenuItem.addElement(skillUp);
        }
        levelUpMenuItem.addElement(new DisplayItem("Confirm", levelUpMenu.getConfirmButtonBound((int)canvas.getWidth()), Color.RED));

        menuItemView.renderMenuItem(levelUpMenuItem);

        renderItemBorder(backgroundBound);
        levelUpMenuItem.clearElements();

    }

    private void renderItemBorder(Bound bound) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.strokeLine(bound.getBoundLeft(), bound.getBoundTop(), bound.getBoundRight(), bound.getBoundTop());
        gc.strokeLine(bound.getBoundLeft(), bound.getBoundTop(), bound.getBoundLeft(), bound.getBoundBottom());
        gc.strokeLine(bound.getBoundRight(), bound.getBoundTop(), bound.getBoundRight(), bound.getBoundBottom());
        gc.strokeLine(bound.getBoundLeft(), bound.getBoundBottom(), bound.getBoundRight(), bound.getBoundBottom());
    }
}
