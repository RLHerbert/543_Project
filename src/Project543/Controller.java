package Project543;

import java.awt.event.WindowFocusListener;

public class Controller {
    /*
    public enum Complexity{
        LOW, MEDIUM, HIGH;
    }
     */
    final private int[][] weightFactors = {
            {3,4,6},    //External Inputs
            {4,5,7},    //External Outputs
            {3,4,6},    //External Inquiries
            {7,10,15},  //Internal Logic Files
            {5,7,10}    //External Interface Files
    };

    private InfoDomVal
            exInputs, //External Inputs
            exOutputs, //External Outputs
            exInquiries, //External Inquiries
            inLogicFiles, //Internal Logic Files
            exInterfaceFiles; //External Interface files

    public Controller(){
        this.exInputs = new InfoDomVal(weightFactors[0]);
        this.exOutputs = new InfoDomVal(weightFactors[1]);
        this.exInquiries = new InfoDomVal(weightFactors[2]);
        this.inLogicFiles = new InfoDomVal(weightFactors[3]);
        this.exInterfaceFiles = new InfoDomVal(weightFactors[4]);
    }

}
