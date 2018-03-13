package Model;

import Controller.PlayerController;
import View.Bound;

import java.util.ArrayList;

public class ControlsMenu extends SubMenu {
    private PlayerController pc;
    private ControlsMenuItem cmi;
    public ControlsMenu(PlayerController pc) {
        super("Controls");
        this.pc = pc;

        cmi = new ControlsMenuItem();
        cmi.addButton(new Bound(475, 725, 150, 250), "Rebind");
    }

    @Override
    ArrayList<String> generateMenuList() {
        return pc.generateControlsList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        menuState.addMenuItem(cmi);
        return menuState;
    }

    @Override
    void Enter(int mouseX, int mouseY) {
        if(cmi.collisionCheckByName("Rebind", mouseX, mouseY)) {
            pc.startListenForRebind(getSubMenuSelectedIndex());
        }
    }

    @Override
    int subMenuSize() {
        return pc.getNumOfControls();
    }
}
