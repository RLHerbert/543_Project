package Project543.Interface;

import Project543.MetricsInterface.MetricsTab;
import Project543.ProjectData;
import Project543.ProjectWindow;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

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
            new Image("https://pngimage.net/wp-content/uploads/2018/06/flat-folder-icon-png-6.png", 16, 16, true, true)
    );

    //Non-Constant Static Fields
    //

    //NON-STATIC MEMBER FIELDS
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    ProjectWindow project; //Links to the represented project
    TreeItem<String> root; //The root of our tree

    //////////////////////
    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //

    public MetricTree(){
        super();

        this.project = null;
        this.root = null;
        this.setEditable(false);
    }

    public MetricTree(ProjectWindow project){
        //Call default constructor
        this();

        //Initialize member fields
        this.project = project;
        this.root = new TreeItem<String>(project.projectData.getProjectName(), rootIcon);

        this.setRoot(this.root);
        this.root.setExpanded(true);

        for (MetricsTab metricsTab : this.project.projectData.metricsTabs){
            //Add the tabs
            TreeItem<String> metricTabLeaf = new TreeItem<>(metricsTab.getText());
            this.root.getChildren().add(metricTabLeaf);
        }

        this.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> stringTreeView) {
                return new MetricTreeCell();
            }
        });
    }

    //GETTERS
    //

    //SETTERS
    //

    public void updateChildren() {
        this.root.getChildren().clear();

        //Add the tabs
    }

    //MISC. MEMBER METHODS
    //

}
