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
       /* window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                int reply = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO SAVE WAREHOUSE'S STATE?", "CLOSING...", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    GUI g=new GUI();
                    try {
                        g.onClose();
                    } catch (IOException e) {
                      System.out.println("CANNOT EXPORT");
                    }
                } else {
                    System.exit(0);
                }
            }
        });*/
    }
}
