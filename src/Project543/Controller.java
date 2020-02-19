package Project543;

import java.awt.event.WindowFocusListener;
import java.time.temporal.ValueRange;

public class Controller {

    //Member Variables
    final private int[][] weightFactors = {
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };


    private int totalCount;     //To hold the total count value
    private InfoDomVal
            exInputs,           //External Inputs
            exOutputs,          //External Outputs
            exInquiries,        //External Inquiries
            inLogicFiles,       //Internal Logic Files
            exInterfaceFiles;   //External Interface files
    //private ValAdjustFactor test; //TODO: Implement this somehow, possibly make window class specifically

    //Member Methods
    //Constructors
    public Controller(){
        //Create the Information Domain Values
        this.exInputs = new InfoDomVal(weightFactors[0]);
        this.exOutputs = new InfoDomVal(weightFactors[1]);
        this.exInquiries = new InfoDomVal(weightFactors[2]);
        this.inLogicFiles = new InfoDomVal(weightFactors[3]);
        this.exInterfaceFiles = new InfoDomVal(weightFactors[4]);

        updateTotalCount();
    }

    //Getters
    int getTotalCount(){
        return this.totalCount;
    }

    //Setters

    //Misc. Methods
    void updateTotalCount() {
        totalCount =
                this.exInputs.getSumOfDomVal()      +
                this.exOutputs.getSumOfDomVal()     +
                this.exInquiries.getSumOfDomVal()   +
                this.inLogicFiles.getSumOfDomVal()  +
                this.exInterfaceFiles.getSumOfDomVal();
    }
}
