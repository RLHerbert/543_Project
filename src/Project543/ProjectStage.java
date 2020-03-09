package Project543;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

//TODO: for new projects, open dialog box, also open window slightly off-center to see ones behind

public class ProjectStage extends Stage {
    //Member Variables
    //TODO: variables number/type of panels open, window identifier for when multiple windows are open?
    MenuBar projectStageMenuBar;
    ProjectMenu [] projectMenuList;
    VBox projectStageLayout;
    Scene projectScene;

    //Member Variables
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 960;
    public static final int X_POS = 165;
    public static final int Y_POS = 0;


    //Member Methods
    //Constructors
    ProjectStage() //default constructor, used on application open
    // and for new projects?
    {
        this.setProjectStageSizePos(WINDOW_HEIGHT, WINDOW_WIDTH, X_POS, Y_POS);
        this.setTitle("CECS 543 Metrics Suite");
        this.initModality(Modality.NONE); //ensures multiple windows can be open simultaneously
        this.setProjectMenuList();
        this.setProjectStageMenuBar(projectMenuList);
        this.setProjectStageLayout();
        this.setProjectScene();
        this.show();
    }

    //Getters

    //Setters
    void setProjectMenuList()
    {
        ProjectMenu menuList [] = new ProjectMenu[5];
        menuList[0] = new FileMenu();
        menuList[1] = new EditMenu();
        menuList[2] = new PreferencesMenu();
        menuList[3] = new MetricsMenu();
        menuList[4] = new HelpMenu();
        this.projectMenuList = menuList;
    }

    void setProjectStageMenuBar(ProjectMenu [] menuList)
    {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuList);
        this.projectStageMenuBar = menuBar;
    }

    void setProjectStageLayout()
    {
        this.projectStageLayout = new VBox(projectStageMenuBar);
    }

    void setProjectScene()
    {
        this.projectScene = new Scene(this.projectStageLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setScene(projectScene);
    }

    void setProjectStageSizePos(int height, int width, int x_pos, int y_pos)
    {
        this.setHeight(height);
        this.setWidth(width);
        this.setX(x_pos);
        this.setY(y_pos);
    }

    //Other Methods

}