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
							x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							EquipmentCodex codex = new EquipmentCodex();
							String tag = codex.getTag(x);
							
							if(tag == "Weapon")
								tile.setObject(new Weapon(x, new Level(codex.getLevelReq(x)), codex.getName(x), codex.getDescription(x), codex.getStatPoints(x), 0, codex.getAttackSpeed(x), new Accuracy(codex.getAccuracy(x)), codex.getRange(x)));
							
							else if(tag == "Armor")
								tile.setObject(new Armor(x, new Level(codex.getLevelReq(x)), codex.getName(x), codex.getDescription(x), codex.getStatPoints(x)));
							
							else if(tag == "Ring")
								tile.setObject(new Ring(x, new Level(codex.getLevelReq(x)), codex.getName(x), codex.getDescription(x)));
							
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
    		File file = new File(path + "/GameFiles/Player.txt");
			BufferedReader br_map = new BufferedReader(new FileReader(file));
			Scanner s_map = new Scanner(br_map.readLine());
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void loadInventory() {
    	
    }

}
