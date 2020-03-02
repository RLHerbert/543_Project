package Project543;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        /*
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("CECS 543 Project");
//        primaryStage.setScene(new Scene(root, 800, 775));
//        primaryStage.setX(50);
//        primaryStage.setY(50);
//        primaryStage.show();
//         */
//
//        primaryStage.setTitle("CECS 543 Project");
//        primaryStage.setHeight(775); primaryStage.setWidth(800); primaryStage.setX(0); primaryStage.setY(0);
//        primaryStage.show();
//
//        Stage popupStage = new Stage();
//        popupStage.setTitle("Popup");
//        popupStage.setX(50); popupStage.setY(50);
//        popupStage.initModality(Modality.WINDOW_MODAL);
//        popupStage.initOwner(primaryStage);
//        //popupStage.initStyle(StageStyle.UNDECORATED);
//        popupStage.showAndWait();
//
//
//        /* Menu Bar initialization */
//        Menu mainMenu = new Menu("Main Menu");
//        MenuBar menuBar = new MenuBar();
//        menuBar.getMenus().add(mainMenu);
//        VBox vBox = new VBox(menuBar);
//        Scene scene = new Scene(vBox, 960, 600);
//
//        primaryStage.setScene(scene);
        UI mainUI = new UI();
    }


    public static void main(String[] args) {
        /*
        launch(args);

        Languages lang = Languages.ADA;
        System.out.println(lang);
         */

        Controller project_controller = new Controller();
    }
}
