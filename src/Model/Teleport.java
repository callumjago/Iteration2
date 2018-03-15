package Model;

public class Teleport extends AOE {
	private int teleID;
	
	public Teleport(int _teleID) {
		super(2);
		teleID = _teleID;
	}
	
	public int getValue() {
		return teleID;
	}
}
