package Model;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveGame {

   private GameState gs;
   private int mapID;

    public SaveGame(GameState gs) {
        this.gs = gs;
        mapID = gs.getPlayer().getMapID();
        saveGame();
    }

    public void saveGame(){
        savePlayer();
        saveMap();
        saveInventory();
        saveNPC();
        saveNPCInv();

    }
    private void savePlayer() {

        try {
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Player/Player.txt");


            pw.println("Location: " + (int)gs.getPlayer().getPosition().getX() + " " + (int)gs.getPlayer().getPosition().getY());
            pw.println("MapID: " + gs.getPlayer().getMapID());
            pw.println("Armor: " + gs.getPlayer().getEquipArmor().getEQID());
            pw.println("Weapon: " + gs.getPlayer().getEquipWeapon().getEQID());
            pw.println("Ring: " + gs.getPlayer().getEquipRing().getEQID());
            pw.println("HP: " + gs.getPlayer().getHP());
            pw.println("MP: " + gs.getPlayer().getMP());
            pw.println("Attack: " + gs.getPlayer().getAtk());
            pw.println("Defense: " + gs.getPlayer().getDef());
            pw.println("Level: " + gs.getPlayer().getLvl());
            pw.println("Exp: " + gs.getPlayer().getExp());
            pw.println("Wallet: " + gs.getPlayer().getWallet().getMoney());
            pw.println("Class: " + "0");//gs.getPlayer().getClass());
            //TODO. player has no sprite attribute --->>>> pw.write("Sprite: " );
            pw.println("Sprite: " + 1);
            //TODO sprite

            pw.println("Name: " + gs.getPlayer().getName());
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    private void saveMap(){
        try{
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Maps/Map"
                                + mapID + "/Map" + mapID + ".txt");

            ArrayList<ArrayList<Tile>> tiles = gs.getTileSet();
            pw.print(tiles.size() + " " + tiles.get(0).size());
            pw.println();
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
    private void saveInventory(){
       try {
           PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Player/Inventory.txt");

            ArrayList<Item> itemList = gs.getPlayer().getInventory().getBag();

            for(int i = 0; i < itemList.size(); i++){
            	if(itemList.get(i).getItemID() > 9)
            		pw.write(itemList.get(i).getObjectID() + "" + itemList.get(i).getItemID() + " ");
            	else
            		pw.write(itemList.get(i).getObjectID() + "" + "0" + "1" + " ");
            }
           pw.close();
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    private void saveNPC(){
        ArrayList<Entity> npc = gs.getEntities();


        //System.out.println("THE NUMBER OF NENEITES: " + npc.size());

        try{
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Maps/Map"
                    + mapID + "/NPC" + mapID + ".txt");


            //Name Sprite Position Armor Weapon Ring HP MP Def Atk Lvl Exp Angle Wallet
            //Projectile Sprite Position damage range Angle
                for(int i = 2; i < npc.size();i++){
                    if(npc.get(i) instanceof SentientEntity){
                        pw.print(((SentientEntity) npc.get(i)).getName() + " ");
                        //TODO sprite field for entity
                       // pw.print(((SentientEntity) npc.get(i)).getSprite() + " ");
                        pw.print((int)((SentientEntity) npc.get(i)).getPosition().getX() + " "+ (int)((SentientEntity) npc.get(i)).getPosition().getY() + " ");
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
                        pw.print(((SentientEntity) npc.get(i)).getWallet().getMoney());
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
   private void saveNPCInv(){
       ArrayList<Entity> npc = gs.getEntities();
       try {
           PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Maps/Map"
                   + mapID + "/Inventory" + mapID + ".txt");

           for (int i = 2; i < npc.size(); i++) {
               if (npc.get(i) instanceof SentientEntity) {
                   ArrayList<Item> itemList = ((SentientEntity) npc.get(i)).getInventory().getBag();

                   //TODO REMOVE THIS
                   System.out.println("ITEM LIST SIZE: " + itemList.size());

                   for(int j = 0; j < itemList.size(); j++) {
                       //TODO REMOVE THIS
                       System.out.println("OBJECT ID: " + itemList.get(j).getObjectID());
                       System.out.println("ITEM ID: " + itemList.get(j).getItemID());
                       pw.write(itemList.get(j).getObjectID() + "" + itemList.get(j).getItemID() + " ");
                   }
                    pw.println();
               }

               else{
                   continue;
               }
           }
          pw.close();
       }catch(Exception e){
           e.printStackTrace();
       }
   }
}
