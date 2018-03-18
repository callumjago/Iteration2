package Model;

import View.Bound;

import java.util.ArrayList;

public class SkillsMenu extends SubMenu {
    private SkillMenuItem smi;
    private Player player;
    public SkillsMenu(Player player) {
        super("Skills");
        this.player = player;
        smi = new SkillMenuItem();
    }

    @Override
    ArrayList<String> generateMenuList() {
        return player.getSkillsAsStringList();
    }

    @Override
    MenuState generateSubMenuState(MenuState menuState) {
        updateSkillMenuItem();
        menuState.addMenuItem(smi);
        menuState.setScrollOffset(getScrollOffset());
        return menuState;
    }

    @Override
    void Enter(int mouseX, int mouseY) {
        if(smi.collisionCheckByName("Activate Skill", mouseX, mouseY)) {
            player.applySkill(getSubMenuSelectedIndex());
        }
    }

    @Override
    int subMenuSize() {
        return player.getNumSkills();
    }

    private void updateSkillMenuItem() {
        smi.setStats(player.getSkillStats(getSubMenuSelectedIndex()));
        smi.addButton(new Bound(450, 750, 675, 750), "Activate Skill");
    }

    public void scrollUp() {
        if(getScrollOffset() > 0) {
            setScrollOffset(getScrollOffset()-1);
        }
    }
    public void scrollDown() {
        if(getScrollOffset() < player.getNumSkills()-8) {
            setScrollOffset(getScrollOffset()+1);
        }

    }
}
