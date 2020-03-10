package Project543;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        UI mainUI = new UI();
        Controller projectController = new Controller();
    }


    public static void main(String[] args) {
        launch(args);

//        Controller testController = new Controller();
//        testController.openProject("TEST.ms");
//        testController.printAllProjectNames();

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
