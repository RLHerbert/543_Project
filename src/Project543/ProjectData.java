package Project543;

import java.util.Scanner;

public class ProjectData extends ProjectMetaData {
    //Member Variables
    public FunctionPoint functionPointMetric;

    //Member Methods
    //Constructor(s)
    public ProjectData(){
        this.functionPointMetric = new FunctionPoint();
    }

    //TODO: Implement public ProjectData(SAVEDFILE){}
    public ProjectData(Scanner savedFile, String fileName){
        super(savedFile, fileName);
        this.functionPointMetric = new FunctionPoint(savedFile);
    }

    //Getters
    public int getCodeSize(){
        return (functionPointMetric.getFunctionPoints() * this.getLanguageLOC());
    }

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
