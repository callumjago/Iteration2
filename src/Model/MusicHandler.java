package Model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicHandler {

    GameState state;

    private boolean isPlaying;

    public MusicHandler(GameState state) {
        this.state = state;
        state.setMusicHandler(this);
    }

    private String track1Path = System.getProperty("user.dir") + "/music/mainMenuMusic.mp3";
    private String track2Path = System.getProperty("user.dir") + "/music/villageMusic.mp3";

    private Media mainMenuMusic = new Media(new File(track1Path).toURI().toString());
    private MediaPlayer mainMenuMusicPlayer = new MediaPlayer(mainMenuMusic);

    /*
    private Media villageMusic = new Media(new File(track2Path).toURI().toString());
    private MediaPlayer villageMusicPlayer = new MediaPlayer(villageMusic);
    */
    public void playMainMenuMusic() {
        isPlaying = true;
        mainMenuMusicPlayer.setStartTime(new javafx.util.Duration(22000));
        mainMenuMusicPlayer.setVolume(0.25);
        mainMenuMusicPlayer.setCycleCount(100);
        mainMenuMusicPlayer.play();
    }

    public void stopMainMenuMusic() {
        isPlaying = false;
        mainMenuMusicPlayer.stop();
    }

    public void toggleMainMenuMusic() {
        if (isPlaying) {
            mainMenuMusicPlayer.pause();
            isPlaying = false;
        }
        else {
            mainMenuMusicPlayer.play();
            isPlaying = true;
        }
    }

    /*
    public void playVillageMusic() {
        villageMusicPlayer.setCycleCount(100);
        villageMusicPlayer.play();
    }

    public void stopVillageMusic() {
        villageMusicPlayer.stop();
    }
    */
}
