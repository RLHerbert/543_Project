package Project543;

import java.lang.Math;
import java.util.Scanner;

public class FunctionPoint extends InformationDomainValue {
    //Member Variables
    private int /*totalCount,*/ totalFunctionPoints;     //To hold the total count value for the FP, and FP value itself respectively

    private ValueAdjustmentFactor valueAdjustmentFactors; //To hold the VAFs for the FP

    //Member Methods
    //Constructors
    public FunctionPoint(){
        //Create the Value Adjustment Factor
        this.valueAdjustmentFactors = new ValueAdjustmentFactor();

        //Initialize totalCount and totalFunctionPoints
        updateTotalFunctionPoints();
    }

    //TODO: Implement FunctionPoint(SAVEDFILE){};
    public FunctionPoint(Scanner savedFile){
        super(savedFile);
        this.valueAdjustmentFactors = new ValueAdjustmentFactor(savedFile);
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
        return totalFunctionPoints;
    }

    //Setters
    public void setValAdjFac(int valToSet, int newVal){
        this.valueAdjustmentFactors.setVal(valToSet, newVal);
    }
    //TODO: Setters for each IDV

    //Misc. Methods
    public void updateTotalFunctionPoints(){
        //Updates totalFunctionPoints
        this.totalFunctionPoints = (int) Math.ceil(
                sumOfInputs * (0.65 * (0.01 * getSumOfValAdjFac()))
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
}
