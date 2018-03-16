package Model;

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
        keyController.addController(p.getPc());
        //canvas.setOnKeyPressed(p.getPc());
        for(int i = 0; i < 7; i++) {
            p.addItem(new Armor());
            p.addItem(new Ring());

        }
        menu.addSubMenu(new InventoryMenu(p.getInventory()));
        menu.addSubMenu(new ControlsMenu(p.getPc()));
        menu.addSubMenu(new SaveGameMenu(save));
        menu.addSubMenu(new QuitGameMenu());


        menuView = new MenuView(canvas);

        ArrayList<ArrayList<Tile>> tileSet = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tileSet.add(new ArrayList<>());
            for(int j = 0; j < 10; j++) {
                tileSet.get(i).add(new Tile(0));
            }
        }

        Tile objt = new Tile(0);
        objt.setObject(new Teleport(1));
        tileSet.get(4).set(4, objt);



        GameState gameState = new GameState();
        gameState.setPlayer(p);
        gameState.setTileSet(tileSet);

        LoadGame load = new LoadGame(); // Just here to test Main Menu, does nothing

        MapView mv = new MapView(canvas);
        final long startNanoTime = System.nanoTime();
        final long delta = 1000000000/ticksPerSecond;
        Map map = new Map(gameState);

        MainMenuHandler mainMenu = new MainMenuHandler(p,save,load,mainStage,mainScene);

        new AnimationTimer() {

            long nanoTime = System.nanoTime()/delta;
            int tick = 0;
            public void handle(long currentNanoTime) {
                //System.out.println(MouseInfo.getPointerInfo().getLocation().x);
            	
            	map.updateGameState(gameState);
            	map.Tick();
                if(menu.isOpen()) {//render menu
                    menuView.render(menu.getActiveMenuState());
                } else {//render map
                    if (System.nanoTime() / delta != nanoTime) {
                        mv.render(gameState);
                        //System.out.println("FPS: " + tick);
                        nanoTime = System.nanoTime() / delta;
                        tick = 0;

                    } else {
                        tick++;
                    }
                }





            }
        }.start();
        theStage.show();
    }


    public static void main(String[] args) {

        //TESTING PURPOSES REOMOVEIMF U WOANT LOL

        EquipmentCodex c = new EquipmentCodex();
        ItemCodex i = new ItemCodex();
        TeleportCodex t = new TeleportCodex();

        //REMOVE THI SLOL

        launch(args);
    }
}
