package Project543.Metrics;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class ProjectCode extends Metrics {
    //**MEMBER FIELDS**//
    //
    //MEMBER ENUMS AND CLASSES
    //

    //STATIC MEMBER FIELDS
    //
    //Constant Static Fields
    //
    public static final int METRIC_ID = ((int) ('P' + 'C'));
    //Save data format: [METRIC_ID, "filePath"]

    //Non-Constant Static Fields
    //

    //NON-STATIC MEMBER FIELDS
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public String filePath;
    public String antlrOutput;

    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    public ProjectCode() {
        //Default constructor
        super();
    }

    public ProjectCode(String javaFile) {
        //TODO: constructor that takes in a java file
        this();
    }

    //GETTERS
    //

    //SETTERS
    //

    //MISC. MEMBER METHODS
    //
    public void parseFile()
    {

    }
    //readFile
    //do antlr stuff
    //string that returns what antlr pops out
    //hold file name/path/address
    //read out save data arraylist of project save data - metrid id, file path
    //metric id
    //extends metrics
    //in save data, use delimiter like quotes so we can use string tokenizer
    @Override
    public void setSaveData() {

    }

    @Override
    public boolean hasChanged() {
        return false;
    }
}
