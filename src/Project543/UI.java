package Project543;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI {
    private Stage mainWindow, langSelectWindow;

    UI() {
        /* main window initialization */
        mainWindow = new Stage();
        mainWindow.setTitle("CECS 543 Project");
        /* create set size function, instead of code below */
        mainWindow.setHeight(775);
        mainWindow.setWidth(800);
        mainWindow.setX(0);
        mainWindow.setY(0); //set window sizing
        mainWindow.show();

        /* Menu Bar initialization */
        MenuBar menuBar = new MenuBar();
        Menu File = new Menu("File");
        Menu Edit = new Menu("Edit");
        Menu Preferences = new Menu("Preferences");
        Menu Metrics = new Menu("Metrics");

        menuBar.getMenus().addAll(File, Edit, Preferences, Metrics);

        VBox vBox = new VBox(menuBar);

        Scene scene = new Scene(vBox, 960, 600);
        mainWindow.setScene(scene);

        mainWindow.show();
    }
}
