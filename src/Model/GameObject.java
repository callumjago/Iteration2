package Model;

public abstract class GameObject{
    private String name;
    private int id;
    public GameObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getObjectID() {
        return id;
    }
}
