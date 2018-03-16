package Model;

import java.util.ArrayList;

public class MapTransitionIR implements Interaction {
	private GameObject obj;
	private SentientEntity e;
	private GameState state;
	
	public MapTransitionIR(SentientEntity _e, GameObject _obj, GameState _state) {
		e = _e;
		obj = _obj;
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
