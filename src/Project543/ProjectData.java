package Project543;

public class ProjectData extends ProjectMetaData {
    //Member Variables
    public FunctionPoint functionPointMetric;

    //Member Methods
    //Constructor(s)
    public ProjectData(){
        this.functionPointMetric = new FunctionPoint();
    }

    //TODO: Implement public ProjectData(SAVEDFILE){}

    //Getters
    //function point
    //totalCount
    //totalFunctionPoints
    //exInputs
    //exOutputs
    //exInquiries
    //inLogicFiles
    //exInterfaceFiles

    //Setters

    //Misc. Member Methods
    //TODO: Fix
    @Override
    public String toString() {
        String outString = super.toString() + "\n" + functionPointMetric;
        return outString;
    }
}
