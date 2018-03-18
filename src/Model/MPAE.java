package Model;

public class MPAE extends AOE{
	private int rate;
	
	public MPAE() {
		super(10);
		rate = 10;
	}
	
	public MPAE(int _rate) {
		super(10);
		rate = _rate;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return rate;
	}

}
