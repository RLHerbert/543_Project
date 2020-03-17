//Controller class for second iteration of the project
//
////NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import javafx.scene.control.Tab;

import java.util.ArrayList;

//TODO: Refactor to new UI
public class ApplicationController {
    //Member Variables
    //
    //Member Classes and Enums
    //

    //Static Member Variables
    //
    //Constants
    //
    public static final int TOTAL_METRICS = 2;

    //Non-Constant
    //
    public static ArrayList<ProjectData> projectList; //TODO: Private

    //Non-Static Member Variables
    UserInterface userInterface;

    //Member Methods
    //
    //Constructor(s)
    //
    public ApplicationController(){
        userInterface = new UserInterface(this);
        //userInterface = new UserInterface();
        projectList = new ArrayList<ProjectData>();
    }

    //Getters
    //

    //Setters
    //

    //Misc. Member Methods
    //
    public static ProjectData createProject(/*String[]*/){
        ProjectData newProject = new ProjectData(/*String[]*/); //TODO: String[] constructor?
        projectList.add(newProject);

        return newProject;
        //project.setProjectName("TEST");

        //userInterface.openProjectWindow(projectList.get(projectList.size()-1));
    }

    public static boolean projectIsOpen(String fileNameToCheck){
        //Checks if a project with a given file name is already open

        for (ProjectData projectData : projectList){
            if (projectData.getFileName().equals(fileNameToCheck)){
                return true;
            }
        }

        return false;
    }

//    public static
}
