package Project543;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class ProjectStage extends Stage {
//    public enum MenuText {
//        File, Edit, Preferences, Metrics, Help;
//    } //change to class vars
    FileMenu File;

    //Member Variables
    public static final int DEF_WINDOW_HEIGHT = 775;
    public static final int DEF_WINDOW_WIDTH = 800;
    public static final int DEF_X_POS = 225;
    public static final int DEF_Y_POS = 0;

    ProjectMenu menuList[];
    MenuBar menuBar;
    Scene windowScene;

    //Member Methods

    //Constructors
    ProjectStage()  //default constructor will open window with the menu
    {
        this.setStageSizePos(DEF_WINDOW_HEIGHT, DEF_WINDOW_WIDTH, DEF_X_POS, DEF_Y_POS);
        this.setTitle("Untitled Window");
//      this.setMenuList();
//      this.setMenuBar();

//        VBox vBox = new VBox(menuBar);
//        Scene scene = new Scene(vBox, 960, 600);
//        this.setScene(scene);
    }

    ProjectStage(String title)  //parameterized constructor will open window with the menu and a title
    {
        this();
        this.setTitle(title);

    }

    //ProjectStage() //parameterized constructor will open window without a menu (like for pop-ups)
    {}

    //Getters

    //Setters
    void setStageSizePos(int height, int width, int x_pos, int y_pos){
        this.setHeight(height);
        this.setWidth(width);
        this.setX(x_pos);
        this.setY(y_pos);
    }

    void setMenuList()
    {
        ProjectMenu menus [] = new ProjectMenu[5];
        menus[0] = new FileMenu();
        menus[1] = new EditMenu();
        menus[2] = new PreferencesMenu();
        menus[3] = new MetricsMenu();
        menus[4] = new HelpMenu();
//        menus[MenuText.File.ordinal()] = new Menu(MenuText.File.toString());
//        menus[MenuText.Edit.ordinal()] = new Menu(MenuText.Edit.toString());
//        menus[MenuText.Preferences.ordinal()] = new Menu(MenuText.Preferences.toString());
//        menus[MenuText.Metrics.ordinal()] = new Menu(MenuText.Metrics.toString());
//        menus[MenuText.Help.ordinal()] = new Menu(MenuText.Help.toString());

        this.menuList = menus;
    }

    void setMenuBar()
    {
        /*
        MenuBar menuBarTemp = new MenuBar();
        for (ProjectMenu item : this.menuList)
            menuBarTemp.getMenus().add(item);

        this.menuBar = menuBarTemp;
         */
    }

    //Misc. Methods

//    MenuBar menuBar = new MenuBar();
//    Menu File = new Menu("File");
//    Menu Edit = new Menu("Edit");
//    Menu Preferences = new Menu("Preferences");
//    Menu Metrics = new Menu("Metrics");
//    Menu Help = new Menu("Help");
//
//        menuBar.getMenus().addAll(File, Edit, Preferences, Metrics, Help);
//    VBox vBox = new VBox(menuBar);
//
//    MenuItem New = new MenuItem("New");
//    MenuItem Open = new MenuItem("Open");
//    MenuItem Save = new MenuItem("Save");
//    MenuItem Exit = new MenuItem("Exit");
//        File.getItems().addAll(New, Open, Save, Exit);
//
//    MenuItem Language = new MenuItem("Language");
//        Preferences.getItems().add(Language);
//
//    Menu FP_menu = new Menu("Function Points");
//    MenuItem EnterFPData_menu = new MenuItem("Enter FP Data");
//        FP_menu.getItems().add(EnterFPData_menu);
//        Metrics.getItems().add(FP_menu);
//
//    Scene scene = new Scene(vBox, 960, 600);
}
