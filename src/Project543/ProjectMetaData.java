package Project543;

import java.util.Scanner;

public class ProjectMetaData {
    //Member Variables
    //
    public static final String FILE_EXT = ".ms"; //Requirement: CECS 543 Metrics Suite deals with .ms files
    protected String fileName, projectName, productName, creatorName, projectComments; //Project metadata
    protected Language projectLanguage; //The project language

    //Member Methods
    //Constructor(s)
    public ProjectMetaData(){
        //Default constructor
        this.projectName = "";
        this.productName = "";
        this.creatorName = "";
        this.projectComments = "";
        this.setFileName();

        this.projectLanguage = Language.NONE;
    }

    //TODO: implement: public ProjectMetaData(SAVEDFILE){}
    public ProjectMetaData(Scanner savedFile, String fileName){
        //Save file constructor
        this.setFileName(fileName);

        String fileData = savedFile.nextLine();
        this.projectName = fileData.substring(fileData.indexOf(":")+2);

        fileData = savedFile.nextLine();
        this.productName = fileData.substring(fileData.indexOf(":")+2);

        fileData = savedFile.nextLine();
        this.creatorName = fileData.substring(fileData.indexOf(":")+2);

        fileData = savedFile.nextLine();
        this.projectComments = fileData.substring(fileData.indexOf(":")+2);

        //savedFile.nextLine(); //TEMPORARY UNTIL LANGUAGE IMPLEMENTED
        fileData = savedFile.nextLine();
        this.projectLanguage = parseLanguage(fileData.substring(fileData.indexOf(":")+2));
    }

    //Getters
    //
    //Getters for member variables
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

    public Language getProjectLanguage(){
        return this.projectLanguage;
    }

    public int getLanguageLOC(){
        //Returns the lines of code per FP for the project's language
        if (projectLanguage != null) {
            return this.projectLanguage.locPerFP();
        }

        System.err.println("ERROR: LANGUAGE_NOT_SET");
        return 0;
    }

    //Setters
    //
    //Setters for the member variables
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

    public void setProjectLanguage(Language language){
        this.projectLanguage = language;
    }

    //Misc. Member Methods
    //TODO: Fix
    @Override
    public String toString() {
        //To allow simple saving
        String outString =
                        "PROJECT_NAME: " + projectName + "\n"        +
                        "PRODUCT_NAME: " + projectName + "\n"        +
                        "CREATOR_NAME: " + projectName + "\n"        +
                        "PROJECT_COMMENTS: " + projectName + "\n"    +
                        "PROJECT_LANGUAGE: " + projectName; //CALLER ALWAYS ADDS "\n"
        return outString;
    }

    public static Language parseLanguage(String language){
        //Returns the language produced by the Language.toString() method
        //TODO: Rethink and refactor this approach
        switch (language){
            case "None":
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
