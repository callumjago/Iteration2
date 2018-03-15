package Model;

import Controller.MenuController;
import Controller.ScrollController;
import Controller.MenuMouseController;
import Controller.MenuClickHandler;

import View.MenuUI;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;

public class Menu {

    private MenuMouseController mmc;
    private MenuClickHandler mch;
    private ScrollController sc;

    private MenuUI ui;
    private Canvas canvas;
    private ArrayList<SubMenu> SubMenus;
    private int selectedInd;
    private int numOfSubMenus;
    private boolean open;



    public Menu(Canvas canvas) {

        this.canvas = canvas;

        mmc = new MenuMouseController();
        mch = new MenuClickHandler(this);
        sc = new ScrollController(this);

        ui = new MenuUI(canvas);
        SubMenus = new ArrayList<>();



        canvas.setOnMouseMoved(mmc);
        canvas.setOnMouseClicked(mch);
        canvas.setOnScroll(sc);

        selectedInd = 0;
        numOfSubMenus = 0;
        open = false;
    }

    public void addSubMenu(SubMenu menu) {
        menu.setMenuIndex(numOfSubMenus++);
        SubMenus.add(menu);
    }

    public void Escape() {
        System.out.println("OPENING");
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
        SubMenus.get(selectedInd).Enter(ui.resizeXCoord(mmc.getMouseX()), ui.resizeYCoord(mmc.getMouseY())); //This shit is garbage

    }

    public void Up() {
        if(!open) { return; }
        if(selectedInd > 0) {
            selectedInd--;
        }
    }
    public void Down() {
        if(!open) { return; }
        if(selectedInd < numOfSubMenus -1) {
            selectedInd++;
        }
    }

    public void scrollUp() {
        if(!open) { return; }
        if(ui.subMenuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY()) != -1) {
            SubMenus.get(selectedInd).scrollUp();
        }
    }
    public void scrollDown() {
        if(!open) { return; }
        if(ui.subMenuEntryCollisionTest(mmc.getMouseX(), mmc.getMouseY()) != -1) {
            SubMenus.get(selectedInd).scrollDown();
        }
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
