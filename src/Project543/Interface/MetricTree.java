package Project543.Interface;

import Project543.MetricsInterface.MetricsTab;
import Project543.ProjectData;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class  MetricTree <G extends MetricsTab> extends TreeView<G> {
    //////////////////////
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

    ProjectData projectData; //Links to the represented project
    TreeItem<String> root; //The root of our tree
    ArrayList<MetricsTab> tabs;
    TreeCell<MetricsTab> leafs;

    //////////////////////
    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //

    public MetricTree(){
        super();

        this.root = null;
        this.tabs = null;
        this.leafs = new TreeCell<>();
    }

    public MetricTree(ProjectData projectData){
        //Call default constructor
        this();

        //Initialize member fields
        this.root = new TreeItem<>(projectData.getProjectName());
        this.tabs = projectData.metricsTabs;


        for (MetricsTab metricsTab : tabs){
            //Add the tabs

        }
    }

    //GETTERS
    //

    //SETTERS
    //

    //MISC. MEMBER METHODS
    //

}
