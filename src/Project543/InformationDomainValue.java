package Project543;

//A class which holds an Information Domain Value (IDV) and relevant methods
public class InformationDomainValue {
    //Enum for complexity
    public enum InformationDomain{
        EXTERNAL_INPUTS, EXTERNAL_OUTPUTS, EXTERNAL_INQUIRIES, INTERNAL_LOGIC_FILES, EXTERNAL_INTERFACE_FILES;
    }

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
    //public static final int NUM_WEIGHTS = 3;

    public static final int[][] weightFactors = {
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };

    //TODO: Refactor (make following disappear)
//    private Complexity selectedComplexity;  //The user selected complexity for the information domain value
//    private int[] complexWeightFactors;     //Complexity weight factors
//    private int numInputs, sumInputs;   //Number of IDV inputs, weighted sum of IDV inputs

    protected Input externalInputs, externalOutputs, externalInquiries, internalLogicFiles, externalInterfaceFiles;
    protected int sumOfInputs; //Weighted sum of the Inputs


    public InformationDomainValue(){
        //TODO: Refactor BABY
//        this.selectedComplexity = Complexity.AVERGAGE;  //Defaults to average complexity
//        this.complexWeightFactors = new int[NUM_WEIGHTS];
//        this.complexWeightFactors[0] = 3;
//        this.complexWeightFactors[1] = 4;
//        this.complexWeightFactors[2] = 6;
//        numInputs = 0;
//        this.setSumOfDomVal();

        this.externalInputs = new Input();
        this.externalOutputs = new Input();
        this.externalInquiries = new Input();
        this.internalLogicFiles = new Input();
        this.externalInterfaceFiles = new Input();

        updateSumOfInputs();
    }

    public InformationDomainValue(int[] complexWeightFactors){
//        this.selectedComplexity = Complexity.AVERGAGE;
//        this.complexWeightFactors = complexWeightFactors;
//        numInputs = 0;
//        this.setSumOfDomVal();
    }

    //Getters
//    public int getNumInputs(){
//        return numInputs;
//    }
//
//    public int getSumInputs(){
//        return this.sumInputs;
//    }
//
//    public int[] getComplexWeightFactors(){
//        return this.complexWeightFactors;
//    }

    //TODO: Getters for new Input
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
//    public void setSumOfDomVal() {
//        this.sumInputs = (this.numInputs) * (complexWeightFactors[this.selectedComplexity.ordinal()]);
//    }
//
//    public void setNumInputs(int numInputs){
//        this.numInputs = numInputs;
//        setSumOfDomVal();
//    }
//
//    public void setComplexity(Complexity selectedComplexity){
//        this.selectedComplexity = selectedComplexity;
//        setSumOfDomVal();
//    }

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
