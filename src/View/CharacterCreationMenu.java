package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import Model.MainMenuHandler;
import Model.Player;

public class CharacterCreationMenu {

    MainMenuHandler mainMenu;
    Scene characterCreationScene;
    Player player;

    public CharacterCreationMenu(MainMenuHandler mainMenu, Player player)
    {
        this.mainMenu = mainMenu;
        this.player = player;
    }

    public Scene generateScene() {

        //Character Creation
        GridPane characterCreationGridPane = new GridPane();
        characterCreationScene = new Scene( characterCreationGridPane, 512, 512);
        characterCreationGridPane.setAlignment(Pos.CENTER);
        characterCreationGridPane.setVgap(30);
        characterCreationGridPane.setHgap(90);
        characterCreationGridPane.setBackground(new Background(new BackgroundFill(Color.ROSYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Player name box
        TextField nameInput = new TextField();

        // Labels for character creation
        Label chooseName = new Label("Type your character's name");
        chooseName.setFont(Font.font("Verdana",14));
        Label chooseSprite = new Label("Select a character");
        chooseSprite.setFont(Font.font("Verdana",14));
        Label chooseClass = new Label("Select a class");
        chooseClass.setFont(Font.font("Verdana",14));

        // Character Creation Choices
        ChoiceBox characterClassChoices = new ChoiceBox(FXCollections.observableArrayList("Warrior","Mage","Rogue"));
        characterClassChoices.setValue("Warrior"); //Setting a default choice

        ChoiceBox characterSprites = new ChoiceBox(FXCollections.observableArrayList("Guy","Girl","Adventurer","Soldier"));
        characterSprites.setValue("Guy"); //Setting a default choice

        Image characterSprite1 = new Image("file:" + System.getProperty("user.dir") + "/sprites/characterGuy.png");
        Image characterSprite2 = new Image("file:" + System.getProperty("user.dir") + "/sprites/characterGirl.png");
        Image characterSprite3 = new Image("file:" + System.getProperty("user.dir") + "/sprites/characterAdventurer.png");
        Image characterSprite4 = new Image("file:" + System.getProperty("user.dir") + "/sprites/characterSoldier.png");

        //player.setPlayerSpriteNumber(1);

        ImageView imageView = new ImageView(characterSprite1);

        Image[] spriteChoices = {characterSprite1,characterSprite2,characterSprite3,characterSprite4};

        // Sets size of sprite choice
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);

        // Watches for users choice in selection box
        characterSprites.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        //label.setText(spriteChoices[newValue.intValue()]);
                        imageView.setImage(spriteChoices[newValue.intValue()]);
                        // spriteNumber = (newValue.intValue()+1);
                    }
                }
        );

        Text noNameCatch = new Text("PLEASE ENTER A NAME");
        noNameCatch.setFont(Font.font("Verdana", FontWeight.BOLD,16));
        noNameCatch.setFill(Color.RED);
        noNameCatch.setVisible(false);

        // Back Button
        Button characterCreationBackButton = new Button("Back");
        characterCreationBackButton.setOnAction(e -> mainMenu.changeMenu(0));

        // Continue Button
        Button characterCreationContinueButton = new Button("Continue");
        characterCreationContinueButton.setOnAction(e -> {

            if(nameInput.getText().isEmpty() == false)
            {
                // Applies advantage to player
                /*
                if (characterStatAdvantage.getValue() == "Health")
                    player.setHealth(110);
                else if (characterStatAdvantage.getValue() == "Attack")
                    player.setAttackPoints(8);
                else if (characterStatAdvantage.getValue() == "Defense")
                    player.setDefensePoints(8);
                    */
                player.setName(nameInput.getText());
                //player.setPlayerSprite(imageView.getImage());
                //main.newGame(nameInput.getText(), spriteNumber);
                //isMenuOpen = false;
                //System.out.println(player.getName());
                //System.out.println(player.getPlayerSpriteNumber());
                //openIntroMenu();
                mainMenu.changeMenu(1); // Main Game Scene
            }
            else {
                System.out.println("Enter a character name!");
                noNameCatch.setVisible(true);
            }
        });

        // Adding buttons and text to characterCreation
        characterCreationGridPane.add(chooseName,1,0);
        characterCreationGridPane.add(nameInput,1,1);
        characterCreationGridPane.add(chooseSprite,1,2);
        characterCreationGridPane.add(characterSprites,1,3);
        characterCreationGridPane.add(chooseClass,1,4);
        characterCreationGridPane.add(characterClassChoices,1,5);
        characterCreationGridPane.add(characterCreationContinueButton,2,7);
        characterCreationGridPane.add(characterCreationBackButton,0,7);
        characterCreationGridPane.add(imageView,2,3);
        characterCreationGridPane.add(noNameCatch,1,7);

        return characterCreationScene;
    }

}