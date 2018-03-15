package Model;

public class OneShotIR implements Interaction{
	private Tile tile;
	private SentientEntity entity;
	private int value;
	
	public OneShotIR(Tile _tile, SentientEntity _entity, int _value) {
		entity = _entity;
		if(_tile.getObject() instanceof OneShotItem) {
			tile = _tile;
		}
		else {
			System.out.println("The passed Tile does not have an Oneshot item in it");
			tile = null;
		}
		value = _value;
	}
	
	public void applyEffect() {
		if(tile == null) {
			System.out.println("Can't apply Oneshot effect if there is no Oneshot item");
			return;
		}
		entity.modifyHP(value);
		tile.removeObject();
	}
}
