package Project543;

import java.util.ArrayList;

//Class to handle the Software Maturity Index metric
//TODO: Ensure that the first row only takes in modules added
public class SoftwareMaturityIndex extends Metrics implements SaveInterface {
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

        public MetricValuesRow(int firstModulesAdded){
            //First row constructor
            this.SMI = 0.0;
            this.modulesAdded = firstModulesAdded;
            this.modulesChanged = 0;
            this.modulesChanged = 0;
            this.totalModules = firstModulesAdded;
        }

        public MetricValuesRow(MetricValuesRow previousRow, int[] saveData){
            //Save data constructor, all rows except the first
            this.SMI = 0.0;
            this.modulesAdded = saveData[0];
            this.modulesChanged = saveData[1];
            this.modulesDeleted = saveData[2];
            this.totalModules = previousRow.totalModules;
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

        public double getSMI(){
            return this.SMI;
        }
        public int getModulesAdded(){
            return this.modulesAdded;
        }
        public int getModulesChanged(){
            return this.modulesChanged;
        }
        public int getModulesDeleted(){
            return this.modulesDeleted;
        }
        public int getTotalModules(){
            return this.totalModules;
        }

        public void setModulesAdded(int newVal){
            this.modulesAdded = newVal;
        }

        public void setModulesChanged(int newVal){
            this.modulesChanged = newVal;
        }

        public void setModulesDeleted(int newVal){
            this.modulesDeleted = newVal;
        }
    }

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final int METRIC_ID = ((int) ('S' + 'M' + 'I'));
    //Save data format: [METRIC_ID, 3-tuples of [modulesAdded, modulesChanged, modulesDeleted]]
    public static final String METRIC_NAME = "Software Maturity Index";
    public static final String[] TAB_COLUMN_NAMES = {"SMI", "Modules Added", "Modules Changed", "Modules Deleted", "Total Modules"};
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
        super();

        //Initialize member fields
        this.softwareMaturityIndexRows = new ArrayList<MetricValuesRow>();
    }

    public SoftwareMaturityIndex(String saveData){
        //Saved file constructor
        super();

        //Set the save data from read
        this.readSaveData(saveData);

        //Initialize member fields
        this.softwareMaturityIndexRows = new ArrayList<MetricValuesRow>();
        this.setFromSaveData();
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

    public ArrayList<MetricValuesRow> getAllRows(){
        //Gets them all
        return this.softwareMaturityIndexRows;
    }

    public MetricValuesRow getNewRow(){
        //Creates a new row and returns it
        this.addRow();

        return this.softwareMaturityIndexRows.get(this.softwareMaturityIndexRows.size()-1);
    }

    public MetricValuesRow getLatestRow(){
        //Returns the row at the end of softwareMaturityIndexRows if it exists, otherwise it adds a row and returns it
        if (this.softwareMaturityIndexRows.size() == 0){
            return this.getNewRow();
        }

        return softwareMaturityIndexRows.get(this.softwareMaturityIndexRows.size()-1);
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
                    ((double) metricValuesRow.totalModules - getAllModulesSum(metricValuesRow))
                    / (double) metricValuesRow.totalModules;
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
        // this.setMetrics(metricValuesRow);
    }

    public void setModulesChanged(MetricValuesRow metricValuesRow, int modulesChanged){
        //Set module data
        metricValuesRow.modulesChanged = modulesChanged;

        //Recompute metrics
        //this.setMetrics(metricValuesRow);
    }

    public void setModulesDeleted(MetricValuesRow metricValuesRow, int modulesDeleted){
        //Set module data
        metricValuesRow.modulesDeleted = modulesDeleted;

        //Recompute metrics
        //this.setMetrics(metricValuesRow);
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

    //Helpers
    //
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
        //Recomputes the metrics of the row, i.e. SMI and TotalModules
        this.setTotalModules(metricValuesRow);
        this.setSoftwareMaturityIndex(metricValuesRow);
    }

    //Save and open
    //
    public boolean hasChanged(){
        //Checks if the metric's data has been saved since its last edit and returns true if it has, false otherwise

        if (this.saveData.size() == 0){ return true; }

        int accumulator = 1;
        for (MetricValuesRow rowValues : this.softwareMaturityIndexRows){
            if (
                    rowValues.modulesAdded != this.saveData.get(accumulator)
                    || rowValues.modulesChanged != this.saveData.get(accumulator+1)
                    || rowValues.modulesDeleted != this.saveData.get(accumulator + 2)
            ){
                return true;
            }

            accumulator+=3;
        }

        return false;
    }

    public void setFromSaveData(){
        //Sets the rows from the save data
        for (int i = 1; i < this.softwareMaturityIndexRows.size(); i+=3){
            int[] rowSavedData = new int[] {};
            if (i == 1){
                MetricValuesRow firstRow = new MetricValuesRow(rowSavedData[0]);
                this.softwareMaturityIndexRows.add(firstRow);
            }
            else {
                MetricValuesRow rowToAdd = new MetricValuesRow(this.softwareMaturityIndexRows.get(this.softwareMaturityIndexRows.size()-1), rowSavedData);
                this.setMetrics(rowToAdd);
                this.softwareMaturityIndexRows.add(rowToAdd);
            }
        }
    }

    //Implement setSaveData method from Metrics
    //
    @Override
    public void setSaveData() {
        this.saveData.add(METRIC_ID);
        for (MetricValuesRow metricValuesRow : this.softwareMaturityIndexRows){
            this.saveData.add(metricValuesRow.modulesAdded);
            this.saveData.add(metricValuesRow.modulesChanged);
            this.saveData.add(metricValuesRow.modulesDeleted);
        }
    }

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
        if (this.softwareMaturityIndexRows.size() == 0){
            //If the metric is empty
            this.softwareMaturityIndexRows.add(new MetricValuesRow());
        }
        else {
            //Otherwise
            this.softwareMaturityIndexRows.add(new MetricValuesRow(this.softwareMaturityIndexRows.get(this.softwareMaturityIndexRows.size()-1)));
            this.setMetrics(this.softwareMaturityIndexRows.get(this.softwareMaturityIndexRows.size()-1));
        }
    }
}
