//Controller class for second iteration of the project
//
////NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationController {
    //Member Fields
    //
    //Member Classes and Enums
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final int TOTAL_METRICS = 2;

    //Non-Constant Static Fields
    //
    public static ArrayList<ProjectData> openProjectList; //TODO: Private

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
    public static ProjectData openProject(File projectToOpen) throws FileNotFoundException {
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
            try {
                openProject.saveProject();
            } catch (IOException e){
                System.err.println("ERROR: PROJECT_SAVE_ERROR");
                e.printStackTrace();
            }
        }
    }
}
