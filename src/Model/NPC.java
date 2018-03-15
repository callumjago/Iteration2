package Model;

public class NPC extends Entity {
	private AI ai;
	private Dialogue dialogue;
	private int ExpUponDeath;
	
	NPC(int exp){
		ExpUponDeath = exp;
	}
	
	NPC(){
		ExpUponDeath = 10;
	}
	
	public void setAI(AI _ai) {
		ai = _ai;
	}
	
	/*public int getMove() {
		return ai.getMove(); //getMove from ai should return an angle 
	}*/
	
	public Dialogue getDialogue() {
		return dialogue;
	}
	
	public void setDialogue(Dialogue _dialogue) {
		dialogue = _dialogue;
	}
	
	public int getExpUponDeath() {
		return ExpUponDeath;
	}
	
	public void setExpUponDeath(int exp) {
		ExpUponDeath = exp;
	}
}
