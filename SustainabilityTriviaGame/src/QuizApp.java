

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class QuizApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quiz Application");

        VBox root = new VBox();
        Button tfButton = new Button("True/False Quiz");
        Button mcButton = new Button("Multiple Choice Quiz");

        // Event listeners for buttons
        tfButton.setOnAction(e -> showTrueFalseQuiz(primaryStage));
        mcButton.setOnAction(e -> showMultipleChoiceQuiz(primaryStage));

        root.getChildren().addAll(tfButton, mcButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showTrueFalseQuiz(Stage stage) {
        // Implement the logic to show True/False Quiz
    }

    private void showMultipleChoiceQuiz(Stage stage) {
        // Implement the logic to show Multiple Choice Quiz
    }

    public static void main(String[] args) {
        launch(args);
    }
}