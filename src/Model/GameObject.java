package Model;

import java.awt.Image;

public abstract class GameObject {
    private int ObjectID;


    public GameObject(int objectID) {
        ObjectID = objectID;
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


    // Sets an Object's ID
    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }

    // Sets an Object's Sprite
    
    //Gives back a value based off of the object's type
    public abstract int getValue();

}
