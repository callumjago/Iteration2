package Model;

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

    // Sets an Object's ID
    public void setObjectID(int objectID) {
        ObjectID = objectID;
    }
    
    //Gives back a value based off of the object's type
    public abstract int getValue();

}
