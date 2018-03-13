package Model;

import java.util.ArrayList;
import java.util.HashMap;

public interface Codex {

    HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

     String getDescription(int ID);
     String getName(int ID);



}
