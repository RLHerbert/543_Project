package Project543;

import java.util.Scanner;

public class ProjectData extends ProjectMetaData {
    //Member Variables
    public FunctionPoint functionPointMetric;

    //Member Methods
    //Constructor(s)
    public ProjectData(){
        //Default constructor

        //Call FunctionPoint default constructor
        this.functionPointMetric = new FunctionPoint();
    }

    //TODO: Implement public ProjectData(SAVEDFILE){}
    public ProjectData(Scanner savedFile, String fileName){
        //Saved file constructor
        super(savedFile, fileName);
        this.functionPointMetric = new FunctionPoint(savedFile);
    }

    //Getters
    public int getCodeSize(){
        //Returns the lines of code (LOC) for the project based on all entered data and the selected language
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
    //

    //Misc. Member Methods
    //
    @Override
    public String toString() {
        String outString = super.toString() + "\n" + functionPointMetric;
        return outString;
    }
}
