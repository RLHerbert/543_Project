package Project543;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Scanner;

//TODO: Convert to new UI system
public class ProjectData extends ProjectMetaData {
    //Metrics interface?
    //Member Variables
    //
    //Member Classes and Enums
    //

    //Static Variables
    //

    //Non-Static Variables
    public FunctionPoint functionPointMetric; //TODO: Create way to interface with multiple FPs in project
    public ArrayList<Tab> metricsTabs;

    //Member Methods
    //Constructor(s)
    //
    public ProjectData(){
        //Default constructor

        //Call FunctionPoint default constructor
        //this.functionPointMetric = new FunctionPoint();

        metricsTabs = new ArrayList<Tab>();
    }

    //TODO: Convert to better save file
    public ProjectData(Scanner savedFile, String fileName){
        //Save file constructor
        //TODO: Convert to new UI form
        super(savedFile, fileName);
        //this.functionPointMetric = new FunctionPoint(savedFile);
        while (savedFile.hasNextLine()){
            metricsTabs.add(metricsTabFromSavedFile(savedFile.nextLine()));
        }
    }

    //Getters
    //TODO: move into FP, PARTIALLY COMPLETED?
    public int getCodeSize(){
        //Returns the lines of code (LOC) for the project based on all entered data and the selected language
        return (functionPointMetric.getFunctionPoints() * this.getLanguageLOC());
    }

    //Setters
    //

    //Misc. Member Methods
    //
    public Tab metricsTabFromSavedFile(String metricSaveData){
        //Tab metricTab;
        Scanner lineScanner = new Scanner(metricSaveData);
        int metricID = lineScanner.nextInt();

        if (metricID == FunctionPoint.METRIC_ID){
            return new FunctionPointTab(/*metricSaveData*/);
        }
        /*
        else if (metricID == SoftwareMaturityIndex.METRIC_ID){

        }
         */
        else {
            System.err.println("ERROR: METRIC_ID MISMATCH");
            return new Tab();
        }
    }

    /*
    @Override
    public String toString() {
        String outString = super.toString() + "\n" + functionPointMetric;
        return outString;
    }
    */

    public Scene projectToScene(){
        //TODO: Implement this method
        return new Scene(new VBox());
    }
}
