package Project543;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//TODO: CONSUME CLOSE REQUEST

public class UserInterface_3 extends Stage {
    //////////////////////
    //**MEMBER FIELDS**//
    //
    //MEMBER ENUMS AND CLASSES
    //
    enum MenuOptions {FILE, EDIT, PREFERENCES, METRICS, HELP}

    //STATIC MEMBER FIELDS
    //
    //Constant Static Fields
    //
    public static final String DEFAULT_STAGE_NAME = "CECS 543 Metrics Suite";
    public static final double MAX_HEIGHT = new Stage().getMaxHeight() - 25.0;
    public static final double MAX_WIDTH = new Stage().getMaxWidth();
    public static Language selectedLanguage = Language.NONE;
    public static final String[] MAIN_MENU = {"File", "Edit", "Preferences", "Metrics", "Project Code", "Help"};
    public static final String[] FILE_MENU = {"New", "Open", "Save", "Exit"};
    public static final String[] EDIT_MENU = {""}; //Not yet implemented
    public static final String[] PREFERENCES_MENU = {"Select Language"};
    public static final String[] METRICS_MENU = {"Function Point", "Software Maturity Index"};
    public static final String[] PROJECT_CODE_MENU = {"Add Code", "Project Code Statistics"};
    public static final String[] HELP_MENU = {""}; //Not yet implemented

    //Non-Constant Static Fields
    //

    //NON-STATIC MEMBER FIELDS
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public ProjectData projectData;
    public Language defaultProjectLanguage; //TODO
    public BorderPane borderPane;
    //VBox menuBarContents; //TODO
    public MenuBar menuBar;
    //HBox projectWindowContents; //Holds the TabPane and the TreeView //TODO
    public TabPane tabPane;
    //public TreeView<> treeView; //TODO
    public Scene windowScene;

    //Menu Items
    //TODO: SMI

    //////////////////////
    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    public UserInterface_3(){
        //Default constructor

        //Initialize fields
        this.projectData = null;
        this.defaultProjectLanguage = Language.NONE;

        //BorderPane
        this.borderPane = new BorderPane();

        //Menu
        //menuBar = new MenuBar();
        this.setMenuBar();
        //VBox menuContents = new VBox(menuBar);

        //Window body
        this.tabPane = new TabPane();
        VBox tabContents = new VBox(tabPane);
        VBox treeContents = new VBox();
        HBox projectContents = new HBox(treeContents, tabContents);
        this.windowScene = new Scene(borderPane);

        //Configure
        this.setTitle(DEFAULT_STAGE_NAME);
        this.setWidth(MAX_WIDTH);
        this.setHeight(MAX_HEIGHT);
        //this.borderPane.setTop(menuContents);
        this.borderPane.setBottom(projectContents);
        this.setScene(this.windowScene);

        //Set misc.
        //this.setExitWindowRequest(this, this.projectData);
        this.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

        //Show stage
        this.show();
    }

    public UserInterface_3(ProjectData projectData){
        //New project constructor
        this();

        this.projectData = projectData;
        this.setMenuBar();
        //this.setExitWindowRequest(this, this.projectData);
        this.setScene(this.windowScene);

        this.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

        this.show();
    }

    //GETTERS
    //

