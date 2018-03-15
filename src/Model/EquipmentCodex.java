package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class EquipmentCodex  extends Codex{


    //HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

    EquipmentCodex() {
        try {

            File EquipmentFile = new File(System.getProperty("user.dir") + "/GameFiles/Codex/EquipmentCodex/EquipmentCodex.csv");
            BufferedReader br = new BufferedReader(new FileReader(EquipmentFile));
            String s;
            br.readLine();
            while ((s = br.readLine()) != null) {
                Scanner sc = new Scanner(s);
                sc.useDelimiter(",");
                int id = Integer.parseInt(sc.next());
                System.out.println("The first id that is parsed is: " + id);
                ArrayList<String> values = new ArrayList<String>();

                map.put(id, values);

                for (int i = 0; sc.hasNext(); i++){
                    String x = sc.next();
                    //System.out.print(" " +x);

                    if(x.equals("")){
                        continue;
                    }
                    else{
                        map.get(id).add(x);
                    }

                    //System.out.print( " " +map.get(id).get(i));
                }

                System.out.println("");
                Set<Integer> setofKeySet = map.keySet();

               /* for(Integer key : setofKeySet){
                    System.out.println("ID - First Column: " + key);
                    for(String test : map.get(key)){
                        System.out.print(" " +test);
                    }
                }*/
            }
            System.out.println("The test for the info is:ssssssssssssssss " + map.get(26).get(2));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getTag(int ID){

        return map.get(ID).get(0);

    }
    public int getLevelReq(int ID){

        return Integer.parseInt(map.get(ID).get(1));

    }
    public int getStatPoints(int ID){

        return Integer.parseInt(map.get(ID).get(2));

    }

    public int getAttackSpeed(int ID){

        return Integer.parseInt(map.get(ID).get(3));

    }

    public int getRange(int ID){

        return Integer.parseInt(map.get(ID).get(4));

    }

    public int getAccuracy(int ID){

        return Integer.parseInt(map.get(ID).get(5));

    }

    public String getDescription(int ID){

        return (map.get(ID).get(6));

    }
    public String getName(int ID){

        return (map.get(ID).get(7));

    }

}