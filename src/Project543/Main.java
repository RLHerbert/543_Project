package Project543;

import Project543.Metrics.ProjectCode;
import javafx.application.Application;
import javafx.stage.Stage;

//TODO: Only one SMI tab per project
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        UI mainUI = new UI();
//        Controller projectController = new Controller();

        //ApplicationController test = new ApplicationController();

//        System.out.println("Is shit broken?");
//
//        TestBench testBench = new TestBench();
        TestBench testBench1 = new TestBench("Melissa");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
