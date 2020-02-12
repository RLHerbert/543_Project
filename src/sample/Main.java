package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CECS 543 Project");
        primaryStage.setScene(new Scene(root, 800, 775));
        primaryStage.setX(50);
        primaryStage.setY(50);
        primaryStage.show();
         */

        primaryStage.setTitle("CECS 543 Project");
        primaryStage.setHeight(775); primaryStage.setWidth(800); primaryStage.setX(0); primaryStage.setY(0);
        primaryStage.show();

        Stage popupStage = new Stage();
        popupStage.setTitle("Popup");
        popupStage.setX(50); popupStage.setY(50);
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage);
        //popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
