package Model;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveGame {

   private GameState gs;
   private int mapID;

    public SaveGame(GameState gs) {
        this.gs = gs;
    }

    public void saveGame(){
    	mapID = gs.getPlayer().getMapID();
        savePlayer();
        saveMap();
        saveInventory();
        saveNPC();
        saveNPCInv();
        savePlayerSkills();
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
            pw.println("Angle: " + gs.getPlayer().getOrientation().getDegree());
            pw.println("Class: " + gs.getPlayer().getRole());

            pw.println("Sprite: " + gs.getPlayer().getSpriteIndex());
            //TODO. player has no sprite attribute --->>>> pw.write("Sprite: " );
            pw.println("Name: " + gs.getPlayer().getName());
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private void savePlayerSkills() {

        try {




            if(gs.getPlayer().getPlayerClass() instanceof Warrior){
                PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Player/WarriorSkills.txt");

               // System.out.println("WHATS THIS: "  + gs.getPlayer().getPlayerClass().getSkill(0).getLvl());

                pw.println("Observation: " + gs.getPlayer().getPlayerClass().getSkill(0).getLvl());
                pw.println("BindWounds: " + gs.getPlayer().getPlayerClass().getSkill(1).getLvl());
                pw.println("StunStrike: " + gs.getPlayer().getPlayerClass().getSkill(2).getLvl());
                pw.println("HeavyStrike: " + gs.getPlayer().getPlayerClass().getSkill(3).getLvl());
                pw.print("CrossSlash: " + gs.getPlayer().getPlayerClass().getSkill(4).getLvl());


                pw.close();

            }


            if(gs.getPlayer().getPlayerClass() instanceof Mage){
                PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Player/MageSkills.txt");

                // System.out.println("WHATS THIS: "  + gs.getPlayer().getPlayerClass().getSkill(0).getLvl());

                pw.println("Observation: " + gs.getPlayer().getPlayerClass().getSkill(0).getLvl());
                pw.println("BindWounds: " + gs.getPlayer().getPlayerClass().getSkill(1).getLvl());
                pw.println("BindEnchantment: " + gs.getPlayer().getPlayerClass().getSkill(2).getLvl());
                pw.println("BrainWash: " + gs.getPlayer().getPlayerClass().getSkill(3).getLvl());
                pw.println("ArcaneBash: " + gs.getPlayer().getPlayerClass().getSkill(4).getLvl());
                pw.println("ArcaneBurst: " + gs.getPlayer().getPlayerClass().getSkill(5).getLvl());
                pw.println("AttackBuff: " + gs.getPlayer().getPlayerClass().getSkill(6).getLvl());
                pw.println("CastLightning: " + gs.getPlayer().getPlayerClass().getSkill(7).getLvl());
                pw.println("Charm: " + gs.getPlayer().getPlayerClass().getSkill(8).getLvl());
                pw.println("DefenseBuff: " + gs.getPlayer().getPlayerClass().getSkill(9).getLvl());
                pw.println("FireBall: " + gs.getPlayer().getPlayerClass().getSkill(10).getLvl());
                pw.print("HealthBuff: " + gs.getPlayer().getPlayerClass().getSkill(11).getLvl());


                pw.close();

            }



            if(gs.getPlayer().getPlayerClass() instanceof Rogue){
                PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Player/RogueSkills.txt");

                // System.out.println("WHATS THIS: "  + gs.getPlayer().getPlayerClass().getSkill(0).getLvl());

                pw.println("Observation: " + gs.getPlayer().getPlayerClass().getSkill(0).getLvl());
                pw.println("BindWounds: " + gs.getPlayer().getPlayerClass().getSkill(1).getLvl());
                pw.println("ArrowHail: " + gs.getPlayer().getPlayerClass().getSkill(2).getLvl());
                pw.println("DetectTrap: " + gs.getPlayer().getPlayerClass().getSkill(3).getLvl());
                pw.print("RemoveTrap: " + gs.getPlayer().getPlayerClass().getSkill(4).getLvl());


                pw.close();

            }


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
                    	pw.print('M');
                    	if(tiles.get(j).get(i).getObject().getValue() < 9)
                    		pw.print("0" + tiles.get(j).get(i).getObject().getValue());
                    	else
                    		pw.print(tiles.get(j).get(i).getObject().getValue());
                    	break;
                    case 3:
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
                    case 10:
                    	pw.print('L');
                    	if(tiles.get(j).get(i).getObject().getValue() < 9)
                    		pw.print("0" + tiles.get(j).get(i).getObject().getValue());
                    	else
                    		pw.print(tiles.get(j).get(i).getObject().getValue());
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
            	if(itemList.get(i) instanceof Equipment) {
            		if(((Equipment)itemList.get(i)).getEQID() < 9)
            			pw.write("00" + ((Equipment)itemList.get(i)).getEQID());
            		else
            			pw.write('0' + ((Equipment)itemList.get(i)).getEQID());
            	}
            	
            	else if(itemList.get(i) instanceof UseItem) {
            		if(itemList.get(i).getItemID() < 9)
            			pw.write("10" + itemList.get(i).getItemID());
            		else
            			pw.write('1' + itemList.get(i).getItemID());
            	}
            	
            	else if(itemList.get(i) instanceof InteractiveItem) {
            		if(itemList.get(i).getItemID() < 9)
            			pw.write("20" + itemList.get(i).getItemID());
            		else
            			pw.write('2' + itemList.get(i).getItemID());
            	}
            }
           pw.close();
       }catch(Exception e){
           e.printStackTrace();
       }
    }

    private void saveNPC(){
        ArrayList<Entity> npc = gs.getEntities();


        //System.out.println("THE NUMBER OF NENEITES: " + npc.size());
        AI ai;

        try{
            PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/SavedGames/PlayerName/Maps/Map"
                    + mapID + "/NPC" + mapID + ".txt");

            System.out.println("the map id that the file will be saved is " + mapID);
            //Name Sprite Position Armor Weapon Ring HP MP Def Atk Lvl Exp Angle Wallet
            //Projectile Sprite Position damage range Angle
            pw.println("Name -     PointX - PointY - Angle - Armor - Weapon - Ring  -  Health  -  Mana - Attack - Defense - Level - Wallet - ExpValue - Tag -  MaxHP - Range");
            for(int i = 1; i < npc.size();i++){
                if(!(npc.get(i) instanceof Projectile)){
                    pw.print(((SentientEntity) npc.get(i)).getName() + "        ");
                    
                    //TODO sprite field for entity
                    // pw.print(((SentientEntity) npc.get(i)).getSprite() + " ");
                    pw.print((int)((SentientEntity) npc.get(i)).getPosition().getX() + "        "+ (int)((SentientEntity) npc.get(i)).getPosition().getY() + "      ");
                    pw.print(((SentientEntity) npc.get(i)).getOrientation().getDegree() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getEquipArmor().getEQID() + "        ");
                    pw.print(((SentientEntity) npc.get(i)).getEquipWeapon().getEQID() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getEquipRing().getEQID() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getHP() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getMP() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getAtk() + "          ");
                    pw.print(((SentientEntity) npc.get(i)).getDef() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getLvl() + "       ");
                    pw.print(((SentientEntity) npc.get(i)).getWallet().getMoney() + "         ");
                    pw.print(((SentientEntity) npc.get(i)).getExp() + "      ");
                    pw.print(((NPC) npc.get(i)).getTag() + "  ");
                    pw.print(((NPC) npc.get(i)).getMaxHP() + " ");
                    pw.print(((NPC)npc.get(i)).getDetectionRange());
                    
                    pw.println();
                    pw.println(((NPC) npc.get(i)).getDescription());

                    //TODO do we need a tag in the document?
                }
               /* else{
                    pw.print("Projectile ");
                    //TODO sprite field for entity
                    // pw.print(((Projectile) npc.get(i)).getSprite() + " ");
                    pw.print(((Projectile) npc.get(i)).getDamage() + " ");
                    pw.print((int)((Projectile) npc.get(i)).getPosition().getX() + " " +
                            (int)((Projectile) npc.get(i)).getPosition().getY() + " ");
                    pw.print(((Projectile) npc.get(i)).getRange() + " ");
                    pw.print(((Projectile) npc.get(i)).getOrientation().getDegree() + " ");

                }*/

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

           for (int i = 1; i < npc.size(); i++) {
               if (npc.get(i) instanceof SentientEntity) {
                   ArrayList<Item> itemList = ((SentientEntity) npc.get(i)).getInventory().getBag();

                   for(int x = 0; x < itemList.size(); x++){
                   	if(itemList.get(x) instanceof Equipment) {
                   		if(((Equipment)itemList.get(x)).getEQID() < 9)
                   			pw.write("00" + ((Equipment)itemList.get(x)).getEQID() + " ");
                   		else
                   			pw.write("0" + ((Equipment)itemList.get(x)).getEQID() + " ");
                   	}
                   	
                   	else if(itemList.get(x) instanceof UseItem) {
                   		if(itemList.get(x).getItemID() < 9)
                   			pw.write("10" + itemList.get(x).getItemID() + " ");
                   		else
                   			pw.write("0" + itemList.get(x).getItemID() + " ");
                   	}
                   	
                   	else if(itemList.get(x) instanceof InteractiveItem) {
                   		if(itemList.get(x).getItemID() < 9)
                   			pw.write("20" + itemList.get(x).getItemID() + " ");
                   		else
                   			pw.write("2" + itemList.get(x).getItemID() + " ");
                   	}
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
