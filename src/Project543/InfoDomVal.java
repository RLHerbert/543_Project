package Project543;

//A class which holds an Information Domain Value (IDV) and relevant methods
public class InfoDomVal {

    //Enum for complexity
    public enum Complexity{
        SIMPLE, AVERGAGE, COMPLEX;
    }

    //Member Variables
    public static int NUMWEIGHTS = 3;

    private Complexity selectedComplexity;  //The user selected complexity for the information domain value
    private int[] complexWeightFactors;     //Complexity weight factors
    private int numOfDomVal, sumOfDomVal;   //Number of IDV, weighted sum of IDV

    public InfoDomVal(){
        this.selectedComplexity = Complexity.AVERGAGE;  //Defaults to average complexity
        this.complexWeightFactors = new int[NUMWEIGHTS];
        this.complexWeightFactors[0] = 3;
        this.complexWeightFactors[1] = 4;
        this.complexWeightFactors[2] = 6;
        numOfDomVal = 0;
        this.setSumOfDomVal();
    }

    public InfoDomVal(int[] complexWeightFactors){
        this.selectedComplexity = Complexity.AVERGAGE;
        this.complexWeightFactors = complexWeightFactors;
        numOfDomVal = 0;
        this.setSumOfDomVal();
    }

    //Getters
    public int getSumOfDomVal(){
        return this.sumOfDomVal;
    }

    public int[] getComplexWeightFactors(){
        return this.complexWeightFactors;
    }

    //Setters
    public void setSumOfDomVal() {
        this.sumOfDomVal = (this.numOfDomVal) * (complexWeightFactors[this.selectedComplexity.ordinal()]);
    }

    public void setNumOfDomVal(int numOfDomVal1){
        this.numOfDomVal = numOfDomVal1;
        setSumOfDomVal();
    }

    public void setComplexity(Complexity selectedComplexity){
        this.selectedComplexity = selectedComplexity;
        setSumOfDomVal();
    }

    //Misc. Methods
}
