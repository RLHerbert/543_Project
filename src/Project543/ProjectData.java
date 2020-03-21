package Project543;

import javafx.scene.control.Tab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProjectData extends ProjectMetaData implements SaveInterface {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    private static final String DEFAULT_TAB_TITLE = "Metric Tab";

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public ArrayList<MetricsTab> metricsTabs;

    //Member Methods
    //
    //Constructor(s)
    //
    public ProjectData(){
        //Default constructor
        super();
        //Call FunctionPoint default constructor

        this.metricsTabs = new ArrayList<MetricsTab>();
        //this.metricsData = new ArrayList<Metrics>();
    }

    public ProjectData(String[] metaData){
        //Metadata constructor, takes in a string and creates a new ProjectData with the relevant metadata
        //Call super constructor
        super(metaData);

        //Initialize member fields
        this.metricsTabs = new ArrayList<MetricsTab>();
    }

    public ProjectData(Scanner savedFile, String fileName){
        //Save file constructor
        //Call super constructor
        super(savedFile, fileName);

        //Initialize member fields
        this.metricsTabs = new ArrayList<MetricsTab>();

        //Populate metricsTabs
        while (savedFile.hasNextLine()){
            metricsTabs.add(openMetricsTabFromSavedFile(savedFile.nextLine()));
        }
    }

    //Getters
    //
    public Tab getNewFunctionPoint(){
        //Creates a new FunctionPointTab, adds it to metricsTabs, and returns it
        this.createNewFunctionPoint();
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    public Tab getNewSoftwareMaturityIndex(){
        //Creates a new SoftwareMaturityIndexTab, adds it to metricsTabs, and returns it
        //TODO: make sures this works
        this.createNewSoftwareMaturityIndex();
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    //Setters
    //

    //Misc. Member Methods
    //
    //Metric tab creation
    //
    public void createNewFunctionPoint(){
        //Creates a new FunctionPointTab and adds it to metricsTabs
        FunctionPoint functionPoint = new FunctionPoint(this.defaultProjectLanguage);
        FunctionPointTab functionPointTab = new FunctionPointTab("Function Point", functionPoint);

        this.metricsTabs.add(functionPointTab);
    }

    public void createNewSoftwareMaturityIndex(){
        //Creates a new SoftwareMaturityIndexTab and adds it to metricsTabs
        //TODO: make sure this works
        SoftwareMaturityIndex softwareMaturityIndex = new SoftwareMaturityIndex();
        //SoftwareMaturityIndexTab softwareMaturityIndexTab = new SoftwareMaturityIndexTab("SMI", softwareMaturityIndex);
        SoftwareMaturityIndexTab softwareMaturityIndexTab = new SoftwareMaturityIndexTab("SMI");

        this.metricsTabs.add(softwareMaturityIndexTab);
    }

    //Save and open methods
    //
    public boolean saveQuery(){
        //Prompts the user to save their project when attempting to close the window
        //TODO
        return false;
    }

    public boolean hasChanged(){
        //Returns true if any metric has unsaved changes (edits since most recent save)
        //TODO: FINISH
        for (MetricsTab openTabs : this.metricsTabs){
            //if (openTabs.hasChanged()) return true;
        }

        return false;
    }

    public MetricsTab openMetricsTabFromSavedFile(String metricSaveData){
        //Adds all the saved metrics and their tab forms to the project
        metricSaveData = metricSaveData.substring(1, metricSaveData.length()-1);
        StringTokenizer lineTokenizer = new StringTokenizer(metricSaveData, ",");
        int metricID = Integer.parseInt(lineTokenizer.nextToken());

        if (metricID == FunctionPoint.METRIC_ID){
            return new FunctionPointTab("Function Point", metricSaveData);
        }
        else if (metricID == SoftwareMaturityIndex.METRIC_ID){
            //Not yet implemented //TODO: FINISH
            return new SoftwareMaturityIndexTab("SMI");
        }
        else {
            System.err.println("ERROR: METRIC_ID MISMATCH");
            return new MetricsTab();
        }
    }

    public void saveProject() throws IOException {
        //Write all data to file
        File projectFile = new File(this.getFileName());
        this.saveProject(projectFile);
    }

    public void saveProject(File projectFile) throws IOException {
        //Write over the old file
        projectFile.delete();
        projectFile.createNewFile();

        FileWriter fileWriter = new FileWriter(projectFile);

        //Write the metadata to the file
        fileWriter.write(super.toString());

        //Write the metrics data to the file
        for (MetricsTab metricsTab : this.metricsTabs){
            metricsTab.setMetric();
            metricsTab.metric.setSaveData();
            fileWriter.write("\n" + metricsTab.metric.writeSaveDataString());
        }

        fileWriter.close();
    }
}
