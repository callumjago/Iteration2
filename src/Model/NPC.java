package Model;

public class NPC extends Entity {
	private AI ai;
	private String dialogue;
	
	public void setAI(AI _ai) {
		ai = _ai;
	}
	
	/*public  getMove() {
		return ai.getMove();
	}*/
	
	public String getDialogue() {
		return dialogue;
	}
	
	public void setDialogue(String _dialogue) {
		dialogue = _dialogue;
	}
}
