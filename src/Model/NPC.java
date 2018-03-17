package Model;

public class NPC extends SentientEntity {
	private AI ai;
	private String dialogue;
	private int ExpUponDeath;
	private int detectionRange;
	
	NPC(int exp){
		ExpUponDeath = exp;
	}
	
	NPC(){
		ExpUponDeath = 10;
		detectionRange = 4;
	}
	
	public void setAI(AI _ai) {
		ai = _ai;
	}
	
	/*public int getMove() {
		return ai.getMove(); //getMove from ai should return an angle 
	}*/
	
	public String getDialogue() {
		return dialogue;
	}
	
	public void setDialogue(String _dialogue) {
		dialogue = _dialogue;
	}
	
	public int getExpUponDeath() {
		return ExpUponDeath;
	}
	
	public void setExpUponDeath(int exp) {
		ExpUponDeath = exp;
	}

	public int getDetectionRange() {
		return detectionRange;
	}

	public void tick() {
		if(ai == null) {
			return;
		}
		ai.tick();
	}
}
