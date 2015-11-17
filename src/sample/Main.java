package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("EisenhowerMatrix");
        TaskChecker tc = new TaskChecker();
        MainWindow mw = new MainWindow();
        primaryStage.setScene(new Scene(mw.getMainLayout(), 1300, 700));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> tc.stopChecking());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
