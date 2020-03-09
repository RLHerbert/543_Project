package Project543;

import java.util.Scanner;

//A class which holds an Information Domain Value (IDV) and relevant methods
public class InformationDomainValue {
    //Enum for information domains
    public enum InformationDomain{
        EXTERNAL_INPUTS, EXTERNAL_OUTPUTS, EXTERNAL_INQUIRIES, INTERNAL_LOGIC_FILES, EXTERNAL_INTERFACE_FILES;
    }

    //Enum for complexity
    public enum Complexity{
        SIMPLE, AVERGAGE, COMPLEX;


        @Override
        public String toString() {
            String outString = "COMPLEXITY: ";

            switch (this){
                case SIMPLE:
                    outString += "SIMPLE";
                    break;
                case AVERGAGE:
                    outString += "AVERAGE";
                    break;
                case COMPLEX:
                    outString += "COMPLEX";
                    break;
                default:
                    outString += "ERROR";
                    System.err.println("ERROR: COMPLEXITY_UNDEFINED");
                    break;
            }

            return outString;
        }
    }

    //Nested class to handle inputs
    protected class Input{
        public int numberInputs/*, weightedSumOfInputs*/;
        public Complexity inputComplexity;

        public Input(){
            numberInputs = 0;
            //weightedSumOfInputs = 0;
            inputComplexity = Complexity.AVERGAGE;
        }

        @Override
        public String toString() {
            return "INPUTS: " + numberInputs + " " + inputComplexity;
        }
    }

    //Member Variables
    public static final int[][] weightFactors = {
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };

    protected Input
            externalInputs,
            externalOutputs,
            externalInquiries,
            internalLogicFiles,
            externalInterfaceFiles;

    protected int sumOfInputs; //Weighted sum of the Inputs (AKA totalCount)

    //Member Methods
    //Constructor(s)
    public InformationDomainValue(){
        this.externalInputs = new Input();
        this.externalOutputs = new Input();
        this.externalInquiries = new Input();
        this.internalLogicFiles = new Input();
        this.externalInterfaceFiles = new Input();

        updateSumOfInputs();
    }

    public InformationDomainValue(Scanner savedFile){

    }

    public InformationDomainValue(int[] complexWeightFactors){

    }

    //Getters
    //TODO: More getters for new Input
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
        int[] currentNumberOfInputs = this.getNumberOfInputs();
        Complexity[] currentComplexityOfInputs = this.getComplexityOfInputs();
        int[] sumsOfInputs = new int[5];
        for (int i = InformationDomain.EXTERNAL_INPUTS.ordinal(); i < InformationDomain.EXTERNAL_INTERFACE_FILES.ordinal(); i++){
            sumsOfInputs[i] = (currentNumberOfInputs[i] * (weightFactors[i][currentComplexityOfInputs[i].ordinal()]));
        }
        return sumsOfInputs;
    }

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
    //TODO: Setters for new Input
    public void setNumberOfExternalInputs(int numberOfInputs){
        this.externalInputs.numberInputs = numberOfInputs;
        this.updateSumOfInputs();
    }

    public void setNumberOfExternalOutputs(int numberOfInputs){
        this.externalOutputs.numberInputs = numberOfInputs;
        this.updateSumOfInputs();
    }

    public void setNumberOfExternalInquiries(int numberOfInputs){
        this.externalInquiries.numberInputs = numberOfInputs;
        this.updateSumOfInputs();
    }

    public void setNumberOfInternalLogicFiles(int numberOfInputs){
        this.internalLogicFiles.numberInputs = numberOfInputs;
        this.updateSumOfInputs();
    }

    public void setNumberOfExternalInterfaceFiles(int numberOfInputs){
        this.externalInterfaceFiles.numberInputs = numberOfInputs;
        this.updateSumOfInputs();
    }

    public void setComplexityOfExternalInputs(Complexity inputComplexity){
        this.externalInputs.inputComplexity = inputComplexity;
        this.updateSumOfInputs();
    }

    public void setComplexityOfExternalOutputs(Complexity inputComplexity){
        this.externalOutputs.inputComplexity = inputComplexity;
        this.updateSumOfInputs();
    }

    public void setComplexityOfExternalInquiries(Complexity inputComplexity){
        this.externalInquiries.inputComplexity = inputComplexity;
        this.updateSumOfInputs();
    }

    public void setComplexityOfInternalLogicFiles(Complexity inputComplexity){
        this.internalLogicFiles.inputComplexity = inputComplexity;
        this.updateSumOfInputs();
    }

    public void setComplexityOfExternalInterfaceFiles(Complexity inputComplexity){
        this.externalInterfaceFiles.inputComplexity = inputComplexity;
        this.updateSumOfInputs();
    }

    //Misc. Methods
    public void updateSumsOfInputs(){
        //NOT YET IMPLEMENTED
        //Updates the individual sums of the IDVs
    }

    public void updateSumOfInputs(){
        //Updates the total weighted sum of the IDVs
        int[] currentSumsOfInputs = this.getSumsOfInputs();
        //Complexity[] currentComplexityOfInputs = this.getComplexityOfInputs();
        sumOfInputs = 0;

        for (int i = InformationDomain.EXTERNAL_INPUTS.ordinal() /*0*/; i < InformationDomain.EXTERNAL_INTERFACE_FILES.ordinal() /*4*/; i++){
            sumOfInputs += currentSumsOfInputs[i];
        }
    }

    //TODO: Fix
    @Override
    public String toString() {
        //To allow easy saving
        //TODO: Refactor
        String outString =
                "EXTERNAL_INPUTS: "             + externalInputs        + "\n" +
                "EXTERNAL_OUTPUTS: "            + externalOutputs       + "\n" +
                "EXTERNAL_INQUIRIES: "          + externalInquiries     + "\n" +
                "INTERNAL_LOGIC_FILES: "        + internalLogicFiles    + "\n" +
                "EXTERNAL_INTERFACE_FILES: "    + externalInterfaceFiles //CALLER ALWAYS ADDS "\n"
                ;

        return outString;
    }
}
