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
	
	public TeleportIR(SentientEntity _entity, GameState _state, GameObject _obj) {
		entity = _entity;
		state = _state;
		obj = _obj;
		path = System.getProperty("user.dir");
	}
	
	public void applyEffect() {
		try {
			TeleportCodex tcodex = new TeleportCodex();
			int mapID = tcodex.getDestinationMap(((Teleport)((AOE)obj)).getValue());
			Point destination = tcodex.getDestinationPosition(((Teleport)((AOE)obj)).getValue());
		
			entity.setPosition(destination);
		
			File mapFile = new File(System.getProperty("user.dir") + "/GameFiles/Maps/Map" + mapID + ".txt");
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
						switch(temp.charAt(1)) {
						case 'B':
							tile.setObject(new Obstacle());
							break;
						case 'C':
							tile.setObject(new InstantDeath());
							break;
						case 'D':
							int x = ((int)temp.charAt(2)-48)*10 + (int)temp.charAt(3)-48;
							tile.setObject(new Teleport(x));
							break;
						case 'E':
							tile.setObject(new HealingAE());
							break;
						case 'F':
							tile.setObject(new DamageAE());
							break;
						case 'G':
							tile.setObject(new ExperienceAE());
							break;
						case 'H':
							tile.setObject(new InteractiveItem());
							break;
						case 'I':
							tile.setObject(new OneShotItem());
							break;
						case 'J':
							NPC npc = new NPC();
							break;
						case 'K':
							tile.setObject(new Weapon());
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
