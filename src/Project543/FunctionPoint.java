package Project543;

import javafx.scene.control.TabPane;

import java.lang.Math;
import java.util.Scanner;

public class FunctionPoint extends InformationDomainValue {
    //TODO: Create functions which return FP panes with all of the correct hookups
    //Member Variables
    //
    private int  totalFunctionPoints;     //To hold the total count value for the FP, and FP value itself respectively
    private ValueAdjustmentFactor valueAdjustmentFactors; //To hold the VAFs for the FP
    //TODO: private Language functionPointLanguage;

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
    }

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
    //TODO: Setters for each IDV

    //Misc. Methods
    public void updateTotalFunctionPoints(){
        //Updates totalFunctionPoints
        this.updateTotalCount(); //Ensures totalCount is up to date
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 + (0.01 * getSumOfValAdjFac())) //The formula for calculating FPs
        );
    }

    //TODO: Fix
    @Override public String toString(){
        //To allow simple saving
        String outString = super.toString() + "\n" + valueAdjustmentFactors;
//                "EXTERNAL_INPUTS: "             + externalInputs        + "\n" +
//                "EXTERNAL_OUTPUTS: "            + externalOutputs       + "\n" +
//                "EXTERNAL_INQUIRIES: "          + externalInquiries     + "\n" +
//                "INTERNAL_LOGIC_FILES: "        + internalLogicFiles    + "\n" +
//                "EXTERNAL_INTERFACE_FILES: "    + externalInterfaceFiles+ "\n" +
//                valueAdjustmentFactors;

        //outString += "";

        return outString;
    }

    public TabPane toTabPane(){
        //TODO: Implement
        return new TabPane();
    }
}
