package Model;

public class Mana {
    private int MagicPoints;
    private int MaxMagicPoints;

    public Mana(int magicPoints) {
        if (magicPoints <= 0) {
            MagicPoints = 1; // Magic must be set above 0
            MaxMagicPoints = 1;
        }
        else {
            MagicPoints = magicPoints;
            MaxMagicPoints = magicPoints;
        }
    }

    public int getMagicPoints() {
        return MagicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        if (magicPoints <= 0) {
            return;
        }
        else {
            MagicPoints = magicPoints;
        }
    }

    public int getMaxMagicPoints() {
        return MaxMagicPoints;
    }

    public void setMaxMagicPoints(int maxMagicPoints) {
        if (maxMagicPoints <= 0) {
            return;
        }
        else {
            MaxMagicPoints = maxMagicPoints;
        }
    }

    public void modifyMana(int delta){
        if (delta > 0){
            recover(delta);
        }
        else if (delta < 0){
            deplete(delta);
        }
        else{
            return;
        }
    }

    public void recover(int MPamt){
        if (MagicPoints + MPamt >= MaxMagicPoints){
            MagicPoints = MaxMagicPoints;
        }
        else{
            MagicPoints = MagicPoints + MPamt;
        }
    }

    public void deplete(int depAmt){
        if (MagicPoints - depAmt <= 0){
            MagicPoints = 0;
        }
        else{
            MagicPoints = MagicPoints - depAmt;
        }
    }

    public void raiseMaxMagicPoints(int boost){ // Max MP can only be raised upon level up!
        if (boost <= 0){
            return;
        }
        else{
            MaxMagicPoints += boost;
            MagicPoints = MaxMagicPoints;
        }
    }
}