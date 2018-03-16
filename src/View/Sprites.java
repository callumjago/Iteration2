package View;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Sprites {

    private ArrayList<Image> objectSprites;
    private ArrayList<Image> terrainSprites;
    private ArrayList<Image> playerSprites;
    private Image mapTransitionSprite;
    private Image obsticleItem;
    private Image defaultObjectSprite;
    private Image arrowSprite;


    private String workingDir;
    public Sprites() {

        workingDir = System.getProperty("user.dir");
        objectSprites = new ArrayList<Image>();
        terrainSprites = new ArrayList<Image>();
        playerSprites = new ArrayList<Image>();
        System.out.println(workingDir);
        initializeSprites();
    }

    private void initializeSprites() {


        playerSprites.add(getImage(workingDir + "/sprites/testCharacter.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterGirl.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterAdventurer.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterSoldier.png"));

        terrainSprites.add(getImage(workingDir + "/sprites/grass.png"));
        terrainSprites.add(getImage(workingDir + "/sprites/water.png"));
        terrainSprites.add(getImage(workingDir + "/sprites/mountains.png"));

        //TODO get sprite for teleport, currently just door
        objectSprites.add(getImage(workingDir + "/sprites/rock.png"));//Obstacle
        objectSprites.add(getImage(workingDir + "/sprites/door.png"));//MapTransition
        objectSprites.add(getImage(workingDir + "/sprites/skull.png"));//InstantDeath
        objectSprites.add(getImage(workingDir + "/sprites/health2.png"));//HealingAE
        objectSprites.add(getImage(workingDir + "/sprites/door.png"));//Teleport
        objectSprites.add(getImage(workingDir + "/sprites/lava.png"));//DamageAE
        objectSprites.add(getImage(workingDir + "/sprites/star.png"));//ExperienceAE
        defaultObjectSprite = getImage(workingDir + "/sprites/star.png");

        arrowSprite = getImage(workingDir + "/sprites/arrow.png");
    }

    public Image getTerrainSprite(int terrainID) {
        return terrainSprites.get(terrainID);
    }

    public Image getPlayerSprite(int ind) {
        return playerSprites.get(ind);
    }

    public Image getObjectSprite(int ind) {
        if(ind >= objectSprites.size()) {
            return defaultObjectSprite;
        }
        return objectSprites.get(ind-1);
    }

    public Image getArrowSprite() {
        return arrowSprite;
    }

    private Image getImage(String fp) {
        File file = new File(fp);
        Image image = new Image(file.toURI().toString());
        return image;
    }
}