package Project543;

public class ProjectData extends ProjectMetaData {
    //Member Variables
    //public ProjectMetaData metaData;
    public FunctionPoint functionPointMetric;

    //Member Methods
    //Constructor(s)
    public ProjectData(){
        //this.metaData = new ProjectMetaData();
        this.functionPointMetric = new FunctionPoint();
    }

    //TODO: Implement public ProjectData(SAVEDFILE){}

    //Getters
    public String getFileName(){
        return "";
    }

    public String getProjectName(){
        return "";
    }

    //metadata
    //product name
    //creator name
    //project comments
    //project language

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

    @Override
    public String toString() {
        //return metaData + "\n" + functionPointMetric;
        return "";
    }
}
