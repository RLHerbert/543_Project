//User Interface class for revision two of the project.
//
//NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


//TODO: Possibly implement extensions of common JavaFX classes such as dropdown menus in Generic form to have immediate access to the values they are supposed to represent

public class UserInterface {
    //Member Fields
    //
    //Member Enums and Classes
    //
    enum MenuOptions {FILE, EDIT, PREFERENCES, METRICS, HELP}

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final String DEFAULT_STAGE_NAME = "CECS 543 Metrics Suite";
    public static final double MAX_HEIGHT = new Stage().getMaxHeight() - 25.0;
    public static final double MAX_WIDTH = new Stage().getMaxWidth();
    public static Language selectedLanguage = Language.NONE;
    public static final String[] MAIN_MENU = {"File", "Edit", "Preferences", "Metrics", "Help"}; //TODO: Use
    public static final String[] FILE_MENU = {"New", "Open", "Save", "Exit"};
    public static final String[] EDIT_MENU = {""}; //Not yet implemented
    public static final String[] PREFERENCES_MENU = {"Select Language"}; //TODO: Use
    public static final String[] METRICS_MENU = {"Function Point", "Software Maturity Index"};
    public static final String[] HELP_MENU = {""}; //Not yet implemented

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    TabPane projectTabs; //The window's TabPane to hold the metrics tabs
    Stage projectStage; //The window's stage
    Scene projectScene; //The window's scene
    Language defaultLanguage; //The default language of all new Metrics
    boolean hasProject; //Keeps track of whether or not a project occupies the window or not

    //Member Methods
    //
    //Constructor(s)
    //
    public UserInterface(){
        //Default constructor

        //Create initial stage
        //Initialize
        projectTabs = new TabPane();
        hasProject = false;
        this.defaultLanguage = Language.NONE;

        //Open the window
        this.newWindow();
    }

    public UserInterface(ProjectData projectData){
        //ProjectData constructor
        //Initialize
        this.hasProject = true;
        this.projectTabs = new TabPane();
        this.defaultLanguage = Language.NONE;

        projectData.setDefaultProjectLanguage(this.defaultLanguage);

        //Open the window
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
        this.projectTabs.getTabs().addAll(projectData.metricsTabs);
        VBox sceneContents = new VBox(getNewMenuBar(projectData), projectTabs);

        Scene scene = new Scene(sceneContents);

        return scene;
    }

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
        //Sets the main menu
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
        //Sets the submenus of each of the main menu items
        //File
        MenuItem[] fileOptions = new MenuItem[4];
        setFileMenu(fileOptions);
        mainMenu[0].getItems().addAll(fileOptions);

        //Edit
        //Not Yet Implemented

        //Preferences
        MenuItem[] preferencesOptions = new MenuItem[1];
        setPreferencesMenu(preferencesOptions);
        mainMenu[2].getItems().addAll(preferencesOptions);


        //Metrics
        Menu[] metricsOptions = new Menu[ApplicationController.TOTAL_METRICS]; //TODO: Automate based on number of metrics?
        setMetricsMenu(metricsOptions);
        mainMenu[3].getItems().addAll(metricsOptions);

