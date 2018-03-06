package Model;

public class Wallet {
    private int Money;
    private int MaxMoney;

    public Wallet(int maxMoney) {
        if (maxMoney <= 0) {
            MaxMoney = 100;
        }
        else {
            MaxMoney = maxMoney;
        }
        Money = 0;
    }

    public int getMaxMoney() {
        return MaxMoney;
    }

    public void setMaxMoney(int maxMoney) {
        if (maxMoney <= 0) {
            MaxMoney = 100;
        }
        else {
            MaxMoney = maxMoney;
        }
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public void modifyMoney(int delta){
        if (delta > 0){
            increaseMoney(delta);
        }
        else if (delta < 0){
            decreaseMoney(delta);
        }
        else{
            return;
        }
    }

    public void increaseMoney(int amt){
        if (Money + amt > MaxMoney){
            Money = MaxMoney;
        }
        else{
            Money += amt;
        }
    }

    public void decreaseMoney(int amt){
        if (Money - amt <= 0){
            Money = 0;
        }
        else{
            Money -= amt;
        }
    }
}
