//Controller class for second iteration of the project
//
////NOT YET COMPLETE NOR INTENDED FOR USE AT THIS MOMENT

package Project543;

import java.util.ArrayList;

public class ApplicationController {
    //Member Variables
    //
    UserInterface userInterface;
    public ArrayList<ProjectData> projectList;

    //Member Methods
    //
    //Constructor(s)
    //
    public ApplicationController(){
        projectList = new ArrayList<ProjectData>();

        userInterface = new UserInterface(this);
    }

    //Getters
    //

    //Setters
    //

    //Misc. Member Methods
    //
    public void createProject(){
        ProjectData project = new ProjectData();
        projectList.add(project);

        project.setProjectName("TEST");

        userInterface.openProjectWindow(projectList.get(projectList.size()-1));
    }
}
