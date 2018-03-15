package View;

import Model.GameState;
import Model.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HUDView {
    private Canvas canvas;
    private GraphicsContext gc;
    public HUDView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }

    public void render(Player player) {

        renderHealthBar(player.getHP(), player.getMaxHP());
        renderExpBar(player.getEXP(), player.getEXPRemaining());
    }

    private void renderHealthBar(int HP, int maxHP) {
        float healthPercentage = (float)HP/(float)maxHP;

        //Background
        gc.setFill(Color.GRAY);
        gc.fillRect(canvas.getWidth()-220, 15, 200, 20);

        //Healthbar
        gc.setFill(Color.RED);
        gc.fillRect(canvas.getWidth()-220, 15, 200*healthPercentage, 20);
    }
    private void renderExpBar(int EXP, int EXPRemaining) {
        float expPercentage = (float)EXP/(float)(EXP+EXPRemaining);

        //Background
        gc.setFill(Color.GRAY);
        gc.fillRect(canvas.getWidth()-220, 35, 200, 10);

        //Exp bar
        gc.setFill(Color.BLUE);
        gc.fillRect(canvas.getWidth()-220, 35, 200*expPercentage, 10);
    }


}
