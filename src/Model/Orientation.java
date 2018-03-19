package Model;
//orientation invariants: orientation > 0 and orientation < 2

public class Orientation {
	private int orientation;
	
	public Orientation(int _orientation) {
		setOrientation(_orientation);
	}
	
	public void setOrientation(int _orientation) {
		if(_orientation > 2 || _orientation < 0) {
			System.out.println("Invalid Orientation passed");
			return;
		}
		
		orientation = _orientation;
	}
	
	public int getOrientation() {
		return orientation;
	}

}
