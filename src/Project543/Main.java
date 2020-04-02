package Project543;

import javafx.application.Application;
import javafx.stage.Stage;

//TODO: Only one SMI tab per project
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        UI mainUI = new UI();
//        Controller projectController = new Controller();

        //ApplicationController test = new ApplicationController();

        TestBench testBench = new TestBench();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
