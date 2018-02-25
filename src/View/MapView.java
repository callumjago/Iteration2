package View;

import Model.EmptyTile;
import Model.GameState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapView {
    private GraphicsContext gc;
    private Canvas canvas;
    private final int tileWidth = 50;
    private final int tileHeight = 50;
    public MapView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }

    public void render(GameState gameState) {

        for(int i = 0; i < gameState.getWidth(); i++) {
            for(int j = 0; j < gameState.getHeight(); j++) {
                if(gameState.getTileAt(i, j) instanceof EmptyTile) {
                    gc.setFill(Color.GRAY);
                } else {
                    gc.setFill(Color.BLUE);
                }
                gc.fillRect(tileWidth*i, tileHeight*j, tileWidth, tileHeight);
            }
        }
    }

    private void renderGrid(int width, int height) {

    }
}
