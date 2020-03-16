package Project543;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.Scanner;

//TODO: Convert to new UI system
public class ProjectData extends ProjectMetaData {
    //TODO: Projects can have multiple Metrics (including FP), Metrics interface?
    //TODO: Implement a function which returns all the relevant data in a JavaFX Scene for UI to display
    //Member Variables
    public FunctionPoint functionPointMetric; //TODO: Create way to interface with multiple FPs in project
    //TODO: public ArrayList<Tab> metricsTabs;

    //Member Methods
    //Constructor(s)
    //
    public ProjectData(){
        //Default constructor

        //Call FunctionPoint default constructor
        this.functionPointMetric = new FunctionPoint();
    }

    //TODO: Convert to better save file
    public ProjectData(Scanner savedFile, String fileName){
        //Saved file constructor
        super(savedFile, fileName);
        this.functionPointMetric = new FunctionPoint(savedFile);
    }

    //Getters
    //TODO: move into FP
    public int getCodeSize(){
        //Returns the lines of code (LOC) for the project based on all entered data and the selected language
        return (functionPointMetric.getFunctionPoints() * this.getLanguageLOC());
    }

    //function point
    //totalCount
    //totalFunctionPoints
    //exInputs
    //exOutputs
    //exInquiries
    //inLogicFiles
    //exInterfaceFiles

    //Setters
    //

    //Misc. Member Methods
    //
    @Override
    public String toString() {
        String outString = super.toString() + "\n" + functionPointMetric;
        return outString;
    }

    public Scene projectToScene(){
        //TODO: Implement this method
        return new Scene(new VBox());
    }
}
