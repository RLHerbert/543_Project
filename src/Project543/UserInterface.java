//User Interface class for revision two of the project.
//
//NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//TODO: Possibly implement extensions of common JavaFX classes such as dropdown menus in Generic form to have imediate access to the values they are supposed to represent

public class UserInterface {
    //Member Variables
    //
    //Static Member Variables
    //
    public static final String PROJECT_TITLE = "CECS 543 Metrics Suite";

    //Non-static Member Variables
    VBox mainMenuBox; //The menu for every new window
    Language defaultLanguage; //The default language of all new Metrics

    //Member Methods
    //
    //Constructor(s)
    //
    public UserInterface(ApplicationController controller){
        //mainMenuBox = setMainMenuBox(controller);
        setMainMenuBox(controller);

        defaultLanguage =  Language.NONE;

        this.openProjectWindow(controller);
    }

    //Getters
    //

    //Setters
    //
    public void setMainMenuBox(ApplicationController controller){
        VBox mainMenuBox;

        //Create the Menu Bar for the main menu //TODO: convert to method
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu preferencesMenu = new Menu("Preferences");
        Menu metricsMenu = new Menu("Metrics");
        menuBar.getMenus().addAll(fileMenu, editMenu, preferencesMenu, metricsMenu);

        //Fill "File" submenu //TODO: Convert to method
        MenuItem[] fileMenuList = new MenuItem[4];
        fileMenuList[0] = new MenuItem("New");
        fileMenuList[1] = new MenuItem("Open");
        fileMenuList[2] = new MenuItem("Save");
        fileMenuList[3] = new MenuItem("Exit");
        fileMenu.getItems().addAll(fileMenuList);

        //Set fileMenuList actions //TODO: Convert to method
        fileMenuList[0].setOnAction(actionEvent -> controller.createProject());
        fileMenuList[3].setOnAction(actionEvent -> Platform.exit());

        //Display everything
        mainMenuBox = new VBox(menuBar);

        this.mainMenuBox = mainMenuBox;
        //return mainMenuBox;
    }

    //Misc. Member Methods
    //
    public void openProjectWindow(ApplicationController controller){
        //Create a new stage
        Stage projectStage = new Stage();

        //Stage setup
        projectStage.setTitle(PROJECT_TITLE);
        projectStage.setX(0); projectStage.setX(0); projectStage.setWidth(800); projectStage.setHeight(775);
        //Scene primaryScene = new Scene(primaryStage, 800, 775);

        Scene projectMenuScene = mainMenu(controller);
        projectStage.setScene(projectMenuScene);

        projectStage.show();
    }

    public void openProjectWindow(ProjectData project){
        //Create a new stage
        Stage projectStage = new Stage();
        //TODO: Handle new project dialog

        //Stage setup
        projectStage.setTitle(PROJECT_TITLE + " - " + project.getProjectName());
        projectStage.setX(0); projectStage.setX(0); projectStage.setWidth(800); projectStage.setHeight(775);
        projectStage.initModality(Modality.NONE);
        VBox localMenuBox = new VBox(mainMenuBox);

        Scene projectMenuScene = new Scene(localMenuBox);
        projectStage.setScene(projectMenuScene);

        projectStage.show();
    }

    private Scene mainMenu(ApplicationController controller){
        Scene mainMenu;

        mainMenu = new Scene(mainMenuBox);

        return mainMenu;
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
