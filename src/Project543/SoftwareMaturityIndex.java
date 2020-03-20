package Project543;

import java.util.ArrayList;

//Class to handle the Software Maturity Index metric
public class SoftwareMaturityIndex extends Metrics {
    //Member Fields
    //
    //Member Enums and Classes
    //
    enum ColumnNames {SMI, MODULES_ADDED, MODULES_CHANGED, MODULES_DELETED, TOTAL_MODULES} //The names of the columns in the SMI tab //TODO: Use or delete
    public class MetricValuesRow {public int SMI, modulesAdded, modulesChanged, modulesDeleted, totalModules;}

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
    public ArrayList<MetricValuesRow> softwareMaturityValues;

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
    //Get metric values
    //
    public int getSoftwareMaturityIndex(MetricValuesRow metricValuesRow){
        return metricValuesRow.SMI;
    }

    public int getModulesAdded(MetricValuesRow metricValuesRow){
        return metricValuesRow.modulesAdded;
    }

    public int getModulesChanged(MetricValuesRow metricValuesRow){
        return metricValuesRow.modulesChanged;
    }

    public int getModulesDeleted(MetricValuesRow metricValuesRow){
        return metricValuesRow.modulesDeleted;
    }

    public int getTotalModules(MetricValuesRow metricValuesRow){
        return metricValuesRow.totalModules;
    }

    public int[] getMetricArray(MetricValuesRow metricValuesRow){
        //Gets an array of all the values of the specified MetricValues
        //TODO
        return new int[] {0};
    }

    //Setters
    //
    //Set metric values
    //
    public void setSoftwareMaturityIndex(MetricValuesRow metricValuesRow){

    }

    public void setModulesAdded(MetricValuesRow metricValuesRow, int modulesAdded){

    }

    public void setModulesChanged(MetricValuesRow metricValuesRow, int modulesChanged){

    }

    public void setModulesDeleted(MetricValuesRow metricValuesRow, int modulesChanged){

    }

    public void setTotalModules(MetricValuesRow metricValuesRow){

    }

    public void setMetricValuesFromArray(MetricValuesRow metricValuesRow, int[] metricValueArray){
        //Takes in an int array of size 3, sets the values for the row from this
    }

    //Implement setSaveData method from Metrics
    //
    @Override
    public void setSaveData() {}

    //Misc. Member Methods
    //
    //Update methods
    //
    public void updateAllRows(){
        //Updates each SMI row based on currently entered information
    }
}
