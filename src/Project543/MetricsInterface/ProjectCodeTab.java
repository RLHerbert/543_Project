package Project543.MetricsInterface;

import Project543.Metrics.ProjectCode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;

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
    ProjectCode projectCode;
    VBox contentsBox;
    TextArea metricsTextField; //TODO: Possibly switch to TextArea


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
        this.metricsTextField = new TextArea();
        this.metricsTextField.setEditable(false);

        this.contentsBox = new VBox(metricsTextField);
    }

    public ProjectCodeTab(File javaInFile){
        //From file constructor
        this(javaInFile.getName());

        //Initialize member fields
        //this.projectCode = new projectCode(); //TODO
    }

    //GETTERS
    //

    //SETTERS
    //
    public void setMetricsTextField(String textToSet){
        this.metricsTextField.setText(textToSet);
    }

    public void setMetricsTextField(){
        this.setMetricsTextField("TODO"); //TODO: get text from this.projectCode
    }

    //MISC. MEMBER METHODS
    //
}
