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
import java.util.StringTokenizer;

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
    //From Hoffman
    //(Halstead) Operators
    public static Set<String> uniqueKeywords = new LinkedHashSet<String>();
    public static Set<String> uniqueSpecial = new LinkedHashSet<String>();

    //(Halstead) Operands
    public static Set<String> uniqueIdentifiers = new LinkedHashSet<String>();
    public static Set<Symbol> uIDSym = new LinkedHashSet<Symbol>();
    public static Set<String> uniqueConstants = new LinkedHashSet<String>();

    //McCabe's Cyclomatic Complexities
    public static Set<String> mccabeValues = new LinkedHashSet<String>();

    //NON-STATIC MEMBER FIELDS
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    public String filePath;

    JavaJavaLexer lexer;
    CommonTokenStream tokens;
    JavaJavaParser parser;
    int operators;
    int operands;


    //**MEMBER METHODS**//
    //
    //CONSTRUCTOR(S)
    //
    public ProjectCode() throws IOException, RecognitionException {
        //Default constructor
        //TODO: how should default work? should there even be a default? give an error if filepath not defined?
        super();
        filePath = "/Users/melissahazlewood/Desktop/cecs543/543_Project/src/Project543/Metrics/FunctionPoint.java";

        JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(filePath));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaJavaParser parser = new JavaJavaParser(tokens);

        this.parseFile();
//        System.out.print(outputString());
    }

    public ProjectCode(String javaFile) throws IOException, RecognitionException {
        //Constructor that takes in a java file for parsing
        this();
        filePath = javaFile;
    }

    //TODO: constructor with saveData as input?

    //GETTERS
    //

    //SETTERS
    //

    //MISC. MEMBER METHODS
    //
    public void parseFile () throws RecognitionException {
        parser.compilationUnit();

        System.out.println("\nParser keyword count: " + parser.keywordCount);
        System.out.println("Parser import keyword count: " + parser.importKWCount);
        System.out.println("Unique keywords set (size = " + this.uniqueKeywords.size() + "): " + this.uniqueKeywords);

        System.out.println("\nParser identifier count: " + parser.identcount);
        System.out.println("Parser import identifier count: " + parser.importIDCount);
        System.out.println("Unique identifiers set (size = " + this.uniqueIdentifiers.size() + "): " + this.uniqueIdentifiers);

        System.out.println("\nuIDSym set (size = " + this.uIDSym.size() + "): " + this.uIDSym);

        System.out.println("\nLexer constant count: " + lexer.constantcount);
        System.out.println("Unique constants set (size = " + this.uniqueConstants.size() + "): " + this.uniqueConstants);
        System.out.println("Parser special count: " + parser.specialcount);
        System.out.println("Unique specials set (size = " + this.uniqueSpecial.size() + "): " + this.uniqueSpecial);

        System.out.println("\nMccabe values set (size = " + this.mccabeValues.size() + "): " + this.mccabeValues);
    }

    public String outputMetaData() {
        //TODO
        String filename = filePath.substring(filePath.lastIndexOf('/') + 1);
        String file = "";
        byte[] fileBytes = file.getBytes();

        return "File name: " + filename +
                "\nFile length in bytes: " + "not sure yet" + //TODO
                "\nFile white space: " + lexer.ws + //TODO: double-check
                "\nFile comment space in bytes: " + lexer.commentcount + //TODO: double check
                "\nComment percentage of file: " + "not sure yet"; //TODO
    }

    public String outputHalsteadData() {
        //TODO
        return "\nHalstead metrics:" +
                "\n\tUnique operators: " +
                "\n\tUnique operands: " +
                "\n\tTotal operators: " +
                "\n\tTotal operands: " +
                "\n\tProgram length (N) = " +
                "\n\tProgram vocabulary (n) = " +
                "\n\tVolume = " +
                "\n\tDifficulty = " +
                "\n\tEffort = " +
                "\tTime = " +
                "\n\tBugs expected = ";
    }

    public String outputMccabeData() { 
        //TODO
        return "\n\nMcCabe's Cyclomatic Complexity:" +
                mccabeValues.toString();
    }

    public String outputString() {
        return outputMetaData() +
        outputHalsteadData() +
        outputMccabeData();
    }


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

//readFile
//do antlr stuff
//string that returns what antlr pops out
//hold file name/path/address
//read out save data arraylist of project save data - metrid id, file path
//metric id
//extends metrics
//in save data, use delimiter like quotes so we can use string tokenizer