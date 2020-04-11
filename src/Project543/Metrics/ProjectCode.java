package Project543.Metrics;

import java.io.IOException;
import Project543.JavaJavaLexer;
import Project543.JavaJavaParser;
import Project543.Symbol;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.util.*;
import java.lang.Math;

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
    //Save data format: [METRIC_ID] "filePath"

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
        filePath = "src/Project543/Metrics/FunctionPoint.java";

        lexer = new JavaJavaLexer(new ANTLRFileStream(filePath));
        tokens = new CommonTokenStream(lexer);
        parser = new JavaJavaParser(tokens);

        this.parseFile();
        System.out.print(outputString());
    }

    public ProjectCode(String javaFile) throws IOException, RecognitionException {
        //Constructor that takes in a java file for parsing
        this();
        filePath = javaFile;
    }

    //TODO: constructor with saveData as input?
    public ProjectCode(String something, int somethingElse) throws IOException, RecognitionException {

    }

    //GETTERS
    //
    public int getUniqueOperators() {
        return uniqueKeywords.size() + uniqueSpecial.size();
    }

    public int getUniqueOperands() {
        return uniqueIdentifiers.size() + uIDSym.size() + uniqueConstants.size();
    }

    public int getTotalOperators() {
        return parser.keywordCount + parser.specialcount;
    }

    public int getTotalOperands() {
        return parser.identcount + uIDSym.size() + lexer.constantcount;
    }

    public int getProgramLength() {
        return getTotalOperators() + getTotalOperands();
    }

    public int getProgramVocabulary() {
        return getUniqueOperators() + getUniqueOperands();
    }

    public double getVolume() {
        return getProgramLength() * Math.log(getProgramVocabulary());
    }

    public double getDifficulty() {
        return getUniqueOperators()/2.0 + ((double) getTotalOperands())/getUniqueOperands();
    }

    public double getEffort() {
        return  getDifficulty() * getVolume();
    }

    public double getTime() {
        return getEffort()/18;
    }

    public double getNumberOfBugs() {
        return getVolume()/3000;
    }

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
                "\n\tUnique operators: " + getUniqueOperators() +
                "\n\tUnique operands: " + getUniqueOperands() +
                "\n\tTotal operators: " + getTotalOperators() +
                "\n\tTotal operands: " + getTotalOperands() +
                "\n\tProgram length (N) = " + getProgramLength() +
                "\n\tProgram vocabulary (n) = " + getProgramVocabulary() +
                "\n\tVolume = " + getVolume() +
                "\n\tDifficulty = " + getDifficulty() +
                "\n\tEffort = " + getEffort() +
                "\tTime = " + getTime() +
                "\n\tBugs expected = " + getNumberOfBugs();
    }

    public String outputMccabeData() { 
        //TODO
        return "\n\nMcCabe's Cyclomatic Complexity:\n" +
                mccabeValues.toString();
    }

    public String outputString() {
        return outputMetaData() +
        outputHalsteadData() +
        outputMccabeData();
    }


    @Override
    public void setSaveData() {
        //Saves METRIC_ID and file path
        //Initialize new saveData
        ArrayList<Integer> tempSaveData = new ArrayList<Integer>();
        tempSaveData.add(ProjectCode.METRIC_ID);

        this.saveData = tempSaveData;
    }

    public void setFromSavedData() {
        //Sets variable values based on integer(s) and file path string in saveData
    }

    @Override
    public boolean hasChanged() {
        return false;
    }
}

//TODO: do what he did for keywords for identifiers, IDSyms?, constants, specials??
//TODO: mccabe stuff
//TODO: make everything static I think
//TODO: make Symbol class a part of this one instead of separate?
//TODO: allow choice of file

//readFile
//hold file name/path/address
//read out save data arraylist of project save data - metrid id, file path
//in save data, use delimiter like quotes so we can use string tokenizer