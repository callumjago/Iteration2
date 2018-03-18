package Model;

import java.awt.Point;

public class NPC extends SentientEntity {
	private AI ai;
	private String dialogue;
	private int ExpUponDeath;
	private int detectionRange;
	private String description;
	private String tag;
	
	NPC(String name, String _description, Point pos, Angle theta,  Armor initArm, Weapon initWeapon,Ring initRing, int initHP, int initMP, int initAtk, int initDef, int initLvl, int initMoney, int _exp, String tag){
		super(pos,theta,name,initArm,initWeapon,initRing,initHP,initMP,initAtk,initDef,initLvl,initMoney);
		ExpUponDeath = _exp;
		description = _description;
		this.tag = tag;
	}
	
	NPC(){
		super();
		ExpUponDeath = 10;
		detectionRange = 4;
		addToInventory(new Ring());
		addToInventory(new Armor());

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
	
	public void setDescriptidon(String _description) {
		description = _description;
	}
	
	public String getDescription() {
		return description;
	}

	public String getTag(){
		return tag;
	}


	public void checkForAnswerEvent() {}
	public void talk() {}

}


