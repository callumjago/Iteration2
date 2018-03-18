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
		
		TeleportCodex tcodex = new TeleportCodex();
		mapID = tcodex.getDestinationMap(((Teleport)((AOE)obj)).getValue());
		Point destination = tcodex.getDestinationPosition(((Teleport)((AOE)obj)).getValue());
		entity.setMapID(mapID);
		entity.setPosition(destination);
		
		
		try {
			int size = state.getEntities().size();
			
			for(int i = 1; i < size; i++) {
				state.removeEntity(state.getEntities().get(1));
			}
		
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
							
							if(itag == "InteractiveItem") { //temporary
								//tile.setObject(new KeyItem(icodex.getLevelReq(x)));
							}
							
							else if(itag.compareToIgnoreCase("health") == 0) {
								tile.setObject(new UseItem(x, icodex.getStatPoints(x), icodex.getName(x), icodex.getDescription(x)));
							}
							else if(itag.compareToIgnoreCase("mana") == 0) {
								tile.setObject(new UseItem(x, icodex.getStatPoints(x), icodex.getName(x), icodex.getDescription(x)));
							}
							break;
						case 'I': //one shot item
							x = (int)temp.charAt(3)-48;
							tile.setObject(new OneShotItem(x, 10));
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
									System.out.println(tag);
									int lvl = ecodex.getLevelReq(id);
									int damage = ecodex.getStatPoints(id);
									int attackSpeed = ecodex.getAttackSpeed(id);
									Accuracy accuracy = new Accuracy(ecodex.getAccuracy(id));
									Weapon weapon = new Weapon(id, new Level(lvl), ecodex.getWeaponName(id), ecodex.getWeaponDescription(id), 
											damage, 0, attackSpeed, accuracy, ecodex.getRange(id) );
								
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
									Ring ring = new Ring(id, level, ecodex.getRingName(id), ecodex.getRingDescription(id));
								
									tile.setObject(ring);
									break;
								}
							
								break;
						case 'L':
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							MPAE mp = new MPAE(x);
							tile.setObject(mp);
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
					System.out.println(name);
					Point pos = new Point(Integer.parseInt(input.next()), Integer.parseInt(input.next()));
					Angle angle = new Angle(Integer.parseInt(input.next()));
				
					int id = Integer.parseInt(input.next());
					EquipmentCodex ecodex = new EquipmentCodex();
					Armor armor = new Armor(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), ecodex.getArmorDescription(id), ecodex.getStatPoints(id));
				
					id = Integer.parseInt(input.next());
					Weapon weapon = new Weapon(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), ecodex.getArmorDescription(id), ecodex.getStatPoints(id), 
						ecodex.getOrientation(id), ecodex.getAttackSpeed(id), new Accuracy(ecodex.getAccuracy(id)), ecodex.getRange(id));
				
					id = Integer.parseInt(input.next());
					Ring ring = new Ring(id, new Level(ecodex.getLevelReq(id)), ecodex.getRingName(id), ecodex.getRingDescription(id));
				
					int HP = Integer.parseInt(input.next());
					int MP = Integer.parseInt(input.next());
					int Atck = Integer.parseInt(input.next());
					int Def = Integer.parseInt(input.next());
					int lvl = Integer.parseInt(input.next());
					int money = Integer.parseInt(input.next());
					int exp = Integer.parseInt(input.next());
					String tag = input.next();
					int maxHP = Integer.parseInt(input.next());
					
					String description = input.nextLine() + input.nextLine();
				
				
					NPC npc = new NPC(name, description, pos, angle, armor, weapon, ring, HP, MP, Atck, Def, lvl, money, exp, tag, maxHP);
					switch(tag) {
					case "Hostile":
						npc.setAI(new HostileAI(npc, state));
						break;
					case "Friendly":
						//npc.setAI(new FriendlyAI());
						break;
					}
					state.getEntities().add(npc);
				}
				
				else {
				/*	//damage
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
    	
	}
}
