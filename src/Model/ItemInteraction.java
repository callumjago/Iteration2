package Model;

public class ItemInteraction implements Interaction {
<<<<<<< HEAD
=======
    private Tile tile;
    private SentientEntity entity;
    private Item value;
    private GameState state;

    public ItemInteraction(Tile _tile, SentientEntity _entity, Item _value, GameState _state) {
        entity = _entity;

        state = _state;

        if(_tile.getObject() instanceof Item) {
            tile = _tile;
        }

        else {
            System.out.println("The passed Tile does not have an Item item in it");
            tile = null;
        }

        value = _value;
    }

    public void applyEffect() {
        if(tile == null) {
            System.out.println("Can't apply Item effect if there is no item");
            return;
        }

        if(value instanceof Equipment) {
        	if(entity.getLvl() < ((Equipment)value).getLevelRequirement())
        		return;
        }

        else if(value instanceof InteractiveItem) {
        	//if(entity.getLvl() < ((InteractiveItem)value).getLvlRequirement())
        		//return;
        }

        entity.addToInventory(value);
        tile.removeObject();
    }
>>>>>>> Interaction
}
