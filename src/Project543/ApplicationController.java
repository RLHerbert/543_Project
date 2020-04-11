//Controller class for second iteration of the project
//
////NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.antlr.runtime.RecognitionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

//TODO: SWITCH TO UI_3

public class ApplicationController {
    //Member Fields
    //
    //Member Classes and Enums
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //

    //Non-Constant Static Fields
    //
    public static ArrayList<ProjectData> openProjectList = new ArrayList<ProjectData>(); //TODO: Private
    public static ArrayList<UserInterface_3> openProjectWindows = new ArrayList<UserInterface_3>();

    //Non-Static Member Variables
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    UserInterface userInterface;

    //Member Methods
    //
    //Constructor(s)
    //
    public ApplicationController(){
        //Default constructor

        //Initialize member fields
        userInterface = new UserInterface();
        openProjectList = new ArrayList<ProjectData>();
    }

    //Getters
    //

    //Setters
    //

    //Misc. Member Methods
    //
    //Project Manipulation
    //
    public static ProjectData createProjectFromMetaData(String[] metaData){
        //Takes in a string of project metadata to create and return a new ProjectData object
        ProjectData projectData = new ProjectData(metaData);
        ApplicationController.openProjectList.add(projectData);

        return projectData;
    }

    public static void deleteProject(ProjectData projectToDelete){
        //Function prototype
        //Deletes the specified project from the open project list
        ApplicationController.openProjectList.remove(projectToDelete);
    }

    //Open and Save methods
    //
    public static boolean exitProgramRequest(){
        boolean windowHasChanged = false;

        for (UserInterface_3 window : ApplicationController.openProjectWindows) {
            if (window.projectData.hasChanged()) {
                windowHasChanged = true;
                break;
            }
        }

        if (windowHasChanged) {

            Boolean[] exitAndSave;

            Dialog<Boolean[]> saveAllProjects = new Dialog<Boolean[]>();
            saveAllProjects.setTitle("Save Projects");
            saveAllProjects.setHeaderText("Would you like to save your open projects?");
            saveAllProjects.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.YES, ButtonType.NO);

            saveAllProjects.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.CANCEL) {
                    return new Boolean[]{false, null};
                } else if (dialogButton == ButtonType.YES) {
                    return new Boolean[]{true, true};
                } else if (dialogButton == ButtonType.NO) {
                    return new Boolean[]{true, false};
                }

                return new Boolean[]{null, null};
            });

            Optional<Boolean[]> result = saveAllProjects.showAndWait();

            if (!result.get()[0]) {
                return false;
            } else {
                if (result.get()[1]) {
                    for (UserInterface_3 window : openProjectWindows) {
                        try {
                            window.projectData.saveProject();
                        } catch (IOException e) {
                            System.err.println("ERROR: SAVE_PROJECT_ERROR");
                            e.printStackTrace();
                        }
                    }
                }
                return true;
            }
        }

        return true;
    }

    public static ProjectData openProject(File projectToOpen) throws IOException, RecognitionException {
        //Opens a project (from a .ms file) to add it to the open project list and return it
        Scanner projectScanner = new Scanner(projectToOpen);

        ProjectData projectData = new ProjectData(projectScanner, projectToOpen.getName());
        ApplicationController.openProjectList.add(projectData);

        return projectData;
    }

    public static boolean projectIsOpen(String fileNameToCheck){
        //Checks if a project with a given file name is already open

        for (ProjectData projectData : openProjectList){
            if (projectData.getFileName().equals(fileNameToCheck)){
                return true;
            }
        }

        return false;
    }

    public static void saveAllProjects(){
        //Saves all currently open projects
        for (ProjectData openProject : ApplicationController.openProjectList){
            UserInterface.exitProjectQuery(openProject);
            //TODO
            /*
            try {
                openProject.saveProject();
            } catch (IOException e){
                System.err.println("ERROR: PROJECT_SAVE_ERROR");
                e.printStackTrace();
            }
             */
        }
    }
}
