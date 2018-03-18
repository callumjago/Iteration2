package Controller;

import Model.GameState;
import Model.InfluenceRadius;
import Model.Player;
import Model.Projectile;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//Player Controlls: 0 = up, 1 = right, 2 = down, 3 = left

public class PlayerController extends SubKeyController {
    private Player player;
    private GameState gs;
    private boolean inputRegistered;
    private boolean isListeningforRebind;
    private int rebindIndex;

    private ArrayList<KeyCode> playerControls;
    private ArrayList<String> controlFunctions; //Represents control functions (i.e. moveUp, attack, etc.)
    public PlayerController(GameState gs){
        super();
        this.gs = gs;
        this.player = gs.getPlayer();
        inputRegistered = false;


        //Initialize default controls
        playerControls = new ArrayList<>();
        playerControls.add(KeyCode.UP);
        playerControls.add(KeyCode.RIGHT);
        playerControls.add(KeyCode.DOWN);
        playerControls.add(KeyCode.LEFT);
        playerControls.add(KeyCode.E);
        playerControls.add(KeyCode.C);
        playerControls.add(KeyCode.Z);
        playerControls.add(KeyCode.Q);
        playerControls.add(KeyCode.SPACE);
        playerControls.add(KeyCode.ENTER);
        playerControls.add(KeyCode.L);

        controlFunctions = new ArrayList<>();
        controlFunctions.add("Up");
        controlFunctions.add("Right");
        controlFunctions.add("Down");
        controlFunctions.add("Left");
        controlFunctions.add("NE");
        controlFunctions.add("SE");
        controlFunctions.add("SW");
        controlFunctions.add("NW");
        controlFunctions.add("Attack");
        controlFunctions.add("Projectile");
        controlFunctions.add("AngleProj");


        isListeningforRebind = false;
        rebindIndex = 0;
    }





    public boolean isInputRegistered() {
        return inputRegistered;
    }

    public void resetInputRegistered() {
        inputRegistered = false;
    }

    @Override
    void keyInput(KeyCode code) {
        inputRegistered = true;

        if(isListeningforRebind) {
            playerControls.set(rebindIndex, code);
            isListeningforRebind = false;
        }

        if(code == playerControls.get(0)) {//MoveUp
            player.moveNorth();
        } else if(code == playerControls.get(1)) {//MoveRight
            player.moveEast();
        } else if(code == playerControls.get(2)) {//MoveDown
            player.moveSouth();
        } else if(code == playerControls.get(3)) {//MoveLeft
            player.moveWest();
        } else if(code == playerControls.get(4)) {//MoveNE
            player.moveNorthEast();
        } else if(code == playerControls.get(5)) {//MoveSE
            player.moveSouthEast();
        } else if(code == playerControls.get(6)) {//MoveSW
            player.moveSouthWest();
        } else if(code == playerControls.get(7)) {//MoveNW
            player.moveNorthWest();
        } else if(code == playerControls.get(8)) {//Attack
            player.setAttemptAttack(true);
        } else if(code == playerControls.get(9)) {//Attack
            InfluenceRadius n = new InfluenceRadius(player.getForewardPosition(4), 2);
            int j = 0;
//            while (j != 3) {
                for (int i = 0; i < n.getInfluence().size(); i++) {
                    gs.addEntity(new Projectile(n.getInfluence().get(i), player.getOrientation().getDegree(), 100, 0));
                    //System.out.println("putting new projectile at " + n.getInfluence().get(i).getX() +","+ n.getInfluence().get(i).getY() + "\n");
                }
                n.extendInfluence();
                j++;
//            }
        } else if (code == playerControls.get(10)) {
            InfluenceRadius n = new InfluenceRadius(player.getForewardPosition(1), 5, player.getOrientation());
//            n.skipToStep(3);
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < n.getInfluence().size(); i++) {
                    gs.addEntity(new Projectile(n.getInfluence().get(i), player.getOrientation().getDegree(), 100, 0));
                }
                n.extendInfluence();
            }
        }
    }


    public ArrayList<String> generateControlsList() {
        ArrayList<String> controlsList = new ArrayList<>();
        for(int i = 0; i < playerControls.size(); i++) {
            controlsList.add(controlFunctions.get(i) + ": " + playerControls.get(i).toString());
        }
        return controlsList;
    }

    public int getNumOfControls() {
        return playerControls.size();
    }

    public void startListenForRebind(int controlIndex) {
        isListeningforRebind = true;
        rebindIndex = controlIndex;
    }

    public String getKeyBindingAsString(int index) {
        if(index >= playerControls.size()) {
            return "";
        }
        return playerControls.get(index).toString();
    }

    private Point getProjectileStartPoint() {


        Point pos = player.getPosition();
        if(player.getOrientation().getDegree() == 0) {
            return new Point(pos.x+1, pos.y);
        } else if(player.getOrientation().getDegree() == 45) {
            return new Point(pos.x+1, pos.y+1);
        } else if(player.getOrientation().getDegree() == 90) {
            return new Point(pos.x, pos.y + 1);
        } else if(player.getOrientation().getDegree() == 135) {
            return new Point(pos.x-1, pos.y+1);
        } else if(player.getOrientation().getDegree() == 180) {
            return new Point(pos.x-1, pos.y);
        } else if(player.getOrientation().getDegree() == 225) {
            return new Point(pos.x-1, pos.y-1);
        } else if(player.getOrientation().getDegree() == 270) {
            return new Point(pos.x, pos.y-1);
        } else if(player.getOrientation().getDegree() == 315) {
            return new Point(pos.x+1, pos.y-1);
        } else {
            return new Point(pos.x+1, pos.y);
        }
    }
}