package Model;

import Controller.PlayerController;
import View.MapView;
import View.MenuView;
import Controller.KeyController;
import Controller.MenuController;
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

        Player p = new Player();
        p.setPosition(new Point(6, 4));
        NPC npc = new NPC();
        npc.setPosition(new Point(3,4));

        //canvas.setOnKeyPressed(p.getPc());
        for(int i = 0; i < 7; i++) {
            p.addItem(new Armor());
            p.addItem(new Ring());

        }




        menuView = new MenuView(canvas);

        ArrayList<ArrayList<Tile>> tileSet = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tileSet.add(new ArrayList<>());
            for(int j = 0; j < 10; j++) {
                tileSet.get(i).add(new Tile(new Point(i, j), 0));
            }
        }

        // OneShot Test Item
        Tile objt = new Tile(0);
        objt.setObject(new OneShotItem(1,-12));
        tileSet.get(4).set(4, objt);

        // HealingAE Test Item
        Tile objt2 = new Tile(0);
        objt2.setObject(new HealingAE(1));
        tileSet.get(4).set(5, objt2);

        // DamageAE Test Item
        Tile objt3 = new Tile(0);
        objt3.setObject(new DamageAE(1));
        tileSet.get(4).set(6, objt3);

        // ExperienceAE Test Item
        Tile objt4 = new Tile(0);
        objt4.setObject(new ExperienceAE(1));
        tileSet.get(4).set(7, objt4);

        // Item Interaction
        Tile objh = new Tile(0);
        objh.setObject(new Weapon(0, 0, new Level(0), "sword", "a sword", 10, new AttackOr (0), 5, new Accuracy(100), 6));
        p.setEquipWeapon((Weapon)objh.getObject());


        tileSet.get(4).set(3, objh);

        //Map Transition
        Tile obj5 = new Tile(0);
        obj5.setObject(new MapTransition());
        tileSet.get(4).set(2, obj5);


        GameState gameState = new GameState();

        gameState.setPlayer(p);
        gameState.addEntity(npc);
        PlayerController pc = new PlayerController(gameState);
        keyController.addController(pc);
        gameState.setTileSet(tileSet);
        gameState.addEntity(new Projectile(new Point(1,1),0,5, 7000));


        menu.addSubMenu(new InventoryMenu(p.getInventory()));
        menu.addSubMenu(new EquipmentMenu(p));
        menu.addSubMenu(new StatsMenu(p));
        menu.addSubMenu(new ControlsMenu(pc));
        menu.addSubMenu(new SaveGameMenu(save));
        menu.addSubMenu(new QuitGameMenu());

        LoadGame load = new LoadGame(); // Just here to test Main Menu, does nothing
        //Map map = new Map(gameState);
        MapView mv = new MapView(canvas);
        final long startNanoTime = System.nanoTime();
        final long delta = 1000000000/ticksPerSecond;

        MainMenuHandler mainMenu = new MainMenuHandler(p,save,load,mainStage,mainScene);

        PlayerDeath playerDeath = new PlayerDeath(p,mainMenu);

        mv.render(gameState);
        new AnimationTimer() {

            long nanoTime = System.nanoTime()/delta;

            int tick = 0;
            int ticksSincePlayerInput = 0;
            public void handle(long currentNanoTime) {
                //map.updateGameState(gameState);
                // map.Tick();
                if(menu.isOpen()) {//render menu
                    menuView.render(menu.getActiveMenuState());
                } else {//render map
                    if(keyController.getKeyPressed() && ticksSincePlayerInput > 15) {//Immediately responds if player input registered
                        gameState.playerTick();
                        mv.render(gameState);
                        gameState.resetEntityAttempts();
                        keyController.resetKeyPressed();
                        ticksSincePlayerInput = 0;
                    }
                    ticksSincePlayerInput++;

                    //Npcs are allowed to move periodically
                    if(tick > 45) {
                        gameState.tick();
                        mv.render(gameState);
                        gameState.resetEntityAttempts();
                        tick = 0;
                    }
//                    System.out.println((npc.getHP()));
                    tick++;
                }

                // Checks if players health is <= 0 for gameover screen
                playerDeath.checkIfDead();




            }
        }.start();
        theStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
