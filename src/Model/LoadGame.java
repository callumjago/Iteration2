package Model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import View.Sprites;

public class LoadGame {
    private GameState state;
    private Player player;
    private Inventory inventory;
    private int mapID;
    private String path;
    private int playerClass;


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
    	loadSkill();
    	loadNPC();
    }

    public void loadMap(){
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
							tile.setObject(new OneShotItem(x, ocodex.getStatPoints(x)));
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public void loadPlayer() {
    	try {
    		File file = new File(path + "/SavedGames/PlayerName/Player/Player.txt");
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
					player.setMapID(mapID);
					break;
				case 2: //equiping armor
					input.next();
					id = Integer.parseInt(input.next());
					if(id <= 0) id = 1;
					Armor armor = new Armor(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), 
							ecodex.getArmorDescription(id), ecodex.getStatPoints(id));
					
					player.setEquipArmor(armor);
					break;
				case 3: //equiping weapon
					input.next();
					id = Integer.parseInt(input.next());
					if(id <= 0) id = 1;
					Weapon weapon = new Weapon(id, new Level(ecodex.getLevelReq(id)), ecodex.getWeaponName(id), ecodex.getWeaponDescription(id), 
							ecodex.getStatPoints(id), new AttackOr(ecodex.getOrientation(id)), ecodex.getAttackSpeed(id), new Accuracy(ecodex.getAccuracy(id)), ecodex.getRange(id), ecodex.getTag(id));
					player.setEquipWeapon(weapon);
					break;
				case 4: //equiping ring
					input.next();
					id = Integer.parseInt(input.next());
					if(id <= 0) id = 1;
					Ring ring = new Ring(id, new Level(ecodex.getLevelReq(id)), ecodex.getRingName(id), ecodex.getRingDescription(id));
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
					player.modifyAtk(Integer.parseInt(input.next()) - player.getAtk());
					break;
				case 8: //setting Defense
					input.next();
					player.modifyDef(Integer.parseInt(input.next()) - player.getDef());
					break;
				case 9: //setting level
					input.next();
					player.setLvl(new Level(Integer.parseInt(input.next())));
					break;
				case 10: //setting EXP
					input.next();
					player.gainExp(Integer.parseInt(input.next()));
					break;	
				case 11: //Wallet
					input.next();
					player.modifyMoney(Integer.parseInt(input.next()) - player.getMoney());
					break;
				case 12: //Orientation
					input.next();
					player.setOrientation(new Angle(Integer.parseInt(input.next())));
					break;
				case 13: //setting player class
					input.next();
					input.next();
					break;
				case 14: //setting player sprite
					input.next();
					playerClass = Integer.parseInt(input.next());
					Sprites sprite = new Sprites();
					player.setRole(playerClass);
					player.setSprite(sprite.getPlayerSprite(playerClass));
					break;
				case 15: //setting player Name
					input.next();
					player.setName(input.next());
					break;
				}
				
				++i;
		
				
				if(i > 15) break;
			}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void loadInventory() {
    	try {
    		File file = new File(path + "/SavedGames/PlayerName/Player/Inventory.txt");
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
						Weapon weapon = new Weapon(id, new Level(lvl), ecodex.getWeaponName(id), ecodex.getWeaponDescription(id), 
								damage, new AttackOr(ecodex.getOrientation(id)), attackSpeed, accuracy, ecodex.getRange(id), ecodex.getTag(id));
						
						inventory.addItem(weapon);
						break;
						
					case "leather":
					case "cloth":
					case "plate":
						Level level = new Level(ecodex.getLevelReq(id));
						Armor armor = new Armor(id, level, ecodex.getArmorName(id), ecodex.getArmorDescription(id), 
								ecodex.getStatPoints(id));
						
						inventory.addItem(armor);
						break;
						
					case "ring":
						level = new Level(ecodex.getLevelReq(id));
						Ring ring = new Ring(id, level, ecodex.getRingName(id), ecodex.getRingDescription(id));
						
						inventory.addItem(ring);
						break;
					}
				}
				
				else if(temp.charAt(0) == '1') { //check if item is an UseItem
					if(icodex.getTag(id).compareToIgnoreCase("health") == 0) {
						HealthPotion healthP = new HealthPotion(id, icodex.getStatPoints(id), 
							icodex.getName(id), icodex.getDescription(id));
						inventory.addItem(healthP);
					}
					else if(icodex.getTag(id).compareToIgnoreCase("mana") == 0) {
						ManaPotion mana = new ManaPotion(id, icodex.getStatPoints(id), 
							icodex.getName(id), icodex.getDescription(id));
						inventory.addItem(mana);
					}
					

				}
				
				else if(temp.charAt(0) == '2') { //check if item is an Interactive item
					//KeyItem key = new KeyItem(icodex.getStatPoints(id));
					
					//inventory.addItem(key);
				}
				
				else System.out.println("Invalid ItemID");
			}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
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
					Armor armor = new Armor(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), ecodex.getArmorDescription(id), ecodex.getStatPoints(id));
				
					id = Integer.parseInt(input.next());
					Weapon weapon = new Weapon(id, new Level(ecodex.getLevelReq(id)), ecodex.getArmorName(id), ecodex.getArmorDescription(id), ecodex.getStatPoints(id), 
						new AttackOr(ecodex.getOrientation(id)), ecodex.getAttackSpeed(id), new Accuracy(ecodex.getAccuracy(id)), ecodex.getRange(id), ecodex.getTag(id));
				
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
				
				
					NPC npc = new NPC(name, description, pos, angle, armor, weapon, ring, HP, MP, Atck, Def, lvl, 500, exp, tag, maxHP);
					npc.modifyMoney(money);
					
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
								Ring ring = new Ring(id, level, ecodex.getRingName(id), ecodex.getRingDescription(id));
						
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
	
	public void loadSkill() {
		try {
			if(playerClass == 0) {
				File file = new File(path + "/SavedGames/PlayerName/Player/WarriorSkills.txt");
				BufferedReader br_map = new BufferedReader(new FileReader(file));
		
				Scanner input = new Scanner(br_map);
				
				input.next();
				ObservationSkill obs = new ObservationSkill(player);
				obs.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(obs);
				
				input.next();
				BindWoundsSkill bindW = new BindWoundsSkill(player);
				bindW.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(bindW);
				
				input.next();
				CrossSlashSkill cross = new CrossSlashSkill(player, state);
				cross.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(cross);
				
				input.next();
				HeavyStrikeSkill heavy = new HeavyStrikeSkill(player, state);
				heavy.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(heavy);
				
				input.next();
				StunStrikeSkill stun = new StunStrikeSkill(player, state);
				stun.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(stun);
			}
			
			else if(playerClass == 1) {
				File file = new File(path + "/SavedGames/PlayerName/Player/MageSkills.txt");
				BufferedReader br_map = new BufferedReader(new FileReader(file));
		
				Scanner input = new Scanner(br_map);
				
				input.next();
				ObservationSkill obs = new ObservationSkill(player);
				obs.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(obs);
				
				input.next();
				BindWoundsSkill bindW = new BindWoundsSkill(player);
				bindW.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(bindW);
				
				input.next();
				BindEnchantmentSkill bindE = new BindEnchantmentSkill(player, state);
				bindE.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(bindE);
				
				input.next();
				BrainWashSkill brainW = new BrainWashSkill(player, state);
				brainW.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(brainW);
				
				input.next();
				ArcaneBashSkill arcaneB = new ArcaneBashSkill(player, state);
				arcaneB.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(arcaneB);
				
				input.next();
				ArcaneBurstSkill arcaneBrst = new ArcaneBurstSkill(player, state);
				arcaneBrst.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(arcaneBrst);
				
				input.next();
				AttackBuffSkill attackB = new AttackBuffSkill(player);
				attackB.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(attackB);
				
				input.next();
				CastLightningSkill castL = new CastLightningSkill(player, state);
				castL.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(castL);
				
				input.next();
				Charm charm = new Charm(player, state);
				charm.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(charm);
				
				input.next();
				DefenseBuffSkill defenseB = new DefenseBuffSkill(player);
				defenseB.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(defenseB);
				
				input.next();
				Fireball fireball = new Fireball(player, state);
				fireball.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(fireball);
				
				input.next();
				HealthBuffSkill healthB = new HealthBuffSkill(player);
				healthB.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(healthB);
			}
			
			else if(playerClass == 2) {
				File file = new File(path + "/SavedGames/PlayerName/Player/RogueSkills.txt");
				BufferedReader br_map = new BufferedReader(new FileReader(file));
		
				Scanner input = new Scanner(br_map);
				
				input.next();
				ObservationSkill obs = new ObservationSkill(player);
				obs.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(obs);
				
				input.next();
				BindWoundsSkill bindW = new BindWoundsSkill(player);
				bindW.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(bindW);
				
				input.next();
				ArrowHailSkill arrowH = new ArrowHailSkill(player, state);
				arrowH.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(arrowH);
				
				input.next();
				DetectTrapSkill detect = new DetectTrapSkill(player);
				detect.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(detect);
				
				input.next();
				RemoveTrapSkill remove = new RemoveTrapSkill(player, state);
				remove.setSkillLvl(new SkillLevel(Integer.parseInt(input.next())));
				player.getPlayerClass().addSkill(remove);
			}
			
			/*while(input.hasNext()) {
				int id = Integer.parseInt(input.next());
				System.out.println(scodex.getName(id));
				switch(scodex.getName(id)) {
				case "Observation":
					
					break;
				case "BindWounds":
					BindWoundsSkill bindW = new BindWoundsSkill(player);
					player.getPlayerClass().addSkill(bindW);
					break;
				case "BindEnchantment":
					BindEnchantmentSkill bindE = new BindEnchantmentSkill(player, state);
					player.getPlayerClass().addSkill(bindE);
					break;
				case "BrainWash":
					BrainWashSkill brainW = new BrainWashSkill(player, state);
					player.getPlayerClass().addSkill(brainW);
					break;
				case "ArcaneBash":
					ArcaneBashSkill arcaneB = new ArcaneBashSkill(player, state);
					player.getPlayerClass().addSkill(arcaneB);
					break;
				case "ArcaneBurst":
					ArcaneBurstSkill arcaneBrst = new ArcaneBurstSkill(player, state);
					player.getPlayerClass().addSkill(arcaneBrst);
					break;
				case "ArrowHail":
					ArrowHailSkill arrowH = new ArrowHailSkill(player, state);
					player.getPlayerClass().addSkill(arrowH);
					break;
				case "AttackBuff":
					AttackBuffSkill attackB = new AttackBuffSkill(player);
					player.getPlayerClass().addSkill(attackB);
					break;
				case "CastLightning":
					CastLightningSkill castL = new CastLightningSkill(player, state);
					player.getPlayerClass().addSkill(castL);
					break;
				case "Charm":
					Charm charm = new Charm(player, state);
					player.getPlayerClass().addSkill(charm);
					break;
				case "CrossSlash":
					CrossSlashSkill cross = new CrossSlashSkill(player, state);
					player.getPlayerClass().addSkill(cross);
					break;
				case "DefenseBuff":
					DefenseBuffSkill defenseB = new DefenseBuffSkill(player);
					player.getPlayerClass().addSkill(defenseB);
					break;
				case "FireBall":
					Fireball fireball = new Fireball(player, state);
					player.getPlayerClass().addSkill(fireball);
					System.out.println("Testing");
					break;
				case "DetectTrap":
					DetectTrapSkill detectS = new DetectTrapSkill(player);
					player.getPlayerClass().addSkill(detectS);
					break;
				case "RemoveTrap":
					RemoveTrapSkill removeT = new RemoveTrapSkill(player, state);
					player.getPlayerClass().addSkill(removeT);
					break;
				case "HealthBuff":
					HealthBuffSkill healthB = new HealthBuffSkill(player);
					player.getPlayerClass().addSkill(healthB);
					break;
				case "HeavyStrike":
					HeavyStrikeSkill heavyS = new HeavyStrikeSkill(player, state);
					player.getPlayerClass().addSkill(heavyS);
					break;
				case "StunStrike":
					StunStrikeSkill stunS = new StunStrikeSkill(player, state);
					player.getPlayerClass().addSkill(stunS);
					break;
				}
			}*/
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
