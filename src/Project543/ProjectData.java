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
    public boolean hasChanged;

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
        hasChanged = true;
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
        this.hasChanged = true;
    }

    public ProjectData(Scanner savedFile, String fileName) throws IOException, RecognitionException {
        //Save file constructor
        //Call super constructor
        super(savedFile, fileName);

        //Initialize member fields
        this.metricsTabs = new ArrayList<MetricsTab>();
        this.hasSMI = false;
        this.fileList = new ArrayList<File>();
        this.hasChanged = false;

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

    public Tab getNewProjectCode(int i) throws IOException, RecognitionException {
        //Creates a new ProjectCodeTab, adds it to metricsTabs, and returns it
        this.createNewProjectCode(i);
        return this.metricsTabs.get(this.metricsTabs.size()-1);
    }

    public ArrayList<Tab> getNewProjectCodes() throws IOException, RecognitionException {
        //Creates new ProjectCodeTabs, adds them to metricsTabs, and returns them as an array list
        ArrayList<Tab> projectCodeTabs = new ArrayList<>();
        for (int i = 0; i < this.fileList.size(); i++)
        {
            projectCodeTabs.add(getNewProjectCode(i));
        }
        return projectCodeTabs;
    }

    public MetricsTab getTabFromName(String name) {
        if (this.metricsTabs.size() > 0) {
            for (MetricsTab tab : this.metricsTabs) {
                if (tab.getText().equals(name)) {return tab;}
            }
        }

        return null;
    }

    //Setters
    //

    //Misc. Member Methods
    //
    public void calculateMetrics(){
        for (MetricsTab metric : this.metricsTabs){
            metric.calculateMetric();
        }
    }

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

    public void createNewProjectCode(int i) throws IOException, RecognitionException {
        //Creates a new ProjectCodeTab and adds it to metricsTabs
        File fileToAdd = fileList.get(i);

        ProjectCode projectCode = new ProjectCode(fileToAdd); //TODO: needed?
        ProjectCodeTab projectCodeTab = new ProjectCodeTab(fileToAdd);

        this.metricsTabs.add(projectCodeTab);
    }

    //Save and open methods
    //

    @Override
    public boolean hasChanged() {
        //Returns true if any metric has unsaved changes (edits since most recent save)
        if (this.hasChanged) {return true;}

        for (MetricsTab tab : this.metricsTabs){
            boolean tabHasChanged = tab.hasChanged();
            if (tabHasChanged){
                return true;
            }
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

    public MetricsTab openMetricsTabFromSavedFile(String metricSaveData) throws IOException, RecognitionException {
        //Adds all the saved metrics and their tab forms to the project
        metricSaveData = metricSaveData.substring(1, metricSaveData.length());
        StringTokenizer lineTokenizer = new StringTokenizer(metricSaveData, ",]");
        int metricID = Integer.parseInt(lineTokenizer.nextToken());

        if (metricID == FunctionPoint.METRIC_ID){
            return new FunctionPointTab("Function Point", metricSaveData);
        }
        else if (metricID == SoftwareMaturityIndex.METRIC_ID){
            this.hasSMI = true;
            return new SoftwareMaturityIndexTab("SMI", metricSaveData);
        }
        else if (metricID == ProjectCode.METRIC_ID) {
            //TODO
            return new ProjectCodeTab("Project Code", metricSaveData);
        }
        else {
            System.err.println("ERROR: METRIC_ID MISMATCH");
            return null;
        }
    }

    public void saveProject() throws IOException {
        //Write all data to file
        this.hasChanged = false;
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
        if (this.metricsTabs.size() == 0) {
            fileWriter.write("\n");
        }

        //Write the metrics data to the file
        for (MetricsTab metricTab : this.metricsTabs){
            metricTab.hasChanged = false;
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
