

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.nio.file.Paths;

public class QuizAppMusic extends Application {

    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        String audioFilePath = "elevator.mp3"; //audio file path
        Media sound = new Media(Paths.get(audioFilePath).toUri().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        
        //rest of stage goes here
    }

    public static void main(String[] args) {
        launch(args);
    }
}

