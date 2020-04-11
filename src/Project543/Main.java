package Project543;

import Project543.Metrics.ProjectCode;
import javafx.application.Application;
import javafx.stage.Stage;

//TODO: Only one SMI tab per project
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        ApplicationController applicationController = new ApplicationController();
//        TestBench testBenchMelissa = new TestBench("Melissa");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
