package Project543.MetricsInterface;

import Project543.Metrics.Metrics;
import Project543.SaveInterface;
import javafx.scene.control.Tab;

//Base class for all Metrics Tabs
//Cannot currently be made abstract without big refactors
public class MetricsTab extends Tab implements SaveInterface {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public Metrics metric;
    public boolean hasChanged;

    //Member Methods
    //
    //Constructor(s)
    //
    public MetricsTab(){
        super();
    }

    public MetricsTab(String title){
        super(title);
    }

    //Getters
    //
    public int getMetricID(){return 0;}

    public String getExtraData(){
        return "";
    }

    //Setters
    //
    public void setMetric(){
        //Virtual
    }

    public void setTabTitle() {
        //Virtual
    }

    public void setTabTitle(String titleToSet){
        this.setText(titleToSet);
    }

    //Misc. Member Methods
    //
    //Save and open methods
    //
    public void calculateMetric(){} //Virtual

    public boolean hasChanged() {
        return true;
    }

    public boolean saveQuery(){
        //Upon attempting to close the tab, prompts the user if they want to save the data first
        //TODO: Needed or not? If the user closes a tab can they even save it? Maybe prompt for just closing, not saving?
        return false;
    }
}
