package Project543;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.lang.Math;
import java.util.*;

public class FunctionPoint extends InformationDomainValue {
    //TODO: Create functions which return FP panes with all of the correct hookups
    //Member Variables
    //
    //Static Variables
    //
    public static final int METRIC_ID = ((int) ('F' + 'P'));
    //FP Save format: [METRIC_ID, functionPointLanguage, IDV value-complexity pairs, ..., VAFs, ...]

    private int  totalFunctionPoints, codeSize;     //To hold the total count value for the FP, and FP value itself respectively
    private ValueAdjustmentFactor valueAdjustmentFactors; //To hold the VAFs for the FP
    private Language functionPointLanguage; //To hold the language for this Metric (on a per tab basis)
    //TODO: hook up functionPointLanguage to tab stuff or everything whatever;

    //Member Methods
    //
    //Constructors
    //
    public FunctionPoint(){
        //Create the Value Adjustment Factor
        this.valueAdjustmentFactors = new ValueAdjustmentFactor();

        //Initialize totalCount and totalFunctionPoints
        updateTotalFunctionPoints();

        //TODO: Set functionPointLanguage in constructors
        this.functionPointLanguage = Language.NONE;
    }

    //TODO: fix this (CHANGE COMPLETELY)
    public FunctionPoint(Scanner savedFile){
        //Constructor for saved files
        super(savedFile);
        this.valueAdjustmentFactors = new ValueAdjustmentFactor(savedFile);

        //Update total function points
        updateTotalFunctionPoints();
    }

    //Getters
    public int getValAdjFac(int valToGet){
        //Returns the specified VAF by calling ValAdjFac's getVal
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF

        return valueAdjustmentFactors.getVal(valToGet);
    }

    public int getSumOfValAdjFac(){
        //Returns the sum of the VAFs
        return valueAdjustmentFactors.getSumOfVals();
    }

    public int getFunctionPoints(){
        //Returns the total function points for the project
        this.updateTotalFunctionPoints();
        return totalFunctionPoints;
    }

    //Setters
    public void setValAdjFac(int valToSet, int newVal){
        //Sets the specified value adjustment factor's value
        this.valueAdjustmentFactors.setVal(valToSet, newVal);
    }

    public void setSaveData()
    {
        //Saves variable values to saveData
        ArrayList<Integer> tempSaveData = new ArrayList<Integer>();
        tempSaveData.add(METRIC_ID);
        tempSaveData.add(functionPointLanguage.ordinal());

        Input[] inputsToSave = {externalInputs, externalOutputs, externalInquiries, internalLogicFiles, externalInterfaceFiles};

        for (int i = 0; i < 5; i++) {
            tempSaveData.add(inputsToSave[i].numberInputs);
            tempSaveData.add(inputsToSave[i].inputComplexity.ordinal());
        }

        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
            tempSaveData.add(this.valueAdjustmentFactors.getValArray()[i]);

        saveData = tempSaveData;
    }

    //Misc. Methods
    public void updateTotalFunctionPoints(){
        //Updates totalFunctionPoints
        this.updateTotalCount(); //Ensures totalCount is up to date
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 + (0.01 * getSumOfValAdjFac())) //The formula for calculating FPs
        );
    }

    public void updateCodeSize(){
        //Updates codeSize
        //Returns the lines of code (LOC) for the FP metric based on all entered data and the selected language
        this.codeSize = (this.getFunctionPoints() * this.functionPointLanguage.locPerFP());
    }

    public void updateCalculations()
    {
        //Updates totalCount, totalFunctionPoints, and codeSize
        this.updateTotalCount(); //Ensures totalCount is up to date
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 + (0.01 * getSumOfValAdjFac())) //The formula for calculating FPs
        );
        this.updateCodeSize();
    }

    public void setFromSavedData()
    {
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
        for (int i = 0; i < ValueAdjustmentFactor.NUM_VAF; i++)
            valueAdjustmentFactors.setVal(i, saveData.get(i+12));
    }


    //TODO: Fix
    @Override public String toString(){
        //To allow simple saving
        String outString = super.toString() + "\n" + valueAdjustmentFactors;
        return outString;
    }

    /*//RETHOUGHT
    public Tab toTab(){
        //TODO: Implement
        Tab FPTab = new Tab("Function Points");
        return FPTab;
    }
     */
}
