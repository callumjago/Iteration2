package Model;

import View.MapView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RunGame extends Application {
    private Stage mainStage;
    private Scene mainScene;
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


        ArrayList<ArrayList<Tile>> tileSet = new ArrayList<ArrayList<Tile>>();
        tileSet.add(new ArrayList<Tile>());
        tileSet.get(0).add(new EmptyTile());
        tileSet.get(0).add(new OneShotItem());

        GameState gameState = new GameState();
        gameState.setTileSet(tileSet);


        MapView mv = new MapView(canvas);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                mv.render(gameState);

            }
        }.start();
        theStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
