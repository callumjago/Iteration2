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
	
	public TeleportIR(SentientEntity _entity, GameState _state, GameObject _obj, Inventory _inventory) {
		entity = _entity;
		state = _state;
		obj = _obj;
		path = System.getProperty("user.dir");
		inventory = _inventory;
	}
	
	public void applyEffect() {
		try {
			int size = state.getEntities().size();
			
			for(int i = 1; i < size; i++) {
				state.removeEntity(state.getEntities().get(1));
			}
			
			TeleportCodex tcodex = new TeleportCodex();
			int mapID = tcodex.getDestinationMap(((Teleport)((AOE)obj)).getValue());
			Point destination = tcodex.getDestinationPosition(((Teleport)((AOE)obj)).getValue());
		
			entity.setPosition(destination);
		
			File mapFile = new File(path + "/SavedGames/H Y P E B O Y/Maps/Map" + mapID + "/Map" + mapID + ".txt");
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
								tile.setObject(new KeyItem(icodex.getLevelReq(x)));
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
}
