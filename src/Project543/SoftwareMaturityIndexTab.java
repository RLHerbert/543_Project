package Project543;

import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

//TODO
public class SoftwareMaturityIndexTab extends MetricsTab {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final String TAB_TITLE = "SMI";

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    SoftwareMaturityIndex softwareMaturityIndex;
    TableView table = new TableView();

    //Member Methods
    //
    //Constructor(s)
    //
    SoftwareMaturityIndexTab(String tabTitle){
        super(TAB_TITLE);
    }

    SoftwareMaturityIndexTab(String tabTitle, SoftwareMaturityIndex softwareMaturityIndex){

    }

    SoftwareMaturityIndexTab(String tabTitle, String saveData){

    }

    //Getters
    //
    public int getModulesAddedFromInput(){
        return 0;
    }

    public int getModulesChangedFromInput(){
        return 0;
    }

    public int getModulesDeletedFromInput(){
        return 0;
    }

    //Setters
    //
    public void setTable(){

    }

    //Setters from Parent Class (Defining Virtual Methods)
    //
    public void setMetric(){
        this.metric = this.softwareMaturityIndex;
    }

    public void setTabTitle(){
        this.setText(TAB_TITLE);
    }

    //Misc. Member Methods
    //
}
