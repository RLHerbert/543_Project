package Project543;

import Project543.MetricsInterface.ProjectCodeTab;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestBench {
    //To test code

    public TestBench(){
        //Things to test
//        TabPane testBenchTabPane = new TabPane();
//        ProjectCodeTab testBenchProjectCodeTab = new ProjectCodeTab("TestBenchProjectCodeTab");
//
//        testBenchTabPane.getTabs().add(testBenchProjectCodeTab);
//
//        //The stage stuff
//        Stage testBenchStage = new Stage();
//        testBenchStage.setTitle("TestBench");
//        testBenchStage.setWidth(UserInterface.MAX_WIDTH);
//        testBenchStage.setHeight(UserInterface.MAX_HEIGHT);
//
//        //Stage contents
//        VBox testBenchContents = new VBox(testBenchTabPane);
//        BorderPane testBenchBorderPane = new BorderPane();
//        testBenchBorderPane.setRight(testBenchContents);
//
//        Scene testBenchScene = new Scene(testBenchBorderPane);
//        testBenchStage.setScene(testBenchScene);
//
//        testBenchStage.show();
//
//        testBenchProjectCodeTab.setMetricsTextField(
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
//                "Lorum Ipsum Lorum Ipsum Lorum Ipsum");

        Stage ui3 = new UserInterface_3();

        System.out.println("Constructed TestBench");
    }
}
