package Model;

import java.util.ArrayList;

public class SkillsMenu extends SubMenu {
    private Player player;
    public SkillsMenu(Player player) {
        super("Skills");
        this.player = player;
    }

    @Override
    ArrayList<String> generateMenuList() {
        return player.getSkillsAsStringList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        return menuState;
    }

    @Override
    void Enter(int mouseX, int mouseY) {

    }

    @Override
    int subMenuSize() {
        return player.getNumSkills();
    }
}
