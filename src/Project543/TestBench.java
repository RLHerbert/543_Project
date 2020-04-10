package Project543;

import Project543.Metrics.ProjectCode;
import Project543.MetricsInterface.ProjectCodeTab;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
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

        Stage ui3 = new UserInterface_3();


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
