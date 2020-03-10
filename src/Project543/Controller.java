package Project543;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    //Member Variables
    public ArrayList<ProjectData> openProjects; //Public to make accessible if needed, TEMPORARY
    ProjectStage currentWindow;

    //Member Methods
    //Constructor(s)
    public Controller() {
        openProjects = new ArrayList<ProjectData>();

        currentWindow = UI.openNewWindow();
        setButtons();
    }

    //Getters

    //Setters
    //TODO: finish button actions!!!!
    void setButtons() {
        final ArrayList<Button>[] FPButtons = new ArrayList[]{new ArrayList<Button>()}; //IDE made me do it this way bc it's set in an anon fxn

        //TODO: complete the menu actions below
        //Menu Item "Buttons"
        currentWindow.getNewButton().setOnAction(event -> {createProject();});
        currentWindow.getOpenButton().setOnAction(event -> {/*get user input, then openProject();*/});
        currentWindow.getSaveButton().setOnAction(event -> {saveProject(openProjects.get(UI.openProjectStages.indexOf(currentWindow)));});
        currentWindow.getExitButton().setOnAction(event -> {Platform.exit();/*TODO: add a diaglog asking if you want to save*/});
        currentWindow.getLanguageMenuButton().setOnAction(event -> {});
        currentWindow.getFPMenuButton().setOnAction(event -> {
            if (openProjects.isEmpty())
                FPButtons[0] = UI.openFunctionPane(currentWindow, new ProjectData());
            FPButtons[0] = UI.openFunctionPane(currentWindow, openProjects.get(UI.openProjectStages.indexOf(currentWindow)));
        });

        //TODO: complete the FP actions below; fix too, since FPButtons is empty at beginning, these go out of bounds
        //FP Pane Buttons
        FPButtons[0].get(0).setOnAction(event -> {/*updates FP values including IDV sums or w/e*/}); //event action for compute FP
        FPButtons[0].get(1).setOnAction(event -> {/*opens VAF window*/}); //event action for VAF button
        FPButtons[0].get(2).setOnAction(event -> {/*updates code size output*/}); //event action for compute code size
        FPButtons[0].get(3).setOnAction(event -> {/*opens language panel*/}); //event action for change language
    }


    //Misc. Member Methods
    public boolean projectIsOpen(File fileName) {
        for (ProjectData projectData : openProjects) {
            if (projectData.getFileName().equals(fileName.getName())) return true;
        }

        return false;
    }

    public void createProject() {
        //Handles the actual creation of and addition to the ProjectData for openProjects
        ProjectData projectToAdd = new ProjectData();
        openProjects.add(projectToAdd);
        //TODO: Pass new ProjectData to UI, somehow
        UI.openNewProjectDialog(projectToAdd);
        currentWindow = UI.updateUI(currentWindow, projectToAdd);
    }

    public void createProject(File savedFile) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(savedFile);
            //Call ProjectData constructor to create project, add to openProjects
            ProjectData projectToAdd = new ProjectData(fileScanner, savedFile.getName());
            openProjects.add(projectToAdd);
            //TODO: Pass new ProjectData to UI, somehow
            ProjectStage projectToAddStage = new ProjectStage();
//            openProjectStages.add(projectToAddStage);

            fileScanner.close();
        } catch (IOException e){
            System.err.println("ERROR: UNKNOWN_FILE_ERROR");
            e.printStackTrace();
        }
    }

    //TODO: COMPLETELY REFACTOR SAVED FILE TO MORE ROBUST SYSTEM
    public static void saveProject(ProjectData project) {
        //Attempts to save a project
        File outFile = new File(project.getFileName());
        if (outFile.exists()) {
            System.err.println("ERROR: FILE_ALREADY_EXISTS");
        } else {
            try {
                //TODO: make sure filename is saved
                outFile.createNewFile();
                FileWriter fileWriter = new FileWriter(outFile);
                fileWriter.write(project.toString());
                fileWriter.close();
                System.out.println(outFile.getName() + " Saved.");
            } catch (IOException e) {
                System.err.println("ERROR: UNKNOWN_FILE_ERROR");
                e.printStackTrace();
            }
        }
    }

    public void openProject(File file) {
        //String fileName = file.getName();
        //Attempts to open a
        if (this.projectIsOpen(file)){
            System.err.println("ERROR: FILE_ALREADY_OPEN");
        }
        else {
            this.createProject(file);
        }
    }

    public void openProject(String fileName){
        File file = new File(fileName);
        if (file.exists()){
            this.openProject(file);
        }
        else {
            System.err.println("ERROR: FILE_DOES_NOT_EXIST");
        }
    }

    public void newProject() {
        //Handles the creation of new projects
        //NOT YET IMPLEMENTED
    }

    public void printAllProjectNames(){
        for (ProjectData projectData : openProjects){
            System.out.println(projectData.getProjectName());
        }
    }

    public void testPassFunction(){
        Stage testStage; //NOT IMPLEMENTED
    }
}

