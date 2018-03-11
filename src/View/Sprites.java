package View;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Sprites {

    private ArrayList<Image> itemSprites;
    private ArrayList<Image> terrainSprites;
    private ArrayList<Image> AESprites;
    private ArrayList<Image> playerSprites;
    private Image mapTransitionSprite;
    private Image obsticleItem;


    private String workingDir;
    public Sprites() {

        workingDir = System.getProperty("user.dir");
        itemSprites = new ArrayList<Image>();
        terrainSprites = new ArrayList<Image>();
        AESprites = new ArrayList<Image>();
        playerSprites = new ArrayList<Image>();
        System.out.println(workingDir);
        initializeSprites();
    }

    private void initializeSprites() {


        playerSprites.add(getImage(workingDir + "/sprites/characterGuy.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterGirl.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterAdventurer.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterSoldier.png"));

        terrainSprites.add(getImage(workingDir + "/sprites/grass.png"));
        terrainSprites.add(getImage(workingDir + "/sprites/water.png"));
        terrainSprites.add(getImage(workingDir + "/sprites/mountains.png"));
    }

    public Image getTerrainSprite(int terrainID) {
        return terrainSprites.get(terrainID);
    }

    public Image getPlayerSprite(int ind) {
        return playerSprites.get(ind);
    }

    private Image getImage(String fp) {
        File file = new File(fp);
        Image image = new Image(file.toURI().toString());
        return image;
    }
}
