package Project543;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    //Member Variables
    private ArrayList<ProjectData> openProjects;

    //Member Methods
    //Constructor(s)
    public Controller() {
        openProjects = new ArrayList<ProjectData>();
    }

    //Getters

    //Setters

    //Misc. Member Methods
    public boolean projectIsOpen(String fileName) {
        for (ProjectData projectData : openProjects) {
            if (projectData.getFileName().equals(fileName)) return true;
        }

        return false;
    }

    public void createProject() {
        //Handles the actual creation of and addition to the ProjectData for openProjects
    }

    public void createProject(Scanner inputFile) {

    }

    public static void saveProject(ProjectData project) {
        //FileWriter fileWriter = new FileWriter();
        File outFile = new File(project.getFileName());
        if (outFile.exists()) {
            System.err.println("ERROR: FILE_ALREADY_EXISTS");
        } else {
            try {
                outFile.createNewFile();
                FileWriter fileWriter = new FileWriter(outFile);
                fileWriter.write(project.toString());
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("ERROR: UNKNOWN_FILE_ERROR");
                e.printStackTrace();
            }
        }
    }

    public void openProject(String fileName) {
        if (this.projectIsOpen(fileName)){
            System.err.println("ERROR: FILE_ALREADY_OPEN");
        }
        else {
            File inputFile;

            try {
                inputFile = new File(fileName);
                Scanner fileScanner  = new Scanner(inputFile);
                this.createProject(fileScanner);
                fileScanner.close();
            } catch (IOException e) {
                System.err.println("ERROR: UNKNOWN_FILE_ERROR");
                e.printStackTrace();
            }
        }
    }

    public void newProject() {
        //Handles the creation of new files
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