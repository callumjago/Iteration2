package Model;

import java.io.PrintWriter;

public class SaveGame {

   private GameState gs;


    public SaveGame(GameState gs) {
        this.gs = gs;
    }

    public void saveGame(){
        savePlayer();
        saveMap();
        saveInventory();

    }
    public void savePlayer() {

        try {
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "TestPlayer.txt");

            pw.write("Location: " + gs.getPlayer().getPosition().getX() + ", " + gs.getPlayer().getPosition().getY());
            pw.write("Armor: " + gs.getPlayer().getEquipArmor());
            pw.write("Weapon: " + gs.getPlayer().getEquipWeapon());
            pw.write("Ring: " + gs.getPlayer().getEquipRing());
            pw.write("HP: " + gs.getPlayer().getHP());
            pw.write("MP: " + gs.getPlayer().getMP());
            pw.write("Attack: " + gs.getPlayer().getAtk());
            pw.write("Defense: " + gs.getPlayer().getDef());
            pw.write("Level: " + gs.getPlayer().getLvl());
            pw.write("Exp: " + gs.getPlayer().getExp());
            pw.write("ExpToLvl: " + gs.getPlayer().getExpToNextLevel());
            pw.write("Class: " + gs.getPlayer().getClass());
            //TODO. player has no sprite attribute --->>>> pw.write("Sprite: " );
            pw.write("Name: " + gs.getPlayer().getName());
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void saveMap(){

    }
    public void saveInventory(){


    }
}
