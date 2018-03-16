package Model;


import java.awt.Image;

public abstract class Equipment extends Item{

    private int EQID;
    private Level reqLvl;
    private String Name;
    private String Description;

    public Equipment(int EQID, Level reqLvl, String name, String description) {
        super(0);
        this.EQID = EQID;
        this.reqLvl = reqLvl;
        Name = name;
        Description = description;
    }

    public Equipment(){
        super();
        EQID = 000;
        reqLvl = new Level(1);
        Name = "Swag";
        Description = "Dank Memes";
    }

    // Returns the equipment's ID
    public int getEQID(){
        return EQID;
    }

    // Sets the equipment's ID
    public void setEQID(int ID){
        EQID = ID;
    }

    // Returns an equipment's required level
    public Level getLevelRequirement(){
        return reqLvl;
    }

    // Sets an equipment's required level
    public void setLevelRequirement(Level lvl) {
        reqLvl = lvl;
    }

    // Returns the name of a piece of equipment
    public String getName(){
        return Name;
    }

    // Sets the name of a piece of equipment
    public void setName(String name){
        Name = name;
    }

    // Returns the description of a piece of equipment
    public String getDescription(){
        return Description;
    }

    // Sets the description of a piece of equipment
    public void setDescription(String description){
        Description = description;
    }
}
