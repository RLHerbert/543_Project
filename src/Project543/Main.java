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
        UI mainUI = new UI();
//      Controller projectController = new Controller();
        System.out.println("TEST");
    }


    public static void main(String[] args) {
//        launch(args);

        Controller testController = new Controller();
        testController.openProject("TEST.ms");
        testController.printAllProjectNames();

//        ProjectData testProject = new ProjectData();
//        testProject.setFileName("TEST");
//        Controller.saveProject(testProject);
//        System.out.println(testProject);
//        ProjectMetaData testMD = new ProjectMetaData();
//
//        testProject.setFileName("TEST");
//
//        System.out.println(testProject.getFileName());
//        System.out.println(testProject.functionPointMetric.sumOfInputs);
    }
}
