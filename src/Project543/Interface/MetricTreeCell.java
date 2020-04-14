package Project543.Interface;

import Project543.Metrics.SoftwareMaturityIndex;
import Project543.MetricsInterface.MetricsTab;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;

public class MetricTreeCell extends TreeCell<String> {
    public ContextMenu menu = new ContextMenu();
    public MetricsTab tab;

    public MetricTreeCell() {
        super();
        this.tab = null;
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem closeMenuItem = new MenuItem("Close");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        menu.getItems().addAll(openMenuItem, closeMenuItem, deleteMenuItem);

        openMenuItem.setOnAction((EventHandler<javafx.event.ActionEvent>) event -> {
            this.openOnClick();
        });

        closeMenuItem.setOnAction((EventHandler<javafx.event.ActionEvent>) event -> {
            this.closeOnClick();
        });

        deleteMenuItem.setOnAction((EventHandler<javafx.event.ActionEvent>) event -> {
            this.deleteOnClick();
        });
    }

    public MetricTreeCell(MetricsTab tab) {
        this();

        this.tab = tab;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);


        if (empty) {this.setText(null); this.setGraphic(null);}
        else {
            this.setText(this.getString());
            this.setGraphic(this.getGraphic());

            if (this.getTreeItem().isLeaf() && this.getTreeItem().getParent() != null) {
                setContextMenu(menu);
            }
        }
    }

    private String getString() {
        return this.getItem() == null ? "" : this.getItem().toString();
    }

    private void openOnClick() {
        //Opens the tab if it not already open
        System.out.println("Tree View Context Menu -> Open Clicked");

        MetricTree tree = (MetricTree) this.getTreeView(); //For convenience

        this.tab = tree.project.projectData.getTabFromName(this.getItem());


        if (!tree.project.tabPane.getTabs().contains(this.tab)) {
            //If the tab is not open
            tree.project.tabPane.getTabs().add(this.tab); //Open it
        }
    }

    private void closeOnClick() {
        //Closes the tab if it is open
        System.out.println("Tree View Context Menu -> Close Clicked");

        MetricTree tree = (MetricTree) this.getTreeView();

        this.tab = tree.project.projectData.getTabFromName(this.getItem());

        tree.project.tabPane.getTabs().remove(this.tab);
    }

    private void deleteOnClick() {
        //Deletes an tab if it is closed
        System.out.println("Tree View Context Menu -> Delete Clicked");

        MetricTree tree = (MetricTree) this.getTreeView();

        this.tab = tree.project.projectData.getTabFromName(this.getItem());

        if (!tree.project.tabPane.getTabs().contains(this.tab)) {
            if (this.tab.getMetricID() == SoftwareMaturityIndex.METRIC_ID) {tree.project.enterSMIData.setDisable(false);}

            tree.project.projectData.removeMetricsTab(this.tab);
            tree.root.getChildren().remove(this.getTreeItem()); //Remove the tree item associated with the tab
            tree.project.projectData.hasChanged = true;
        }
    }
}
