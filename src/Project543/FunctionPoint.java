package Project543;

import java.lang.Math;

public class FunctionPoint extends InformationDomainValue {
    //Member Variables
    public static final int[][] weightFactors = {
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };

    private int totalCount, totalFunctionPoints;     //To hold the total count value for the FP, and FP value itself respectively
    private InformationDomainValue
            exInputs,           //External Inputs
            exOutputs,          //External Outputs
            exInquiries,        //External Inquiries
            inLogicFiles,       //Internal Logic Files
            exInterfaceFiles;   //External Interface files

    private ValueAdjustmentFactor vaf; //To hold the VAFs for the FP

    //Member Methods
    //Constructors
    public FunctionPoint(){
        //Create the Information Domain Values
        this.exInputs = new InformationDomainValue(weightFactors[0]);
        this.exOutputs = new InformationDomainValue(weightFactors[1]);
        this.exInquiries = new InformationDomainValue(weightFactors[2]);
        this.inLogicFiles = new InformationDomainValue(weightFactors[3]);
        this.exInterfaceFiles = new InformationDomainValue(weightFactors[4]);

        //Create the Value Adjustment Factor array with the VAF questions as elements
        //this.VAF_array = new ValAdjustFactor[NUM_VAF_VALS];
        //this.setVAF_array();
        this.vaf = new ValueAdjustmentFactor();

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
    //TODO: Getters for each IDV
    public int getNumberExternalInputs(){
        return exInputs.getNumInputs();
    }

    public int getNumberExternalOutputs(){
        return exOutputs.getNumInputs();
    }

    public int getExternalInquiries(){
        return exInquiries.getNumInputs();
    }

    public int getNumberInternalLogic(){
       return inLogicFiles.getNumInputs();
    }

    public int getExternalInterface(){
        return exInterfaceFiles.getNumInputs();
    }

    public int getSumExternalInputs(){
        return exInputs.getSumInputs();
    }

    public int getSumExternalOutputs(){
        return exOutputs.getSumInputs();
    }

    //public int getS

    //Setters
    public void setValAdjFac(int valToSet, int newVal){
        this.vaf.setVal(valToSet, newVal);
    }
    //TODO: Setters for each IDV

    //Misc. Methods
    public void updateTotalCount() {
        //Updates totalCount
        totalCount =
                this.exInputs.getSumInputs()      +
                this.exOutputs.getSumInputs()     +
                this.exInquiries.getSumInputs()   +
                this.inLogicFiles.getSumInputs()  +
                this.exInterfaceFiles.getSumInputs();
    }

    public void updateTotalFunctionPoints(){
        //Updates totalFunctionPoints
        this.totalFunctionPoints = (int) Math.ceil(
                totalCount * (0.65 * (0.01 * getSumOfValAdjFac()))
        );
    }

    @Override public String toString(){
        //To allow simple saving
        String outString =
                        "EX_INP: " + exInputs           + "\n" +
                        "EX_OUT: " + exOutputs          + "\n" +
                        "EX_INQ: " + exInquiries        + "\n" +
                        "IN_LOG: " + inLogicFiles       + "\n" +
                        "EX_INF: " + exInterfaceFiles   + "\n" +
                                    vaf; //CALLER ALWAYS ADDS "\n"

        return outString;
    }
}
