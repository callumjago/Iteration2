package Model;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveGame {

   private GameState gs;


    public SaveGame(GameState gs) {
        this.gs = gs;
    }

    public void saveGame(){
        savePlayer();
        saveMap();
        saveInventory();
        saveNPC();

    }
    public void savePlayer() {

        try {
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/"
                    + gs.getPlayer().getName() + "/Player/Player.txt");

            pw.println("Location: " + (int)gs.getPlayer().getPosition().getX() + ", " + (int)gs.getPlayer().getPosition().getY());
            pw.println("Armor: " + gs.getPlayer().getEquipArmor());
            pw.println("Weapon: " + gs.getPlayer().getEquipWeapon());
            pw.println("Ring: " + gs.getPlayer().getEquipRing());
            pw.println("HP: " + gs.getPlayer().getHP());
            pw.println("MP: " + gs.getPlayer().getMP());
            pw.println("Attack: " + gs.getPlayer().getAtk());
            pw.println("Defense: " + gs.getPlayer().getDef());
            pw.println("Level: " + gs.getPlayer().getLvl());
            pw.println("Exp: " + gs.getPlayer().getExp());
            //pw.println("ExpToLvl: " + gs.getPlayer().getExpToNextLevel());
            pw.println("Class: " + gs.getPlayer().getClass());
            //TODO. player has no sprite attribute --->>>> pw.write("Sprite: " );
            pw.println("Name: " + gs.getPlayer().getName());
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void saveMap(){
        try{
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/"
                    + gs.getPlayer().getName() + "/Maps/Map3.txt");

            ArrayList<ArrayList<Tile>> tiles = gs.getTileSet();
            for(int i = 0; i < tiles.size(); i++){
                for(int j = 0; j < tiles.get(i).size(); j++){
                    pw.print(tiles.get(j).get(i).getTerrainID());
                    pw.print(tiles.get(j).get(i).getTileObjectID() + " ");

                    //if(tiles.get(i).get(j).getTileObjectID() == );

                }
                pw.println();
            }

        pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }




    }
    public void saveInventory(){
       try {
           PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/"
                                            + gs.getPlayer().getName() + "/Player/Inventory.txt");

            ArrayList<Item> itemList = gs.getPlayer().getInventory().getBag();

            for(int i = 0; i < itemList.size(); i++){
                pw.write(itemList.get(i).getObjectID() + itemList.get(i).getItemID() + ",");
            }
           pw.close();
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    public void saveNPC(){
        ArrayList<Entity> npc = gs.getEntities();

        try{
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/"
                    + gs.getPlayer().getName() + "/Maps/NPC1.txt");


            //Name Sprite Position Armor Weapon Ring HP MP Def Atk Lvl Exp Angle
            //Projectile Sprite Position damage range Angle
                for(int i = 2; i < npc.size();i++){
                    if(npc.get(i) instanceof SentientEntity){
                        pw.print(((SentientEntity) npc.get(i)).getName() + " ");
                        //TODO sprite field for entity
                       // pw.print(((SentientEntity) npc.get(i)).getSprite() + " ");
                        pw.print((int)((SentientEntity) npc.get(i)).getPosition().getX() + " " +
                                (int)((SentientEntity) npc.get(i)).getPosition().getY() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getEquipArmor() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getEquipWeapon() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getEquipRing() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getHP() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getMP() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getDef() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getAtk() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getLvl() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getExp() + " ");
                        pw.print(((SentientEntity) npc.get(i)).getOrientation() + " ");
                    }
                    else{
                        pw.print("Projectile ");
                        //TODO sprite field for entity
                       // pw.print(((Projectile) npc.get(i)).getSprite() + " ");
                        pw.print(((Projectile) npc.get(i)).getDamage() + " ");
                        pw.print((int)((Projectile) npc.get(i)).getPosition().getX() + " " +
                                (int)((Projectile) npc.get(i)).getPosition().getY() + " ");
                        pw.print(((Projectile) npc.get(i)).getRange() + " ");
                        pw.print(((Projectile) npc.get(i)).getOrientation() + " ");


                    }
                    pw.println();

            }




        pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
