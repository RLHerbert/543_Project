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

import java.awt.desktop.AppForegroundListener;


//TODO: Possibly implement extensions of common JavaFX classes such as dropdown menus in Generic form to have immediate access to the values they are supposed to represent

public class UserInterface {
    //Member Variables
    //
    //Member Classes and Enums
    //
    enum MenuOptions {FILE, EDIT, PREFERENCES, METRICS, HELP}

    //Static Member Variables
    //
    //Constants
    //
    public static final String DEFAULT_STAGE_NAME = "CECS 543 Metrics Suite";
    public static final double MAX_HEIGHT = new Stage().getMaxHeight() - 25.0;
    public static final double MAX_WIDTH = new Stage().getMaxWidth();
    public static Language selectedLanguage = Language.NONE;
    public static final String[] FILE_MENU = {"New", "Open", "Save", "Exit"};
    //public static final String[] EDIT_MENU = {""};
    //public static final String[] PREFERENCES_MENU = {""};
    public static final String[] METRICS_MENU = {"Function Point", "Software Maturity Index"};
    //public static final String[] HELP_MENU = {""};

    //Non-static Member Variables
    VBox mainMenuBox; //The menu for every new window //TODO: Convert to MenuBar
    TabPane projectTabs;
    Stage projectStage;
    Scene projectScene;
    Language defaultLanguage; //The default language of all new Metrics
    boolean hasProject;
    //public ProjectData project; //Maybe?

    //Member Methods
    //
    //Constructor(s)
    //
    public UserInterface(){
        //Create initial stage
        projectTabs = new TabPane();
        hasProject = false;
        this.newWindow();
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
        projectTabs = new TabPane();
        this.hasProject = true;
        this.newWindow(projectData);
    }

    //Getters
    //
    public MenuBar getNewMenuBar(){
        MenuBar menuBar = new MenuBar();
        Menu[] mainMenu = new Menu[5];
        setMainMenu(menuBar, mainMenu);
        setSubMenus(mainMenu);

        return menuBar;
    }

    public MenuBar getNewMenuBar(ProjectData projectData){
        MenuBar menuBar = new MenuBar();
        Menu[] mainMenu = new Menu[5];
        setMainMenu(menuBar, mainMenu);
        setSubMenus(mainMenu, projectData);

        return menuBar;
    }

    public Scene getNewScene(){
        VBox sceneContents = new VBox(getNewMenuBar(), projectTabs);

        //sceneContents.

        Scene scene = new Scene(sceneContents);

        return scene;
    }

    public Scene getNewScene(ProjectData projectData){
        VBox sceneContents = new VBox(getNewMenuBar(projectData), projectTabs);

        //sceneContents.

        Scene scene = new Scene(sceneContents);

        return scene;
    }

    //TODO: public static Scene getNewScene(ProjectData projectData){}

    //Setters
    //
    public void setStageToDefault(Stage stageToSet, String stageName){
        //Sets up our default stage layout
        stageToSet.setTitle(stageName);
        stageToSet.setHeight(MAX_HEIGHT);
        stageToSet.setWidth(MAX_WIDTH);
        stageToSet.initModality(Modality.NONE);
    }

    public void setMainMenu(MenuBar menuBar, Menu[] mainMenu){
        //File
        mainMenu[0] = new Menu("File");

        //Edit
        mainMenu[1] = new Menu("Edit");

        //Preferences
        mainMenu[2] = new Menu("Preferences");

        //Metrics
        mainMenu[3] = new Menu("Metrics");

        //Help
        mainMenu[4] = new Menu("Help");

        menuBar.getMenus().addAll(mainMenu);
    }

    public void setSubMenus(Menu[] mainMenu){
        //File
        MenuItem[] fileOptions = new MenuItem[4];
        setFileMenu(fileOptions);
        mainMenu[0].getItems().addAll(fileOptions);

        //Edit
        //Not Yet Implemented

        //Preferences
        //NYI

        //Metrics
        Menu[] metricsOptions = new Menu[ApplicationController.TOTAL_METRICS]; //TODO: Automate based on number of metrics?
        setMetricsMenu(metricsOptions);
        mainMenu[3].getItems().addAll(metricsOptions);
        //setMetricsMenu(metricsOptions);

        //Help
        //NYI
    }

    //TODO
    public void setSubMenus(Menu[] mainMenu, ProjectData projectData){
        //File
        MenuItem[] fileOptions = new MenuItem[4];
        setFileMenu(fileOptions, projectData);
        mainMenu[0].getItems().addAll(fileOptions);

        //Edit
        //Not Yet Implemented

        //Preferences
        //NYI

        //Metrics
        Menu[] metricsOptions = new Menu[ApplicationController.TOTAL_METRICS]; //TODO: Automate based on number of metrics?
        setMetricsMenu(metricsOptions, projectData);
        mainMenu[3].getItems().addAll(metricsOptions);
        //setMetricsMenu(metricsOptions);

        //Help
        //NYI
    }

    public void setFileMenu(MenuItem[] fileOptions){
        for (int i = 0; i < 4; i++){
            fileOptions[i] = new MenuItem(FILE_MENU[i]);
        }

        //File -> New
        fileOptions[0].setOnAction(actionEvent -> this.fileNewClick());

        //File -> Open
        fileOptions[1].setOnAction(actionEvent -> fileOpenClick());

        //File -> Save
        fileOptions[2].setDisable(true);
        //Cannot save a project which doesn't yet exist

        //File ->
        fileOptions[3].setOnAction(actionEvent -> System.exit(0));
    }

