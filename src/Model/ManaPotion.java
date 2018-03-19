package Model;

public class ManaPotion extends UseItem {
    public ManaPotion(int _itemID, int _value, String _name, String _description) {
        super(_itemID,_value,_name,_description);
    }

    @Override
    public void use(Player player) {
        player.modifyMP(getValue());
    }
}