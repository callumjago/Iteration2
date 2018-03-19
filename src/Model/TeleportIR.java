package Model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TeleportIR implements Interaction{
	private GameObject obj;
	private GameState state;
	private SentientEntity entity;
	private String path;
	private Inventory inventory;
	private SaveGame save;
	private int mapID;
	
	public TeleportIR(SentientEntity _entity, GameState _state, GameObject _obj, Inventory _inventory) {
		entity = _entity;
		state = _state;
		obj = _obj;
		path = System.getProperty("user.dir");
		inventory = _inventory;
	}
	
	public void applyEffect() {
		save = new SaveGame(state);
		save.saveGame();
		Point destination = null;
		
		TeleportCodex tcodex = new TeleportCodex();
		if(obj instanceof Teleport) {
			mapID = tcodex.getDestinationMap(((Teleport)((AOE)obj)).getValue());
			destination = tcodex.getDestinationPosition(((Teleport)((AOE)obj)).getValue());
		}
		else if(obj instanceof MapTransition) {
			mapID = tcodex.getDestinationMap(((MapTransition)obj).getValue());
			destination = tcodex.getDestinationPosition(((MapTransition)obj).getValue());
		}
		
		entity.setMapID(mapID);
		entity.setPosition(destination);
		
		int size = state.getEntities().size();
		
		for(int i = 1; i < size; i++) {
			state.removeEntity(state.getEntities().get(1));
		}
		
		
		try {
			File mapFile = new File(path + "/SavedGames/PlayerName/Maps/Map" + mapID + "/Map" + mapID + ".txt");
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
						case 'B': //obstacle
							tile.setObject(new Obstacle());
							break;
						case 'C': //instant death
							tile.setObject(new InstantDeath());
							break;
						case 'D': //teleport
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new Teleport(x));
							break;
						case 'E': //healing aoe
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new HealingAE(x));
							break;
						case 'F': //damage aoe
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new DamageAE(x));
							break;
						case 'G': //experience aoe
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new ExperienceAE(x));
							break;
						case 'H': //item
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							ItemCodex icodex = new ItemCodex();
							String itag = icodex.getTag(x);
							
							if(itag == "key") { //temporary
								//tile.setObject(new KeyItem(icodex.getLevelReq(x)));
							}
							
							else if(itag.compareToIgnoreCase("health") == 0) {
								tile.setObject(new HealthPotion(x, icodex.getStatPoints(x), icodex.getName(x), icodex.getDescription(x)));
							}
							else if(itag.compareToIgnoreCase("mana") == 0) {
								tile.setObject(new ManaPotion(x, icodex.getStatPoints(x), icodex.getName(x), icodex.getDescription(x)));
							}
							break;
						case 'I': //one shot item
							OneShotCodex ocodex = new OneShotCodex();
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new OneShotItem(x, ocodex.getStatPoints(x), ocodex.getLevelReq(x)));
							break;
						case 'J': //npc
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							NPC npc = new NPC();
							break;
						case 'K': //equipment
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
									AttackOr orientation = new AttackOr(ecodex.getOrientation(id));
									Weapon weapon = new Weapon(id, new Level(lvl), ecodex.getWeaponName(id), ecodex.getWeaponDescription(id), 
											damage, orientation, attackSpeed, accuracy, ecodex.getRange(id), ecodex.getTag(id));
								
									tile.setObject(weapon);
									break;
								
								case "leather":
								case "cloth":
								case "plate":
									Level level = new Level(ecodex.getLevelReq(id));
									Armor armor = new Armor(id, level, ecodex.getArmorName(id), ecodex.getArmorDescription(id), 
											ecodex.getStatPoints(id));
								
									tile.setObject(armor);
									break;
								
								case "ring":
									level = new Level(ecodex.getLevelReq(id));
									Ring ring = new Ring(id, ecodex.getRingName(id), ecodex.getRingDescription(id), ecodex.getRingName(id), ecodex.getRingDescription(id), level, entity.getHealth(),  ecodex.getRingAmount(id));
								
									tile.setObject(ring);
									break;
								}
							break;
						case 'L':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							MPAE mp = new MPAE(x);
							tile.setObject(mp);
							break;
						case 'M':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							MapTransition map = new MapTransition(x);
							tile.setObject(map);
							break;
						}
					}
					
					tileSet.get(j).add(tile);
				}
				
				input.close();
			}
			state.setTileSet(tileSet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		loadNPC();
	}
	
	 public void loadNPC() {
		 try {
	    		File mapFile = new File(path + "/SavedGames/PlayerName/Maps/Map" + mapID + "/NPC" + mapID + ".txt");
				BufferedReader br_map = new BufferedReader(new FileReader(mapFile));
				br_map.readLine();
				Scanner input = new Scanner(br_map);
				
				while(input.hasNextLine()) {
					String name = input.next();
					if(name.compareToIgnoreCase("Projectile") != 0) {
						Point pos = new Point(Integer.parseInt(input.next()), Integer.parseInt(input.next()));
						Angle angle = new Angle(Integer.parseInt(input.next()));
					
						int id = Integer.parseInt(input.next());
						EquipmentCodex ecodex = new EquipmentCodex();
						Armor armor = null;
						if(id >= 26 && id < 41) {
							armor = new Armor(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), ecodex.getArmorDescription(id), ecodex.getStatPoints(id));
						}
						
						id = Integer.parseInt(input.next());
						Weapon weapon = null;
						if((id > 0 && id < 26) || id == 46) {
							weapon = new Weapon(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), ecodex.getArmorDescription(id), ecodex.getStatPoints(id), 
									new AttackOr(ecodex.getOrientation(id)), ecodex.getAttackSpeed(id), new Accuracy(ecodex.getAccuracy(id)), ecodex.getRange(id), ecodex.getTag(id));
						}
						
						id = Integer.parseInt(input.next());
						Ring ring = null;
						if(id > 40 || id != 46) {
							ring = new Ring(id, ecodex.getRingName(id), ecodex.getRingDescription(id), ecodex.getRingName(id), ecodex.getRingDescription(id), new Level(ecodex.getLevelReq(id)), entity.getHealth(),  ecodex.getRingAmount(id));
						}
						int HP = Integer.parseInt(input.next());
						int MP = Integer.parseInt(input.next());
						int Atck = Integer.parseInt(input.next());
						int Def = Integer.parseInt(input.next());
						int lvl = Integer.parseInt(input.next());
						int money = Integer.parseInt(input.next());
						int exp = Integer.parseInt(input.next());
						String tag = input.next();
						int maxHP = Integer.parseInt(input.next());
						int range = Integer.parseInt(input.next());
					
						String description = input.nextLine() + input.nextLine();

						NPC npc;

						if (tag.equals("ShopKeeper")) {
							npc = new ShopKeeper(state.getDialogue(), name, description, pos, angle, armor, weapon, ring, HP, MP, Atck, Def, lvl, money, exp, tag, maxHP);
						}
						else if (tag.equals("Villager")) {
							npc = new Villager(state.getDialogue(),name, description, pos, angle, armor, weapon, ring, HP, MP, Atck, Def, lvl, money, exp, tag, maxHP);
						}
						else {
							npc = new NPC(name, description, pos, angle, armor, weapon, ring, HP, MP, Atck, Def, lvl, money, exp, tag, maxHP);
						}
					
					
						//NPC npc = new NPC(name, description, pos, angle, armor, weapon, ring, HP, MP, Atck, Def, lvl, 500, exp, tag, maxHP);
						npc.modifyMoney(money);

						switch(tag) {
						case "Hostile":
							npc.setDetectionRange(range);
							npc.setAI(new HostileAI(npc, state));
							break;
						case "Friendly":
							npc.setAI(new FriendlyAI(npc, state));
							break;
						}
						state.getEntities().add(npc);
					}
					
					else {
						/*//damage
						int damage = Integer.parseInt(input.next());
						//position x
						int x = Integer.parseInt(input.next());
						//position y
						int y = Integer.parseInt(input.next());
						Point pos = new Point(x, y);
						//range
						int range = Integer.parseInt(input.next());
						//degree
						int degree = Integer.parseInt(input.next());
						
						state.getEntities().add(new Projectile(pos, degree, damage, range, 0));*/
					}
					
					
				}
				
				input.close();
				br_map.close();
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}     	
	    	
	    	loadNPCInv(state.getEntities());
	    }
	    
		public void loadNPCInv(ArrayList<Entity> npc) {
			int size = npc.size();
			
			try {
				File file = new File(path + "/SavedGames/PlayerName/Maps/Map" + mapID + "/Inventory" + mapID + ".txt");
				BufferedReader br_map = new BufferedReader(new FileReader(file));
		
				ItemCodex icodex = new ItemCodex();
				EquipmentCodex ecodex = new EquipmentCodex();
		
				Scanner input = new Scanner(br_map);
				Scanner read = null;
			
				for(int i = 1; i < size; i++) {
					read = new Scanner(input.nextLine());
					
					if(npc.get(i) instanceof NPC)
						do {
							String temp = read.next();
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
									Weapon weapon = new Weapon(id, new Level(lvl), ecodex.getWeaponName(id), ecodex.getWeaponDescription(id), 
											damage, new AttackOr(ecodex.getOrientation(id)), attackSpeed, accuracy, ecodex.getRange(id), ecodex.getTag(id));
									
									((NPC)npc.get(i)).getInventory().addItem(weapon);
									break;
							
								case "leather":
								case "cloth":
								case "plate":
									Level level = new Level(ecodex.getLevelReq(id));
									Armor armor = new Armor(id, level, ecodex.getArmorName(id), ecodex.getArmorDescription(id), 
											ecodex.getStatPoints(id));
							
									((NPC)npc.get(i)).getInventory().addItem(armor);
									break;
							
								case "ring":
									level = new Level(ecodex.getLevelReq(id));
									Ring ring = new Ring(id, ecodex.getRingName(id), ecodex.getRingDescription(id), ecodex.getRingName(id), ecodex.getRingDescription(id), level, entity.getHealth(),  ecodex.getRingAmount(id));
							
									((NPC)npc.get(i)).getInventory().addItem(ring);
									break;
								}
							}
					
							else if(temp.charAt(0) == '1') { //check if item is an UseItem
								if(icodex.getTag(id).compareToIgnoreCase("health") == 0) {
									HealthPotion healthP = new HealthPotion(id, icodex.getStatPoints(id), 
										icodex.getName(id), icodex.getDescription(id));
									((NPC)npc.get(i)).getInventory().addItem(healthP);
								}
								else if(icodex.getTag(id).compareToIgnoreCase("mana") == 0) {
									ManaPotion mana = new ManaPotion(id, icodex.getStatPoints(id), 
										icodex.getName(id), icodex.getDescription(id));
									((NPC)npc.get(i)).getInventory().addItem(mana);
								}
							}
					
							else if(temp.charAt(0) == '2') { //check if item is an Interactive item
								//KeyItem key = new KeyItem(icodex.getStatPoints(id));
						
								//inventory.addItem(key);
							}
					
							else System.out.println("Invalid ItemID");
						} while(read.hasNext());
					}
			}
	    		catch(Exception e) {
	    			e.printStackTrace();
	    		}
			}
}
