package Model;

public class Transaction implements Interaction {
    private ShopKeeper merchant;
    private Player player;
    public Transaction(ShopKeeper merchant, Player player) {
        this.merchant = merchant;
        this.player = player;
    }

    @Override
    public void applyEffect() {

    }

    public void applyEffect(int index) {
        System.out.println("hi");
        int itemPrice = 10;
        int cost = (int)(itemPrice*merchant.getPriceModifyer());
        if(player.getMoney() < cost) {//Not enough money
            return;
        }

        Item item = merchant.getInventory().getItem(index);
        merchant.getInventory().tossItem(index);
        player.addItem(item);

        player.modifyMoney(-cost);

    }

    public ShopKeeper getMerchant() {
        return merchant;
    }


}
