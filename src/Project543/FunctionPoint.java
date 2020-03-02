package Project543;

public class FunctionPoint {
    //Member Variables
    public static final int NUM_VAF_VALS = 14;

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

    private ValAdjustFactor [] VAF_array;
    private Languages currentLang;

    //Member Methods
    //Constructors
    public FunctionPoint(){
        //Create the Information Domain Values
        this.exInputs = new InfoDomVal(weightFactors[0]);
        this.exOutputs = new InfoDomVal(weightFactors[1]);
        this.exInquiries = new InfoDomVal(weightFactors[2]);
        this.inLogicFiles = new InfoDomVal(weightFactors[3]);
        this.exInterfaceFiles = new InfoDomVal(weightFactors[4]);

        //Create the Value Adjustment Factor array with the VAF questions as elements
        this.VAF_array = new ValAdjustFactor[NUM_VAF_VALS];
        this.setVAF_array();

        updateTotalCount();
    }

    //Getters
    public int getTotalCount(){
        return this.totalCount;
    }

    //Setters
    void setVAF_array()
    {
        String VAF_strings [] = {
                "Does the system require reliable backup and recovery processes?",
                "Are specialized data communications required to transfer information to and from the application?",
                "Are there distributed processing functions?",
                "Is performance critical?",
                "Will the system run in an existing, heavily utilized operational environment?",
                "Does the system require online data entry?",
                "Does the online data entry require the input transaction to be built over multiple screens or operations?",
                "Are the internal logical files updated online?",
                "Are the inputs, outputs, files, or inquiries complex?",
                "Is the internal processing complex?",
                "Is the code designed to be reusable?",
                "Are conversion and installation included in the design?",
                "Is the system designed for multiple installations in different organizations?",
                "Is the application designed to facilitate change and for ease of use by the user?"
        };

        for (int i = 0; i < NUM_VAF_VALS; i++)
        {
            //System.out.println(i);
            //this.VAF_array[i].setDescriptionText(VAF_strings[i]);
            this.VAF_array[i] = new ValAdjustFactor(VAF_strings[i]);
        }
    }

    //Misc. Methods
    public void updateTotalCount() {
        totalCount =
                this.exInputs.getSumOfDomVal()      +
                this.exOutputs.getSumOfDomVal()     +
                this.exInquiries.getSumOfDomVal()   +
                this.inLogicFiles.getSumOfDomVal()  +
                this.exInterfaceFiles.getSumOfDomVal();
    }
}
