package Model;

import View.HUDView;
import View.MapView;
import View.MenuView;
import Controller.KeyController;
import Controller.MenuController;
import View.Sprites;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class RunGame extends Application {
    private Stage mainStage;
    private Scene mainScene;

    private Menu menu;
    private MenuView menuView;
    private Sprites sprites;


    private KeyController keyController;

    private final long ticksPerSecond = 2;

    @Override
    public void start(Stage theStage) {
        mainStage = theStage;
        mainStage.setTitle("The H Y P E");

        Group root = new Group();
        mainScene = new Scene(root);
        theStage.setScene( mainScene );
        Canvas canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);


        canvas.setFocusTraversable(true);

        keyController = new KeyController();
        canvas.setOnKeyPressed(keyController);

        SaveGame save = new SaveGame();

        menu = new Menu(canvas);
        MenuController mc = new MenuController(menu);
        keyController.addController(mc);
        sprites = new Sprites();


        Player p = new Player();
        p.setPosition(new Point(6, 4));
        keyController.addController(p.getPc());
        //canvas.setOnKeyPressed(p.getPc());
        for(int i = 0; i < 7; i++) {
            p.addItem(new Armor());
            p.addItem(new Ring());

        }
        menu.addSubMenu(new InventoryMenu(p));
        menu.addSubMenu(new EquipmentMenu(p));
        menu.addSubMenu(new StatsMenu(p));
        menu.addSubMenu(new ControlsMenu(p.getPc()));
        menu.addSubMenu(new SaveGameMenu(save));
        menu.addSubMenu(new QuitGameMenu());



        menuView = new MenuView(canvas);

        ArrayList<ArrayList<Tile>> tileSet = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tileSet.add(new ArrayList<>());
            for(int j = 0; j < 10; j++) {
                tileSet.get(i).add(new Tile(new Point(i, j), 0));
            }
        }

        ObjectTile objt = new ObjectTile(0);
        objt.setObject(new Obstacle());
        tileSet.get(4).set(4, objt);



        GameState gameState = new GameState();
        gameState.setPlayer(p);
        gameState.addEntity(new NPC());
        NPC npc = new NPC();
        npc.setPosition(new Point(6, 1));
        gameState.addEntity(npc);
        gameState.setTileSet(tileSet);

        LoadGame load = new LoadGame(); // Just here to test Main Menu, does nothing

        MapView mv = new MapView(canvas);
        final long startNanoTime = System.nanoTime();
        final long delta = 1000000000/ticksPerSecond;

        //MainMenuHandler mainMenu = new MainMenuHandler(p,save,load,mainStage,mainScene);

        mv.render(gameState);
        new AnimationTimer() {

            long nanoTime = System.nanoTime()/delta;

            int tick = 0;
            int ticksSincePlayerInput;
            public void handle(long currentNanoTime) {

                if(menu.isOpen()) {//render menu
                    menuView.render(menu.getActiveMenuState());
                } else {//render map
                    //Player is moved immediately if input registered to avoid delay in response
                    if(keyController.getKeyPressed() && ticksSincePlayerInput > 15) {
                        gameState.playerTick();
                        mv.render(gameState);
                        gameState.resetEntities();
                        keyController.resetKeyPressed();
                        ticksSincePlayerInput = 0;
                    }
                    ticksSincePlayerInput++;

                    //Npcs are allowed to move periodically
                    if(tick > 15) {
                        gameState.tick();
                        mv.render(gameState);
                        gameState.resetEntities();
                        tick = 0;
                    }
                    tick++;

                    //gameState.resetEntities();
                }





            }
        }.start();
        theStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
