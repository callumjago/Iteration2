package View;

import Model.EmptyTile;
import Model.GameState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class MapView {
    private GraphicsContext gc;
    private Canvas canvas;
    private Sprites sprites;
    private int cameraX, cameraY;
    private final int tileWidth = 50;
    private final int tileHeight = 50;
    public MapView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        sprites = new Sprites();
        cameraX = 0; cameraY = 0;

    }

    public void render(GameState gameState) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i = 0; i < gameState.getWidth(); i++) {
            for(int j = 0; j < gameState.getHeight(); j++) {
                if(gameState.getTileAt(i, j) instanceof EmptyTile) {
                    gc.setFill(Color.GRAY);
                } else {
                    gc.setFill(Color.BLUE);
                }

                //Draw tile
                gc.fillRect(tileWidth*i-(gameState.getPlayerPosition().x*tileWidth)+400, tileHeight*j-(gameState.getPlayerPosition().y*tileHeight)+400, tileWidth, tileHeight);
                if(gameState.getPlayerPosition().x == i && gameState.getPlayerPosition().y == j) {//Draw Player
                    gc.setFill(Color.GREEN);
                    gc.drawImage(sprites.getPlayerSprite(0),tileWidth*i-(gameState.getPlayerPosition().x*tileWidth)+400, tileHeight*j-(gameState.getPlayerPosition().y*tileHeight)+400, tileWidth, tileHeight);
                }
            }
        }
        renderGrid(gameState.getPlayerPosition(), gameState.getWidth(), gameState.getHeight());
    }

    private void renderGrid(Point playerPos, int width, int height) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        for(int i = Math.max(0, (width/2)-playerPos.x+3); i < 2*width-playerPos.x-1; i++) {//Verticle Lines
            //System.out.println(i);
            gc.strokeLine(i*tileWidth, ((height/2)-playerPos.y+3)*tileHeight, i*tileWidth, (2*height-playerPos.y-2)*tileHeight);
        }

        for(int j = Math.max(0, (height/2)-playerPos.y+3); j < 2*height-playerPos.y-1; j++) {//Horizontal Lines
            gc.strokeLine(((width/2)-playerPos.x+3)*tileWidth, j*tileHeight, (2*width-playerPos.x-2)*tileWidth, j*tileHeight);
        }
    }
}
