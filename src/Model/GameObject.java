package Model;

import java.awt.Image;

public abstract class GameObject {
    private int ObjectID;
    private Image Sprite;

    public GameObject(int objectID, Image sprite) {
        ObjectID = objectID;
        Sprite = sprite;
    }

    public GameObject(){
        ObjectID = 000; // Default ID
        // Default Sprite goes here.
    }

    // Returns the object's ID
    int getObjectID() {
        return ObjectID;
    }

    // Returns the object's sprite
    Image getSprite(){
        return Sprite;
    }

    // Sets an Object's ID
    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }

    // Sets an Object's Sprite
    public void setSprite(Image sprite) {
        Sprite = sprite;
>>>>>>> Player
    }
}
