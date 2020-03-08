package Project543;

//A class which holds an Information Domain Value (IDV) and relevant methods
public class InfoDomVal {

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

    //Member Variables
    public static int NUMWEIGHTS = 3;

    private Complexity selectedComplexity;  //The user selected complexity for the information domain value
    private int[] complexWeightFactors;     //Complexity weight factors
    private int numInputs, sumInputs;   //Number of IDV inputs, weighted sum of IDV inputs

    public InfoDomVal(){
        this.selectedComplexity = Complexity.AVERGAGE;  //Defaults to average complexity
        this.complexWeightFactors = new int[NUMWEIGHTS];
        this.complexWeightFactors[0] = 3;
        this.complexWeightFactors[1] = 4;
        this.complexWeightFactors[2] = 6;
        numInputs = 0;
        this.setSumOfDomVal();
    }

    public InfoDomVal(int[] complexWeightFactors){
        this.selectedComplexity = Complexity.AVERGAGE;
        this.complexWeightFactors = complexWeightFactors;
        numInputs = 0;
        this.setSumOfDomVal();
    }

    //Getters
    public int getSumInputs(){
        return this.sumInputs;
    }

    public int[] getComplexWeightFactors(){
        return this.complexWeightFactors;
    }

    //Setters
    public void setSumOfDomVal() {
        this.sumInputs = (this.numInputs) * (complexWeightFactors[this.selectedComplexity.ordinal()]);
    }

    public void setNumInputs(int numOfDomVal1){
        this.numInputs = numOfDomVal1;
        setSumOfDomVal();
    }

    public void setComplexity(Complexity selectedComplexity){
        this.selectedComplexity = selectedComplexity;
        setSumOfDomVal();
    }

    //Misc. Methods
    @Override
    public String toString() {
        //To allow easy saving
        String outString = "INPUTS: " + numInputs + " " + selectedComplexity; //CALLER ALWAYS ADDS "\n"

        return outString;
    }
}
