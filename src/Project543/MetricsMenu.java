package Project543;
import javafx.scene.control.*;

public class MetricsMenu extends ProjectMenu
{
    //Member Variables
    Menu metricsSubmenu; //change to array if more submenus are needed

    //Member Functions
    //Constructors
    MetricsMenu()
    {
        super("Metrics", 1);
        this.setMetricsSubmenu();
        this.getItems().add(metricsSubmenu);
    }

    //Getters

    //Setters
    void setMetricsSubmenu()
    {
        Menu submenu = new Menu("Function Points");
            MenuItem FPSubmenu = new MenuItem("Enter FP Data");
            submenu.getItems().add(FPSubmenu);
        this.metricsSubmenu = submenu;
    }
}

////    Menu FP_menu = new Menu("Function Points");
////    MenuItem EnterFPData_menu = new MenuItem("Enter FP Data");
////        FP_menu.getItems().add(EnterFPData_menu);
////        Metrics.getItems().add(FP_menu);
