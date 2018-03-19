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
        if (merchant.isSelling())
            sellItem(index);
        else {
            System.out.println("Buying");
            int itemPrice = 10;
            int cost = (int) (itemPrice * merchant.getPriceModifyer());
            if (player.getMoney() < cost) { //Not enough money
                return;
            }

            Item item = merchant.getInventory().getItem(index);
            merchant.getInventory().tossItem(index);
            player.addItem(item);

            player.modifyMoney(-cost);
        }
    }

    public void sellItem(int index) {
        System.out.println("Selling");
        int itemPrice = 10;
        int cost = (int)(itemPrice*merchant.getPriceModifyer());
        if(merchant.getMoney() < cost) {
            return;
        }
        Item item = merchant.getInventory().getItem(index);
        player.getInventory().tossItem(index);
        merchant.getInventory().addItem(item);

        player.modifyMoney(cost);
    }

    public ShopKeeper getMerchant() {
        return merchant;
    }


}
