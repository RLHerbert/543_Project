package Project543;

import java.util.Scanner;

//A class which holds an Information Domain Value (IDV) and relevant methods
public abstract class InformationDomainValue extends Metrics {
    //Member Fields
    //
    //Member Enums and Classes
    //
    public enum InformationDomain{
        //Enum for each IDV
        //TODO: Use(?)
        EXTERNAL_INPUTS, EXTERNAL_OUTPUTS, EXTERNAL_INQUIRIES, INTERNAL_LOGIC_FILES, EXTERNAL_INTERFACE_FILES;
    }

    //Enum for complexity
    public enum Complexity{
        //Enum to hold complexity for each IDV
        SIMPLE, AVERAGE, COMPLEX;
    }

    //Nested class to handle inputs
    protected class Input{
        public int numberInputs;
        public Complexity inputComplexity;

        public Input(){
            numberInputs = 0;
            inputComplexity = Complexity.AVERAGE;
        }

        public Input(int numberInputs, int inputComplexity){
            //Parameterized constructor
            this.numberInputs = numberInputs;
            this.inputComplexity = Complexity.values()[inputComplexity];
        }
    }

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final int[][] weightFactors = {
            //The weight factors for each IDV
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    protected Input
            externalInputs,
            externalOutputs,
            externalInquiries,
            internalLogicFiles,
            externalInterfaceFiles;

    protected int totalCount; //Weighted sum of the Inputs (AKA total count)

    //Member Methods
    //
    //Constructor(s)
    //
    public InformationDomainValue(){
        //Default constructor

        //Initialize the IDV Inputs
        this.externalInputs = new Input();
        this.externalOutputs = new Input();
        this.externalInquiries = new Input();
        this.internalLogicFiles = new Input();
        this.externalInterfaceFiles = new Input();

        updateTotalCount();
    }

    public InformationDomainValue(String saveDataString){
        //Save data constructor
        //Call super constructor
        super(saveDataString);

        //Initialize member fields from saved data
        this.externalInputs = new Input(this.saveData.get(2),this.saveData.get(3));
        this.externalOutputs = new Input(this.saveData.get(4),this.saveData.get(5));
        this.externalInquiries = new Input(this.saveData.get(6),this.saveData.get(7));
        this.internalLogicFiles = new Input(this.saveData.get(8),this.saveData.get(9));
        this.externalInterfaceFiles = new Input(this.saveData.get(10),this.saveData.get(11));

        updateTotalCount();
    }

    //Getters
    public int getTotalCount(){
        //Returns the most recent total count value
        this.updateTotalCount();
        return totalCount;
    }

    public int[] getNumberOfInputs(){
        //Returns an array where each value corresponds to the number of inputs for that IDV
        int[] inputNumbers = {
                externalInputs.numberInputs,
                externalOutputs.numberInputs,
                externalInquiries.numberInputs,
                internalLogicFiles.numberInputs,
                externalInterfaceFiles.numberInputs
        };
        return inputNumbers;
    }

    public Complexity[] getComplexityOfInputs(){
        //Returns an array where each value corresponds to the complexity for that IDV
        Complexity[] inputComplexity = {
                externalInputs.inputComplexity,
                externalOutputs.inputComplexity,
                externalInquiries.inputComplexity,
                internalLogicFiles.inputComplexity,
                externalInterfaceFiles.inputComplexity
        };
        return inputComplexity;
    }

    public int[] getSumsOfInputs(){
        //Returns the array which is the weighted sums of each input
        int[] currentNumberOfInputs = this.getNumberOfInputs();
        Complexity[] currentComplexityOfInputs = this.getComplexityOfInputs();
        int[] sumsOfInputs = new int[5];
        for (int i = InformationDomain.EXTERNAL_INPUTS.ordinal(); i <= InformationDomain.EXTERNAL_INTERFACE_FILES.ordinal(); i++){
            sumsOfInputs[i] = (currentNumberOfInputs[i] * (weightFactors[i][currentComplexityOfInputs[i].ordinal()]));
        }
        return sumsOfInputs;
    }


    //Access numbers of inputs
    //
    //TODO: Delete(?), unused
    public int getNumberOfExternalInputs(){
        return externalInputs.numberInputs;
    }

    public int getNumberOfExternalOutputs(){
        return externalOutputs.numberInputs;
    }

    public int getNumberOfExternalInquiries(){
        return externalInquiries.numberInputs;
    }

    public int getNumberOfInternalLogicFiles(){
        return internalLogicFiles.numberInputs;
    }

    public int getNumberOfExternalInterfaceFiles(){
        return externalInterfaceFiles.numberInputs;
    }

    //Setters
    //
    //Individual setters for number of inputs for each IDV
    //
    public void setNumberOfExternalInputs(int numberOfInputs){
        this.externalInputs.numberInputs = numberOfInputs;
        this.updateTotalCount();
    }

    public void setNumberOfExternalOutputs(int numberOfInputs){
        this.externalOutputs.numberInputs = numberOfInputs;
        this.updateTotalCount();
    }

    public void setNumberOfExternalInquiries(int numberOfInputs){
        this.externalInquiries.numberInputs = numberOfInputs;
        this.updateTotalCount();
    }

    public void setNumberOfInternalLogicFiles(int numberOfInputs){
        this.internalLogicFiles.numberInputs = numberOfInputs;
        this.updateTotalCount();
    }

    public void setNumberOfExternalInterfaceFiles(int numberOfInputs){
        this.externalInterfaceFiles.numberInputs = numberOfInputs;
        this.updateTotalCount();
    }

    //Individual setters for complexity of inputs for each IDV
    //
    public void setComplexityOfExternalInputs(Complexity inputComplexity){
        this.externalInputs.inputComplexity = inputComplexity;
        this.updateTotalCount();
    }

    public void setComplexityOfExternalOutputs(Complexity inputComplexity){
        this.externalOutputs.inputComplexity = inputComplexity;
        this.updateTotalCount();
    }

    public void setComplexityOfExternalInquiries(Complexity inputComplexity){
        this.externalInquiries.inputComplexity = inputComplexity;
        this.updateTotalCount();
    }

    public void setComplexityOfInternalLogicFiles(Complexity inputComplexity){
        this.internalLogicFiles.inputComplexity = inputComplexity;
        this.updateTotalCount();
    }

    public void setComplexityOfExternalInterfaceFiles(Complexity inputComplexity){
        this.externalInterfaceFiles.inputComplexity = inputComplexity;
        this.updateTotalCount();
    }

    //Misc. Methods
    //
    //Update Methods
    //
    public void updateSumsOfInputs(){
        //Unused
        //Updates the individual sums of the IDVs
    }

    public void updateTotalCount(){
        //Updates the total weighted sum of the IDVs (totalCount)
        int[] currentSumsOfInputs = this.getSumsOfInputs();
        //Complexity[] currentComplexityOfInputs = this.getComplexityOfInputs();
        totalCount = 0;

        for (int i = InformationDomain.EXTERNAL_INPUTS.ordinal() /*0*/; i <= InformationDomain.EXTERNAL_INTERFACE_FILES.ordinal() /*4*/; i++){
            totalCount += currentSumsOfInputs[i];
        }
    }
}
