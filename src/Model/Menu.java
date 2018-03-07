package Model;

import Controller.MenuController;

import javafx.scene.canvas.Canvas;
import java.util.ArrayList;

public class Menu {
    MenuController controller;
    Canvas canvas;
    ArrayList<SubMenu> SubMenus;
    private int selectedInd;
    private boolean open;
    public Menu(Canvas canvas) {

        this.canvas = canvas;
        controller = new MenuController(this);
        SubMenus = new ArrayList<>();

        canvas.setOnKeyPressed(controller);
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
        return SubMenus.get(selectedInd).generateMenuState(getMenuList());
    }

    public boolean isOpen() {
        return open;
    }
}
