package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;



public class ItemCodex extends Codex {

    private static final File itemCodex = new File (System.getProperty("user.dir") + "/SavedGames/PlayerName/Codex/ItemCodex/ItemCodex.csv");

    ItemCodex(){
        super(itemCodex);
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
