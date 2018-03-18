package Model;

public interface Stat {
    void modify(int ModifyAmt);
    void raiseBaseStat(int AddedPoints);
    void clearModifier();
    String getName();
}
