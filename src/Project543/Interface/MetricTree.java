package Project543.Interface;

import Project543.MetricsInterface.MetricsTab;
import Project543.ProjectData;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class MetricTree extends TreeView<String> {
    //////////////////////
    //**MEMBER FIELDS**//
    //
    //MEMBER ENUMS AND CLASSES
    //gi

    //STATIC MEMBER FIELDS
    //
    //Constant Static Fields
    //
    private static final Node rootIcon = new ImageView(
            new Image("https://pngimage.net/wp-content/uploads/2018/06/flat-folder-icon-png-6.png")
    );

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
        this.root = new TreeItem<String>(projectData.getProjectName(), rootIcon);

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
