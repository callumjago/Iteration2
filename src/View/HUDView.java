package View;

import Model.GameState;
import Model.Player;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class HUDView {
    private Canvas canvas;
    private GraphicsContext gc;
    public HUDView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }

    public void render(Player player) {
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.BASELINE);
        renderHealthBar(player.getHP(), player.getMaxHP());
        renderExpBar(player.getEXP(), player.getEXPRemaining());
        renderMPBar(player.getMP(), player.getMaxMP());
        renderPlayerLevel(player.getLvl());
        renderPlayerMoney(player.getMoney());
        renderArrowCount(player.getArrowCount());
    }

    private void renderHealthBar(int HP, int maxHP) {
        float healthPercentage = (float)HP/(float)maxHP;

        //Background
        gc.setFill(Color.GRAY);
        gc.fillRect(canvas.getWidth()-210, 15, 200, 20);

        //Healthbar
        gc.setFill(Color.RED);
        gc.fillRect(canvas.getWidth()-210, 15, 200*healthPercentage, 20);
        renderItemBorder(new Bound((int)canvas.getWidth()-210,(int)canvas.getWidth()-10, 15,  35));
    }
    private void renderExpBar(int EXP, int EXPRemaining) {
        float expPercentage = (float)EXP/(float)(EXP+EXPRemaining);

        //Background
        gc.setFill(Color.GRAY);
        gc.fillRect(canvas.getWidth()-210, 35, 200, 10);

        //Exp bar
        gc.setFill(Color.BLUE);
        gc.fillRect(canvas.getWidth()-210, 35, 200*expPercentage, 10);
        renderItemBorder(new Bound((int)canvas.getWidth()-210,(int)canvas.getWidth()-10, 35,  45));
    }

    private void renderMPBar(int MP, int maxMP) {
        float expPercentage = (float)MP/(float)maxMP;

        //Background
        gc.setFill(Color.GRAY);
        gc.fillRect(canvas.getWidth()-210, 45, 200, 10);

        //Exp bar
        gc.setFill(Color.PURPLE);
        gc.fillRect(canvas.getWidth()-210, 45, 200*expPercentage, 10);
        renderItemBorder(new Bound((int)canvas.getWidth()-210,(int)canvas.getWidth()-10, 45,  55));
    }



    private void renderPlayerLevel(int level) {
        //Background
        gc.setFill(Color.GOLDENROD);
        gc.fillRect(canvas.getWidth()-250, 15, 40, 40);

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font ("Verdana", FontWeight.BOLD, 24));
        gc.fillText(Integer.toString(level), canvas.getWidth()-239, 43);

        renderItemBorder(new Bound((int) canvas.getWidth()-250, (int)canvas.getWidth()-210, 15, 55));
    }

    private void renderPlayerMoney(int money) {
        //Background
        gc.setFill(Color.DARKGOLDENROD);
        gc.fillRect(10, 15, 150, 40);

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        gc.fillText("Gold: " + Integer.toString(money), 21, 43);

        renderItemBorder(new Bound(10, 160, 15, 55));
    }

    private void renderArrowCount(int count) {
        //Background
        gc.setFill(Color.ROYALBLUE);
        gc.fillRect(160, 15, 150, 40);

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        gc.fillText("Arrows: " + Integer.toString(count), 171, 43);

        renderItemBorder(new Bound(160, 310, 15, 55));
    }

    private void renderItemBorder(Bound bound) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.strokeLine(bound.getBoundLeft(), bound.getBoundTop(), bound.getBoundRight(), bound.getBoundTop());
        gc.strokeLine(bound.getBoundLeft(), bound.getBoundTop(), bound.getBoundLeft(), bound.getBoundBottom());
        gc.strokeLine(bound.getBoundRight(), bound.getBoundTop(), bound.getBoundRight(), bound.getBoundBottom());
        gc.strokeLine(bound.getBoundLeft(), bound.getBoundBottom(), bound.getBoundRight(), bound.getBoundBottom());
    }


}
