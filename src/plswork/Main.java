package plswork;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("WAREHOUSE");
        Parent root = FXMLLoader.load(getClass().getResource("/plswork/GUI.fxml"));
        Scene scene = new Scene(root, 760, 540);
        window.setScene(scene);
        window.show();
    }
}
