package Model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGame {
    private GameState state;
    private Player player;
    private Inventory inventory;
    private int mapID;
    private String path;


    public LoadGame(GameState _state, Player _player, Inventory _inventory){
    	state = _state;
    	player = _player;
    	inventory = _inventory;
    	path = System.getProperty("user.dir");
    }
    
    public void loadGame() {
    	loadPlayer();
    	loadMap();
    	loadInventory();
    }

    public void loadMap(){
    	try {
			File mapFile = new File(path + "/GameFiles/Maps/Map" + mapID + ".txt");
			BufferedReader br_map = new BufferedReader(new FileReader(mapFile));
			Scanner s_map = new Scanner(br_map.readLine());
			
			ArrayList<ArrayList<Tile>> tileSet = new ArrayList<>();
			
			int mapY = Integer.parseInt(s_map.next());
			int mapX = Integer.parseInt(s_map.next());
			
			for(int i = 0; i < mapX; i++) {
				tileSet.add(new ArrayList<>());
			}
			
			for(int i = 0; i < mapY; i++) {
				String st = br_map.readLine();
				Scanner input = new Scanner(st);
				
				for(int j = 0; j < mapX; j++) {
					String temp = null;
					
					temp = input.next();
					
					Tile tile = new Tile((int)temp.charAt(0)-48);
					
					if(temp.charAt(1) == 'A') {
						tile.setObject(null);
					}
					else {
						int x;
						
						switch(temp.charAt(1)) {
						case 'B':
							tile.setObject(new Obstacle());
							break;
						case 'C':
							tile.setObject(new InstantDeath());
							break;
						case 'D':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new Teleport(x));
							break;
						case 'E':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new HealingAE(x));
							break;
						case 'F':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new DamageAE(x));
							break;
						case 'G':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new ExperienceAE(x));
							break;
						case 'H':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							ItemCodex icodex = new ItemCodex();
							String itag = icodex.getTag(x);
							
							if(itag == "InteractiveItem") {
								tile.setObject(new KeyItem(icodex.getLevelReq(x)));
							}
							
							else if(itag == "UseItem") {
								tile.setObject(new UseItem(x, icodex.getStatPoints(x), icodex.getName(x), icodex.getDescription(x)));
							}
							break;
						case 'I':
							x = (int)temp.charAt(3)-48;
							tile.setObject(new OneShotItem(x, 10));
							break;
						case 'J':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							NPC npc = new NPC();
							break;
						case 'K':
							int id = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							EquipmentCodex ecodex = new EquipmentCodex();
							String tag = ecodex.getTag(id);
							
							switch(tag) {
							
								case "fist":
								case "ranged":
								case "staff":
								case "two-handed":
								case "one-handed":
									int lvl = ecodex.getLevelReq(id);
									int damage = ecodex.getStatPoints(id);
									int attackSpeed = ecodex.getAttackSpeed(id);
									Accuracy accuracy = new Accuracy(ecodex.getAccuracy(id));
									Weapon weapon = new Weapon(id, new Level(lvl), ecodex.getName(id), ecodex.getDescription(id), 
											damage, 0, attackSpeed, accuracy, ecodex.getRange(id) );
								
									inventory.addItem(weapon);
									break;
								
								case "leather":
								case "cloth":
								case "plate":
									Level level = new Level(ecodex.getLevelReq(id));
									Armor armor = new Armor(id, level, ecodex.getName(id), ecodex.getDescription(id), 
											ecodex.getStatPoints(id));
								
									inventory.addItem(armor);
									break;
								
								case "ring":
									level = new Level(ecodex.getLevelReq(id));
									Ring ring = new Ring(id, level, ecodex.getName(id), ecodex.getDescription(id));
								
									inventory.addItem(ring);
									break;
								}
							
								break;
							}
					}
					
					tileSet.get(j).add(tile);
				}
				
				input.close();
			}
			state.setTileSet(tileSet);
			System.out.println(tileSet.size());
			System.out.println(tileSet.get(0).size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public void loadPlayer() {
    	try {
    		File file = new File(path + "/GameFiles/Player/Player.txt");
			BufferedReader br_map = new BufferedReader(new FileReader(file));
			
			EquipmentCodex ecodex = new EquipmentCodex();
			
			int i = 0;
			int id;
			
			while(true) {
				Scanner input;
				
				if(br_map != null)
					input = new Scanner(br_map.readLine());
				else break;
				
				switch(i) {
				case 0: //setting position
					input.next();
					int x = Integer.parseInt(input.next());
					int y = Integer.parseInt(input.next());
					player.setPosition(new Point(x, y));
					break;
				case 1: //setting mapID
					input.next();
					mapID = Integer.parseInt(input.next());
					break;
				case 2: //equiping armor
					input.next();
					id = Integer.parseInt(input.next());
					Armor armor = new Armor(id, new Level(ecodex.getLevelReq(id)), ecodex.getName(id), 
							ecodex.getDescription(id), ecodex.getStatPoints(id));
					
					player.setEquipArmor(armor);
					break;
				case 3: //equiping weapon
					input.next();
					id = Integer.parseInt(input.next());
					Weapon weapon = new Weapon(id, new Level(ecodex.getLevelReq(id)), ecodex.getName(id), ecodex.getDescription(id), 
							ecodex.getStatPoints(id), 0, ecodex.getAttackSpeed(id), new Accuracy(ecodex.getAccuracy(id)), ecodex.getRange(id));
					player.setEquipWeapon(weapon);
					break;
				case 4: //equiping ring
					input.next();
					id = Integer.parseInt(input.next());
					Ring ring = new Ring(id, new Level(ecodex.getLevelReq(id)), ecodex.getName(id), ecodex.getDescription(id));
					player.setEquipRing(ring);
					break;
				case 5: //setting hp
					input.next();
					player.setHP(Integer.parseInt(input.next()));
					break;
				case 6: //setting mp
					input.next();
					player.modifyMP(player.getMP() - Integer.parseInt(input.next()));
					break;
				case 7: //setting attack
					input.next();
					break;
				case 8: //setting Defense
					input.next();
					break;
				case 9: //setting level
					input.next();
					player.setLvl(new Level(Integer.parseInt(input.next())));
					break;
				case 10: //setting EXP
					input.next();
					player.gainExp(Integer.parseInt(input.next()));
					break;		
				case 11: //setting player class
					input.next();
					break;
				case 12: //setting player sprite
					input.next();
					break;
				case 13: //setting player Name
					input.next();
					player.setName(input.next());
					break;
				}
				
				++i;
				
				if(i > 13) break;
			}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void loadInventory() {
    	try {
    		File file = new File(path + "/GameFiles/Player/Inventory.txt");
			BufferedReader br_map = new BufferedReader(new FileReader(file));
			
			ItemCodex icodex = new ItemCodex();
			EquipmentCodex ecodex = new EquipmentCodex();
			
			Scanner input = new Scanner(br_map.readLine());
			
			input.next();
			
			while(input.hasNext()) {
				String temp = input.next();
				int id = ((int)temp.charAt(1) -48) * 10 + (int)temp.charAt(2)-48;
				String tag = ecodex.getTag(id);
				
				if(temp.charAt(0) == '0') { //check if item is an equipment
					switch(tag) {
					
					case "fist":
					case "ranged":
					case "staff":
					case "two-handed":
					case "one-handed":
						int lvl = ecodex.getLevelReq(id);
						int damage = ecodex.getStatPoints(id);
						int attackSpeed = ecodex.getAttackSpeed(id);
						Accuracy accuracy = new Accuracy(ecodex.getAccuracy(id));
						Weapon weapon = new Weapon(id, new Level(lvl), ecodex.getName(id), ecodex.getDescription(id), 
								damage, 0, attackSpeed, accuracy, ecodex.getRange(id) );
						
						inventory.addItem(weapon);
						break;
						
					case "leather":
					case "cloth":
					case "plate":
						Level level = new Level(ecodex.getLevelReq(id));
						Armor armor = new Armor(id, level, ecodex.getName(id), ecodex.getDescription(id), 
								ecodex.getStatPoints(id));
						
						inventory.addItem(armor);
						break;
						
					case "ring":
						level = new Level(ecodex.getLevelReq(id));
						Ring ring = new Ring(id, level, ecodex.getName(id), ecodex.getDescription(id));
						
						inventory.addItem(ring);
						break;
					}
				}
				
				else if(temp.charAt(0) == '1') { //check if item is an UseItem
					UseItem useItem = new UseItem(id, icodex.getStatPoints(id), 
							ecodex.getName(id), ecodex.getDescription(id));
					
					inventory.addItem(useItem);
				}
				
				else if(temp.charAt(0) == '2') { //cehck if item is an Interactive item
					KeyItem key = new KeyItem(icodex.getStatPoints(id));
					
					inventory.addItem(key);
				}
				
				else System.out.println("Invalid ItemID");
			}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}
