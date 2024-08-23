

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;

    public static void playMusic(String audioFilePath) {
        Media sound = new Media(Paths.get("elevator.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}