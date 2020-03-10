//User Interface class for revision two of the project.
//
//NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class UserInterface {
    //Member Variables
    //

    //Member Methods
    //
    //Constructor(s)
    //

    //Getters
    //

    //Setters
    //

    //Misc. Member Methods
    //
    public static void openMainWindow(Controller controller){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("CECS 543 Metrics Suite");
        primaryStage.setX(0); primaryStage.setX(0); primaryStage.setWidth(800); primaryStage.setHeight(775);
        //Scene primaryScene = new Scene(primaryStage, 800, 775);

        //Create the Menu Bar for the main menu //TODO: convert to method
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu preferencesMenu = new Menu("Preferences");
        Menu metricsMenu = new Menu("Metrics");
        menuBar.getMenus().addAll(fileMenu, editMenu, preferencesMenu, metricsMenu);

        //Fill "File" //TODO: Convert to method
        MenuItem[] fileMenuList = new MenuItem[4];
        fileMenuList[0] = new MenuItem("New");
        fileMenuList[1] = new MenuItem("Open");
        fileMenuList[2] = new MenuItem("Save");
        fileMenuList[3] = new MenuItem("Exit");

        //Set fileMenuList actions //TODO: Convert to method
    }

    public static void openProjectPane(ProjectData project){

    }

    public static void createNewProject(ProjectData newProject){
        //Open new project dialog window
        //Set newProject data to values in dialog window
        openProjectPane(newProject);
    }

    public static void openLanguageSelectionWindow(ProjectData project){

    }
}
