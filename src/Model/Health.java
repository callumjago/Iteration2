package Model;

public class Health implements Stat{
    private int HealthPoints;
    private int MaxHealthPoints;

    public Health(int healthPoints) {
        if (healthPoints <= 0) {
            HealthPoints = 100; // Health must be set above 0
            MaxHealthPoints = 100;
        }
        else {
            HealthPoints = healthPoints;
            MaxHealthPoints = healthPoints;
        }
    }

    public Health(){
        HealthPoints = 100; // Health must be set above 0
        MaxHealthPoints = 100;
    }

    public int getHealthPoints() {
        return HealthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            return;
        }
        else {
            HealthPoints = healthPoints;
        }
    }

    public int getMaxHealthPoints() {
        return MaxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        if (maxHealthPoints <= 0) {
            return;
        }
        else {
            MaxHealthPoints = maxHealthPoints;
            if (HealthPoints > maxHealthPoints) HealthPoints = MaxHealthPoints;
        }
    }

    public boolean checkUse(int cost) {return (HealthPoints+cost > 0);}

    public void modify(int delta){
        if (delta > 0){
            heal(delta);
        }
        else if (delta < 0){
            takeDamage(delta);
        }
        else{
            return;
        }
    }

    public void heal(int HPamt){
        if (HealthPoints + HPamt >= MaxHealthPoints){
            HealthPoints = MaxHealthPoints;
        }
        else{
            HealthPoints = HealthPoints + HPamt;
        }
    }

    public void takeDamage(int dmgAmt){
        if (HealthPoints + dmgAmt <= 0){
            HealthPoints = 0;
        }
        else{
            HealthPoints = HealthPoints + dmgAmt;
        }
    }

    public void refillHP(){
        HealthPoints = MaxHealthPoints;
    }

    public void raiseBaseStat(int boost){ // Max HP can only be raised upon level up!
        if (boost <= 0){
            return;
        }
        else{
            MaxHealthPoints += boost;
            HealthPoints = MaxHealthPoints;
        }
    }

    @Override
    public void clearModifier() {
        return;
    }

    @Override
    public String getName() {
        return "Health";
    }

    public Boolean isDead(){
        return HealthPoints == 0;
    }
}
