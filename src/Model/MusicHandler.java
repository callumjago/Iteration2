package Model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicHandler {

    String path = System.getProperty("user.dir") + "/music/mainMenuMusic.mp3";

    Media mainMenuMusic = new Media(new File(path).toURI().toString());
    MediaPlayer mainMenuMusicPlayer = new MediaPlayer(mainMenuMusic);


    public void playMainMenuMusic() {
        mainMenuMusicPlayer.setStartTime(new javafx.util.Duration(22000));
        mainMenuMusicPlayer.setCycleCount(100);
        mainMenuMusicPlayer.play();
    }

    public void stopMainMenuMusic() {
        mainMenuMusicPlayer.stop();
    }


}
