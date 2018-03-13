package Model;

public abstract AOE extends GameObject {
    private int ID;
    private Image sprite;

    public AOE(int objectID, Image sprite){
            super(int objectID, Image sprite);

    }

    public AOE(){
            super();

    }
    public int getID()
    {
        return this.ID
    }
    public setID(int id)
    {
        this.ID = id
    }

}
