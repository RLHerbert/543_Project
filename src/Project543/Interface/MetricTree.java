package Project543.Interface;

import Project543.MetricsInterface.MetricsTab;
import Project543.ProjectData;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class MetricTree extends TreeView<MetricsTab> {
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
    TreeCell<MetricsTab> root; //The root of our tree

    //////////////////////
    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //

    public MetricTree(){
        super();

        this.projectData = null;
        this.root = null;
        this.setEditable(false);
    }

    public MetricTree(ProjectData projectData){
        //Call default constructor
        this();

        //Initialize member fields
        this.projectData = projectData;
        this.root = new TreeCell<MetricsTab>();

        for (MetricsTab metricsTab : this.projectData.metricsTabs){
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
