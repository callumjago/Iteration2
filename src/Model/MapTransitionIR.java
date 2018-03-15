package Model;

import java.util.ArrayList;

public class MapTransitionIR implements Interaction {
	private int mapID;
	private SentientEntity e;
	private GameState state;
	
	public MapTransitionIR(SentientEntity _e, int _mapID, GameState _state) {
		e = _e;
		mapID = _mapID;
		state = _state;
	}
	
	public void applyEffect() {
		ArrayList<ArrayList<Tile>> tileSet = new ArrayList();
		
		for(int i = 0; i < 5; i++) {
			tileSet.add(new ArrayList());
			for(int h = 0; h < 5; h++) {
				tileSet.get(i).add(new Tile(0));
			}
		}
		
		state.setTileSet(tileSet);
	}

}
