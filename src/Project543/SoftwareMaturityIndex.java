package Project543;

import java.time.chrono.MinguoEra;
import java.util.ArrayList;

//Class to handle the Software Maturity Index metric
public class SoftwareMaturityIndex extends Metrics {
    //Member Fields
    //
    //Member Enums and Classes
    //
    enum ColumnNames {SMI, MODULES_ADDED, MODULES_CHANGED, MODULES_DELETED, TOTAL_MODULES} //The names of the columns in the SMI tab
    public class MetricValues{public int SMI, modulesAdded, modulesChanged, modulesDeleted, totalModules;}

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final int METRIC_ID = ((int) ('S' + 'M' + 'I'));
    public static final String[] tabColumnNames = {"SMI", "Modules Added", "Modules Changed", "Modules Deleted", "Total Modules"};
    public static final int NUMBER_OF_COLUMNS = ColumnNames.values().length; //TODO: Use or delete

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public ArrayList<MetricValues> softwareMaturityValues;

    //Member Methods
    //
    //Constructor(s)
    //
    public SoftwareMaturityIndex(){
        //Default constructor

        //Initialize
    }

    //Getters
    //

    //Setters
    //
    //Set Local Data
    //
    public void setSoftwareMaturityIndex(MetricValues metricValues){

    }

    public void setModulesAdded(MetricValues metricValues, int modulesAdded){

    }

    public void setModulesChanged(MetricValues metricValues, int modulesChanged){

    }

    public void setModulesDeleted(MetricValues metricValues, int modulesChanged){

    }

    public void setTotalModules(MetricValues metricValues){

    }

    //Implement setSaveData method from Metrics
    //
    @Override
    public void setSaveData() {}

    //Misc. Member Methods
    //
}
