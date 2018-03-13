package Model;

public class ExperienceIR implements Interaction{
	SentientEntity entity;
	int expAmt;
	
	public ExperienceIR(SentientEntity _entity, int _expAmt) {
		entity = _entity;
		
		if(_expAmt < 0) { //can't have negative exp
			_expAmt = _expAmt*(-1);
		}
		
		expAmt = _expAmt;
	}
	
	public void applyEffect() {
		entity.gainExp(expAmt);
	}

}
