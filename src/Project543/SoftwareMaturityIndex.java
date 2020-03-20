package Project543;

import java.util.ArrayList;

//Class to handle the Software Maturity Index metric
public class SoftwareMaturityIndex extends Metrics {
    //Member Fields
    //
    //Member Enums and Classes
    //
    enum ColumnNames {SMI, MODULES_ADDED, MODULES_CHANGED, MODULES_DELETED, TOTAL_MODULES} //The names of the columns in the SMI tab //TODO: Use or delete
    public class MetricValuesRow {
        //Member fields
        public double SMI;
        public int modulesAdded, modulesChanged, modulesDeleted, totalModules;

        //Constructor(s)
        public MetricValuesRow(){
            //Default constructor
            this.SMI = 0.0; this.modulesAdded = 0; this.modulesChanged = 0; this.modulesDeleted = 0; this.totalModules = 0;
        }

        public MetricValuesRow(int[] saveData){
            //Save file constructor
            //TODO:
        }

        public MetricValuesRow(MetricValuesRow previousRow){
            //Constructor for constructing from previous row

            //Initialize
            this.SMI = 1.0;
            this.modulesAdded = 0;
            this.modulesChanged = 0;
            this.modulesDeleted = 0;
            this.totalModules = previousRow.totalModules;
        }
    }

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
    public ArrayList<MetricValuesRow> softwareMaturityIndexRows;

    //Member Methods
    //
    //Constructor(s)
    //
    public SoftwareMaturityIndex(){
        //Default constructor

        //Initialize member fields
        this.softwareMaturityIndexRows = new ArrayList<MetricValuesRow>();
    }

    public SoftwareMaturityIndex(String saveData){
        //Saved file constructor

        //Initialize member fields
    }

    //Getters
    //
    //Get metric values
    //
    public double getSoftwareMaturityIndex(MetricValuesRow metricValuesRow){
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

    //Helpers
    //
    public int getTotalModulesDelta(MetricValuesRow metricValuesRow){
        //Returns the change in the row's totalModules from the previous row
        return metricValuesRow.modulesAdded - metricValuesRow.modulesDeleted;
    }

    public int getAllModulesSum(MetricValuesRow metricValuesRow){
        //Returns the sum of modules added, changed, and deleted
        return (metricValuesRow.modulesAdded + metricValuesRow.modulesChanged + metricValuesRow.modulesDeleted);
    }

    /*
    public int[] getMetricArray(MetricValuesRow metricValuesRow){
        //Gets an array of all the values of the specified MetricValues
        int[] rowValues = new int[] {
                metricValuesRow.SMI,
                metricValuesRow.modulesAdded,
                metricValuesRow.modulesChanged,
                metricValuesRow.modulesDeleted,
                metricValuesRow.totalModules
        };
        return rowValues;
    }
     */

    //Setters
    //
    //Set metric values
    //
    public void setSoftwareMaturityIndex(MetricValuesRow metricValuesRow){
        if (metricValuesRow.totalModules > 0) {
            metricValuesRow.SMI =
                    (metricValuesRow.totalModules - getAllModulesSum(metricValuesRow))
                    /
                    metricValuesRow.totalModules;
        }
        else {
            System.err.println("ERROR: NON_POSITIVE_TOTAL_MODULES");
            metricValuesRow.SMI = 0;
        }
    }

    public void setModulesAdded(MetricValuesRow metricValuesRow, int modulesAdded){
        //Set module data
        metricValuesRow.modulesAdded = modulesAdded;

       //Recompute metrics
        this.setMetrics(metricValuesRow);
    }

    public void setModulesChanged(MetricValuesRow metricValuesRow, int modulesChanged){
        //Set module data
        metricValuesRow.modulesChanged = modulesChanged;

        //Recompute metrics
        this.setMetrics(metricValuesRow);
    }

    public void setModulesDeleted(MetricValuesRow metricValuesRow, int modulesDeleted){
        //Set module data
        metricValuesRow.modulesDeleted = modulesDeleted;

        //Recompute metrics
        this.setMetrics(metricValuesRow);
    }

    public void setTotalModules(MetricValuesRow metricValuesRow){
        int totalModules = metricValuesRow.totalModules + this.getTotalModulesDelta(metricValuesRow);
        if (totalModules < 0){
            System.err.println("ERROR: NEGATIVE_TOTAL_MODULES");
            metricValuesRow.modulesDeleted = metricValuesRow.modulesAdded;
        }
        else {
            metricValuesRow.totalModules = totalModules;
        }
    }

    public void setMetricValuesFromArray(MetricValuesRow metricValuesRow, int[] metricValueArray){
        //Takes in an int array of size 3, sets the values for the row from this
        //PRECONDITION: metricValueArray is an integer array of size 3
        //Set the modules
        metricValuesRow.modulesAdded = metricValueArray[0];
        metricValuesRow.modulesChanged = metricValueArray[1];
        metricValuesRow.modulesDeleted = metricValueArray[2];

        //Recompute the metrics
        this.setMetrics(metricValuesRow);
    }

    public void setMetrics(MetricValuesRow metricValuesRow){
        //Recomputes the metrics of the row
        this.setTotalModules(metricValuesRow);
        this.setSoftwareMaturityIndex(metricValuesRow);
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

    //Helpers
    //
    public void /*return something?*/ addRow(){
        //Adds a row to the metrics //Returns it?

    }
}
