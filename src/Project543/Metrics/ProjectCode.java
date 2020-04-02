package Project543.Metrics;

import java.io.IOException;
import Project543.JavaJavaLexer;
import Project543.JavaJavaParser;
import Project543.Symbol;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.util.LinkedHashSet;
import java.util.Set;

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

    //from Hoffman
    public static Set<String> uniqueKeywords = new LinkedHashSet<String>();
    public static Set<String> uniqueIdentifiers = new LinkedHashSet<String>();

    public static Set<Symbol> uIDSym = new LinkedHashSet<Symbol>();

    public static Set<String> uniqueConstants = new LinkedHashSet<String>();
    public static Set<String> uniqueSpecial = new LinkedHashSet<String>();

    public static Set<String> mccabeValues = new LinkedHashSet<String>();

    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    public ProjectCode() {
        //Default constructor
        super();
        filePath = "/Users/melissahazlewood/Desktop/cecs543/543_Project/src/Project543/Metrics/FunctionPoint.java";
        System.out.println("inside projectCode constructor");
        try {
            this.parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
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
    public void parseFile () throws IOException, RecognitionException
    {
        JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(filePath));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        JavaJavaParser parser = new JavaJavaParser(tokens);

        parser.compilationUnit();

        System.out.println("\nParser keyword count: " + parser.keywordCount);
        System.out.println("Parser import keyword count: " + parser.importKWCount);
        System.out.println("Unique keywords set (size = " + this.uniqueKeywords.size() + "): " + this.uniqueKeywords);

        System.out.println("\nParser identifier count: " + parser.identcount);
        System.out.println("Parser import identifier count: " + parser.importIDCount);
        System.out.println("Unique identifiers set (size = " + this.uniqueIdentifiers.size() + "): " + this.uniqueIdentifiers);

//        System.out.println("Parser uIDSym?? count: not a thing??");
        System.out.println("\nuIDSym set (size = " + this.uIDSym.size() + "): " + this.uIDSym);

        System.out.println("\nUnique constants set (size = " + this.uniqueConstants.size() + "): " + this.uniqueConstants);
        System.out.println("Unique specials set (size = " + this.uniqueSpecial.size() + "): " + this.uniqueSpecial);

        System.out.println("\nParser cc?? count: " + parser.cc);
        System.out.println("Parser ec?? count: " + parser.ec);

        System.out.println("\nMccabe values set (size = " + this.mccabeValues.size() + "): " + this.mccabeValues);
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

//TODO: figure out what Symbol class is (should be? if I have to create it)
//TODO: do what he did for keywords for identifiers, IDSyms?, constants, specials??
//TODO: mccabe stuff
//TODO: calculations of indirect values
//TODO: make everything static I think
//TODO: make Symbol class a part of this one instead of separate?
//TODO: allow choice of file
