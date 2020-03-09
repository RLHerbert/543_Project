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
    public static final String DEFAULT_WINDOW_TITLE = "CECS 543 Metrics Suite";

    @Override
    public void start(Stage primaryStage) throws Exception{
//        /*
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("CECS 543 Project543.Project");
//        primaryStage.setScene(new Scene(root, 800, 775));
//        primaryStage.setX(50);
//        primaryStage.setY(50);
//        primaryStage.show();
//         */
//
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
//
//        primaryStage.setScene(scene);
        UI mainUI = new UI();
//        Controller projectController = new Controller();
        System.out.println("TEST");
    }


    public static void main(String[] args) {
        launch(args);

//        ProjectData testProject = new ProjectData();
//        ProjectMetaData testMD = new ProjectMetaData();
//
//        testProject.setFileName("TEST");
//
//        System.out.println(testProject.getFileName());
//        System.out.println(testProject.functionPointMetric.sumOfInputs);
    }
}
