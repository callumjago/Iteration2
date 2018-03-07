package Model;

import View.Bound;

import java.util.ArrayList;

public class QuitGameMenu extends SubMenu{
    private ConfirmationMenuItem confirmation;
    public QuitGameMenu(int index) {
        super(index, "Quit Game");
        confirmation = new ConfirmationMenuItem();
        Bound itemBound = confirmation.getBound();
        confirmation.addButton(new Bound(itemBound.getBoundLeft()+100, itemBound.getBoundLeft()+300,
                itemBound.getBoundTop()+200, itemBound.getBoundTop()+300), "Confirm");

    }
    @Override
    ArrayList<String> generateMenuList() {
        return new ArrayList<String>();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        menuState.addMenuItem(confirmation);
        return menuState;
    }

    //If mouse is in bounds of confirm box, quit game
    @Override
    void Enter(int mouseX, int mouseY) {
        if(confirmation.collisionCheckByName("Confirm", mouseX, mouseY)) {
            System.exit(0);
        }
    }

    @Override
    int subMenuSize() {
        return 0;
    }
}
