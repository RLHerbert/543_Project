package Project543;

import Project543.Metrics.ProjectCode;
import javafx.stage.Stage;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.IOException;

public class TestBench {
    //To test code

    public TestBench(){
        //Things to test

        Stage ui3 = new ProjectWindow();


        System.out.println("Constructed TestBench");
    }

    TestBench(String name) throws IOException, RecognitionException {
        if (name.equalsIgnoreCase("Melissa"))
        {
            //melissa's code to test
            ProjectCode proj = new ProjectCode(new File("xxx.java"));
        } else if (name.equalsIgnoreCase("Rowan")) {
            //rowan test code
        } else {
            //whatever else
        }
    }
}
