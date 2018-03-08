package Model;

import Controller.MenuController;

import Controller.MenuMouseController;
import Controller.MenuClickHandler;
import View.MenuUI;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;

public class Menu {
    private MenuController controller;
    private MenuMouseController mmc;
    private MenuClickHandler mch;
    private MenuUI ui;
    private Canvas canvas;
    private ArrayList<SubMenu> SubMenus;
    private int selectedInd;
    private boolean open;

    public Menu(Canvas canvas) {

        this.canvas = canvas;
        controller = new MenuController(this);
        mmc = new MenuMouseController();
        mch = new MenuClickHandler(this);
        ui = new MenuUI();
        SubMenus = new ArrayList<>();

        canvas.setOnKeyPressed(controller);
        canvas.setOnMouseMoved(mmc);
        canvas.setOnMouseClicked(mch);
        selectedInd = 0;
        open = false;
    }

    public void addSubMenu(SubMenu menu) {
        SubMenus.add(menu);
    }

    public void Escape() {
        System.out.println("Escape");
        open = !open;
    }
    public void Enter() {
        if(!open) {
            return;
        }
        if(ui.menuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY()) != -1) {//Menu entry clicked
            if(ui.menuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY()) >= SubMenus.size()) {//Out of bounds
                return;
            }
            selectedInd = ui.menuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY());
            return;
        }

        if(ui.subMenuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY()) != -1) {//Sub menu entry clicked
            SubMenus.get(selectedInd).setSubMenuSelectedIndex(ui.subMenuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY()));
            return;
        }

        //Tell sub menu to check for actions
        SubMenus.get(selectedInd).Enter(mmc.getMouseX(), mmc.getMouseY());

    }

    public ArrayList<String> getMenuList() {
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < SubMenus.size(); i++) {
            names.add(SubMenus.get(i).getName());
        }
        return names;
    }

    public MenuState getActiveMenuState() {
        if(SubMenus.size() == 0) {
            return null;
        }
        return SubMenus.get(selectedInd).generateMenuState(getMenuList(), mmc.getMouseX(), mmc.getMouseY());
    }

    public boolean isOpen() {
        return open;
    }
}
