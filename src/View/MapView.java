package View;


import Model.Entity;
import Model.GameState;
import Model.Projectile;
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
                    System.out.println(gameState.getObjectID(i, j));
                    gc.drawImage(sprites.getObjectSprite(gameState.getObjectID(i, j)),x, y, tileWidth, tileHeight);
                }
            }
        }
        renderGrid(gameState.getPlayerPosition(), gameState.getWidth(), gameState.getHeight());
        renderProjectiles(gameState.getEntities(), gameState.getPlayerPosition());
    }




    private void renderGrid(Point playerPos, int width, int height) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        int x, y;
        for(int i = 0; i < width+1; i++) {
            x = tileWidth * i - (playerPos.x * tileWidth) + (int) canvas.getWidth() / 2;
            y = ((int) canvas.getHeight() / 2) - (playerPos.y * tileHeight);
            gc.strokeLine(x, y, x, y+(height*tileHeight));
        }

        for(int i = 0; i < height+1; i++) {
            x = ((int) canvas.getWidth() / 2) - (playerPos.x * tileWidth);
            y = tileHeight * i - (playerPos.y * tileHeight) + (int) canvas.getHeight() / 2;
            gc.strokeLine(x, y, x+(width*tileWidth), y);
        }
    }

    private void renderProjectiles(ArrayList<Entity> projectiles, Point playerPos) {
        int x, y;
        for(int i = 0; i < projectiles.size(); i++) {
            if(projectiles.get(i) instanceof Projectile) {
                x = tileWidth * projectiles.get(i).getPosition().x - (playerPos.x * tileWidth) + (int) canvas.getWidth() / 2;
                y = tileHeight * projectiles.get(i).getPosition().y - (playerPos.y * tileHeight) + (int) canvas.getHeight() / 2;

                drawRotatedImage(sprites.getArrowSprite(), projectiles.get(i).getOrientation().getDegree(), x, y);
            }
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
