package Project543;
import javafx.scene.control.*;

public class PreferencesMenu extends ProjectMenu
{
    //Member Variables
    MenuItem prefSubmenu; //change to array if more submenus are needed

    //Member Functions
    //Constructors
    PreferencesMenu()
    {
        super("Preferences", 1);
        this.setPrefSubmenu();
        this.getItems().add(prefSubmenu);
    }

    //Getters


    //Setters
    void setPrefSubmenu()
    {
        MenuItem submenu = new MenuItem("Language");
        this.prefSubmenu = submenu;
    }
}