    public void setFileMenu(MenuItem[] fileOptions, ProjectData projectData){
        for (int i = 0; i < 4; i++){
            fileOptions[i] = new MenuItem(FILE_MENU[i]);
        }

        //File -> New
        fileOptions[0].setOnAction(actionEvent -> this.fileNewClick());

        //File -> Open
        fileOptions[1].setOnAction(actionEvent -> this.fileOpenClick());

        //File -> Save
        fileOptions[2].setOnAction(actionEvent -> this.fileSaveClick(projectData));

        //File ->
        fileOptions[3].setOnAction(actionEvent -> System.exit(0));
    }
    
    public void setMetricsMenu(Menu[] metricsOptions){
        for (int i = 0; i < ApplicationController.TOTAL_METRICS; i++){
            metricsOptions[i] = new Menu(METRICS_MENU[i]);
        }

        //Function Point
        MenuItem enterFunctionPointData = new MenuItem("Enter FP Data");
        enterFunctionPointData.setDisable(true);
        metricsOptions[0].getItems().add(enterFunctionPointData);

        //Software Maturity Index
        MenuItem enterSoftwareMaturityData = new MenuItem("Enter SMI Data");
        enterSoftwareMaturityData.setDisable(true);
        metricsOptions[1].getItems().add(enterSoftwareMaturityData);
    }
    
    public void setMetricsMenu(Menu[] metricsOptions, ProjectData projectData){
        for (int i = 0; i < ApplicationController.TOTAL_METRICS; i++){
            metricsOptions[i] = new Menu(METRICS_MENU[i]);
        }

        //Function Point
        MenuItem enterFunctionPointData = new MenuItem("Enter FP Data");
        enterFunctionPointData.setOnAction(actionEvent -> this.enterFunctionPointClick(projectData));
        metricsOptions[0].getItems().add(enterFunctionPointData);

        //Software Maturity Index
        MenuItem enterSoftwareMaturityData = new MenuItem("Enter SMI Data");
        enterSoftwareMaturityData.setOnAction(actionEvent -> this.enterSoftwareMaturityClick(projectData));
        metricsOptions[1].getItems().add(enterSoftwareMaturityData);
    }

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
        //fileMenuList[0].setOnAction(actionEvent -> controller.createProject());
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
        TabPane metricsTabPane = new TabPane();
        enterFPData.setOnAction(e ->
                metricsTabPane.getTabs().add(new FunctionPointTab("New tab")));

        //Display everything
        mainMenuBox = new VBox(menuBar);

        //TODO: change stuff below; modularize into methods and stuff
        //change so that it only creates tab pane on first tab opening


        mainMenuBox.getChildren().add(metricsTabPane);

//        FPtab.setContent(grid);
//        tabs.getTabs().add(FPtab);
//        stage.projectStageLayout.getChildren().add(tabs);

        this.mainMenuBox = mainMenuBox;
        //return mainMenuBox;
    }

    //Misc. Member Methods
    //
    public void openProjectWindow(ApplicationController controller){
        //Create a new stage
        Stage projectStage = new Stage();

        //Stage setup
        projectStage.setTitle(DEFAULT_STAGE_NAME);
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
        projectStage.setTitle(DEFAULT_STAGE_NAME + " - " + project.getProjectName());
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
        //TODO: Move into FP tab
    }

    public void newWindow(){
        this.projectStage = new Stage();
        setStageToDefault(projectStage, DEFAULT_STAGE_NAME);

        this.projectScene = getNewScene();

        projectStage.setScene(projectScene);

        projectStage.show();
    }

    public void newWindow(ProjectData projectData){
        projectStage = new Stage();
        setStageToDefault(projectStage, DEFAULT_STAGE_NAME + " - " + projectData.getProjectName());

        this.projectScene = getNewScene(projectData);

        projectStage.setScene(projectScene);

        projectStage.show();
    }

    public static String[] openNewProjectDialog(){
        String[] newProjectMetaData = new String[4];

        //Temporary
        for (int i = 0; i < 4; i++){
            newProjectMetaData[i] = "default";
        }

        //Open the dialog

        return newProjectMetaData;
    }

    public void fileNewClick(){
        //Click action for File -> New
        if (this.hasProject == false){
            //If there is not a project associated with window, make one and associate it
            hasProject = true;
            ProjectData projectData = ApplicationController.createProject(openNewProjectDialog());
            projectScene = getNewScene(projectData);
            projectStage.setTitle(DEFAULT_STAGE_NAME + " - " + projectData.getProjectName());
            projectStage.setScene(projectScene);
        }
        else {
            //Otherwise open a new windows
            UserInterface newProjectWindow = new UserInterface(ApplicationController.createProject(openNewProjectDialog()));
        }
    }

    public void fileOpenClick(){
        //On click action for File -> Open
        System.out.println("Not yet implemented.");
    }

    public void fileSaveClick(ProjectData projectData){
        //On click action for File -> Save
        System.out.println("Not yet implemented.");
    }

    public void enterFunctionPointClick(ProjectData projectData){
        //Click action of the Metrics -> Function Point -> "Enter FP Data" button
        this.projectTabs.getTabs().add(projectData.getNewFunctionPoint());
    }

    public void enterSoftwareMaturityClick(ProjectData projectData){
        //Click action of the Metrics -> Software Maturity Index -> "Enter SMI Data" button
        System.out.println("Not yet implemented");
    }
}
