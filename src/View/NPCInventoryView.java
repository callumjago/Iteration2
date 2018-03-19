package View;

import Model.Inventory;
import Model.Item;
import Model.NPC;
import Model.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class NPCInventoryView {
    private GraphicsContext gc;
    private Canvas canvas;

    private int entryHeight, entryWidth;
    private Bound viewBound;
    private int selectedIndex;

    public NPCInventoryView(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();

        viewBound = new Bound((int)canvas.getWidth()/4, (int)(3*canvas.getWidth()/4), (int)canvas.getHeight()/4, (int)(3*canvas.getHeight()/4));
        entryHeight = (int)canvas.getHeight()/16;
        entryWidth = (int)canvas.getWidth()/2;

        selectedIndex = 0;
    }

    public void render(NPC npc, int selectedIndex) {
        this.selectedIndex = selectedIndex;
        Inventory inventory = npc.getInventory();

        gc.setFill(Color.BLACK);
        gc.fillRect(viewBound.getBoundLeft(), viewBound.getBoundTop(), viewBound.getWidth(), viewBound.getHeight());

        for(int i = 0; i < inventory.numOfItems(); i++) {
            renderItem(inventory.getItem(i), i);
        }
    }

    public void render(Player player, int selectedIndex) {
        this.selectedIndex = selectedIndex;
        Inventory inventory = player.getInventory();

        gc.setFill(Color.BLACK);
        gc.fillRect(viewBound.getBoundLeft(), viewBound.getBoundTop(), viewBound.getWidth(), viewBound.getHeight());

        for(int i = 0; i < inventory.numOfItems(); i++) {
            renderItem(inventory.getItem(i), i);
        }
    }

    public void renderItem(Item item, int index) {
        if(index == selectedIndex) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.GRAY);
        }
        gc.fillRect(viewBound.getBoundLeft()+5, viewBound.getBoundTop()+5 + entryHeight*index, entryWidth-10, entryHeight-10);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(item.getName(), viewBound.getBoundLeft()+10, viewBound.getBoundTop()+30 + entryHeight*index);
    }


}
