package Model;

import java.awt.Image;

public abstract class GameObject {
    private int ObjectID;
    private Image sprite;


    public GameObject(int objectID) {
        ObjectID = objectID;
    }
    
    public GameObject(int _objectID, Image _sprite) {
    	ObjectID = _objectID;
    	sprite = _sprite;
    }

    public GameObject(){
        ObjectID = 0; // Default ID
        // Default Sprite goes here.
    }

    // Returns the object's ID
    int getObjectID() {
        return ObjectID;
    }

    // Returns the object's sprite
    public Image getSprite() {
    	return sprite;
    }

    // Sets an Object's ID
    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }

    // Sets an Object's Sprite
    public void setSprite(Image _sprite) {
    	sprite = _sprite;
    }
}
