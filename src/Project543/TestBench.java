package Project543;

import Project543.Metrics.ProjectCode;
import Project543.MetricsInterface.ProjectCodeTab;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;

public class TestBench {
    //To test code

    public TestBench(){
        //Things to test
        TabPane testBenchTabPane = new TabPane();
        ProjectCodeTab testBenchProjectCodeTab = new ProjectCodeTab("TestBenchProjectCodeTab");

        testBenchTabPane.getTabs().add(testBenchProjectCodeTab);

        //The stage stuff
        Stage testBenchStage = new Stage();
        testBenchStage.setTitle("TestBench");
        testBenchStage.setWidth(UserInterface.MAX_WIDTH);
        testBenchStage.setHeight(UserInterface.MAX_HEIGHT);

        //Stage contents
        VBox testBenchContents = new VBox(testBenchTabPane);
        BorderPane testBenchBorderPane = new BorderPane();
        testBenchBorderPane.setRight(testBenchContents);

        Scene testBenchScene = new Scene(testBenchBorderPane);
        testBenchStage.setScene(testBenchScene);

        testBenchStage.show();

        testBenchProjectCodeTab.setMetricsTextField(
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum \n" +
                "Lorum Ipsum Lorum Ipsum Lorum Ipsum");

        System.out.println("Constructed TestBench");
    }

    TestBench(String name) throws IOException, RecognitionException {
        if (name.equalsIgnoreCase("melissa"))
        {
            //melissa's code to test
            ProjectCode proj = new ProjectCode();
        } else if (name.equalsIgnoreCase("rowan")) {
            //rowan test code
        } else {
            //whatever else
        }
    }
}