        //Help
        //NYI
    }

    public void setSubMenus(Menu[] mainMenu, ProjectData projectData){
        //Sets the submenus and hooks them up to the ProjectData that is passed in
        //File
        MenuItem[] fileOptions = new MenuItem[4];
        setFileMenu(fileOptions, projectData);
        mainMenu[0].getItems().addAll(fileOptions);

        //Edit
        //Not Yet Implemented

        //Preferences
        MenuItem[] preferencesOptions = new MenuItem[1];
        setPreferencesMenu(preferencesOptions, projectData);
        mainMenu[MenuOptions.PREFERENCES.ordinal()].getItems().addAll(preferencesOptions);

        //Metrics
        Menu[] metricsOptions = new Menu[ApplicationController.TOTAL_METRICS]; //TODO: Automate based on number of metrics?
        setMetricsMenu(metricsOptions, projectData);
        mainMenu[3].getItems().addAll(metricsOptions);
        //setMetricsMenu(metricsOptions);

        //Help
        //NYI
    }

    public void setFileMenu(MenuItem[] fileOptions){
        //Sets the "File" menu
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
        //Sets the "File" menu and hooks it up to the ProjectData passed in
        for (int i = 0; i < 4; i++){
            fileOptions[i] = new MenuItem(FILE_MENU[i]);
        }

        //File -> New
        fileOptions[0].setOnAction(actionEvent -> this.fileNewClick());

        //File -> Open
        fileOptions[1].setOnAction(actionEvent -> this.fileOpenClick());

        //File -> Save
        fileOptions[2].setOnAction(actionEvent -> {
            try {
                this.fileSaveClick(projectData);
            } catch (IOException e){
                System.err.println("ERROR: IOEXCEPTION");
                e.printStackTrace();
            }
        });

        //File ->
        fileOptions[3].setOnAction(actionEvent -> System.exit(0));
    }

    public void setPreferencesMenu(MenuItem[] preferencesOptions){
        //Sets the "Preferences" menu
        preferencesOptions[0] = new MenuItem("Select Language");

        preferencesOptions[0].setOnAction(actionEvent -> preferencesLanguageClick());
    }

    public void setPreferencesMenu(MenuItem[] preferencesOptions, ProjectData projectData){
        //Sets the "Preferences" menu and hooks it up to the ProjectData passed in
        preferencesOptions[0] = new MenuItem("Select Language");

        preferencesOptions[0].setOnAction(actionEvent -> preferencesLanguageClick(projectData));
    }
    
    public void setMetricsMenu(Menu[] metricsOptions){
        //Sets the "Metrics" menu
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
        //Sets the "Metrics" menu and hooks it up to the ProjectData passed in
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

    //Misc. Member Methods
    //
    public void newWindow(){
        //Creates a new window without an associated ProjectData
        this.projectStage = new Stage();
        setStageToDefault(projectStage, DEFAULT_STAGE_NAME);

        this.projectScene = getNewScene();

        projectStage.setScene(projectScene);

        projectStage.show();
    }

    public void newWindow(ProjectData projectData){
        //Creates a new window associated to the passed ProjectData
        projectStage = new Stage();
        setStageToDefault(projectStage, DEFAULT_STAGE_NAME + " - " + projectData.getProjectName());

        this.projectScene = getNewScene(projectData);

        projectStage.setScene(projectScene);

        projectStage.show();
    }

    public static String[] openNewProjectDialog(){
        //Opens the new project dialog and returns the metadata entered by the user
        //TODO: Cleanup and comments, refactor and allow for cancelling
        String[] newProjectMetaData = new String[4];

        //Temporary
        for (int i = 0; i < 4; i++){
            newProjectMetaData[i] = "";
        }

        Dialog<ArrayList<String>> dialog = new Dialog<ArrayList<String>>();
        dialog.setTitle("New Project");
        dialog.setHeaderText("Enter project information.");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        //TODO: make sizing based on constants
        grid.setHgap(10);
        grid.setVgap(10);

        TextField projectTitle = new TextField();
        projectTitle.setPromptText("Project Title");
        TextField productName = new TextField();
        productName.setPromptText("Product Name");
        TextField author = new TextField();
        author.setPromptText("Author");
        TextArea comments = new TextArea();
        comments.setPromptText("Comments");

        grid.add(new Label("Project Title:"), 0, 0);
        grid.add(projectTitle, 1, 0);
        grid.add(new Label("Product Name:"), 0, 1);
        grid.add(productName, 1, 1);
        grid.add(new Label("Author:"), 0, 2);
        grid.add(author, 1, 2);
        grid.add(new Label("Comments:"), 0, 3);
        grid.add(comments, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                ArrayList<String> dialogInfoEntered = new ArrayList<String>(4);
                dialogInfoEntered.add(projectTitle.getText());
                dialogInfoEntered.add(productName.getText());
                dialogInfoEntered.add(author.getText());
                dialogInfoEntered.add(comments.getText());
                return dialogInfoEntered;
            }
            return null;
        });

        //Updates project meta data
        Optional<ArrayList<String>> result = dialog.showAndWait();
        result.ifPresent(dialogInfoEntered ->
                {
                    newProjectMetaData[0] = dialogInfoEntered.get(0);
                    newProjectMetaData[1] = dialogInfoEntered.get(1);
                    newProjectMetaData[2] = dialogInfoEntered.get(2);
                    newProjectMetaData[3] = dialogInfoEntered.get(3);
                    //project.setFileName();
                }
        );

        //Open the dialog

        return newProjectMetaData;
    }

    //Onclick methods
    //
    public void fileNewClick(){
        //Click action for File -> New
        if (this.hasProject == false){
            //If there is not a project associated with window, make one and associate it
            hasProject = true;
            ProjectData projectData = ApplicationController.createProjectFromMetaData(openNewProjectDialog());
            projectData.setDefaultProjectLanguage(this.defaultLanguage);
            projectScene = getNewScene(projectData);
            projectStage.setTitle(DEFAULT_STAGE_NAME + " - " + projectData.getProjectName());
            projectStage.setScene(projectScene);
        }
        else {
            //Otherwise open a new windows
            UserInterface newProjectWindow = new UserInterface(ApplicationController.createProjectFromMetaData(openNewProjectDialog()));
        }
    }

    public void fileOpenClick(){
        //On click action for File -> Open

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(projectStage);

        if (selectedFile != null){
            if (ApplicationController.projectIsOpen(selectedFile.getName())){
                System.err.println("ERROR: PROJECT_ALREADY_OPEN");
            }
            else {
                if (this.hasProject == false){
                    try {
                        //Open the project
                        hasProject = true;
                        ProjectData projectData = ApplicationController.openProject(selectedFile);

                        //Set the new scene
                        projectScene = getNewScene(projectData);

                        //Set the stage up
                        projectStage.setScene(projectScene);
                        projectStage.setTitle(DEFAULT_STAGE_NAME + " - " + projectData.getProjectName());
                        projectStage.show();
                    } catch (FileNotFoundException f) {
                        System.err.println("ERROR: FILE_NOT_FOUND");
                        f.printStackTrace();
                    }
                }
                else {
                    try {
                        UserInterface openNewProjectWindow = new UserInterface(ApplicationController.openProject(selectedFile));
                    } catch (FileNotFoundException f){
                        System.err.println("ERROR: FILE_NOT_FOUND");
                        f.printStackTrace();
                    }
                }
            }
        }
    }

    public void fileSaveClick(ProjectData projectData) throws IOException {
        //On click action for File -> Save
        projectData.saveProject();
    }

    public void preferencesLanguageClick(){
        this.defaultLanguage = Language.openLanguageSelectWindow();
    }

    public void preferencesLanguageClick(ProjectData projectData){
        this.defaultLanguage = Language.openLanguageSelectWindow();
        projectData.setDefaultProjectLanguage(this.defaultLanguage);
    }

    public void enterFunctionPointClick(ProjectData projectData){
        //Click action of the Metrics -> Function Point -> "Enter FP Data" button
        this.projectTabs.getTabs().add(projectData.getNewFunctionPoint());
    }

    public void enterSoftwareMaturityClick(ProjectData projectData){
        //Click action of the Metrics -> Software Maturity Index -> "Enter SMI Data" button
        //TODO: make sure this works
        this.projectTabs.getTabs().add(projectData.getNewSoftwareMaturityIndex());
    }
}
