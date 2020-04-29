package plswork;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    Stage window;

    public boolean check(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CLOSING....");
        alert.setHeaderText("CONFIRMATION");
        alert.setContentText("You might want to click import to save changes. Do you want to?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
           return false;
        }
    }

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
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                if(check()){
                    event.consume();
                }
                else{
                    window.close();
                }
            }
        });

       /* window.setOnCloseRequest( event -> {Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //NIE DZIALA:/ importuje sie pusta lista wiem dlaczego moze nie dzialac ale nie mialam juz pomyslu
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("???");
            alert.setContentText("Do you want to save it?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                GUI g=new GUI();
                try {
                    g.serialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                    System.out.println("OKS");
            }} );*/
    }
}
