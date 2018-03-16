package Model;
import java.awt.Image;

public class Obstacle extends GameObject{
	public Obstacle(int  _obstacleID){
		super(_obstacleID);
	}
	
	public Obstacle() {
		super(1);
	}

	@Override
	public int getValue() {
		return 0;
	}
}
