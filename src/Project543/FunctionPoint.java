package Project543;

import java.lang.Math;
import java.util.*;

public class FunctionPoint extends InformationDomainValue {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    //FP Save format: [METRIC_ID, functionPointLanguage, IDV value-complexity pairs, ..., VAFs, ...]
    public static final int METRIC_ID = ((int) ('F' + 'P')); //METRIC_ID indicates which type of metric is being saved and opened

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    private int  totalFunctionPoints, codeSize;     //To hold the FP value, and code size (functions pts * LOC/fp)
    private ValueAdjustmentFactor valueAdjustmentFactors; //To hold the VAFs for the FP
    private Language functionPointLanguage; //To hold the language for this Metric (on a per tab basis)

    //Member Methods
    //
    //Constructors
    //
    public FunctionPoint(){
        //Default constructor

        //Initialize member fields
        this.valueAdjustmentFactors = new ValueAdjustmentFactor();
        //Initialize totalCount and totalFunctionPoints
        updateTotalFunctionPoints();
        this.functionPointLanguage = Language.NONE;
    }

    //TODO: Delete
    public FunctionPoint(Scanner savedFile){
        //OLD Constructor for saved files
        super(savedFile);
        this.valueAdjustmentFactors = new ValueAdjustmentFactor(savedFile);

        //Update total function points
        updateTotalFunctionPoints();
    }

    public FunctionPoint(String saveDataString){
        //Save data constructor

        //Read in the save data, initialize and set all member fields
        super(saveDataString);
        this.setFromSavedData();
    }

    public FunctionPoint(Language defaultProjectLanguage){
        //Constructor for when a default language is set in the project
        super();
        this.valueAdjustmentFactors = new ValueAdjustmentFactor();
        this.functionPointLanguage = defaultProjectLanguage;
    }

    //Getters
    public int getValueAdjustmentFactor(int valueToGet){
        //Returns the specified VAF by calling ValAdjFac's getVal
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF

        return valueAdjustmentFactors.getValue(valueToGet);
    }

    //TODO: Use
    public int[] getValueAdjustmentFactorArray(){
        //Returns all of the valueAdjustmentFactors values as an array
        return this.valueAdjustmentFactors.getCurrentValuesArray();
    }

    public int getSumOfValAdjFac(){
        //Returns the sum of the VAFs
        return this.valueAdjustmentFactors.getSumOfValues();
    }

    public int getTotalFunctionPoints(){
        //Updates and returns the total function points for the project
        this.updateTotalFunctionPoints();
        return this.totalFunctionPoints;
    }

    public Language getFunctionPointLanguage(){
        //Returns the language for the associated with the metric
        return functionPointLanguage;
    }

    public int getCodeSize(){
        //Returns code size corresponding to the current language and function point input values
        this.updateCodeSize();
        return codeSize;
    }

    //Setters
    //TODO: Use
    public void setValueAdjustmentFactor(int valToSet, int newVal){
        //Sets the specified value adjustment factor's value
        this.valueAdjustmentFactors.setValue(valToSet, newVal);
    }

    public void setValueAdjustmentFactorsFromArray(int[] newVals){
        //Sets the entire VAF array in the VAF object
        this.valueAdjustmentFactors.setAllValuesFromArray(newVals);
    }

    public void setSaveData() {
        //Saves METRIC_ID, Inputs values, VAF values, and the FP's language to saveData
        //Initialize new saveData
        ArrayList<Integer> tempSaveData = new ArrayList<Integer>();

        //Add METRIC_ID and FP language
        tempSaveData.add(FunctionPoint.METRIC_ID);
        tempSaveData.add(this.functionPointLanguage.ordinal());

        //Add the Inputs and complexities
        Input[] inputsToSave = {
                this.externalInputs,
                this.externalOutputs,
                this.externalInquiries,
                this.internalLogicFiles,
                this.externalInterfaceFiles
        };

        for (int i = 0; i < 5; i++) {
            tempSaveData.add(inputsToSave[i].numberInputs);
            tempSaveData.add(inputsToSave[i].inputComplexity.ordinal());
        }

        //Add the VAF values
        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
            tempSaveData.add(this.valueAdjustmentFactors.getCurrentValuesArray()[i]);

        //set saveData
        this.saveData = tempSaveData;
    }

    public void setFunctionPointLanguage(Language languageToSet){
        //Sets this FunctionPoint's Language to the specified Language
        this.functionPointLanguage = languageToSet;
    }

    public void setFromSavedData() {
        //Sets variable values based on integers in saveData
        //Sets language
        this.functionPointLanguage = functionPointLanguage.values()[saveData.get(1)];
        //Sets IDV values and complexities
        this.externalInputs = new Input(); externalInputs.numberInputs = saveData.get(2); externalInputs.inputComplexity = Complexity.values()[saveData.get(3)];
        this.externalOutputs = new Input(); externalOutputs.numberInputs = saveData.get(4); externalOutputs.inputComplexity = Complexity.values()[saveData.get(5)];
        this.externalInquiries = new Input(); externalInquiries.numberInputs = saveData.get(6); externalInquiries.inputComplexity = Complexity.values()[saveData.get(7)];
        this.internalLogicFiles = new Input(); internalLogicFiles.numberInputs = saveData.get(8); internalLogicFiles.inputComplexity = Complexity.values()[saveData.get(9)];
        this.externalInterfaceFiles = new Input(); externalInterfaceFiles.numberInputs = saveData.get(10); externalInterfaceFiles.inputComplexity = Complexity.values()[saveData.get(11)];

        //Sets VAF values
        this.valueAdjustmentFactors = new ValueAdjustmentFactor();

        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
            valueAdjustmentFactors.setValue(i, saveData.get(i+12));
    }

    //Misc. Methods
    //
    //Update Methods
    //
    public void updateTotalFunctionPoints(){
        //Updates totalFunctionPoints using the formula:
        //totalFunctionPoints = totalCount * (0.65 + (0.01 * (Sum of valueAdjustmentFactors)))

        this.updateTotalCount(); //Ensures totalCount is up to date
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 + (0.01 * getSumOfValAdjFac())) //The formula for calculating FPs, rounded up
        );
    }

    public void updateCodeSize(){
        //Updates codeSize (estimated lines of code) for the FP metric based on all entered data and the selected language
        this.codeSize = (this.getTotalFunctionPoints() * this.functionPointLanguage.linesOfCodePerFunctionPoint());
    }

    //TODO: Use(?)
    public void updateCalculations() {
        //Updates totalCount, totalFunctionPoints, and codeSize
        this.updateTotalCount(); //Ensures totalCount is up to date
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 + (0.01 * getSumOfValAdjFac())) //The formula for calculating FPs
        );
        this.updateCodeSize();
    }


    //Not used
    @Override public String toString(){
        //To allow simple saving
        String outString = super.toString() + "\n" + valueAdjustmentFactors;
        return outString;
    }
}
