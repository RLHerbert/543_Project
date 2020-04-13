package Project543.Metrics;

import java.io.File;
import java.io.IOException;
import Project543.JavaJavaLexer;
import Project543.JavaJavaParser;
import Project543.Symbol;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.text.DecimalFormat;
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
    private static final DecimalFormat one_dec = new DecimalFormat("#.#");
    private static final DecimalFormat sci_not = new DecimalFormat("0.########E0");
    private static final DecimalFormat three_dec_commas = new DecimalFormat("#,###.###");

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
    public String filePath; //TODO: reassess... needed?
    public File file;

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
        super();
    }

    public ProjectCode(File javaFile) throws IOException, RecognitionException {
        //Constructor that takes in a java file for parsing
        this();
        filePath = javaFile.getAbsolutePath();
        file = javaFile;

        lexer = new JavaJavaLexer(new ANTLRFileStream(filePath));
        tokens = new CommonTokenStream(lexer);
        parser = new JavaJavaParser(tokens);

        clearStaticData();
        this.parseFile();
        System.out.print(outputString());
    }

    public ProjectCode(String saveData) throws IOException, RecognitionException {
        //Constructor that takes in saveData and sets variables accordingly
        super(saveData);
        filePath = this.extraData;
        file = new File(filePath);

        lexer = new JavaJavaLexer(new ANTLRFileStream(filePath));
        tokens = new CommonTokenStream(lexer);
        parser = new JavaJavaParser(tokens);

        clearStaticData();
        this.parseFile();
        System.out.print(outputString());
    }

    //GETTERS
    //
    public double getFileLengthInBytes() {
        return file.length();
    }

    public int getWhiteSpace() {
        return lexer.ws;
    }

    public int getCommentSpaceInBytes() {
        return lexer.commentcount; //TODO: fix value
    }

    public double getCommentPercentage() {
        return getCommentSpaceInBytes()/getFileLengthInBytes()*100; //TODO: once comment space in bytes is fixed, double-check this value
    }

    public int getUniqueOperators() {
        return uniqueKeywords.size() + uniqueSpecial.size();
    }

    public int getUniqueOperands() {
        return uniqueIdentifiers.size() + uniqueConstants.size();
    }

    public int getTotalOperators() {
        return parser.keywordCount + parser.specialcount;
    }

    public int getTotalOperands() {
        return parser.identcount + lexer.constantcount;
    }

    public int getProgramLength() {
        return getTotalOperators() + getTotalOperands();
    }

    public int getProgramVocabulary() {
        return getUniqueOperators() + getUniqueOperands();
    }

    public double getVolume() {
//        System.out.println("GET VOLUME PROGRAM LEN: " + getProgramLength());
//        System.out.println("GET VOLUME PROGRAM VOCAB: " + getProgramVocabulary());
//        System.out.println("GET VOLUME PROGRAM LOG(VOCAB): " + Math.log(getProgramVocabulary())/Math.log(2));
        return getProgramLength() * Math.log(getProgramVocabulary())/Math.log(2); //TODO: wrong value.. figure out why and fix
    }

    public double getDifficulty() {
        return getUniqueOperators()/2.0 * ((double) getTotalOperands())/getUniqueOperands(); //TODO: wrong value.. figure out why and fix
    }

    public double getEffort() {
        return getDifficulty() * getVolume();
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
    }

    public String outputMetaData() {
        //TODO

        return "File name: " + file.getName() +
                "\nFile length in bytes: " + ((int) getFileLengthInBytes()) +
                "\nFile white space: " + getWhiteSpace() +
                "\nFile comment space in bytes: " + getCommentSpaceInBytes() +
                "\nComment percentage of file: " + three_dec_commas.format(getCommentPercentage()) + "%";
    }

    public String outputHalsteadData() {
        return "\nHalstead metrics:" +
                "\n\tUnique operators: " + getUniqueOperators() +
                "\n\tUnique operands: " + getUniqueOperands() +
                "\n\tTotal operators: " + getTotalOperators() +
                "\n\tTotal operands: " + getTotalOperands() +
                "\n\tProgram length (N) = " + getProgramLength() +
                "\n\tProgram vocabulary (n) = " + getProgramVocabulary() +
                "\n\tVolume = " + one_dec.format(getVolume()) +
                "\n\tDifficulty = " + one_dec.format(getDifficulty()) +
                "\n\tEffort = " + sci_not.format(getEffort()) +
                "\tTime = " + minutesHoursMonthsString(getTime()) +
                "\n\tBugs expected = " + three_dec_commas.format(getNumberOfBugs());
    }

    public String minutesHoursMonthsString(double timeInSec) {
        double timeInMin = timeInSec / 60;
        double timeInHours = timeInMin / 60;
        double timeInMonths = timeInHours / 8 / 5 / 4;
        double timeInHoffmanMonths = timeInHours / 7 / 5 / 4;
        return three_dec_commas.format(timeInSec) + " seconds (" + three_dec_commas.format(timeInMin) + " minutes or " +
                three_dec_commas.format(timeInHours) + " hours or, \nassuming 40-hour work weeks and 4-week months, " +
                three_dec_commas.format(timeInMonths) + " person months or, excluding lunch breaks, " +
                three_dec_commas.format(timeInHoffmanMonths) + " person months";
    }

    public String outputMccabeData() {
        String mccabeString = "";

        String[] mccabeArray = mccabeValues.toArray(new String[0]);
        for (String mccabeValue : mccabeArray)
            mccabeString += "\t" +mccabeValue + "\n";

        return "\n\nMcCabe's Cyclomatic Complexity:\n" +
                mccabeString;
    }

    public String outputString() {
        return outputMetaData() +
        outputHalsteadData() +
        outputMccabeData();
    }

    public void clearStaticData() {
        uniqueKeywords.clear();
        uniqueSpecial.clear();
        uniqueIdentifiers.clear();
        uIDSym.clear();
        uniqueConstants.clear();
        mccabeValues.clear();
    }


    @Override
    public void setSaveData() {
        //Saves METRIC_ID and file path
        //Initialize new saveData
        ArrayList<Integer> tempSaveData = new ArrayList<>();
        tempSaveData.add(ProjectCode.METRIC_ID);

        this.saveData = tempSaveData;
        this.extraData = file.getAbsolutePath();
    }

    @Override
    public boolean hasChanged() {
        return false;
    }
}

//TODO: make everything static I think
//TODO: make Symbol class a part of this one instead of separate?
