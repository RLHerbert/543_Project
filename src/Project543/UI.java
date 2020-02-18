package Project543;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI {
    private Stage mainWindow, langSelectWindow;

    UI() {
        /* Main Window initialization */
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
        Menu Help = new Menu("Help");

        menuBar.getMenus().addAll(File, Edit, Preferences, Metrics, Help);
        VBox vBox = new VBox(menuBar);

        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");
        MenuItem Exit = new MenuItem("Exit");
        File.getItems().addAll(New, Open, Save, Exit);

        MenuItem Language = new MenuItem("Language");
        Preferences.getItems().add(Language);

        Menu FP_menu = new Menu("Function Points");
        MenuItem EnterFPData_menu = new MenuItem("Enter FP Data");
        FP_menu.getItems().add(EnterFPData_menu);
        Metrics.getItems().add(FP_menu);


        Scene scene = new Scene(vBox, 960, 600);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    void openLangSelectWindow()
    {
        /* Language Selection Window initialization */
    }
}
