package Model;

public class ItemInteraction extends TileInteraction {
    private ObjectTile tile;
    private SentientEntity entity;
    private Item value;
    private GameState state;

    public ItemInteraction(ObjectTile _tile, SentientEntity _entity, Item _value) {
        entity = _entity;

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

        entity.addToInventory(value);
        state.setTileAt(new Tile(tile.getPosition(), tile.getTerrainID()), tile.getPosition());

    }
}
