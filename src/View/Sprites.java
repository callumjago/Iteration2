package View;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Sprites {

    private ArrayList<Image> objectSprites;
    private ArrayList<Image> terrainSprites;
    private ArrayList<Image> playerSprites;
//    private ArrayList<Image> NPCSprites;
    private ArrayList<Image> projectileSprites;
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

        projectileSprites = new ArrayList<>();
        System.out.println(workingDir);
        initializeSprites();
    }

    private void initializeSprites() {


        playerSprites.add(getImage(workingDir + "/sprites/testCharacter.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterGirl.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterAdventurer.png"));
        playerSprites.add(getImage(workingDir + "/sprites/characterSoldier.png"));
//        NPCSprites.add(getImage(workingDir + "/sprites/characterSoldier.png"));

        terrainSprites.add(getImage(workingDir + "/sprites/grass.png"));
        terrainSprites.add(getImage(workingDir + "/sprites/water.png"));
        terrainSprites.add(getImage(workingDir + "/sprites/mountains.png"));

        //TODO get sprite for teleport, currently just door
        objectSprites.add(getImage(workingDir + "/sprites/boulder.png"));//Obstacle
        objectSprites.add(getImage(workingDir + "/sprites/door.png"));//MapTransition:2
        objectSprites.add(getImage(workingDir + "/sprites/skull.png"));//InstantDeath
        objectSprites.add(getImage(workingDir + "/sprites/portal.png"));//Teleport
        objectSprites.add(getImage(workingDir + "/sprites/health2.png"));//HealingAE:5
        objectSprites.add(getImage(workingDir + "/sprites/lava.png"));//DamageAE:6
        objectSprites.add(getImage(workingDir + "/sprites/star.png"));//ExperienceAE
        defaultObjectSprite = getImage(workingDir + "/sprites/star.png");

        projectileSprites.add(getImage(workingDir + "/sprites/arrow.png"));
        projectileSprites.add(getImage(workingDir + "/sprites/fireball.png"));
    }

    public Image getTerrainSprite(int terrainID) {
        return terrainSprites.get(terrainID);
    }

    public Image getPlayerSprite(int ind) {
        return playerSprites.get(ind);
    }

//    public Image getNPCSprite(int ind) {
//        return NPCSprites.get(ind);
//    }

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

    public Image getProjectileSprite(int index) {
        if(index >= projectileSprites.size()) {
            return projectileSprites.get(0);
        }
        return projectileSprites.get(index);
    }
}
