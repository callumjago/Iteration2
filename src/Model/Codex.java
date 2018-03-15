package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

abstract class Codex {

    HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();


    Codex(File templateCodexFile){
    try {
            BufferedReader br = new BufferedReader(new FileReader(templateCodexFile));
            String s;
            br.readLine();
            while ((s = br.readLine()) != null) {
                Scanner sc = new Scanner(s);
                sc.useDelimiter(",");
                int id = Integer.parseInt(sc.next());
                //System.out.println("The first id that is parsed is: " + id);
                ArrayList<String> values = new ArrayList<String>();

                map.put(id, values);

            while (sc.hasNext()) {
                String x = sc.next();
                //System.out.print(" " +x);

                if (x.equals("")) {
                    continue;
                }
                else {
                    map.get(id).add(x);
                }

                //System.out.print( " " +map.get(id).get(i));
            }
            System.out.println();
            //Set<Integer> setofKeySet = map.keySet();

               /* for(Integer key : setofKeySet){
                    System.out.println("ID - First Column: " + key);
                    for(String test : map.get(key)){
                        System.out.print(" " +test);
                    }
                }*/
        }
        //System.out.println("The test for the info is:ssssssssssssssss " + map.get(2).get(1) + " " + map.get(2).get(2));



    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
