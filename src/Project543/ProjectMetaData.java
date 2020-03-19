package Project543;

import java.util.Scanner;

public class ProjectMetaData {
    //Member Fields
    //
    //Member Enums and Classes
    //

    //Static Member Fields
    //
    //Constant Static Fields
    //
    public static final String FILE_EXT = ".ms"; //Requirement: CECS 543 Metrics Suite deals with .ms files

    //Non-Constant Static Fields
    //

    //Non-Static Member Fields
    //
    //Constant Member Fields
    //

    //Non-Constant Member Fields
    //
    protected String fileName, projectName, productName, creatorName, projectComments; //Project metadata
    protected Language defaultProjectLanguage; //The project's default language

    //Member Methods
    //
    //Constructor(s)
    //
    public ProjectMetaData(){
        //Default constructor

        //Initialize member fields
        this.projectName = "";
        this.productName = "";
        this.creatorName = "";
        this.projectComments = "";
        this.setFileName();

        this.defaultProjectLanguage = Language.NONE;
    }

    public ProjectMetaData(String[] metaData){
        //Metadata constructor, takes in a string of metadata and sets relevant fields

        //Initialize member fields
        this.projectName = metaData[0];
        this.productName = metaData[1];
        this.creatorName = metaData[2];
        this.projectComments = metaData[3];
        this.setFileName();

        this.defaultProjectLanguage = Language.NONE;
    }

    public ProjectMetaData(Scanner savedFile, String fileName){
        //Save file constructor
        this.setFileName(fileName);

        this.projectName = savedFile.nextLine();
        this.productName = savedFile.nextLine();
        this.creatorName = savedFile.nextLine();
        this.projectComments = savedFile.nextLine();
        this.defaultProjectLanguage = parseLanguage(savedFile.nextLine());
    }

    //Getters
    //
    //Getters for member variables
    //
    public String getFileName() {
        return this.fileName;
    }

    public String getProjectName(){
        return this.projectName;
    }

    public String getProductName(){
        return this.productName;
    }

    public String getCreatorName(){
        return this.creatorName;
    }

    public String getProjectComments(){
        return this.projectComments;
    }

    public Language getDefaultProjectLanguage(){
        return this.defaultProjectLanguage;
    }

    public int getLanguageLinesOfCode(){
        //Returns the lines of code per FP for the project's language
        if (defaultProjectLanguage != null) {
            return this.defaultProjectLanguage.linesOfCodePerFunctionPoint();
        }

        System.err.println("ERROR: LANGUAGE_NOT_SET");
        return 0;
    }

    //Setters
    //
    //Setters for the member variables
    //
    public void setFileName(){
        this.fileName = projectName + FILE_EXT;
    }

    public void setFileName(String fileName){
        if (fileName.indexOf(".ms") < 0){
            this.fileName = fileName + FILE_EXT;
        }
        else {
            this.fileName = fileName;
        }
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setProjectComments(String projectComments){
        this.projectComments = projectComments;
    }

    public void setDefaultProjectLanguage(Language language){
        this.defaultProjectLanguage = language;
    }

    //Misc. Member Methods
    @Override
    public String toString() {
        //To allow simple saving
        String metaData =
                projectName + "\n" +
                productName + "\n" +
                creatorName + "\n" +
                projectComments + "\n" +
                defaultProjectLanguage; //CALLER ALWAYS ADDS "\n"

        return metaData;
    }

    public static Language parseLanguage(String language){
        //Returns the language produced by the Language.toString() method
        //TODO: Rethink and refactor this approach
        switch (language){
            case "":
                return Language.NONE;
            case "Assembler":
                return Language.ASSEMBLER;
            case "Ada 95":
                return Language.ADA;
            case "C":
                return Language.C;
            case "C++":
                return Language.CPP;
            case "HTML":
                return Language.HTML;
            case "Java":
                return Language.JAVA;
            case "C#":
                return Language.CSHARP;
            case "FORTRAN":
                return Language.FORTRAN;
            case "VBScript":
                return Language.VBSCRIPT;
            case "JavaScript":
                return Language.JAVASCRIPT;
            case "VisualBasic":
                return Language.VISUALBASIC;
            default:
                System.err.println("ERROR: NO_PROGRAMMING_LANGUAGE_FOUND");
                System.err.println("Defaulting to Language.NONE");
                break;
        }
        return Language.NONE;
    }
}