    //SETTERS
    //
    private void setExitWindowRequest(UserInterface_3 window, ProjectData projectData){
        this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                if (UserInterface_3.saveProjectQuery(projectData)) { window.close(); }
                else {
                    window.show();
                }
            }
        }); //TODO: FIX CANCELING
    }

    private void setMenuBar(){
        //Sets the whole menu bar
        this.menuBar = null;
        this.menuBar = new MenuBar(); //Create new MenuBar

        //Set the main menu items
        Menu[] mainMenuItems = new Menu[MAIN_MENU.length];
        for (int i = 0; i < MAIN_MENU.length; i++){
            mainMenuItems[i] = new Menu(MAIN_MENU[i]);
        }

        this.menuBar.getMenus().addAll(mainMenuItems);

        //File
        MenuItem[] fileMenu = new MenuItem[FILE_MENU.length];
        for (int i = 0; i < FILE_MENU.length; i++){
            fileMenu[i] = new MenuItem(FILE_MENU[i]);
        }
        fileMenu[0].setOnAction(actionEvent -> fileNewOnClick());
        fileMenu[2].setOnAction(actionEvent -> this.fileSaveOnClick());
        fileMenu[1].setOnAction(actionEvent -> fileOpenOnClick());
        fileMenu[3].setOnAction(actionEvent -> fileExitOnClick());

        mainMenuItems[0].getItems().addAll(fileMenu);

        //Edit //TODO

        //Preferences
        MenuItem[] preferencesMenu = new MenuItem[PREFERENCES_MENU.length];
        for (int i = 0; i < PREFERENCES_MENU.length; i++){
            preferencesMenu[i] = new MenuItem(PREFERENCES_MENU[i]);
        }
        preferencesMenu[0].setOnAction(actionEvent -> this.preferencesSelectLanguageOnClick());

        mainMenuItems[2].getItems().addAll(preferencesMenu);

        //Metrics
        Menu[] metricsMenu = new Menu[METRICS_MENU.length];
        for (int i = 0; i < METRICS_MENU.length; i++){
            metricsMenu[i] = new Menu(METRICS_MENU[i]);
        }
        MenuItem enterFunctionPointData = new MenuItem("Enter Function Point Data"); //Function Point
        MenuItem enterSMIData = new MenuItem("Enter SMI Data"); //SMI
        enterFunctionPointData.setOnAction(actionEvent -> this.metricsEnterFunctionPointOnClick());
        enterSMIData.setOnAction(actionEvent -> this.metricsEnterSMIOnCLick());
        metricsMenu[0].getItems().add(enterFunctionPointData);
        metricsMenu[1].getItems().add(enterSMIData);
        mainMenuItems[3].getItems().addAll(metricsMenu);

        //Project Code
        MenuItem[] projectCodeMenu = new MenuItem[PROJECT_CODE_MENU.length];
        for (int i = 0; i < PROJECT_CODE_MENU.length; i++){
            projectCodeMenu[i] = new MenuItem(PROJECT_CODE_MENU[i]);
        }
        projectCodeMenu[0].setOnAction(actionEvent -> this.projectCodeAddOnClick());
        projectCodeMenu[1].setOnAction(actionEvent -> this.projectCodeStatisticsOnClick());
        mainMenuItems[4].getItems().addAll(projectCodeMenu);


        //Help //TODO

        //Set (remaining) projectData dependent actionEvents
        if (this.projectData == null){ //Ternary's need to returnary
            //File
            fileMenu[2].setDisable(true);

            //Metrics
            enterFunctionPointData.setDisable(true);
            enterSMIData.setDisable(true);

            //Project Code
            projectCodeMenu[0].setDisable(true);
            projectCodeMenu[1].setDisable(true);
        }

        VBox menuContents = new VBox(menuBar);
        this.borderPane.setTop(menuContents);
    }

    //MISC. MEMBER METHODS
    //
    //On Click methods
    //File
    private void fileNewOnClick(){
        //File -> New
        System.out.println("File -> New Clicked");

        String[] projectToCreateMetaData = this.createNewProjectDialog();

        if (projectToCreateMetaData != null){
            if (this.projectData == null){
                this.projectData = new ProjectData(projectToCreateMetaData);
                this.setMenuBar();
                //this.setExitWindowRequest(this, this.projectData);
            }
            else {
                UserInterface_3 openNewWindow = new UserInterface_3(new ProjectData(projectToCreateMetaData));
            }
        }
    }

    private void fileOpenOnClick(){
        //File -> Open
        System.out.println("File -> Open Clicked");
        ProjectData projectToOpen = this.openProjectDialog(); //Prompts user to open a file

        if (projectToOpen != null){
            //If the user has opened a file
            if (this.projectData != null){
                //If the window does not already have a project
                this.projectData = projectToOpen;
            }
            else {
                //The window already has a project
                UserInterface_3 windowToOpen = new UserInterface_3(projectToOpen); //Open a new project
            }
        }
    }

    private void fileSaveOnClick(){
        //File -> Save
        System.out.println("File -> Save Clicked");

        try {
            this.projectData.saveProject();
        } catch (IOException e){
            System.err.println("ERROR: SAVE_PROJECT_ERROR");
            e.printStackTrace();
        }
    }

    private void fileExitOnClick(){
        //File -> Exit
        System.out.println("File -> Exit Clicked");

        if(ApplicationController.exitProgramRequest()) { System.exit(0); }
    }

    //Preferences
    private void preferencesSelectLanguageOnClick(){
        //Preferences -> Select Language
        System.out.println("Preferences -> Select Language Clicked");
        this.defaultProjectLanguage = Language.openLanguageSelectWindow();
    }

    //Metrics
    private void metricsEnterFunctionPointOnClick(){
        //Metrics -> Function Point -> Enter Function Point Data
        System.out.println("Metrics -> Function Point -> Enter Function Point Data Clicked");

        this.tabPane.getTabs().add(this.projectData.getNewFunctionPoint());
    }

    private void metricsEnterSMIOnCLick(){
        //Metrics -> Software Maturity Index -> Enter SMI Data
        System.out.println("Metrics -> SMI -> Enter SMI Data Clicked");

        this.tabPane.getTabs().add(this.projectData.getNewSoftwareMaturityIndex());
        //TODO: Disable when SMI added, enable if removed
    }

    //Project Code
    private void projectCodeAddOnClick(){
        // -> Add Code
        System.out.println("Project Code -> Add Code Clicked");
    }

    private void projectCodeStatisticsOnClick(){
        // -> Project Code Statistics
        System.out.println("Project Code -> Project Code Statistics Clicked");
    }

    //Other On Clicks

    //Saving and opening files
    private String[] createNewProjectDialog(){
        //Opens the new project dialog and returns the metadata entered by the user
        //TODO: Cleanup and comments, refactor and allow for cancelling
        String[] newProjectMetaData;

        Dialog<String[]> dialog = new Dialog<>();

        //Dialog<ArrayList<String>> dialog = new Dialog<ArrayList<String>>();
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
                String[] dialogInfoEntered = new String[4];
                dialogInfoEntered[0] = projectTitle.getText();
                dialogInfoEntered[1] = productName.getText();
                dialogInfoEntered[2] = author.getText();
                dialogInfoEntered[3] = comments.getText();
                return dialogInfoEntered;
            }
            else if (dialogButton == ButtonType.CANCEL) {
                return new String[]{""};
            }
            return null;
        });

        //Updates project meta data

        newProjectMetaData = dialog.showAndWait().get();

        //Open the dialog
        if (newProjectMetaData.length == 1){return null;}

        return newProjectMetaData;
    }

    private ProjectData openProjectDialog(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Metric Suite Files", "*.ms"));
        File savedProject = fileChooser.showOpenDialog(this);

        if (savedProject != null){
            //Open the project
            try {
                return ApplicationController.openProject(savedProject);
            } catch (FileNotFoundException f){
                System.err.println("ERROR: FILE_NOT_FOUND");
                f.printStackTrace();
            }
        }

        return null;
    }

    public static Boolean saveProjectQuery(ProjectData projectData){
        if (projectData == null){
            return true;
        }

        if (!projectData.hasChanged()){
            return true;
        }

        Dialog<Boolean> saveProjectDialog = new Dialog<Boolean>();
        saveProjectDialog.setTitle("Save Project");
        saveProjectDialog.setHeaderText("Would you like to save \"" + projectData.getProjectName() + "\" before exiting?");

        saveProjectDialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);



        saveProjectDialog.setResultConverter(dialogButton -> {
            //Boolean saveProject = null;
            if(dialogButton == ButtonType.YES){
                try {
                    projectData.saveProject();
                } catch (IOException e){
                    System.err.println("ERROR: SAVE_PROJECT_ERROR");
                }
                return true;
            }
            else if (dialogButton == ButtonType.NO){
                return true;
            }
            else if (dialogButton == ButtonType.CANCEL){
                return false;
            }

            return null;
        });

        //Optional<Boolean> result = saveProjectDialog.showAndWait();

        return saveProjectDialog.showAndWait().get();
    }

    public void closeWindowEvent(WindowEvent windowEvent){
        if (!this.saveProjectQuery()) {windowEvent.consume();}
    }

    public Boolean saveProjectQuery(){
        if (this.projectData == null){
            return true;
        }

        if (!this.projectData.hasChanged()){
            return true;
        }

        Dialog<Boolean> saveProjectDialog = new Dialog<Boolean>();
        saveProjectDialog.setTitle("Save Project");
        saveProjectDialog.setHeaderText("Would you like to save \"" + this.projectData.getProjectName() + "\" before exiting?");

        saveProjectDialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);



        saveProjectDialog.setResultConverter(dialogButton -> {
            //Boolean saveProject = null;
            if(dialogButton == ButtonType.YES){
                try {
                    this.projectData.saveProject();
                } catch (IOException e){
                    System.err.println("ERROR: SAVE_PROJECT_ERROR");
                }
                return true;
            }
            else if (dialogButton == ButtonType.NO){
                return true;
            }
            else if (dialogButton == ButtonType.CANCEL){
                return false;
            }

            return null;
        });

        //Optional<Boolean> result = saveProjectDialog.showAndWait();

        return saveProjectDialog.showAndWait().get();
    }

    //Overrides

    /*
    @Override
    public void close(){
        if (this.saveProjectQuery()){
            super.close();
        }
    }
     */
}
