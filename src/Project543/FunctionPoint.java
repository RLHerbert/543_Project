package Project543;

import java.lang.Math;

public class FunctionPoint {
    //Member Variables
    //public static final int NUM_VAF_VALS = 14;

    private static final int[][] weightFactors = {
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };

    private int totalCount, totalFunctionPoints;     //To hold the total count value for the FP, and FP value itself respectively
    private InfoDomVal
            exInputs,           //External Inputs
            exOutputs,          //External Outputs
            exInquiries,        //External Inquiries
            inLogicFiles,       //Internal Logic Files
            exInterfaceFiles;   //External Interface files

    //private ValAdjustFactor [] VAF_array; //TODO: Refactor entirely
    private ValAdjFac vaf; //To hold the VAFs for the FP
    //private Languages currentLang;

    //Member Methods
    //Constructors
    public FunctionPoint(){
        //Create the Information Domain Values
        this.exInputs = new InfoDomVal(weightFactors[0]);
        this.exOutputs = new InfoDomVal(weightFactors[1]);
        this.exInquiries = new InfoDomVal(weightFactors[2]);
        this.inLogicFiles = new InfoDomVal(weightFactors[3]);
        this.exInterfaceFiles = new InfoDomVal(weightFactors[4]);

        //Create the Value Adjustment Factor array with the VAF questions as elements
        //this.VAF_array = new ValAdjustFactor[NUM_VAF_VALS];
        //this.setVAF_array();
        this.vaf = new ValAdjFac();

        //Initialize totalCount and totalFunctionPoints
        updateTotalCount();
        updateTotalFunctionPoints();
    }

    //TODO: Implement FunctionPoint(SAVEDFILE){};

    //Getters
    public int getTotalCount(){
        return this.totalCount;
    }

    public int getValAdjFac(int valToGet){
        //Returns the specified VAF by calling ValAdjFac's getVal
        //PRECONDITIONS: 0 <= valToSet < NUM_VAF

        return vaf.getVal(valToGet);
    }

    public int getSumOfValAdjFac(){
        //Returns the sum of the VAFs
        return vaf.getSumOfVals();
    }

    public int getFunctionPoints(){
        return totalFunctionPoints;
    }

    //Setters
    public void setFalAdjFac(int valToSet, int newVal){
        this.vaf.setVal(valToSet, newVal);
    }

    //Misc. Methods
    public void updateTotalCount() {
        //Updates totalCount
        totalCount =
                this.exInputs.getSumOfDomVal()      +
                this.exOutputs.getSumOfDomVal()     +
                this.exInquiries.getSumOfDomVal()   +
                this.inLogicFiles.getSumOfDomVal()  +
                this.exInterfaceFiles.getSumOfDomVal();
    }

    public void updateTotalFunctionPoints(){
        //Updates totalFunctionPoints
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 * (0.01 * getSumOfValAdjFac()))
        );
    }

    //TODO: Implement @Override public String toString(){String outString = ""; return outString //To allow simple saving
}
