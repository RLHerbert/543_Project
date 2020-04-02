package Project543;

import Project543.MetricsInterface.MetricsTab;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

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
    //public Language selectedLanguage //TODO
    public BorderPane borderPane;
    public MenuBar menuBar;
    public TabPane tabPane;
    public Scene windowScene;
    //public TreeView<> treeView; //TODO

    //////////////////////
    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    public UserInterface_3(){
        //Default constructor

        //Initialize fields
        projectData = null;

        //Menu
        //menuBar = new MenuBar();
        this.setMenuBar();
        VBox menuContents = new VBox(menuBar);

        //Window body
        this.tabPane = new TabPane();
        VBox tabContents = new VBox(tabPane);
        VBox treeContents = new VBox();
        HBox projectContents = new HBox(treeContents, tabContents);
        this.borderPane = new BorderPane();
        this.windowScene = new Scene(borderPane);

        //Configure
        this.setTitle(DEFAULT_STAGE_NAME);
        this.setWidth(MAX_WIDTH);
        this.setHeight(MAX_HEIGHT);
        this.borderPane.setTop(menuContents);
        this.borderPane.setBottom(projectContents);
        this.setScene(this.windowScene);

        //Show stage
        this.show();
    }

    public UserInterface_3(ProjectData projectData){
        this();

        this.projectData = projectData;
    }

    //GETTERS
    //

    //SETTERS
    //
    private void setMenuBar(){
        //Sets the whole menu bar
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
        metricsMenu[0].getItems().add(enterFunctionPointData);
        metricsMenu[1].getItems().add(enterSMIData);
        mainMenuItems[3].getItems().addAll(metricsMenu);

        //Project Code
        MenuItem[] projectCodeMenu = new MenuItem[PROJECT_CODE_MENU.length];
        for (int i = 0; i < PROJECT_CODE_MENU.length; i++){
            projectCodeMenu[i] = new MenuItem(PROJECT_CODE_MENU[i]);
        }
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
        else {
            //File
            fileMenu[2].setOnAction(actionEvent -> this.fileSaveOnClick());

            //Metrics
            enterFunctionPointData.setOnAction(actionEvent -> this.metricsEnterFunctionPointOnClick());
            enterSMIData.setOnAction(actionEvent -> this.metricsEnterSMIOnCLick());

            //Project Code
            projectCodeMenu[0].setOnAction(actionEvent -> this.projectCodeAddOnClick());
            projectCodeMenu[1].setOnAction(actionEvent -> this.projectCodeStatisticsOnClick());
        }
    }

    //MISC. MEMBER METHODS
    //
    //On Click methods
    //File
    private void fileNewOnClick(){
        //File -> New
    }

    private void fileOpenOnClick(){
        //File -> Open
    }

    private void fileSaveOnClick(){
        //File -> Save
    }

    private void fileExitOnClick(){
        //File -> Exit
    }

    //Preferences
    private void preferencesSelectLanguageOnClick(){
        //Preferences -> Select Language
    }

    //Metrics
    private void metricsEnterFunctionPointOnClick(){
        //Metrics -> Function Point -> Enter Function Point Data
    }

    private void metricsEnterSMIOnCLick(){
        //Metrics -> Software Maturity Index -> Enter SMI Data
    }

    //Project Code
    private void projectCodeAddOnClick(){
        // -> Add Code
    }

    private void projectCodeStatisticsOnClick(){
        // -> Project Code Statistics
    }
}
