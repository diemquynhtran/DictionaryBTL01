package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Từ điển Anh-Việt");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}
