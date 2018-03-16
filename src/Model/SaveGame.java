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

            //finish this
            pw.write("Level: ");
            pw.write("Exp: ");
            pw.write("ExpToLvl: ");

            pw.write("Class: ");
            // do this

           // pw.write("Sprite: " + gs.getPlayer().getSprite());
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
