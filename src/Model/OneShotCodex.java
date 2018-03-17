package Model;

import java.io.File;

public class OneShotCodex extends Codex {

    private static final File oneShotCodex = new File (System.getProperty("user.dir") + "/SavedGames/H Y P E B O Y/Codex/OneShotCodex/OneShotCodex.csv");

    OneShotCodex(){
        super(oneShotCodex);
    }

    public String getTag(int ID){
        return (map.get(ID).get(0));
    }
    public int getStatPoints(int ID){
        return Integer.parseInt(map.get(ID).get(1));
    }

    public int getLevelReq(int ID){
        return Integer.parseInt(map.get(ID).get(2));
    }

    public String getDescription(int ID){

        return (map.get(ID).get(3));

    }
    public String getName(int ID){

        return (map.get(ID).get(4));

    }
}
