package Project543;

import java.lang.Math;

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
        String outString = ""
                /*
                        "EX_INP: " + exInputs           + "\n" +
                        "EX_OUT: " + exOutputs          + "\n" +
                        "EX_INQ: " + exInquiries        + "\n" +
                        "IN_LOG: " + inLogicFiles       + "\n" +
                        "EX_INF: " + exInterfaceFiles   + "\n" +
                                valueAdjustmentFactors*/; //CALLER ALWAYS ADDS "\n"

        return outString;
    }
}
