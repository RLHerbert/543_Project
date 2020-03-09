package Project543;

public class ProjectData {
    //Member Variables
    public ProjectMetaData metaData;
    public FunctionPoint functionPointMetric;

    //Member Methods
    //Constructor(s)
    public ProjectData(){
        this.metaData = new ProjectMetaData();
        this.functionPointMetric = new FunctionPoint();
    }

    //TODO: Implement public ProjectData(SAVEDFILE){}

    //Getters

    //Setters

    //Misc. Member Methods

    @Override
    public String toString() {
        return metaData + "\n" + functionPointMetric;
    }
}
