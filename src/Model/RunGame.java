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
    
    private LoadGame load;

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

        menu = new Menu(canvas);
        MenuController mc = new MenuController(menu);
        keyController.addController(mc);

        Player p = new Player();
        p.setPosition(new Point(6, 4));

        //canvas.setOnKeyPressed(p.getPc());
        for(int i = 0; i < 7; i++) {
            p.addItem(new Armor());
            p.addItem(new Ring());

        }




        menuView = new MenuView(canvas);

        GameState gameState = new GameState();
        Map map = new Map(gameState);
        gameState.setPlayer(p);
        NPC npc = new NPC();
        npc.setAI(new HostileAI(npc, gameState));
        gameState.addEntity(npc);
        PlayerController pc = new PlayerController(gameState);
        keyController.addController(pc);
        
        load = new LoadGame(map.getState(), map.getState().getPlayer(), map.getState().getPlayer().getInventory());
        load.loadGame();

        //gameState.addEntity(new Projectile(new Point(1,1),0,5, 7000));
        
        SaveGame save = new SaveGame(map.getState());

        menu.addSubMenu(new InventoryMenu(p.getInventory()));
        menu.addSubMenu(new EquipmentMenu(p));
        menu.addSubMenu(new StatsMenu(p));
        menu.addSubMenu(new ControlsMenu(pc));
        menu.addSubMenu(new SaveGameMenu(save));
        menu.addSubMenu(new QuitGameMenu());
        
        load = new LoadGame(map.getState(), map.getState().getPlayer(), map.getState().getPlayer().getInventory()); // Just here to test Main Menu, does nothing
        
        
        //Map map = new Map(gameState);
        MapView mv = new MapView(canvas);
        final long startNanoTime = System.nanoTime();
        final long delta = 1000000000/ticksPerSecond;

        MainMenuHandler mainMenu = new MainMenuHandler(p,save,load,mainStage,mainScene, this);

        PlayerDeath playerDeath = new PlayerDeath(p,mainMenu);

        mv.render(gameState);
        new AnimationTimer() {

            long nanoTime = System.nanoTime()/delta;

            int tick = 0;
            int ticksSincePlayerInput = 0;
            public void handle(long currentNanoTime) {
                //System.out.println(MouseInfo.getPointerInfo().getLocation().x);
            	
            	//map.updateGameState(gameState);
            	//map.Tick();

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
    
    public void loadGame() {
    	load.loadGame();
    }
}
