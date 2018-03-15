package Model;

public class Teleport extends AOE {
	private int teleID;
	
	public Teleport(int _teleID) {
		teleID = _teleID;
	}
	
    @Override
    public int getValue() {
        return teleID;
    }
}
