package Project543;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

//TODO: Convert to new UI system
public class ProjectData extends ProjectMetaData {
    //Metrics interface?
    //Member Variables
    private static final String DEFAULT_TAB_TITLE = "New Tab"; //TODO: name based on language

    //Member Classes and Enums
    //

    //Static Variables
    //

    //Non-Static Variables
    public FunctionPoint functionPointMetric; //TODO: Create way to interface with multiple FPs in project
    public ArrayList<MetricsTab> metricsTabs;
    //public ArrayList<Metrics> metricsData;

    //Member Methods
    //Constructor(s)
    //
    public ProjectData(){
        //Default constructor
        super();
        //Call FunctionPoint default constructor
        //this.functionPointMetric = new FunctionPoint();

        this.metricsTabs = new ArrayList<MetricsTab>();
        //this.metricsData = new ArrayList<Metrics>();
    }

    public ProjectData(String[] metaData){
        //Used when creating a new project
        super(metaData);
        this.metricsTabs = new ArrayList<MetricsTab>();
        //this.metricsData = new ArrayList<Metrics>();
    }

    //TODO: Convert to better save file
    public ProjectData(Scanner savedFile, String fileName){
        //Save file constructor
        //TODO: Convert to new UI form
        super(savedFile, fileName);
        //this.functionPointMetric = new FunctionPoint(savedFile);

        this.metricsTabs = new ArrayList<MetricsTab>();
        //this.metricsData = new ArrayList<Metrics>();

        while (savedFile.hasNextLine()){
            //Tab metricTab = metricsTabFromSavedFile(savedFile.nextLine());
            metricsTabs.add(metricsTabFromSavedFile(savedFile.nextLine()));
        }

        System.out.println("Finished reading from saved file");
    }

    //Getters
    //TODO: move into FP, PARTIALLY COMPLETED?
    public int getCodeSize(){
        //Returns the lines of code (LOC) for the project based on all entered data and the selected language
        return (functionPointMetric.getFunctionPoints() * this.getLanguageLOC());
    }

    public Tab getNewFunctionPoint(){
        //Creates a new FunctionPointTab, adds it to metricsTabs, and returns it
        this.createNewFunctionPoint();
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    //Setters
    //

    //Misc. Member Methods
    //
    public void createNewFunctionPoint(){
        //Creates a new FunctionPointTab and adds it to metricsTabs,
        //TODO: do title stuff differently
        FunctionPoint functionPoint = new FunctionPoint(this.defaultProjectLanguage);
        //functionPoint.setFunctionPointLanguage();
        FunctionPointTab functionPointTab = new FunctionPointTab("Function Point", functionPoint);

        this.metricsTabs.add(functionPointTab);
    }

    public MetricsTab metricsTabFromSavedFile(String metricSaveData){
        //Tab metricTab;
        metricSaveData = metricSaveData.substring(1, metricSaveData.length()-1);
        StringTokenizer lineTokenizer = new StringTokenizer(metricSaveData, ",");
        int metricID = Integer.parseInt(lineTokenizer.nextToken());

        if (metricID == FunctionPoint.METRIC_ID){
            return new FunctionPointTab("Function Point", metricSaveData);
        }
        /*
        else if (metricID == SoftwareMaturityIndex.METRIC_ID){
            //Not yet implemented //TODO
        }
         */
        else {
            System.err.println("ERROR: METRIC_ID MISMATCH");
            return new MetricsTab();
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

    public void saveProject() throws IOException {
        //Write all data to file
        File projectFile = new File(this.getFileName());
        this.saveProject(projectFile);
    }

    public void saveProject(File projectFile) throws IOException {
        projectFile.delete();
        projectFile.createNewFile();

        FileWriter fileWriter = new FileWriter(projectFile);

        fileWriter.write(super.toString());

        for (MetricsTab metricsTab : this.metricsTabs){
            metricsTab.setMetric();
            metricsTab.metric.setSaveData();
            fileWriter.write("\n" + metricsTab.metric.writeData());
        }

        fileWriter.close();
    }
}
