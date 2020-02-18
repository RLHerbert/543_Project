package Project543;

public class InfoDomVal {

    public enum Complexity{
        SIMPLE, AVERGAGE, COMPLEX;
    }

    public static int NUMWEIGHTS = 3;

    private Complexity selectedComplexity;
    private int[] complexWeightFactors; //Complexity weight factors
    private int numOfDomVal, sumOfDomVal;

    public InfoDomVal(){
        this.selectedComplexity = Complexity.AVERGAGE;
        this.complexWeightFactors = new int[NUMWEIGHTS];
        this.complexWeightFactors[0] = 3;
        this.complexWeightFactors[1] = 4;
        this.complexWeightFactors[2] = 6;
        numOfDomVal = 0;
        //sumOfDomVal = 0;
        this.setSumOfDomVal();
    }

    public void setSumOfDomVal() {
        this.sumOfDomVal = (this.numOfDomVal) * (this.selectedComplexity.ordinal());
    }
}
