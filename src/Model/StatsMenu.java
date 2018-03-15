package Model;

import java.util.ArrayList;

public class StatsMenu extends SubMenu {
    private Player player;
    public StatsMenu(Player player) {
        super("Player Stats");
        this.player = player;
    }

    @Override
    ArrayList<String> generateMenuList() {
        return player.getStatsAsStringList();
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
        return player.getStatsAsStringList().size();
    }
}