//    //Member Variables
//    public UI userInterface;                //
//    public FunctionPoint currentFP;         //TODO: Replace with ArrayList
//    public ProjectMetaData currentProject;  //TODO: Replace with ArrayList
//
//    private ArrayList<ProjectData> openProjects; //The currently open projects
//
//    //TODO: Refactor to allow for multiple projects to be open at he same time
//    //TODO: Make projects savable
//
//    //Member Functions
//    //Constructor(s)
//    Controller()
//    {
//        //TODO: Cleanup
//        //start UI with default settings
//        //just the blank window with menus
//
//        /*
//        The Plan:
//        Create menu dropdowns, etc., in Controller constructor, pass to UI
//
//        Note: This makes the classes extremely co-dependent on each other, we will need to refactor at a later date
//        do as to reduce coupling and increase cohesion
//         */
//
//        //ProjectMenu projMenu = new ProjectMenu()
//
//        currentFP = new FunctionPoint();
//        currentProject = new ProjectMetaData();
//        userInterface = new UI(/*projMenu*/);
//
//        setMainWindowContents();
//    }
//
//    Controller(ProjectStage stage)
//    {
//        //open with particular project stage? or should input be a UI object?
//    }

    //Getters
    //mostly getting user input or values calculated using function point method

    //Setters
    //mostly setting things like different stage, UI changes (based on user input), and
    //EXAMPLE OF PASSING TO UI
//    public void setMainWindowContents(){
//        //EXAMPLE
//        //Create radio buttons (maybe scene)
//        //Hookup radio buttons to some Language object (maybe) and currentFP
//        //Pass radio buttons to UI.setContentsLangSelectWindow
//        //UI sets them to the scene of langSelectWindow
//
//        ProjectStage stageToSet = new ProjectStage();
//        /* Menu Bar initialization */
//        MenuBar menuBar = new MenuBar();
//        Menu File = new Menu("File");
//        Menu Edit = new Menu("Edit");
//        Menu Preferences = new Menu("Preferences");
//        Menu Metrics = new Menu("Metrics");
//        Menu Help = new Menu("Help");
//
//        menuBar.getMenus().addAll(File, Edit, Preferences, Metrics, Help);
//        VBox vBox = new VBox(menuBar);
//
//        MenuItem New = new MenuItem("New");
//        MenuItem Open = new MenuItem("Open");
//        MenuItem Save = new MenuItem("Save");
//        MenuItem Exit = new MenuItem("Exit");
//        File.getItems().addAll(New, Open, Save, Exit);
//
//        MenuItem Language = new MenuItem("Language");
//        Preferences.getItems().add(Language);
//
//        Menu FP_menu = new Menu("Function Points");
//        MenuItem EnterFPData_menu = new MenuItem("Enter FP Data");
//        //PAR EXAMPLE:
//        //EnterFPData_menu.setOnAction(actionEvent -> System.out.println(currentFP.getTotalCount()));
//        FP_menu.getItems().add(EnterFPData_menu);
//        Metrics.getItems().add(FP_menu);
//
//
//        Scene scene = new Scene(vBox, 960, 600);
//        stageToSet.setScene(scene);
//
//        userInterface.setMainWindow(stageToSet);
//        userInterface.showMainWindow();
//    }

    //Misc. Member Methods
    /*
    TODO: Create methods which create buttons which are passed to UI with the relevant methods attached AND proper projects pointed to
     */
//}