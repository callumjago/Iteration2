package Model;
import java.io.File;

public class SkillCodex  extends Codex{

   private static final File SkillCodex = new File (System.getProperty("user.dir") + "/SavedGames/PlayerName/Codex/SkillCodex/SkillCodex.csv");

    SkillCodex() {
        super(SkillCodex);
    }


    public boolean requiresGameState(int ID) {
    	if(map.get(ID).get(0).compareToIgnoreCase("true") == 0) {
    		return true;
    	}
    	
    	else return false;
    }
    
    public String getName(int ID) {
    	return map.get(ID).get(1);
    }

}