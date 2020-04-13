package Project543.MetricsInterface;

import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;

public class MetricTreeCell extends TreeCell<MetricsTab> {
    public ContextMenu menu = new ContextMenu();

    public MetricTreeCell() {
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem closeMenuItem = new MenuItem("Close");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        menu.getItems().addAll(openMenuItem, closeMenuItem, deleteMenuItem);

        openMenuItem.setOnAction((EventHandler<javafx.event.ActionEvent>) event -> {
            System.out.println("Tree View Context Menu -> Open Clicked");
            //TODO
        });

        closeMenuItem.setOnAction((EventHandler<javafx.event.ActionEvent>) event -> {
            System.out.println("Tree View Context Menu -> Close Clicked");
            //TODO
        });

        deleteMenuItem.setOnAction((EventHandler<javafx.event.ActionEvent>) event -> {
            System.out.println("Tree View Context Menu -> Delete Clicked");
            //TODO
        });
    }

    @Override
    protected void updateItem(MetricsTab item, boolean empty) {
        super.updateItem(item, empty);

        if (getTreeItem().isLeaf() && getTreeItem().getParent()!= null) {
            setContextMenu(menu);
        }
    }
}
