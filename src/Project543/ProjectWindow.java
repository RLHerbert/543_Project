package Project543;

import Project543.Interface.Dialogs;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProjectWindow extends Stage {
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
    private MenuItem enterSMIData;
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
    public ProjectWindow() {
        //Default constructor

        //Initialize fields
        this.projectData = null;
        this.defaultProjectLanguage = Language.NONE;

        this.setWindow();

        //this.setScene(this.windowScene);

        //Set misc.
        this.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

        //Show stage
        this.show();
    }

    public ProjectWindow(ProjectData projectData) {
        //New project constructor
        this();

        this.projectData = projectData;
        this.projectData.setDefaultProjectLanguage(this.defaultProjectLanguage);
        this.setWindow();
        //this.setScene(this.windowScene);

        this.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent); //TODO: Remove, should be unnecessary

        this.show();
    }

    //GETTERS
    //

    //SETTERS
    //

    private void setMenuBar() {
        //Sets the whole menu bar
        this.menuBar = null;
        this.menuBar = new MenuBar(); //Create new MenuBar

        //Set the main menu items
        Menu[] mainMenuItems = new Menu[MAIN_MENU.length];
        for (int i = 0; i < MAIN_MENU.length; i++) {
            mainMenuItems[i] = new Menu(MAIN_MENU[i]);
        }

        this.menuBar.getMenus().addAll(mainMenuItems);

        //File
        MenuItem[] fileMenu = new MenuItem[FILE_MENU.length];
        for (int i = 0; i < FILE_MENU.length; i++) {
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
        for (int i = 0; i < PREFERENCES_MENU.length; i++) {
            preferencesMenu[i] = new MenuItem(PREFERENCES_MENU[i]);
        }
        preferencesMenu[0].setOnAction(actionEvent -> this.preferencesSelectLanguageOnClick());

        mainMenuItems[2].getItems().addAll(preferencesMenu);

        //Metrics
        Menu[] metricsMenu = new Menu[METRICS_MENU.length];
        for (int i = 0; i < METRICS_MENU.length; i++) {
            metricsMenu[i] = new Menu(METRICS_MENU[i]);
        }
        MenuItem enterFunctionPointData = new MenuItem("Enter Function Point Data"); //Function Point
        this.enterSMIData = new MenuItem("Enter SMI Data"); //SMI
        enterFunctionPointData.setOnAction(actionEvent -> this.metricsEnterFunctionPointOnClick());
        this.enterSMIData.setOnAction(actionEvent -> this.metricsEnterSMIOnCLick());
        metricsMenu[0].getItems().add(enterFunctionPointData);
        metricsMenu[1].getItems().add(this.enterSMIData);
        mainMenuItems[3].getItems().addAll(metricsMenu);

        //Project Code
        MenuItem[] projectCodeMenu = new MenuItem[PROJECT_CODE_MENU.length];
        for (int i = 0; i < PROJECT_CODE_MENU.length; i++) {
            projectCodeMenu[i] = new MenuItem(PROJECT_CODE_MENU[i]);
        }
        projectCodeMenu[0].setOnAction(actionEvent -> {
            try {
                this.projectCodeAddOnClick();
            } catch (IOException | RecognitionException e) {
                e.printStackTrace();
            }
        });
        projectCodeMenu[1].setOnAction(actionEvent -> this.projectCodeStatisticsOnClick());
        mainMenuItems[4].getItems().addAll(projectCodeMenu);


        //Help //TODO

        //Set (remaining) projectData dependent actionEvents
        if (this.projectData == null) { //Ternary's need to returnary
            //File
            fileMenu[2].setDisable(true);

            //Metrics
            enterFunctionPointData.setDisable(true);
            this.enterSMIData.setDisable(true);

            //Project Code
            projectCodeMenu[0].setDisable(true);
            projectCodeMenu[1].setDisable(true);
        }
        else {
            this.enterSMIData.setDisable(this.projectData.hasSMITab());
        }

        VBox menuContents = new VBox(menuBar);
        this.borderPane.setTop(menuContents);
    }

    private void setWindow(){
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

        //Move into a function
        tabContents.prefWidthProperty().bind(this.widthProperty().multiply(0.8));
        treeContents.prefWidthProperty().bind(this.widthProperty().multiply(0.2));
        tabContents.prefHeightProperty().bind(this.heightProperty().multiply(1.0));
        treeContents.prefHeightProperty().bind(this.heightProperty().multiply(1.0));

        //this.borderPane.setTop(menuContents);
        this.borderPane.setBottom(projectContents);

        if (projectData != null){
            this.tabPane.getTabs().addAll(this.projectData.metricsTabs);
        }

        this.setScene(this.windowScene);
    }
    //MISC. MEMBER METHODS
    //
    //On Click methods
    //File
    private void fileNewOnClick() {
        //File -> New
        System.out.println("File -> New Clicked");

        String[] projectToCreateMetaData = this.createNewProjectDialog();

        if (projectToCreateMetaData != null) {
            if (this.projectData == null) {
                this.projectData = new ProjectData(projectToCreateMetaData);
                this.projectData.setDefaultProjectLanguage(this.defaultProjectLanguage);
                this.setMenuBar();
                //this.setExitWindowRequest(this, this.projectData);
            } else {
                ProjectWindow openNewWindow = new ProjectWindow(new ProjectData(projectToCreateMetaData));
            }
        }
    }

    private void fileOpenOnClick() {
        //File -> Open
        System.out.println("File -> Open Clicked");
        ProjectData projectToOpen = this.openProjectDialog(); //Prompts user to open a file

        if ((projectToOpen != null) && (!ApplicationController.projectIsOpen(projectToOpen.getFileName()))) {
            //If the user has opened a file
            if (this.projectData == null) {
                //If the window does not already have a project
                System.out.println("Opening project in same window.");
                this.projectData = projectToOpen;
                this.setWindow();
            } else {
                //The window already has a project
                System.out.println("Opening project in new window.");
                ProjectWindow windowToOpen = new ProjectWindow(projectToOpen); //Open a new project window
                ApplicationController.openProjectWindows.add(windowToOpen);
            }
        }
    }

    private void fileSaveOnClick() {
        //File -> Save
        System.out.println("File -> Save Clicked");

        try {
            this.projectData.saveProject();
        } catch (IOException e) {
            System.err.println("ERROR: SAVE_PROJECT_ERROR");
            e.printStackTrace();
        }
    }

    private void fileExitOnClick() {
        //File -> Exit
        System.out.println("File -> Exit Clicked");

        if (ApplicationController.exitProgramRequest()) {
            System.exit(0);
        }
    }

    //Preferences
    private void preferencesSelectLanguageOnClick() {
        //Preferences -> Select Language
        System.out.println("Preferences -> Select Language Clicked");
        this.defaultProjectLanguage = Language.openLanguageSelectWindow();
    }

    //Metrics
    private void metricsEnterFunctionPointOnClick() {
        //Metrics -> Function Point -> Enter Function Point Data
        System.out.println("Metrics -> Function Point -> Enter Function Point Data Clicked");

        String tabName = Dialogs.createFunctionPointDialog();

        if (tabName != null) {
            this.tabPane.getTabs().add(this.projectData.getNewFunctionPoint(tabName));
        }
    }

    private void metricsEnterSMIOnCLick() {
        //Metrics -> Software Maturity Index -> Enter SMI Data
        System.out.println("Metrics -> SMI -> Enter SMI Data Clicked");

        this.enterSMIData.setDisable(true); //TODO: Get window from SMI tab and setDisable false on close using event handler (I THINK)
        this.tabPane.getTabs().add(this.projectData.getNewSoftwareMaturityIndex());
        //TODO: Disable when SMI added, enable if removed
    }

    //Project Code
    private void projectCodeAddOnClick() throws IOException, RecognitionException {
        // -> Add Code
        System.out.println("Project Code -> Add Code Clicked");

        //opens fileChooser
        List<File> selectedFileList = Dialogs.createAddCodeDialog(this);

        if (selectedFileList != null) {
            this.projectData.fileList.clear();
            this.projectData.fileList.addAll(selectedFileList);
            this.tabPane.getTabs().addAll(this.projectData.getNewProjectCodes());
        }
        //if no file is chosen, does nothing (cancels)
        //otherwise, add chosen files to tree view and opens project code tabs without anything in them

    }

    private void projectCodeStatisticsOnClick() {
        // -> Project Code Statistics
        System.out.println("Project Code -> Project Code Statistics Clicked");

        //computes the metric data and displays
        this.projectData.calculateMetrics();
    }

    //Other On Clicks

    //Saving and opening files
    private String[] createNewProjectDialog() {
        //Opens the new project dialog and returns the metadata entered by the user
        //TODO: Cleanup and comments, refactor and allow for cancelling
        String[] newProjectMetaData;

        Dialog<String[]> dialog = new Dialog<>();

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
            } else if (dialogButton == ButtonType.CANCEL) {
                return new String[]{""};
            }
            return null;
        });

        //Updates project meta data

        newProjectMetaData = dialog.showAndWait().get();

        //Open the dialog
        if (newProjectMetaData.length == 1) {
            return null;
        }

        return newProjectMetaData;
    }

    private ProjectData openProjectDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Metric Suite Files", "*.ms"));
        File savedProject = fileChooser.showOpenDialog(this);

        if (savedProject != null) {
            //Open the project
            try {
                return ApplicationController.openProject(savedProject);
            } catch (FileNotFoundException f) {
                System.err.println("ERROR: FILE_NOT_FOUND");
                f.printStackTrace();
            } catch (RecognitionException | IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void closeWindowEvent(WindowEvent windowEvent) {
        if (!this.saveProjectQuery()) {
            windowEvent.consume();
        }
    }

    public Boolean saveProjectQuery() {
        //Returns true if no changes have been made to the project
        if (this.projectData == null) {
            return true;
        }

        if (!this.projectData.hasChanged()) {
            return true;
        }

        //Otherwise, it prompts the user to save.
        //Create the save query dialog
        Dialog<ButtonType> saveProjectDialog = new Dialog<>();
        saveProjectDialog.setTitle("Save Project");
        saveProjectDialog.setHeaderText("Would you like to save \"" + this.projectData.getProjectName() + "\" before exiting?");
        saveProjectDialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

        saveProjectDialog.setResultConverter(dialogButton -> {
            return dialogButton;
        });

        Optional<ButtonType> saveDialogChoice = saveProjectDialog.showAndWait();

        if (saveDialogChoice.isPresent()) {
            if (saveDialogChoice.get() == ButtonType.YES) {
                //If yes, save and exit the window
                try {
                    this.projectData.saveProject();
                } catch (IOException e) {
                    System.err.println("ERROR: SAVE_PROJECT_ERROR");
                }
                return true;
            } else if (saveDialogChoice.get() == ButtonType.NO) { //Probably coerced to int != 0
                return true;
            }
        }

        return false;
    }

    //Overrides
}
