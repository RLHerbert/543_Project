package Project543;

import Project543.Metrics.FunctionPoint;
import Project543.Metrics.ProjectCode;
import Project543.Metrics.SoftwareMaturityIndex;
import Project543.MetricsInterface.FunctionPointTab;
import Project543.MetricsInterface.MetricsTab;
import Project543.MetricsInterface.ProjectCodeTab;
import Project543.MetricsInterface.SoftwareMaturityIndexTab;
import javafx.scene.control.Tab;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

//TODO: Confirm SMI is hooked up properly and working
//TODO: Reopen only saved metrics, open previously open metrics tabs
public class ProjectData extends ProjectMetaData implements SaveInterface {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public ArrayList<MetricsTab> metricsTabs;
    private boolean hasSMI;
    public ArrayList<File> fileList;

    //Member Methods
    //
    //Constructor(s)
    //
    public ProjectData(){
        //Default constructor
        super();
        //Call FunctionPoint default constructor

        this.metricsTabs = new ArrayList<MetricsTab>();
        this.hasSMI = false;
        this.fileList = new ArrayList<File>();
        //this.metricsData = new ArrayList<Metrics>();
    }

    public ProjectData(String[] metaData){
        //Metadata constructor, takes in a string and creates a new ProjectData with the relevant metadata
        //Call super constructor
        super(metaData);

        //Initialize member fields
        this.metricsTabs = new ArrayList<MetricsTab>();
        this.hasSMI = false;
        this.fileList = new ArrayList<File>();
    }

    public ProjectData(Scanner savedFile, String fileName){
        //Save file constructor
        //Call super constructor
        super(savedFile, fileName);

        //Initialize member fields
        this.metricsTabs = new ArrayList<MetricsTab>();
        this.hasSMI = false;
        this.fileList = new ArrayList<File>();

        //Populate metricsTabs
        while (savedFile.hasNextLine()){
            metricsTabs.add(openMetricsTabFromSavedFile(savedFile.nextLine()));
        }

        //Populate fileList
        //TODO: this^^
    }

    //Getters
    //
    public Tab getNewFunctionPoint(){
        //Creates a new FunctionPointTab, adds it to metricsTabs, and returns it
        this.createNewFunctionPoint();
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    public Tab getNewFunctionPoint(String tabName){
        this.createNewFunctionPoint();
        this.metricsTabs.get(this.metricsTabs.size()-1).setTabTitle(tabName);
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    public Tab getNewSoftwareMaturityIndex(){
        //Creates a new SoftwareMaturityIndexTab, adds it to metricsTabs, and returns it
        this.createNewSoftwareMaturityIndex();
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    public Tab getNewProjectCode() throws IOException, RecognitionException {
        //Creates a new ProjectCodeTab, adds it to metricsTabs, and returns it
        this.createNewProjectCode();
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
        SoftwareMaturityIndex softwareMaturityIndex = new SoftwareMaturityIndex();
        //SoftwareMaturityIndexTab softwareMaturityIndexTab = new SoftwareMaturityIndexTab("SMI", softwareMaturityIndex); //TODO: why is this here?
        SoftwareMaturityIndexTab softwareMaturityIndexTab = new SoftwareMaturityIndexTab("SMI");

        this.metricsTabs.add(softwareMaturityIndexTab);
    }

    public void createNewProjectCode() throws IOException, RecognitionException {
        //Creates a new ProjectCodeTab and adds it to metricsTabs
        File fileToAdd = fileList.get(fileList.size()-1);

        ProjectCode projectCode = new ProjectCode(fileToAdd); //TODO: needed?
        ProjectCodeTab projectCodeTab = new ProjectCodeTab(fileToAdd);

        this.metricsTabs.add(projectCodeTab);
    }

    //Save and open methods
    //

    public boolean hasChanged(){
        //Returns true if any metric has unsaved changes (edits since most recent save)
        for (MetricsTab openTabs : this.metricsTabs){
            if (openTabs.hasChanged()) return true;
        }

        return false;
    }

    public boolean hasSMITab(){
        return this.hasSMI;
    }

    public void removeMetricsTab(MetricsTab metricsTabToRemove){
        if (metricsTabToRemove.getMetricID() == SoftwareMaturityIndex.METRIC_ID){this.hasSMI = false;}

        this.metricsTabs.remove(metricsTabToRemove);
    }

    public MetricsTab openMetricsTabFromSavedFile(String metricSaveData){
        //Adds all the saved metrics and their tab forms to the project
        metricSaveData = metricSaveData.substring(1, metricSaveData.length());
        StringTokenizer lineTokenizer = new StringTokenizer(metricSaveData, ",");
        int metricID = Integer.parseInt(lineTokenizer.nextToken());

        if (metricID == FunctionPoint.METRIC_ID){
            return new FunctionPointTab("Function Point", metricSaveData);
        }
        else if (metricID == SoftwareMaturityIndex.METRIC_ID){
            this.hasSMI = true;
            return new SoftwareMaturityIndexTab("SMI", metricSaveData);
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
        for (MetricsTab metricTab : this.metricsTabs){
            metricTab.setMetric();
            metricTab.metric.setSaveData();
            fileWriter.write("\n" + metricTab.metric.writeSaveDataString());

            if (metricTab.getMetricID() == FunctionPoint.METRIC_ID) {
                fileWriter.write( " #" + metricTab.getText());
            }
            else if (metricTab.getMetricID() == ProjectCode.METRIC_ID) {
                fileWriter.write(" #" + metricTab.getExtraData()); //TODO: TEMP
            }
        }

        fileWriter.close();
    }
}
