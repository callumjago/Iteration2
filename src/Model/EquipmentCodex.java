package Model;
import java.io.File;

public class EquipmentCodex  extends Codex{

   private static final File EquipmentCodex = new File (System.getProperty("user.dir") + "/SavedGames/PlayerName/Codex/EquipmentCodex/EquipmentCodex.csv");

    EquipmentCodex() {
        super(EquipmentCodex);
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

    public String getWeaponDescription(int ID){

        return (map.get(ID).get(6));

    }
    public String getWeaponName(int ID){

        return (map.get(ID).get(7));

    }
    
    public String getArmorDescription(int ID){

        return (map.get(ID).get(3));

    }
    public String getArmorName(int ID){

        return (map.get(ID).get(4));

    }
    
    public String getRingDescription(int ID){

        return (map.get(ID).get(3));

    }
    public String getRingName(int ID){

        return (map.get(ID).get(4));

    }
    public int getArmorValue (int ID) {
        return Integer.parseInt(map.get(ID).get(5));
    }
    public int getWeaponValue (int ID) {
        return Integer.parseInt(map.get(ID).get(9));
    }

	public int getOrientation(int id) {
		// TODO Auto-generated method stub
		return Integer.parseInt(map.get(id).get(8));
	}

}