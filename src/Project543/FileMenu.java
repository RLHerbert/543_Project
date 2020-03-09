package Project543;

import javafx.scene.control.*;

public class FileMenu extends ProjectMenu
{
    //Member Variables
    MenuItem [] fileSubmenuList;

    //Member Functions
    //Constructors
    FileMenu()
    {
        super("File", 4);
        this.setFileSubmenuList();
        this.getItems().addAll(fileSubmenuList);

        //set menu actions
        this.setNewClickAction();
    }

    //Getters

    //Setters
    void setFileSubmenuList()
    {
        MenuItem [] submenus = new MenuItem[NUM_SUBMENUS];
        submenus[0] = new MenuItem("New");
        submenus[1] = new MenuItem("Open");
        submenus[2] = new MenuItem("Save");
        submenus[3] = new MenuItem("Exit");
        fileSubmenuList = submenus;
    }

    //Other Methods
    void setNewClickAction()
    {
        fileSubmenuList[0].setOnAction(event -> {
            System.out.println("NEW WAS CLICKED");
//            ProjectStage newStage = new ProjectStage();
//            newStage.show();
        });
    }
}