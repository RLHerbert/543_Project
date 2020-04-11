package Project543.MetricsInterface;

import Project543.Metrics.ProjectCode;
import Project543.UserInterface;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.IOException;

public class ProjectCodeTab extends MetricsTab {
    //**MEMBER FIELDS**//
    //
    //MEMBER ENUMS AND CLASSES
    //

    //STATIC MEMBER FIELDS
    //
    //Constant Static Fields
    //

    //Non-Constant Static Fields
    //

    //NON-STATIC MEMBER FIELDS
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public ProjectCode projectCode;
    public VBox contentsBox;
    public TextArea metricsTextField; //TODO: Possibly switch to TextArea


    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    private ProjectCodeTab(){
        //Default constructor
        //Don't use
        super("DEFAULT");
    }

    public ProjectCodeTab(String title){
        //Tab constructor
        super(title);

        //Initialize members
        //Create the text area
        this.metricsTextField = new TextArea();
        this.metricsTextField.setEditable(false);
        this.metricsTextField.setPrefSize(5000.0, 5000.0);

        //Create VBox and set its contents to be the text area
        this.contentsBox = new VBox(metricsTextField);
        this.setContent(contentsBox);
    }

    public ProjectCodeTab(File javaInFile) throws IOException, RecognitionException {
        //From file constructor
        this(javaInFile.getName());

        //Initialize member fields
        this.projectCode = new ProjectCode(javaInFile); //TODO
    }

    //GETTERS
    //
    @Override
    public int getMetricID() {
        return ProjectCode.METRIC_ID;
    }

    @Override
    public String getExtraData() {
        return this.projectCode.filePath;
    }

    //SETTERS
    //
    public void setMetricsTextField(String textToSet){
        this.metricsTextField.setText(textToSet);
    }

    public void setMetricsTextField(){
        this.setMetricsTextField(this.projectCode.outputString()); //TODO: get text from this.projectCode
    }

    //MISC. MEMBER METHODS
    //
}
