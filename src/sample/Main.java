package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("EisenhowerMatrix");
        TaskChecker tc = new TaskChecker();
        TaskTable table = new TaskTable("Tasks");
        primaryStage.setScene(new Scene(table.getMainLayout(), 700, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            tc.stopChecking();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
