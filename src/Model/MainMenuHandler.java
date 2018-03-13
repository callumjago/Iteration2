package Model;

import javafx.scene.Scene;
import javafx.stage.Stage;

import View.StartingMenu;
import View.CharacterCreationMenu;

public class MainMenuHandler {

    StartingMenu startingMenu;
    CharacterCreationMenu characterCreationMenu;
    //LoadGameMenu loadGameMenu;
    //SettingsMenu settingsMenu;
    //ExitGame exitGame;

    Scene startingScene;
    Scene mainScene;
    Stage mainStage;

    public MainMenuHandler(Player player, SaveGame saveGame, LoadGame loadGame,Stage mainStage,Scene mainScene)
    {
        this.mainStage = mainStage;
        this.mainScene = mainScene;
        startingMenu = new StartingMenu(this);
        characterCreationMenu = new CharacterCreationMenu(this, player);
        changeMenu(0); // Sets scene to starting scene
    }

    public void changeMenu(int menuNumber) {

        if(menuNumber == 0) // Starting Scene (Main Menu)
            mainStage.setScene(startingMenu.generateScene());
        else if(menuNumber == 1) // Main Scene
            mainStage.setScene(mainScene);
        else if(menuNumber == 2) // New Game (Character Creation)
            mainStage.setScene(characterCreationMenu.generateScene());
        else if(menuNumber == 3) // Exits Game
            mainStage.close();
    }

}
