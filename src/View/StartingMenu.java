package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.net.MalformedURLException;

import Model.MainMenuHandler;
import Model.RunGame;

public class StartingMenu {

    MainMenuHandler mainMenu;
    Scene startingScene;
    RunGame run;

    public StartingMenu(MainMenuHandler mainMenu, RunGame _run)
    {
        this.mainMenu = mainMenu;
        run = _run;
    }

    public Scene generateScene()
    {
        //menuMusic.playMainMenuMusic();

        GridPane mainMenuGrid = new GridPane();
        mainMenuGrid.setAlignment(Pos.TOP_CENTER);
        mainMenuGrid.setVgap(40);

        GridPane titleArea = new GridPane();
        titleArea.setAlignment(Pos.CENTER);
        //titleArea.setVgap(40);

        //Main Menu (first scene)
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mainMenuGrid);
        titleArea.setPrefHeight(200);
        borderPane.setTop(titleArea);

        startingScene = new Scene( borderPane , 512 , 512);

        //Set Background Image
        File file = new File(System.getProperty("user.dir") + "/sprites/lava.png");
        Image i = null;
        //System.out.println(System.getProperty("user.dir"));
        try {
            i = new Image(file.toURI().toURL().toString(), mainMenuGrid.getWidth(), mainMenuGrid.getHeight(), false, false);
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        BackgroundImage im = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, false, false, false, true));

        borderPane.setBackground(new Background(im));

        //Main Menu title text
        Text gameTitle = new Text("THE SEARCH FOR THE HYPE");
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.5);
        DropShadow titleShadow = new DropShadow();
        titleShadow.setOffsetY(3.0f);
        titleShadow.setColor(Color.BLACK);
        gameTitle.setEffect(bloom);
        gameTitle.setCache(true);
        //gameTitle.setX(50.0f);
        //gameTitle.setY(0.0f);
        gameTitle.setFill(Color.WHITE);
        gameTitle.setFont(Font.font(null, FontWeight.BOLD, 32));


        //Three main menu buttons
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> mainMenu.changeMenu(2)); //mainStage.setScene(characterCreationScene));

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setOnAction(e -> {
            File teststart = new File(System.getProperty("user.dir") + "/GameFiles/Player/Player.txt");
            if(teststart.exists()) {
                //main.loadGame();
                //mainStage.setScene(mainScene);
            	run.loadGame();
                mainMenu.changeMenu(1);
            }
        });

        Button exitGameButton = new Button("Exit Game");
        exitGameButton.setOnAction(e -> mainMenu.changeMenu(3));

        titleArea.add( gameTitle,0,0);

        mainMenuGrid.add( newGameButton, 0,0);
        mainMenuGrid.add( loadGameButton, 0,1);
        mainMenuGrid.add( exitGameButton, 0,2);

        return startingScene;
    }

}