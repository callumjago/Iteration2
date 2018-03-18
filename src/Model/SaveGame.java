package Model;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveGame {

   private GameState gs;
   private int mapID;

    public SaveGame(GameState gs) {
        this.gs = gs;
        mapID = gs.getPlayer().getMapID();
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
<<<<<<< HEAD
            pw.println("Angle: " + gs.getPlayer().getOrientation().getDegree());
            pw.println("Class: " + gs.getPlayer().getRole());
=======
            pw.println("Angle: " + gs.getPlayer().getOrientation());
            pw.println("Class: " + "0");//gs.getPlayer().getClass());
<<<<<<< HEAD
            pw.println("Sprite: " + gs.getPlayer().getSprite());
=======
>>>>>>> dab258343d0a6c333f7399a3d7e4cb9c5eae3ea8
            //TODO. player has no sprite attribute --->>>> pw.write("Sprite: " );
            pw.println("Sprite: " + 1);
            
>>>>>>> 3316020ffb495c7eaaa265e42e21276134b45b8d
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
            pw.print(tiles.get(0).size() + " " + tiles.size());
            pw.println();
            
            for(int i = 0; i < tiles.get(0).size(); i++){
                for(int j = 0; j < tiles.size(); j++){
                    pw.print(tiles.get(j).get(i).getTerrainID());
                    
                    int objID = tiles.get(j).get(i).getTileObjectID();
                    
                    switch(objID) {
                    case 0:
                    	pw.print("A00");
                    	break;
                    case 1:
                    	pw.print("B00");
                    	break;
                    case 2:
                    	pw.print("C00");
                    	break;
                    case 4: 
                    	pw.print('D');
                    	if(tiles.get(j).get(i).getObject().getValue() < 9)
                    		pw.print("0" + tiles.get(j).get(i).getObject().getValue());
                    	else
                    		pw.print(tiles.get(j).get(i).getObject().getValue());
                    	break;
                    case 5:
                    	pw.print('E');
                    	if(tiles.get(j).get(i).getObject().getValue() < 9)
                    		pw.print("0" + tiles.get(j).get(i).getObject().getValue());
                    	else
                    		pw.print(tiles.get(j).get(i).getObject().getValue());
                    	break;
                    case 6:
                    	pw.print('F');
                    	if(tiles.get(j).get(i).getObject().getValue() < 9)
                    		pw.print("0" + tiles.get(j).get(i).getObject().getValue());
                    	else
                    		pw.print(tiles.get(j).get(i).getObject().getValue());
                    	break;
                    case 7:
                    	pw.print('G');
                    	if(tiles.get(j).get(i).getObject().getValue() < 9)
                    		pw.print("0" + tiles.get(j).get(i).getObject().getValue());
                    	else
                    		pw.print(tiles.get(j).get(i).getObject().getValue());
                    	break;
                    case 8:
                    	if(tiles.get(j).get(i).getObject() instanceof Equipment) {
                    		pw.print('K');
                    		
                    		if(((Equipment)tiles.get(j).get(i).getObject()).getEQID() < 9)
                    			pw.print("0" + ((Equipment)tiles.get(j).get(i).getObject()).getEQID());
                    		else
                    			pw.print(((Equipment)tiles.get(j).get(i).getObject()).getEQID());
                    	}
                    	
                    	else {
                    		pw.print('H');
                    		pw.print(((Item)tiles.get(j).get(i).getObject()).getItemID());
                    	}
                    	break;
                    case 9:
                    	pw.print('I');
                    	
                    	if(((OneShotItem)tiles.get(j).get(i).getObject()).getOneShotID() < 9)
                    		pw.print("0" + ((OneShotItem)tiles.get(j).get(i).getObject()).getOneShotID());
                    	else
                    		pw.print(((OneShotItem)tiles.get(j).get(i).getObject()).getOneShotID());
                    	break;
                    }
                    
                    pw.print(" ");
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
