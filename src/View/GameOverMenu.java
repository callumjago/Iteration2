package View;

import Model.MainMenuHandler;
import com.sun.tools.javac.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.net.MalformedURLException;

public class GameOverMenu {

    MainMenuHandler mainMenu;
    Scene gameOverScene;

    public GameOverMenu(MainMenuHandler mainMenu) { this.mainMenu = mainMenu; }

    public Scene generateScene(){
        GridPane gameOver = new GridPane();
        gameOver.setAlignment(Pos.CENTER);
        gameOver.setVgap(40);
        gameOver.setHgap(100);

        gameOverScene = new Scene( gameOver, 800, 800);

        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Verdana", FontWeight.BOLD,60));
        gameOverText.setFill(Color.DARKRED);

        Button goToMainMenuButtonFromGameOver = new Button("  Exit  ");
        goToMainMenuButtonFromGameOver.setOnAction(e -> mainMenu.changeMenu(0));//mainStage.setScene(startingScene));


        //Set Background Image
        File file = new File(System.getProperty("user.dir") + "/src/sample/sprites/crying-cat-face.png");
        Image i = null;
        try {
            i = new Image(file.toURI().toURL().toString(), gameOver.getWidth(), gameOver.getHeight(), false, false);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        BackgroundImage im = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, false, false, false, true));

        gameOver.setBackground(new Background(im));

        gameOver.add(gameOverText,1,0);
        //gameOver.add(catView,1,3);
        gameOver.add(goToMainMenuButtonFromGameOver,2,6);

        return gameOverScene;
    }
}
