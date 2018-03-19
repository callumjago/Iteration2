package Model;

import View.Bound;

import java.util.ArrayList;

public class SaveGameMenu extends SubMenu {
    private SaveGame save;
    private ConfirmationMenuItem confirmation;
    private final String saveButtonText = "Save";
    public SaveGameMenu(SaveGame save) {
        super("Save Game");
        this.save = save;

        confirmation = new ConfirmationMenuItem();
        Bound itemBound = confirmation.getBound();
        confirmation.addButton(new Bound(itemBound.getBoundLeft()+100, itemBound.getBoundLeft()+300,
                itemBound.getBoundTop()+200, itemBound.getBoundTop()+300), saveButtonText);
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

    @Override
    void Enter(int mouseX, int mouseY) {
        if(confirmation.collisionCheckByName(saveButtonText, mouseX, mouseY)) {
            save.saveGame();
        }
    }

    @Override
    int subMenuSize() {
        return 0;
    }
}
