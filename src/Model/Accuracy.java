// Attribute Class
package Model;

public class Accuracy {

    private int percentage;
    private int modifier;

    public Accuracy(int Percentage){
        if (Percentage < 0) {
            percentage = 0;
        }
        else if (Percentage > 100) {
            percentage = 100;
        }
        else {
            percentage = Percentage;
        }
    }

    public Accuracy(int Percentage, int modifier) {
        this.modifier = modifier;
        if (Percentage < 0) {
            percentage = 0;
        }
        else if (Percentage > 100) {
            percentage = 100;
        }
        else {
            percentage = Percentage;
        }
    }

    public Accuracy(){
        modifier = 0;
        percentage = 0;
    }

    public int getAccuracy() {
        if (percentage + modifier > 100){
            return 100;
        }
        else if (percentage + modifier < 0){
            return 0;
        }
        else {
            return percentage + modifier;
        }
    }

    public void setAccuracy(int Percentage) {
        if (Percentage < 0 || Percentage > 100){
            percentage = 0;
        }
        percentage = Percentage;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int mod) {
        modifier = mod;
    }

    public void clearModifier(){
        modifier = 0;
    }

    public void modify(int delta) {
        modifier += delta;
    }
}
