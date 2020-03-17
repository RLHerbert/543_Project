//User Interface class for revision two of the project.
//
//NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


//TODO: Possibly implement extensions of common JavaFX classes such as dropdown menus in Generic form to have immediate access to the values they are supposed to represent

public class UserInterface {
    //Member Variables
    //
    //Static Member Variables
    //
    public static final String PROJECT_TITLE = "CECS 543 Metrics Suite";

    //Non-static Member Variables
    VBox mainMenuBox; //The menu for every new window //TODO: Convert to MenuBar
    Language defaultLanguage; //The default language of all new Metrics
    //public ProjectData project; //Maybe?

    //Member Methods
    //
    //Constructor(s)
    //
    public UserInterface(){

    }

    public UserInterface(ApplicationController controller){
        //First constructor called, hooks up to controller
        //TODO: Convert to default constructor, shouldn't need to know about the controller
        //mainMenuBox = setMainMenuBox(controller);
        setMainMenuBox(controller);

        defaultLanguage =  Language.NONE;

        this.openProjectWindow(controller);
    }

    public UserInterface(ProjectData projectData){

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
        Menu helpMenu = new Menu("Help");
        menuBar.getMenus().addAll(fileMenu, editMenu, preferencesMenu, metricsMenu, helpMenu);

        //Fill "File" with submenus //TODO: Convert to method
        MenuItem[] fileMenuList = new MenuItem[4];
        fileMenuList[0] = new MenuItem("New");
        fileMenuList[1] = new MenuItem("Open");
        fileMenuList[2] = new MenuItem("Save");
        fileMenuList[3] = new MenuItem("Exit");
        fileMenu.getItems().addAll(fileMenuList);

        //Set fileMenuList actions //TODO: Convert to method
        fileMenuList[0].setOnAction(actionEvent -> controller.createProject());
        fileMenuList[3].setOnAction(actionEvent -> Platform.exit());

        //Fill "Metrics" with submenu(s) //TODO: Convert to method
        Menu functionPointsMenu = new Menu("Function Points");
        Menu softwareMaturityIndexMenu = new Menu("Software Maturity Index");
        metricsMenu.getItems().addAll(functionPointsMenu, softwareMaturityIndexMenu);

        //Fill "Function Points" menu with submenu //TODO: Convert to method?
        MenuItem enterFPData = new MenuItem("Enter FP Data");
        functionPointsMenu.getItems().add(enterFPData);

        //Set enterFPData action //TODO: Convert to method?
        //TODO: IMPORTANT!!! change action below to check if project exists already
        // if project exists, thisProject.getNewFunctionPoint() and add to tab pane
        // also make tab title language specific? or like differentiate tab titles somehow
        FunctionPointTab newFPTab;
        enterFPData.setOnAction(e -> );

        //Display everything
        mainMenuBox = new VBox(menuBar);

        //TODO: change stuff below; modularize into methods and stuff
        //change so that it only creates tab pane on first tab opening
        TabPane metricsTabPane = new TabPane();
        metricsTabPane.getTabs().add()
        mainMenuBox.getChildren().add(metricsTabPane);

        FPtab.setContent(grid);
        tabs.getTabs().add(FPtab);
        stage.projectStageLayout.getChildren().add(tabs);

        this.mainMenuBox = mainMenuBox;
        //return mainMenuBox;
    }

    //TODO: move this method somewhere else
    public MenuItem createFPMenuItem(String name){
        MenuItem newMenuItem = new MenuItem(name);
        newMenuItem.setOnAction(e -> );
    }

    public void addFPTab(TabPane tabPane)
    {
        FunctionPointTab newFPTab = new FunctionPointTab();
        tabPane.getTabs().add()
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
