package Model;

import Controller.*;
import View.LevelUpMenuView;
import View.MapView;
import View.MenuView;
import View.NPCInventoryView;

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

    private KeyController keyController;
    
    private LoadGame load;

    private final long ticksPerSecond = 2;

    @Override
    public void start(Stage theStage) {
        mainStage = theStage;
        //mainStage.setFullScreen(true);

        mainStage.setTitle("The H Y P E");

        Group root = new Group();
        mainScene = new Scene(root);
        theStage.setScene( mainScene );
        Canvas canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);


        canvas.setFocusTraversable(true);

        keyController = new KeyController();
        canvas.setOnKeyPressed(keyController);

        menu = new Menu(canvas);
        MenuController mc = new MenuController(menu);
        keyController.addController(mc);

        Player p = new Player();
//        p.setPosition(new Point(6, 4));
//        NPC npc = new NPC();
//        npc.setPosition(new Point(3,4));

        p.setPosition(new Point(6, 5));

        //canvas.setOnKeyPressed(p.getPc());
        for(int i = 0; i < 7; i++) {
            p.addItem(new Armor());
            p.addItem(new Ring());

        }

        menuView = new MenuView(canvas);

        ArrayList<ArrayList<Tile>> tileSet = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tileSet.add(new ArrayList<>());
            for(int j = 0; j < 10; j++) {
            	tileSet.get(i).add(new Tile(new Point(i, j), 0));
            }
        }

        // OneShot Test Item
        Tile objt = new Tile(0);
        objt.setObject(new Obstacle());
        tileSet.get(4).set(4, objt);

        // HealingAE Test Item
        Tile objt2 = new Tile(0);
        objt2.setObject(new HealingAE(1));
        tileSet.get(4).set(5, objt2);

        // DamageAE Test Item
        Tile objt3 = new Tile(0);
        objt3.setObject(new DamageAE(1));
        tileSet.get(4).set(6, objt3);

        // ExperienceAE Test Item
        Tile objt4 = new Tile(0);
        objt4.setObject(new ExperienceAE(20));
        tileSet.get(4).set(7, objt4);

        // Item Interaction
        Tile objh = new Tile(0);
        objh.setObject(new Weapon(1, new Level(0), "sword", "a sword", 10, new AttackOr (0), 5, new Accuracy(100), 6, "bow"));
        p.setEquipWeapon((Weapon)objh.getObject());


        tileSet.get(4).set(3, objh);

        //Map Transition
        Tile obj5 = new Tile(0);
        obj5.setObject(new Teleport(1));
        tileSet.get(4).set(2, obj5);

        // Trap test
        Tile obj6 = new Tile(0);
        obj6.setObject(new Trap(-50,0));
        tileSet.get(4).set(8, obj6);

        p.setLevel(5);

        GameState gameState = new GameState();
        gameState.setPlayer(p);
        
        //NPC npc = new NPC();
        //npc.setAI(new HostileAI(npc, gameState));
        //gameState.addEntity(npc);
        //p.addItem(new ManaPotion(6,10,"Mp Gob","Restores 10 MP"));
        //p.addItem(new HealthPotion(1,20,"HP Elixir","Restores 20 HP"));
        PlayerController pc = new PlayerController(gameState);
        keyController.addController(pc);
        PickPocketController ppc = new PickPocketController();
        keyController.addController(ppc);
        TransactionController tc = new TransactionController(gameState);
        keyController.addController(tc);
        gameState.setTileSet(tileSet);

        gameState.setTransactionController(tc);

        Map map = new Map(gameState);

        //gameState.addEntity(new Projectile(new Point(1,1),0,5, 7000));
        
        SaveGame save = new SaveGame(map.getState());

        menu.addSubMenu(new InventoryMenu(p));
        menu.addSubMenu(new EquipmentMenu(p));
        menu.addSubMenu(new StatsMenu(p));
        menu.addSubMenu(new SkillsMenu(p));
        menu.addSubMenu(new ControlsMenu(pc));
        menu.addSubMenu(new SaveGameMenu(save));
        menu.addSubMenu(new QuitGameMenu());

        
        load = new LoadGame(map.getState(), map.getState().getPlayer(), map.getState().getPlayer().getInventory()); // Just here to test Main Menu, does nothing
        


        NPCInventoryView inventoryView = new NPCInventoryView(canvas);


        //Map map = new Map(gameState);
        MapView mv = new MapView(canvas);
        LevelUpMenuView levelUpMenuView = new LevelUpMenuView(canvas);
        final long startNanoTime = System.nanoTime();
        final long delta = 1000000000/ticksPerSecond;

        MusicHandler musicHandler = new MusicHandler(gameState);

        MainMenuHandler mainMenu = new MainMenuHandler(p,save,load, mainStage, mainScene, this, gameState, musicHandler);

        //MainMenuHandler mainMenu = new MainMenuHandler(p,save,load,musicHandler,mainStage,mainScene);
        //PlayerDeath playerDeath = new PlayerDeath(p,mainMenu);

        Dialogue dialogue = new Dialogue(canvas);
        gameState.setDialogue(dialogue);

        //NPC shopKeeper = new ShopKeeper(dialogue);
       // gameState.addEntity(shopKeeper);
        //shopKeeper.setPosition(new Point(8,0));
        //shopKeeper.setOrientation(new Angle(90));

        NPC villager1 = new Villager(dialogue);
        //gameState.addEntity(villager1);
        villager1.setPosition(new Point(8,8));
        villager1.setOrientation(new Angle(270));


        mv.render(gameState);
        new AnimationTimer() {

            long nanoTime = System.nanoTime()/delta;

            int tick = 0;
            int ticksSincePlayerInput = 0;
            public void handle(long currentNanoTime) {
                //System.out.println(MouseInfo.getPointerInfo().getLocation().x);
            	
            	//map.updateGameState(gameState);
            	//map.Tick();

                if(menu.isOpen()) {//render menu
                    if(!dialogue.getDialogueOpen()) {
                        canvas.setOnMouseMoved(menu.getMenuMouseController());
                        canvas.setOnMouseClicked(menu.getMenuClickHandler());
                    }
                    menuView.render(menu.getActiveMenuState());
                } else {//render map
                    if(keyController.getKeyPressed() && ticksSincePlayerInput > p.getPlayerSpeed()) {//Immediately responds if player input registered
                        gameState.playerTick();
                        mv.render(gameState);
                        gameState.resetEntityAttempts();
                        keyController.resetKeyPressed();
                        ticksSincePlayerInput = 0;
                    }
                    ticksSincePlayerInput++;

                    //Npcs are allowed to move periodically
                    if(tick > 15) {
                        gameState.tick();
                        mv.render(gameState);
                        gameState.resetEntityAttempts();
                        tick = 0;
                    }
                    tick++;

                    if(gameState.getPickPocketInteraction() != null) {//Player is pickpocketing
                        ppc.setPickPocketInteraction(gameState.getPickPocketInteraction());
                        inventoryView.render(gameState.getPickPocketInteraction().getNpc(), ppc.getSelectedIndex());
                        ppc.handlePickPocket(gameState);

                    }
                    if(gameState.getTransaction() != null && !tc.getCloseRequest()) {//Player is trading
                        tc.setTransaction(gameState.getTransaction());
                        if (gameState.getTransaction().getMerchant().isSelling())
                            inventoryView.render(p, tc.getSelectedIndex());
                        else
                            inventoryView.render(gameState.getTransaction().getMerchant(), tc.getSelectedIndex());
                        tc.handleTransaction(gameState);

                    }
                }

                if(gameState.getLevelUpMenu() != null) {
                    levelUpMenuView.render(gameState.getLevelUpMenu(), menu.getMouseCoords());
                    menu.setLevelUpMenu(gameState.getLevelUpMenu());

                    if(gameState.getLevelUpMenu().isLevelUpApplied()) {
                        menu.setLevelUpMenu(null);
                    }

                }

                dialogue.startDialogue();

                // Checks if players health is <= 0 for gameover screen
                //playerDeath.checkIfDead();

                


            }
        }.start();
        theStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
    
    public void loadGame() {
    	load.loadGame();
    }
}
