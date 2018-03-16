package Model;

public abstract class AOE extends GameObject {
    private int ID;

    public AOE(int objectID){
        super(objectID);
    }

    public AOE(){
        super();
    }
    public int getID()
    {
        return this.ID;
    }
    public void setID(int id)
    {
        this.ID = id;
    }
}
