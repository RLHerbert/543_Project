package Project543;

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
        public int numberInputs;
        public Complexity inputComplexity;

        public Input(){
            numberInputs = 0;
            inputComplexity = Complexity.AVERGAGE;
        }

        //TODO: toString();
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

    protected int sumOfInputs; //Weighted sum of the Inputs

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

    //Setters
    //TODO: Setters for new Input

    //Misc. Methods
    public void updateSumOfInputs(){
        int[] currentNumberOfInputs = this.getNumberOfInputs();
        Complexity[] currentComplexityOfInputs = this.getComplexityOfInputs();
        sumOfInputs = 0;

        for (int i = InformationDomain.EXTERNAL_INPUTS.ordinal(); i < InformationDomain.EXTERNAL_INTERFACE_FILES.ordinal(); i++){
            sumOfInputs += (currentNumberOfInputs[i] * (weightFactors[i][currentComplexityOfInputs[i].ordinal()]));
        }
    }

    @Override
    public String toString() {
        //To allow easy saving
        //TODO: Refactor
        String outString = "INPUTS: " /*+ numInputs + " " + selectedComplexity*/; //CALLER ALWAYS ADDS "\n"

        return outString;
    }
}
