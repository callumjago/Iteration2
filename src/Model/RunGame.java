package Model;

import View.MapView;
import View.MenuView;
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
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);

        SaveGame save = new SaveGame();
        menu = new Menu(canvas);
        Player p = new Player();
        p.addItem(new Armor());
        p.addItem(new Ring());
        menu.addSubMenu(new InventoryMenu(0, p.getInventory()));
        menu.addSubMenu(new SaveGameMenu(1, save));
        menu.addSubMenu(new QuitGameMenu(2));

        menuView = new MenuView(canvas);

        ArrayList<ArrayList<Tile>> tileSet = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < 10; i++) {
            tileSet.add(new ArrayList<Tile>());
            for(int j = 0; j < 10; j++) {
                tileSet.get(i).add(new EmptyTile());
            }
        }




        GameState gameState = new GameState();
        gameState.setTileSet(tileSet);


        MapView mv = new MapView(canvas);
        final long startNanoTime = System.nanoTime();
        final long delta = 1000000000/ticksPerSecond;

        new AnimationTimer() {

            long nanoTime = System.nanoTime()/delta;
            int tick = 0;
            public void handle(long currentNanoTime) {
                //System.out.println(MouseInfo.getPointerInfo().getLocation().x);
                if(menu.isOpen()) {//render menu
                    menuView.render(menu.getActiveMenuState());
                } else {//render map
                    if (System.nanoTime() / delta != nanoTime) {
                        mv.render(gameState);
                        System.out.println("FPS: " + tick);
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
        launch(args);
    }
}
