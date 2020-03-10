package Project543;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.util.*;

//TODO: for new projects, open dialog box, also open window slightly off-center to see ones behind

public class ProjectStage extends Stage {
    //Member Variables
    //TODO: variables number/type of panels open, window identifier for when multiple windows are open?
    MenuBar projectStageMenuBar;
//    ProjectMenu [] projectMenuList;
    //TODO: turn back into arrays bc hashtables are unnecessary, except maybe for menu items, which need to be accessed more
    LinkedHashMap<String, Menu> menuList;
        MenuItem [] fileMenuItems;
        MenuItem [] prefMenuItems; //only one item right now
        LinkedHashMap<String, Menu> metricSubmenuList; //only one submenu right now
            MenuItem FPMenuItem;
    VBox projectStageLayout;
    Scene projectScene;

    //Constants
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 960;
    public static final int X_POS = 165;
    public static final int Y_POS = 0;

    //TODO: more constants like:
    public static final String DEFAULT_WINDOW_TITLE = "CECS 543 Metrics Suite";


    //Member Methods
    //Constructors
    ProjectStage() //default constructor, used on application open and for new projects
    {
        this.setProjectStageSizePos(WINDOW_HEIGHT, WINDOW_WIDTH, X_POS, Y_POS);
        this.setTitle("CECS 543 Metrics Suite");
        this.initModality(Modality.NONE); //ensures multiple windows can be open simultaneously
//        this.setProjectMenuList();
//        this.setProjectStageMenuBar(menuList);
        this.setUpMenu();
        this.setProjectStageLayout();
        this.setProjectScene();
        this.show();
    }

    ProjectStage(String title)
    {
        this();
        this.setTitle(title);
    }

    //Getters
    MenuItem getNewButton()
    {
        return fileMenuItems[0];
    }

    MenuItem getOpenButton()
    {
        return fileMenuItems[1];
    }

    MenuItem getSaveButton()
    {
        return fileMenuItems[2];
    }

    MenuItem getExitButton()
    {
        return fileMenuItems[3];
    }

    MenuItem getLanguageMenuButton()
    {
        return prefMenuItems[0];
    }

    MenuItem getFPMenuButton()
    {
        return FPMenuItem;
    }

    //Setters
    void setProjectMenuList()
    {
        LinkedHashMap<String, Menu> menuList = new LinkedHashMap<String, Menu>();
        menuList.put("file", new Menu("File"));
        menuList.put("edit", new Menu("Edit"));
        menuList.put("preferences", new Menu("Preferences"));
        menuList.put("metrics", new Menu("Metrics"));
        menuList.put("help", new Menu("Help"));


//        menuList[0] = new FileMenu();
//        menuList[1] = new EditMenu();
//        menuList[2] = new PreferencesMenu();
//        menuList[3] = new MetricsMenu();
//        menuList[4] = new HelpMenu();
//        this.projectMenuList = menuList;
        this.menuList = menuList;
    }

    void setFileMenuItems()
    {
        MenuItem [] menuItems = new MenuItem[4];
        menuItems[0] = new MenuItem("New");
        menuItems[1] = new MenuItem("Open");
        menuItems[2] = new MenuItem("Save");
        menuItems[3] = new MenuItem("Exit");
        this.fileMenuItems = menuItems;
    }

    void setPrefMenuItems()
    {
        MenuItem [] menuItems = new MenuItem[1];
        menuItems[0] = new MenuItem("Language");
        this.prefMenuItems = menuItems;
    }

    void setMetricSubmenuList()
    {
        LinkedHashMap<String, Menu> submenus = new LinkedHashMap<String, Menu>();
        submenus.put("fp", new Menu("Function Points"));
        this.metricSubmenuList = submenus;
    }

    void setFPMenuItem()
    {
        MenuItem menuItem = new MenuItem("Enter FP Data");
        this.FPMenuItem = menuItem;
    }

    void setUpMenu()
    {
        //Set menu, menu item, and submenu lists
        this.setProjectMenuList();
        this.setFileMenuItems();
        this.setPrefMenuItems();
        this.setMetricSubmenuList();
        this.setFPMenuItem();

        //Attach lists to actual menu/submenu objects
        this.menuList.get("file").getItems().addAll(fileMenuItems);
        this.menuList.get("preferences").getItems().addAll(prefMenuItems);
        this.menuList.get("metrics").getItems().addAll(metricSubmenuList.values());
        this.metricSubmenuList.get("fp").getItems().add(FPMenuItem);

        //Attach menu object list to menu bar
        this.setProjectStageMenuBar(menuList);
    }

    void setProjectStageMenuBar(LinkedHashMap<String, Menu> menuList)
    {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuList.values());
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