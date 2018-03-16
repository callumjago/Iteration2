package Model;

public class PlayerDeath {

    private Player player;
    private MainMenuHandler mainMenu;

    private boolean isDead = false;

    public PlayerDeath(Player player, MainMenuHandler mainMenu) {
        this.player = player;
        this.mainMenu = mainMenu;
    }

    public void checkIfDead() {
        if (player.isDead()) {
            mainMenu.changeMenu(4);
        }
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
}
