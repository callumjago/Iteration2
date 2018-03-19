package Model;

public class Map {
    private GameState State;
    private InteractionHandler IH;

    public Map(GameState state) {
        State = state;
        IH = new InteractionHandler();
    }

    public void updateGameState(GameState GS){
        State = GS;
    }

    public void Tick(){
        //IH.generateInteractions(State)
    }

    public GameState getState() {
        return State;
    }
}