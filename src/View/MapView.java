package View;


import Model.Entity;
import Model.GameState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import java.awt.Point;
import java.util.ArrayList;


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
        int x, y;

        for(int i = 0; i < gameState.getWidth(); i++) {
            for(int j = 0; j < gameState.getHeight(); j++) {

                x = tileWidth*i-(gameState.getPlayerPosition().x*tileWidth)+400;
                y = tileHeight*j-(gameState.getPlayerPosition().y*tileHeight)+400;
                //Draw tile

                gc.drawImage(sprites.getTerrainSprite(gameState.getTerrainTypeAt(i, j)), x, y, tileWidth, tileHeight);

                if(gameState.getPlayerPosition().x == i && gameState.getPlayerPosition().y == j) {//Draw Player

                    //gc.drawImage(sprites.getPlayerSprite(0),x, y, tileWidth, tileHeight);
                    drawRotatedImage(sprites.getPlayerSprite(0), gameState.getPlayer().getOrientation().getDegree(), x, y);
                }

                if(gameState.getObjectID(i, j) > 0) {//Draw tile object
                    gc.drawImage(sprites.getObjectSprite(gameState.getObjectID(i, j)),x, y, tileWidth, tileHeight);
                }
            }
        }
        renderGrid(gameState.getPlayerPosition(), gameState.getWidth(), gameState.getHeight());
        drawNPCs(gameState.getEntities(), gameState.getPlayerPosition());
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

    private void drawNPCs(ArrayList<Entity> npcs, Point playerPos) {

        for(int i = 1; i < npcs.size(); i++) {
            int x = tileWidth*npcs.get(i).getPosition().x-(playerPos.x*tileWidth)+400;
            int y = tileHeight*npcs.get(i).getPosition().y-(playerPos.y*tileHeight)+400;
            drawRotatedImage(sprites.getPlayerSprite(1), npcs.get(i).getOrientation().getDegree(), x, y);
        }
    }



    private void drawRotatedImage(Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + tileWidth / 2, tlpy + tileHeight / 2);
        gc.drawImage(image, tlpx, tlpy, tileWidth, tileHeight);
        gc.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
